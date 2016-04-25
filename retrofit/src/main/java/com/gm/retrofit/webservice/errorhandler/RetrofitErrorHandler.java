package com.gm.retrofit.webservice.errorhandler;


import android.util.Log;

import com.gm.retrofit.R;
import com.gm.retrofit.utils.OfflineDialog;
import com.gm.retrofit.webservice.RetrofitCallbackPOJO;
import com.gm.retrofit.webservice.api.RestApi;
import com.gm.retrofit.webservice.interfaces.DataSelectionCallback;

import retrofit.Call;


/**
 * Created by gowtham on 10/1/16.
 */
public class RetrofitErrorHandler {
    private static final String TAG = "RetrofitErrorHandler";
    public RetrofitCallbackPOJO retrofitCallbackPOJO;
    private DataSelectionCallback dataSelectionCallback;
    private Boolean isLogin = false;

    public <T> RetrofitErrorHandler(RetrofitException e, RetrofitCallbackPOJO CallbackPOJO, final Call<T> tCall) {

        retrofitCallbackPOJO = CallbackPOJO;
        dataSelectionCallback = retrofitCallbackPOJO.dataSelectionCallback;
        try {
            Log.e(TAG, "ERROR URL " + e.getUrl() + " " + e.getKind().name() + " " + e.getResponse().code());
            isLogin = e.getUrl().equals(RestApi.BASE_URL + "auth/Login");
        } catch (Exception ignored) {
            Log.e("Exception in Error Handler", ignored.getMessage());

        }


        try {
            if (e.getKind().name().equals("NETWORK")) {
                tCall.cancel();
                OfflineDialog dialog = new OfflineDialog(retrofitCallbackPOJO.activity, retrofitCallbackPOJO.dataSelectionCallback);
                dialog.showOfflineDialog(retrofitCallbackPOJO.activity.getString(R.string.network_error), "Refresh");
                //dialog.showOfflineAlert(retrofitCallbackPOJO.activity.getString(R.string.network_error));

            } else if (e.getResponse().code() == 404) {
                Log.e(TAG, "" + e.getResponse().code());
                //CommonModel model = e.getErrorBodyAs(CommonModel.class);
                //  Toast.makeText(retrofitCallbackPOJO.activity, model.getError(), Toast.LENGTH_SHORT).show();
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


}
