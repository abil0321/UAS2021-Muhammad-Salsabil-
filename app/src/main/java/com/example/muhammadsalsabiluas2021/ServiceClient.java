package com.example.muhammadsalsabiluas2021;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceClient {
    private static Retrofit.Builder builder = new Retrofit.Builder();
    private static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();

    public static ServiceRepository buildServiceClient(){
        return builder.baseUrl("http://192.168.43.162/Kuliah/JSON/UAS2021/loginS.php/").addConverterFactory(GsonConverterFactory.create()).client(client).build().create(ServiceRepository.class);
    }
}
