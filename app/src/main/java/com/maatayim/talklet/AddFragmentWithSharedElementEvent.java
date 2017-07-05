package com.maatayim.talklet;

import android.view.View;

import com.maatayim.talklet.baseline.events.AddFragmentEvent;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;

/**
 * Created by Sophie on 7/2/2017.
 */

public class AddFragmentWithSharedElementEvent extends AddFragmentEvent{
    private final TalkletFragment fragment;
    private final View view;

    public AddFragmentWithSharedElementEvent(TalkletFragment fragment, View view) {
        super(fragment);
        this.fragment = fragment;
        this.view = view;
    }

    @Override
    public TalkletFragment getFragment() {
        return fragment;
    }

    public View getView() {
        return view;
    }


}
