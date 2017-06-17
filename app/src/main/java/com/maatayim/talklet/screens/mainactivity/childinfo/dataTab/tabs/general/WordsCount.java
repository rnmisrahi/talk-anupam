package com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general;

import android.support.v4.util.Pair;

/**
 * Created by Sophie on 6/7/2017
 */

public class WordsCount {

    private Pair<Integer, Integer> uniqueWords = new Pair<>(0, 0);
    private Pair<Integer, Integer> newWords = new Pair<>(0, 0);
    private Pair<Integer, Integer> advanceWords = new Pair<>(0, 0);


    public WordsCount(Pair<Integer, Integer> uniqueWords,
                      Pair<Integer, Integer> newWords,
                      Pair<Integer, Integer> advanceWords){

        this.uniqueWords = uniqueWords;
        this.newWords = newWords;
        this.advanceWords = advanceWords;

    }

    public Pair<Integer, Integer> getUniqueWords() {
        return uniqueWords;
    }

    public Pair<Integer, Integer> getNewWords() {
        return newWords;
    }

    public Pair<Integer, Integer> getAdvanceWords() {
        return advanceWords;
    }

    public Pair<Integer, Integer> getTotalWordsCount(){
        int totalSaidWords = uniqueWords.first + newWords.first + advanceWords.first;
        int totalWords = uniqueWords.second + newWords.second + advanceWords.second;
        return new Pair<Integer, Integer>(totalSaidWords, totalWords);
    }
}
