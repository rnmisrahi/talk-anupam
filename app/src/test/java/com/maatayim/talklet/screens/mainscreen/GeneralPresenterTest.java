package com.maatayim.talklet.screens.mainscreen;

import android.support.v4.util.Pair;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainscreen.generalticket.GeneralTipTicket;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Observable;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Sophie on 6/5/2017.
 */
public class GeneralPresenterTest {

    public static final String TEST_NAME = "test name";

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private GeneralContract.View view;

//    @Mock
//    Context context;

    @Mock
    BaseContract.Repository repository;


//    @Mock
//    EventBus eventBus;


    private GeneralPresenter presenter;


    @Before
    public void setUp() throws Exception {
        presenter = new GeneralPresenter(view, repository, Schedulers.trampoline());
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }


    @Test
    public void getWordsCount_RepositorySuccess() throws Exception {
        when(repository.getChildrenList()).thenReturn(Observable<List<Child>>.class);
        when(repository.getLastConnectionChild()).thenReturn(Observable<Integer>.class);
        when(repository.getTipsList(any(String.class))).thenReturn(Observable<List<GeneralTipTicket>>.class);
        when(repository.getWordsCount(any(String.class))).thenReturn(Observable<Pair<Integer, Integer>>.class);

        presenter.getData();
        verify(view, only()).setChildName(TEST_NAME);
        verify(view, only()).updateWordsCount(int,  int);
        verify(view, only()).updateTipsViewPager(int,  int);


    }



}