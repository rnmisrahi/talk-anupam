package com.maatayim.talklet.screens.mainactivity.mainscreen;


import java.util.List;

/**
 * Created by Sophie on 7/4/2017.
 */

public class MainScreenChild{

    private String id;
    private String url;
    private int wordCount;
    private int total;
    private List<Tip> tips;


    public String getUrl() {
        return url;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getTotal() {
        return total;
    }

    public List<Tip> getTips() {
        return tips;
    }

    public MainScreenChild(String id, String url, int wordCount, int total, List<Tip> tips) {
        this.id = id;
        this.url = url;
        this.wordCount = wordCount;
        this.total = total;
        this.tips = tips;
    }

    public static class Tip {
        private String text;
        private boolean assertion;

        public Tip(String text, boolean assertion) {
            this.text = text;
            this.assertion = assertion;
        }

        public String getText() {
            return text;
        }

        public boolean isAssertion() {
            return assertion;
        }
    }

    public String getId() {
        return id;
    }

    public void setTips(List<Tip> tips) {
        this.tips = tips;
    }
}
