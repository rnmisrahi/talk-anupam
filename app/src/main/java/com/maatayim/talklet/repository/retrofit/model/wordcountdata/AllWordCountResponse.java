package com.maatayim.talklet.repository.retrofit.model.wordcountdata;

import java.util.List;

/**
 * Created by Sophie on 7/16/2017
 */

public class AllWordCountResponse {

    private List<ChildWordModel> wordCountList;

    public AllWordCountResponse(List<ChildWordModel> wordCountList){

        this.wordCountList = wordCountList;
    }

    public List<ChildWordModel> getWordCountList() {
        return wordCountList;
    }

    public void setWordCountList(List<ChildWordModel> wordCountList) {
        this.wordCountList = wordCountList;
    }


}
