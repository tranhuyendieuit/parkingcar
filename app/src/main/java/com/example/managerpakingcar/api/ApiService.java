package com.example.managerpakingcar.api;

import com.example.managerpakingcar.model.response.BaseResponseModel;
import com.example.managerpakingcar.model.request.SignUpRequestModel;
import com.example.managerpakingcar.model.request.SignInRequestModel;
import com.example.managerpakingcar.constant.Constant;
import com.example.managerpakingcar.model.response.CheckingResponseModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    //https://core-java-api-app.herokuapp.com/users/sign-in
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .setLenient()
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl(Constant.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService.class);

    @GET("hello")
    Call<String> getSayHello();

    @POST("users/sign-in")
    Call<BaseResponseModel<String>> signIn(@Body SignInRequestModel user);

    @POST("users/sign-up")
    Call<BaseResponseModel<String>> signUp(@Body SignUpRequestModel user);

    @GET("payment/check-parking/{parkingAreaId}")
    Call<BaseResponseModel<CheckingResponseModel>> checking(@Header("authorization") String token, @Path("parkingAreaId") String parkingAreaId);
}
