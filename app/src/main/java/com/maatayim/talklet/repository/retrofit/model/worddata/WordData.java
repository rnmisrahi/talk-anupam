package com.maatayim.talklet.repository.retrofit.model.worddata;

import java.util.List;

/**
 * Created by Sophie on 5/28/2017.
 */

public class WordData {

    private List<String> favorite;
    private List<Word> newWords;
    private List<Word> advance;
    private List<Word> other;

    public WordData(List<String> favorite, List<Word> newWords, List<Word> advance, List<Word> other){

        this.favorite = favorite;
        this.newWords = newWords;
        this.advance = advance;
        this.other = other;
    }


    public List<String> getFavorite() {
        return favorite;
    }

    public void setFavorite(List<String> favorite) {
        this.favorite = favorite;
    }

    public List<Word> getNewWords() {
        return newWords;
    }

    public void setNewWords(List<Word> newWords) {
        this.newWords = newWords;
    }

    public List<Word> getAdvance() {
        return advance;
    }

    public void setAdvance(List<Word> advance) {
        this.advance = advance;
    }

    public List<Word> getOther() {
        return other;
    }

    public void setOther(List<Word> other) {
        this.other = other;
    }
}

