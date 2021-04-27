package com.example.muhammadsalsabiluas2021;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServiceRepository {
    @Headers({
            "Accept: application/json",
            "x-rainbow-client: android",
            "x-rainbow-client-version: 1.10.7"
    })
    @GET("api/rainbow/authentication/v1.0/login")
    Call<AuthorizationResponse> login(@Header("Authorization") String authorization);

    @Headers({
            "Accept: application/json"
    })
    @GET("api/rainbow/authentication/v1.0/logout")
    Call<Void> logout(@Header("Authorization") String authorization);

    @Headers(
            "Accept: application/json"
    )
    @GET("api/rainbow/admin/v1.0/users/{userId}")
    Call<UserResponse> getUser(@Header("Authorization") String authorization, @Path("userId") String userId);

    @Headers(
            "Content-Type: application/json"
    )
    @PUT("api/rainbow/admin/v1.0/users/{userId}")
    Call<UserResponse> updateInfoUser(@Header("Authorization") String authorization, @Path("userId") String userId, @Body User user);


}
