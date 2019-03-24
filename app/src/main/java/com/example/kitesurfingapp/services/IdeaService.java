package com.example.kitesurfingapp.services;


import com.example.kitesurfingapp.models.Idea;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface IdeaService {
    @GET("api-spot-get-all")
    Call<List<Idea>> getIdeas();

    @GET("api-spot-get-details/{id}")
    Call<Idea> getIdea(@Path("id") int id);


    @FormUrlEncoded
    @PUT("api-spot-favorites-ad/{id}")
    Call<Idea> putIdea(
            @Path("id") int id,
            @Field("country") String country,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("windProbability") String windProbability,
            @Field("whereToGo") String whereToGo
    );

    @DELETE("api-spot-favorites-remove/{id}")
    Call<Void> deleteIdea(@Path("id") int id);
}
