package com.maatayim.talklet.repository;

import com.maatayim.talklet.repository.realm.RealmRecording;
import com.maatayim.talklet.repository.realm.RealmTip;
import com.maatayim.talklet.repository.realm.RealmWordOfTheDay;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.DaysWordsObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.RecordingObj;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipTicket;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.functions.Function;
import io.realm.RealmList;

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

    public static List<RecordingObj> mapRealmRecordingListToRecordsObj(RealmList<RealmRecording> realmRecordings) {
        List<RecordingObj> recordingObjs = new ArrayList<>();
        for (RealmRecording realmRecording : realmRecordings) {
            recordingObjs.add(new RecordingObj(realmRecording.getId(), realmRecording.getNumber(),
                    new Date(Long.valueOf(realmRecording.getDate())), realmRecording.getWordCounter(),
                    realmRecording.getWordCounterGoal(), Long.valueOf(realmRecording.getDuration()), false));
        }
        return recordingObjs;
    }

}
