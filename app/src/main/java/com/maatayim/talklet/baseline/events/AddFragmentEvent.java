package com.maatayim.talklet.baseline.events;

import com.maatayim.talklet.baseline.TalkletFragment;

/**
 * Created by Sophie on 5/22/2017.
 */

public class AddFragmentEvent {

    private TalkletFragment fragment;

    public AddFragmentEvent(TalkletFragment fragment){

        this.fragment = fragment;
    }

    public TalkletFragment getFragment() {
        return fragment;
    }
}
