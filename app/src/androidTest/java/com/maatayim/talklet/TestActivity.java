package com.maatayim.talklet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;


/**
 * Created by Sophie on 5/24/2017.
 */

public class TestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        FrameLayout view = new FrameLayout(this);
//        view.setId(R.id.test_activity);
//
//        setContentView(view);
    }

}
