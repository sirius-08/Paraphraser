package com.example.minorproject.sentenceHandling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.minorproject.LoadFileActivity;
import com.example.minorproject.R;

import java.util.ArrayList;
import java.util.List;

public class SentenceActivity extends AppCompatActivity implements SentenceAdapter.onSentenceClickListener{

    private RecyclerView recyclerView;
    private SentenceAdapter adapter;
    private SentenceViewModel viewModel;
    private String TAG = SentenceActivity.class.getSimpleName();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence);

        initViews();

        viewModel = new ViewModelProvider(this).get(SentenceViewModel.class);
        viewModel.getLiveDataSentences().observe(this, new Observer<List<Sentence>>() {
            @Override
            public void onChanged(List<Sentence> sentences) {
                Log.d(TAG,"Sentences Changed");
                adapter.changeDataSet(sentences);
            }
        });
        
        Intent intent = getIntent();
        if(intent.hasExtra(LoadFileActivity.paragraphKey)){
            String paragraph = intent.getStringExtra(LoadFileActivity.paragraphKey);
            setLiveData(paragraph);
        }
    }
    
    void initViews(){
        recyclerView = (RecyclerView) findViewById(R.id.sentence_recylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SentenceAdapter(this,this);
        recyclerView.setAdapter(adapter);
    }

    void setLiveData(String paragraph){
        List<Sentence> sentences = SentenceUtils.paragraphToSentences(paragraph);
        viewModel.getLiveDataSentences().setValue(sentences);
    }

    @Override
    public void onSentenceClick(int position) {

    }
}