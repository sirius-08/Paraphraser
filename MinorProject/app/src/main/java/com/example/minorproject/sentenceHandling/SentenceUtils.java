package com.example.minorproject.sentenceHandling;

import java.util.ArrayList;
import java.util.List;

public class SentenceUtils {
    public static  List<Sentence> paragraphToSentences(String paragraph){
        ArrayList<Sentence> sentences = new ArrayList<Sentence>();
        String temp = "";

        for(int i=0;i<paragraph.length();i++)
        {
            if(paragraph.charAt(i) == '.'){
                sentences.add(new Sentence(temp,temp));
                temp = "";
            }
            else{
                temp = temp + paragraph.charAt(i);
            }
        }
        return sentences;
    }

    public static String sentencesToParagraph(ArrayList<String> sentences){
        String paragraph = "";
        for(int i=0;i<sentences.size();i++){
            paragraph += sentences.get(i) + ".";
        }
        return paragraph;
    }

}
