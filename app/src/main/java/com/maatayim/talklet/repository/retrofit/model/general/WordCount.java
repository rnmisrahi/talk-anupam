package com.maatayim.talklet.repository.retrofit.model.general;

/**
 * Created by Sophie on 5/28/2017
 */

public class WordCount {

    private int total;
//    private int unique;
//    private int newWords;
//    private int advance;


    public WordCount(int total) {
        this.total = total;
    }

    //    public WordCount(int total, int unique, int newWords, int advance){
//
//        this.total = total;
//        this.unique = unique;
//        this.newWords = newWords;
//        this.advance = advance;
//    }
//
//
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
//
//    public int getUnique() {
//        return unique;
//    }
//
//    public void setUnique(int unique) {
//        this.unique = unique;
//    }
//
//    public int getNewWords() {
//        return newWords;
//    }
//
//    public void setNewWords(int newWords) {
//        this.newWords = newWords;
//    }
//
//    public int getAdvance() {
//        return advance;
//    }
//
//    public void setAdvance(int advance) {
//        this.advance = advance;
//    }
}
