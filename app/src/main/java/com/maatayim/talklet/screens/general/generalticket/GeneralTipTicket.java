package com.maatayim.talklet.screens.general.generalticket;


/**
 * Created by Sophie on 5/26/2017.
 */

public class GeneralTipTicket {

    private final String tip;
    private final boolean isAssertion;

    public GeneralTipTicket(String tip, boolean isAssertion){

        this.tip = tip;
        this.isAssertion = isAssertion;
    }

    public String getTip() {
        return tip;
    }

    public boolean isAssertion() {
        return isAssertion;
    }
}
