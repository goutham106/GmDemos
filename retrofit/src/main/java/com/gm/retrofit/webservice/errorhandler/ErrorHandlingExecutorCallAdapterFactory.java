package com.gm.retrofit.webservice.errorhandler;


import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.gm.retrofit.webservice.RetrofitCallbackPOJO;
import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.concurrent.Executor;

import retrofit.Call;
import retrofit.CallAdapter;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by gowtham on 29/12/15.
 */
public class ErrorHandlingExecutorCallAdapterFactory implements CallAdapter.Factory {
    private final Executor callbackExecutor;
    private final RetrofitCallbackPOJO callbackstar;

    public ErrorHandlingExecutorCallAdapterFactory(Executor callbackExecutor, RetrofitCallbackPOJO callbackstar) {
        this.callbackExecutor = callbackExecutor;
        this.callbackstar = callbackstar;
    }

    @Override
    public CallAdapter<Call<?>> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if ($Gson$Types.getRawType(returnType) != Call.class) {
            return null;
        }
        final Type responseType = getCallResponseType(returnType);
        return new CallAdapter<Call<?>>() {
            @Override
            public Type responseType() {
                return responseType;
            }

            @Override
            public <R> Call<R> adapt(Call<R> call) {
                return new ExecutorCallbackCall<>(callbackExecutor, call, callbackstar);
            }
        };
    }

    static final class ExecutorCallbackCall<T> implements Call<T> {
        private final Executor callbackExecutor;
        private final RetrofitCallbackPOJO callbackstar;
        private final Call<T> delegate;

        ExecutorCallbackCall(Executor callbackExecutor, Call<T> delegate, RetrofitCallbackPOJO callbackPOJO) {
            this.callbackExecutor = callbackExecutor;
            this.delegate = delegate;
            this.callbackstar = callbackPOJO;
        }

        @Override
        public void enqueue(Callback<T> callback) {
            delegate.enqueue(new ExecutorCallback<>(callbackExecutor, callback, callbackstar, delegate));
        }

        @Override
        public Response<T> execute() throws IOException {
            return delegate.execute();
        }

        @Override
        public void cancel() {
            delegate.cancel();
        }

        @SuppressWarnings("CloneDoesntCallSuperClone") // Performing deep clone.
        @Override
        public Call<T> clone() {
            return new ExecutorCallbackCall<>(callbackExecutor, delegate.clone(), callbackstar);
        }
    }

    static final class ExecutorCallback<T> implements Callback<T> {
        private final Executor callbackExecutor;
        private final RetrofitCallbackPOJO callbackstar;
        private final Callback<T> delegate;
        private final Call<T> callbackcall;

        ExecutorCallback(Executor callbackExecutor, Callback<T> delegate, RetrofitCallbackPOJO callbackPOJO, Call<T> aCall) {
            this.callbackExecutor = callbackExecutor;
            this.callbackstar = callbackPOJO;
            this.delegate = delegate;
            this.callbackcall = aCall;
        }

        @Override
        public void onResponse(final Response<T> response, final Retrofit retrofit) {

            try {
                // isSuccess = [200..300)
                if (response.isSuccess()) {
                    callbackExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            delegate.onResponse(response, retrofit);

                        }
                    });
                } else {
                    callbackExecutor.execute(new Runnable() {
                        @Override
                        public void run() {

                            delegate.onFailure(RetrofitException.httpError(response.raw().request().urlString(), response, retrofit));
                            new RetrofitErrorHandler(RetrofitException.httpError(response.raw().request().urlString(), response, retrofit), callbackstar, callbackcall);
                        }
                    });
                }
            } catch (Exception e) {
e.printStackTrace();
            }
        }

        @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
        @Override
        public void onFailure(final Throwable t) {
            RetrofitException exception;
            if (t instanceof IOException) {
                exception = RetrofitException.networkError((IOException) t);
            } else {
                exception = RetrofitException.unexpectedError(t);

            }
            final RetrofitException finalException = exception;
            callbackExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    delegate.onFailure(finalException);
                    new RetrofitErrorHandler(finalException, callbackstar, callbackcall);
                }
            });
        }
    }

    public static class MainThreadExecutor implements Executor {
        private final Handler handler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable r) {
            handler.post(r);
        }
    }

    static Type getCallResponseType(Type returnType) {
        if (!(returnType instanceof ParameterizedType)) {
            throw new IllegalArgumentException(
                    "Call return type must be parameterized as Call<Foo> or Call<? extends Foo>");
        }
        final Type responseType = getSingleParameterUpperBound((ParameterizedType) returnType);

        // Ensure the Call response type is not Response, we automatically deliver the Response object.
        if ($Gson$Types.getRawType(responseType) == Response.class) {
            throw new IllegalArgumentException(
                    "Call<T> cannot use Response as its generic parameter. "
                            + "Specify the response body type only (e.g., Call<TweetResponse>).");
        }
        return responseType;
    }

    public static Type getSingleParameterUpperBound(ParameterizedType type) {
        Type[] types = type.getActualTypeArguments();
        if (types.length != 1) {
            throw new IllegalArgumentException(
                    "Expected one type argument but got: " + Arrays.toString(types));
        }
        Type paramType = types[0];
        if (paramType instanceof WildcardType) {
            return ((WildcardType) paramType).getUpperBounds()[0];
        }
        return paramType;
    }
}