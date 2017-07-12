package com.maatayim.talklet.repository;

import com.maatayim.talklet.repository.realm.RealmTip;
import com.maatayim.talklet.repository.realm.RealmWordOfTheDay;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.DaysWordsObj;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipTicket;

import io.reactivex.functions.Function;

/**
 * Created by Sophie on 7/5/2017
 */

public class Mapper {


    public static Function<RealmWordOfTheDay, DaysWordsObj> getWordOfTheDayMapperFunction() {
        return wordOfTheDay -> new DaysWordsObj(wordOfTheDay.getWord(), wordOfTheDay.getSubtext(), wordOfTheDay.getTopic());
    }

    public static Function<RealmTip, TipTicket> getTipMapperFunction(String babyPhoto) {
        return realmTip -> new TipTicket(realmTip.getTipText(), realmTip.isAssertion(), babyPhoto);
    }

}
