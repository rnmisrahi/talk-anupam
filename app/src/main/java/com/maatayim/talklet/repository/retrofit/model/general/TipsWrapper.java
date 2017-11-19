package com.maatayim.talklet.repository.retrofit.model.general;

import java.util.List;

/**
 * Created by Sophie on 5/28/2017
 */

public class TipsWrapper {

    private List<Tip> tips;

    public TipsWrapper(List<Tip> tips){

        this.tips = tips;
    }


    public List<Tip> getTips() {
        return tips;
    }

    public void setTips(List<Tip> tips) {
        this.tips = tips;
    }
}
