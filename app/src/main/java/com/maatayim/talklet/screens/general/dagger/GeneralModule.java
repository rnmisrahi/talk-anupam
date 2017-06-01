package com.maatayim.talklet.screens.general.dagger;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.repository.LocalData;
import com.maatayim.talklet.repository.RemoteData;
import com.maatayim.talklet.repository.RepositoryImpl;
import com.maatayim.talklet.screens.general.GeneralContract;
import com.maatayim.talklet.screens.general.GeneralPresenter;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Sophie on 5/28/2017
 */

@Module
public class GeneralModule {

    @Singleton
    @Provides
    public GeneralPresenter getPresenter(GeneralContract.View view, BaseContract.Repository repo) {
        return new GeneralPresenter(view, repo);
    }



    @Singleton
    @Provides
    public RepositoryImpl provideRepository(LocalData localDataImpl, RemoteData remoteData) {
        return new RepositoryImpl(localDataImpl, remoteData);
    }

}
