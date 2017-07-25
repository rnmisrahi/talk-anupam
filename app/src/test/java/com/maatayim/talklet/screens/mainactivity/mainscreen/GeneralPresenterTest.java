package com.maatayim.talklet.screens.mainactivity.mainscreen;

import android.net.Uri;
import android.support.v4.util.Pair;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipTicket;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Sophie on 6/5/2017
 */
public class GeneralPresenterTest {

    public static final String TEST_NAME = "test name";
    public static final int WORDS_COUNT = 20;
    public static final int MAX_WORDS = 400;
    public static final int CHILD_IDX = 1;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private MainContract.View view;

    @Mock
    private BaseContract.Repository repository;

    @Mock
    private List<TipTicket> tipList;

    @Mock
    private List<TipTicket> tipsList;


    private List<Child> childList = new ArrayList<>();
    private Pair<Integer, Integer> wordsCount = new Pair<>(WORDS_COUNT, MAX_WORDS);
    private MainPresenter presenter;


    @Before
    public void setUp() throws Exception {
        presenter = new MainPresenter(view, repository, Schedulers.trampoline());
        RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
            @Override
            public Scheduler apply(@NonNull Scheduler scheduler) throws Exception {
                return Schedulers.trampoline();
            }
        });
    }


    @Test
    public void getData_RepositorySuccess() throws Exception {
        childList.add(new Child("1", TEST_NAME + "!@!", new Date(), Uri.EMPTY));
        childList.add(new Child("11", TEST_NAME, new Date(), Uri.EMPTY));
        childList.add(new Child("12", TEST_NAME + "!@!@!@!@!@", new Date(), Uri.EMPTY));


        when(repository.getMainScreenChildrenList()).thenReturn(Observable.<List<Child>>just(childList));
        when(repository.getLastConnectionChild()).thenReturn(Observable.<Child>just(childList.get(CHILD_IDX)));
        when(repository.getTipsList(any(String.class))).thenReturn(Observable.<List<TipTicket>>just(tipsList));
        when(repository.getWordsCount(any(String.class))).thenReturn(Observable.<Pair<Integer, Integer>>just(wordsCount));


        presenter.getData();
        verify(view).updateTipsViewPager(tipsList);
        verify(view).updateWordsCount(wordsCount.first,  wordsCount.second);



    }


    @Test
    public void getChildrenList_RepositoryFailure() throws Exception {
        childList.add(new Child("1", TEST_NAME + "!@!", new Date(), Uri.EMPTY));
        childList.add(new Child("11", TEST_NAME, new Date(), Uri.EMPTY));
        childList.add(new Child("12", TEST_NAME + "!@!@!@!@!@", new Date(), Uri.EMPTY));

        when(repository.getMainScreenChildrenList()).thenReturn(Observable.<List<Child>>error(new Exception()));

        presenter.getData();
        verify(view, only()).onDisplayChildrenError();

    }

    @Test
    public void loadLastConnectionChild_RepositoryFailure() throws Exception{
        childList.add(new Child("1", TEST_NAME + "!@!", new Date(), Uri.EMPTY));
        childList.add(new Child("11", TEST_NAME, new Date(), Uri.EMPTY));
        childList.add(new Child("12", TEST_NAME + "!@!@!@!@!@", new Date(), Uri.EMPTY));


        when(repository.getMainScreenChildrenList()).thenReturn(Observable.<List<Child>>just(childList));
        when(repository.getLastConnectionChild()).thenReturn(Observable.<Child>error(new Exception()));

        presenter.getData();
        verify(view, only()).onChildLoadError();

    }


    @Test
    public void getTips_RepositoryFailure() throws Exception{
        childList.add(new Child("1", TEST_NAME + "!@!", new Date(), Uri.EMPTY));
        childList.add(new Child("11", TEST_NAME, new Date(), Uri.EMPTY));
        childList.add(new Child("12", TEST_NAME + "!@!@!@!@!@", new Date(), Uri.EMPTY));


        when(repository.getMainScreenChildrenList()).thenReturn(Observable.<List<Child>>just(childList));
        when(repository.getLastConnectionChild()).thenReturn(Observable.<Child>just(childList.get(CHILD_IDX)));
        when(repository.getTipsList(any(String.class))).thenReturn(Observable.<List<TipTicket>>error(new Exception()));
        when(repository.getWordsCount(any(String.class))).thenReturn(Observable.<Pair<Integer, Integer>>just(wordsCount));


        presenter.getData();
        verify(view).onTipsLoadError();

    }


    @Test
    public void getWordsCount_RepositoryFailure() throws Exception{
        childList.add(new Child("1", TEST_NAME + "!@!", new Date(), Uri.EMPTY));
        childList.add(new Child("11", TEST_NAME, new Date(), Uri.EMPTY));
        childList.add(new Child("12", TEST_NAME + "!@!@!@!@!@", new Date(), Uri.EMPTY));


        when(repository.getMainScreenChildrenList()).thenReturn(Observable.<List<Child>>just(childList));
        when(repository.getLastConnectionChild()).thenReturn(Observable.<Child>just(childList.get(CHILD_IDX)));
        when(repository.getTipsList(any(String.class))).thenReturn(Observable.<List<TipTicket>>just(tipsList));
        when(repository.getWordsCount(any(String.class))).thenReturn(Observable.<Pair<Integer, Integer>>error(new Exception()));


        presenter.getData();
        verify(view).updateTipsViewPager(tipsList);
        verify(view).onWordsCountLoadError();


    }



}