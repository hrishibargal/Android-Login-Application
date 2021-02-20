package com.hrishi.firstlogin;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginApi {
    String DJANGO_SITE= "http://192.168.43.216:8000/";

    @FormUrlEncoded
    @POST("api/account/login/")
    Call<Token> token(@Field("username") String username,
                      @Field("password") String password);

    @GET("api/blog/")
    Call<List<Body>> getImage(@Header("Authorization")String token);

}
