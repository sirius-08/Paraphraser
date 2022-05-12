package com.example.minorproject;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UtteranceViewModel extends ViewModel {
    public String TAG = UtteranceViewModel.class.getSimpleName();
    private MutableLiveData<List<String>> utterancesList = new MutableLiveData<List<String>>();

    public MutableLiveData<List<String>> getUtterancesList(){
        return utterancesList;
    }

    public List<String> getUtterances(){
        return utterancesList.getValue();
    }

    public void loadUtterances(String utterance){
        UtteranceAPIService.getUtteranceAPI().getUtterances(new UtteranceQuery(utterance)).enqueue(new Callback<ResponseUtterance>() {
            @Override
            public void onResponse(Call<ResponseUtterance> call, Response<ResponseUtterance> response) {
                utterancesList.setValue(response.body().getUtterances());
            }

            @Override
            public void onFailure(Call<ResponseUtterance> call, Throwable t) {
                Log.v(TAG,"Request Failed");
            }
        });
    }
}
