package com.gm.retrofit.webservice.api;


/**
 * Created by gowtham on 20/12/15.
 */
public interface RestApi {

    String BASE_URL = "";


    String CONTENT_TYPE_JSON_HEADER = "Content-Type: application/json";
    String ACCEPT_JSON_HEADER = "Accept: application/json";
    String CONTENT_TYPE_MULTIPART_HEADER = "Content-Type: multipart/form-data";

//    @POST("users/register")
//        //@POST("users/registerconverter")
//    Call<CommonModel> registerUser(@Body RequestBody requestBody);
//
//    @POST("users/register/check/email")
//    Call<CommonModel> verifyEmailId(@Body RequestBody requestBody);
//
//    @POST("users/forgot")
//    Call<CommonModel> forgotPassword(@Body RequestBody requestBody);
//
//
//    @Multipart
//    @POST("users/avatar/{uid}")
//    Call<CommonModel> uploadAvathar(@Path("uid") String uid,
//                                    @Part("avatar\"; filename=\"avatar\" ") RequestBody file);
//
//    @GET("auth/Login")
//    Call<LoginModel> login();
//
//    @GET("auth/facebook/token")
//    Call<LoginModel> fbAuth(@Query("access_token") String accesstoken);
//
//    @GET("api/getstars/{userId}")
//    Call<AddCelebrityModel> getStars(@Path("userId") String userId,
//                                     @Query("limit") int limitValue,
//                                     @Query("offset") int offsetValue);
//
//    @POST("api/stars/follow")
//    Call<CommonModel> setMyStars(@Body RequestBody requestBody);
//
//    @POST("api/stars/unfollow")
//    Call<CommonModel> unFollow(@Body RequestBody requestBody);
//
//    @GET("api/search/{keyword}")
//    Call<AddCelebrityModel> searchStar(@Path("keyword") String keyword);
//
//    @GET("api/getstars/following/id/{userid}")
//    Call<SearchCheckModel> searchCheck(@Path("userid") String userid);
//
//    @GET("api/getstars/following/{userId}")
//    Call<LandingModel> getMyStars(@Path("userId") String userId,
//                                  @Query("limit") int limitValue,
//                                  @Query("offset") int offsetValue);
//
//    @GET("api/newnotifications/{uid}")
//    Call<NotificationModel> getNotification(@Path("uid") String userId);
//
//    @GET("api/timeline/{userId}")
//    Call<TimelineBaseModel> getTimeLine(@Path("userId") String userId,
//                                        @Query("limit") int limitValue,
//                                        @Query("offset") int offsetValue);
//
//    @GET("api/star/{sid}")
//    Call<StarLandingModel> getStarLanding(@Path("sid") String sid);
//
//    @GET("api/webcasts/upcoming/{sid}")
//    Call<UpcomingModel> getUpcomingWebcast(@Path("sid") String sid,
//                                           @Query("limit") int limitValue,
//                                           @Query("offset") int offsetValue);
//
//    @GET("api/webcasts/previous/{sid}")
//    Call<PreviousModel> getPreviousWebcast(@Path("sid") String sid,
//                                           @Query("limit") int limitValue,
//                                           @Query("offset") int offsetValue);
//
//    @GET("api/webcasts/live/{sid}")
//    Call<TimelinePreviousModel> getLiveWebcast(@Path("sid") String sid);
//
//    @GET("api/galleries/{sid}")
//    Call<GalleryModel> getGalleryWebcast(@Path("sid") String sid,
//                                         @Query("limit") int limitValue,
//                                         @Query("offset") int offsetValue);
//
//    @GET("api/comments/get/{wid}")
//    Call<CommentsModel> getComments(@Path("wid") String wid,
//                                    @Query("limit") int limitValue,
//                                    @Query("offset") int offsetValue);
//
//
//    @POST("api/comments/add/{wid}")
//    Call<CommonModel> setComments(@Path("wid") String wid, @Body RequestBody requestBody);
//
//    @POST("api/comments/report/{wid}")
//    Call<CommonModel> reportAbuse(@Path("wid") String wid, @Body RequestBody requestBody);
//
//    @GET("api/webcasts/{wid}")
//    Call<TimelinePreviousModel> getLiveContent(@Path("wid") String wid);
//
//
//    // *******************8   Star API's   **************************
//
//    @POST("api/webcasts/insert")
//    Call<ScheduledNewWebcastResponseModel> scheduledWebcast(@Body RequestBody requestBody);
//
//    @Multipart
//    @POST("api/webcasts//image/{wid}")
//    Call<CommonModel> uploadWebcastSchedulePicture(@Path("wid") String wid,
//                                                   @Part("avatar\"; filename=\"avatar\" ") RequestBody file);
//
//    @GET("api/webcasts/remove/{wid}")
//    Call<CommonModel> deleteWebcast(@Path("wid") String wid);
//
//    @POST("api/webcasts/edit/{wid}")
//    Call<CommonModel> editWebcast(@Path("wid") String wid, @Body RequestBody requestBody);
//
//    @Multipart
//    @POST("api/galleries/insert/{sid}")
//    Call<CommonModel> uploadStarPhotos(@Path("sid") String sid,
//                                       @PartMap() Map<String, ProgressRequestBody> mapFileAndName);
//
//    @POST("api/webcasts/start/{wid}")
//    Call<CommonModel> startStream(@Path("wid") String wid);
//
//    @POST("api/webcasts/stop/{wid}")
//    Call<CommonModel> stopStream(@Path("wid") String wid);
//
//
//    @POST("/api/analytics/addrecord")
//    Call<CommonModel> startStopVideoPlayStatus(@Body RequestBody requestBody);

}
