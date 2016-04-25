package com.gm.retrofit.webservice;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.gm.retrofit.app.RetrofitApp;
import com.gm.retrofit.webservice.api.RestApi;
import com.gm.retrofit.webservice.errorhandler.ErrorHandlingExecutorCallAdapterFactory;
import com.gm.retrofit.webservice.interfaces.DataSelectionCallback;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;


/**
 * Created by gowtham on 21/04/16.
 */
public class RestClient {

    private static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB
    public static final String TAG = RestClient.class.getSimpleName();

    private static Retrofit.Builder sRetrofitBuilder =
            new Retrofit.Builder();
    @SuppressWarnings("FieldCanBeLocal")
    private static Context context;
    private static RetrofitCallbackPOJO retrofitCallbackPOJO;

    private RestApi restApi;

    private static Boolean uploadProgress = false;


    public RestClient() {
    }

    public RestClient(Activity act, DataSelectionCallback dsCallback) {

        retrofitCallbackPOJO = new RetrofitCallbackPOJO();
        retrofitCallbackPOJO.activity = act;
        retrofitCallbackPOJO.dataSelectionCallback = dsCallback;
        this.context = act;
    }

    public RestApi getApi() {
        return createService(RestApi.class, RestApi.BASE_URL, null, null, null);
    }

    public <T> T getApi(Class<T> serviceClass) {
        //return createService(serviceClass, RestApi.BASE_URL);
        return createService(serviceClass, RestApi.BASE_URL, null, null, null);
    }

    public RestApi getApi(String token) {
        return createService(RestApi.class, RestApi.BASE_URL, token, null, null);
    }

    public RestApi getApi(String token, Boolean isprogress) {
        uploadProgress = isprogress;
        return createService(RestApi.class, RestApi.BASE_URL, token, null, null);
    }

    public RestApi getApi(String username, String password) {
        return createService(RestApi.class, RestApi.BASE_URL, null, username, password);

    }


    public static <S> S createService(Class<S> serviceClass, String baseUrl, final String AccessToken, final String userName, final String password) {

        OkHttpClient okHttpClient = getClient();
        okHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(10, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(10, TimeUnit.SECONDS);

        okHttpClient.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                if (chain != null) {
                    Request originalRequest = chain.request();

                    if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
                        // We Have to concatenate username and password with colon for authentication
                        final String credentials = userName + ":" + password;

                        String authorization = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

                        Request modifiedRequest = originalRequest.newBuilder()
                                .header("Authorization", authorization)
                                .header("Accept", "application/json")
                                .build();

                        return chain.proceed(modifiedRequest);
                    } else if (!TextUtils.isEmpty(AccessToken)) {

                        Request modifiedRequest = originalRequest.newBuilder()
                                .header("x-access-token", AccessToken)
                                .build();

                        return chain.proceed(modifiedRequest);
                    } else {
                        return chain.proceed(originalRequest);
                    }
                }

                return null;
            }
        });

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        if (uploadProgress) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            uploadProgress = false;
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        okHttpClient.interceptors().add(httpLoggingInterceptor); // Add only for debugging purposes

        sRetrofitBuilder.client(okHttpClient);
        sRetrofitBuilder.baseUrl(baseUrl);
        sRetrofitBuilder.addConverterFactory(JacksonConverterFactory.create());
        sRetrofitBuilder.addCallAdapterFactory(new ErrorHandlingExecutorCallAdapterFactory(new ErrorHandlingExecutorCallAdapterFactory.MainThreadExecutor(), retrofitCallbackPOJO));
        Retrofit retrofit = sRetrofitBuilder.build();
        return retrofit.create(serviceClass);
    }


    private static OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient();

        // Install an HTTP cache in the application cache directory.
        try {
            File cacheDir = new File(RetrofitApp.getCacheDirectory(), "http");
            Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
            client.setCache(cache);
        } catch (Exception e) {
            Log.e(TAG, "Unable to install disk cache.");
        }
        client.setSslSocketFactory(createBadSslSocketFactory());

        return client;
    }

    private static SSLSocketFactory createBadSslSocketFactory() {
        try {

            // Default SSLSocketConnection
//            // Construct SSLSocketFactory that accepts any cert.
            SSLContext context = SSLContext.getInstance("TLS");
            TrustManager permissive = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @SuppressLint("TrustAllX509TrustManager")
                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            context.init(null, new TrustManager[]{permissive}, null);
            return context.getSocketFactory();


//TODO: If we have specified Certificate
            // loading CAs from an InputStream
//            CertificateFactory cf = CertificateFactory.getInstance("X.509");
//            InputStream cert = context.getResources().openRawResource(R.raw.certificate);
//            Certificate ca;
//            try {
//                ca = cf.generateCertificate(cert);
//            } finally {
//                cert.close();
//            }
//
//            // creating a KeyStore containing our trusted CAs
//            String keyStoreType = KeyStore.getDefaultType();
//            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
//            keyStore.load(null, null);
//            keyStore.setCertificateEntry("ca", ca);
//
//            // creating a TrustManager that trusts the CAs in our KeyStore
//            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
//            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
//            tmf.init(keyStore);
//
//            // creating an SSLSocketFactory that uses our TrustManager
//            SSLContext sslContext = SSLContext.getInstance("TLS");
//            sslContext.init(null, tmf.getTrustManagers(), null);
//
//            //Adding Interceptors and ssl
//            return sslContext.getSocketFactory();

        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }
}
