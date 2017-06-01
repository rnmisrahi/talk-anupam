package com.maatayim.talklet.repository.retrofit.model.worddata;

/**
 * Created by Sophie on 5/28/2017
 */

public class Word {

    private String id;
    private int freqency;
    private int age;
    private String word;

    public Word(String id, int freqency, int age, String word){

        this.id = id;
        this.freqency = freqency;
        this.age = age;
        this.word = word;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFreqency() {
        return freqency;
    }

    public void setFreqency(int freqency) {
        this.freqency = freqency;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}

