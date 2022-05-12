package com.example.minorproject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UtteranceAPI {
    @POST("get-utterances")
    Call<ResponseUtterance> getUtterances(@Body UtteranceQuery query);
}
