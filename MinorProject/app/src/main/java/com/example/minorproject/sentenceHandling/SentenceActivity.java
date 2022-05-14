package com.example.minorproject.sentenceHandling;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatSideChannelService;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.minorproject.LoadFileActivity;
import com.example.minorproject.R;
import com.example.minorproject.utteranceHandling.UtteranceActivity;

import java.util.ArrayList;
import java.util.List;

public class SentenceActivity extends AppCompatActivity implements SentenceAdapter.onSentenceClickListener{

    private RecyclerView recyclerView;
    private SentenceAdapter adapter;
    private SentenceViewModel viewModel;
    private String TAG = SentenceActivity.class.getSimpleName();
    public final static String sentenceKey = "com.example.minorproject.sentenceHandling.sentenceKey";
    public final static String positionKey = "com.example.minorproject.sentenceHandling.positionKey";

    private int clickPosition;
    private String originalSentence;

    private List<Sentence> mSentences;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence);

        initViews();

//        viewModel = new ViewModelProvider(this).get(SentenceViewModel.class);
//        viewModel.getLiveDataSentences().observe(this, new Observer<List<Sentence>>() {
//            @Override
//            public void onChanged(List<Sentence> sentences) {
//                Log.d(TAG,"Sentences Changed");
//                adapter.changeDataSet(sentences);
//            }
//        });
        
        Intent intent = getIntent();
        if(intent.hasExtra(LoadFileActivity.paragraphKey)){
            String paragraph = intent.getStringExtra(LoadFileActivity.paragraphKey);
            setAdapterData(paragraph);
        }
    }
    
    void initViews(){
        recyclerView = (RecyclerView) findViewById(R.id.sentence_recylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SentenceAdapter(this,this);
        recyclerView.setAdapter(adapter);
    }

    void setAdapterData(String paragraph){
        mSentences = SentenceUtils.paragraphToSentences(paragraph);
        //viewModel.getLiveDataSentences().setValue(sentences);
        adapter.changeDataSet(mSentences);
    }

    @Override
    public void onSentenceClick(int position) {
        clickPosition = position;
        Intent intent = new Intent(this, UtteranceActivity.class);
        originalSentence = mSentences.get(position).getOriginal();
        intent.putExtra(sentenceKey,originalSentence);
        intent.putExtra(positionKey,position);
        startActivityForResult(intent,UtteranceActivity.UTTERANCE_RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG,requestCode + " " + resultCode);
        if(requestCode == UtteranceActivity.UTTERANCE_RESULT_CODE && resultCode == Activity.RESULT_OK){
            String utterance = data.getData().toString();
            //viewModel.getSentences().set(clickPosition,new Sentence(originalSentence,utterance));
            mSentences.set(clickPosition,new Sentence(originalSentence,utterance));
            adapter.changeDataSet(mSentences);
            //Log.d(TAG,viewModel.getSentences().get(clickPosition).getRephrased());

        }
        else{
            Log.d(TAG,"Result not Ok");
        }
    }
}