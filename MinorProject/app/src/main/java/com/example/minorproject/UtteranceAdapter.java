package com.example.minorproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UtteranceAdapter extends RecyclerView.Adapter<UtteranceAdapter.UtteranceViewHolder>{

    private List<String> utteranceEntries;
    private Context mContext;

    public UtteranceAdapter(Context context){
        mContext = context;
    }

    @NonNull
    @NotNull
    @Override
    public UtteranceViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new UtteranceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UtteranceViewHolder holder, int position) {
        TextView textView = holder.getTextView();
        textView.setText(utteranceEntries.get(position));
    }

    @Override
    public int getItemCount() {
        if(utteranceEntries == null){
            return 0;
        }
        else
            return utteranceEntries.size();
    }

    public void setDataset(List<String> utteranceEntries){
        this.utteranceEntries = utteranceEntries;
        notifyDataSetChanged();
    }

    public static class UtteranceViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;

        public UtteranceViewHolder(View view){
            super(view);
            textView = (TextView) view.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
