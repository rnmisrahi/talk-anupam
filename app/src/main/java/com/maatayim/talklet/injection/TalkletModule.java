package com.maatayim.talklet.injection;

import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Sophie on 6/1/2017.
 */

@Module
public class TalkletModule {

    @Provides
    public Scheduler provideScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }

}
