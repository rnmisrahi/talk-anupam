package com.maatayim.talklet.repository.retrofit.model.worddata;

import java.util.List;

/**
 * Created by Sophie on 5/28/2017
 */

public class WordData {

    private List<WordOfTheDay> wordsOfTheDay;


    public WordData(List<WordOfTheDay> wordsOfTheDay) {
        this.wordsOfTheDay = wordsOfTheDay;
    }

    public List<WordOfTheDay> getWordsOfTheDay() {
        return wordsOfTheDay;
    }


}

