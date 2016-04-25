package com.gm.retrofit.webservice.interfaces;


public interface DataSelectionCallback {

    void dataSelection(int whichView, int selectedPos);

    void alertPositiveBtn(int whichView, int statusCode);

    void alertNegativeBtn(int whichView, int statusCode);

    void onReConnect();

    void onResetWebservice(Boolean isSuccess);

}
