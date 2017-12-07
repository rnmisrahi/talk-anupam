package com.maatayim.talklet.screens.mainactivity.childinfo.favorites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.CustomWordSection;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.FavoriteWordsAdapter;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.FourWordsObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsrv.OpenWordsTableEvent;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsrv.SpecialWords;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsviewpager.WordAdviceAdapter;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.injection.FavoriteWordsModule;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipTicket;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.maatayim.talklet.screens.mainactivity.mainscreen.MainFragment.DEFAULT_GAP;
import static com.maatayim.talklet.screens.mainactivity.mainscreen.MainFragment.HALF_GAP;

/**
 * Created by Sophie on 6/22/2017
 */

public class FavoriteWordsFragment extends TalkletFragment implements FavoriteWordsContract.View {

    public static final String ARG_ID = "babyId";


    @Inject
    FavoriteWordsContract.Presenter presenter;


    private int babyId;

    @BindView(R.id.favorites_words_rv)
    RecyclerView favoritesWordsRV;

    @BindView(R.id.view_all_words)
    TextView otherWords;

    @BindView(R.id.new_words_section)
    CustomWordSection newWordsSection;

    @BindView(R.id.advance_words_section)
    CustomWordSection advanceWordsSection;

    @BindView(R.id.other_words_section)
    CustomWordSection otherWordsSection;

    @BindView(R.id.words_view_pager)
    ViewPager wordsPager;

    private boolean isClickedAllWords = false;


    private CustomWordSection currentCustommView;


    public static FavoriteWordsFragment newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        FavoriteWordsFragment fragment = new FavoriteWordsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!= null) {
            babyId = getArguments().getInt(ARG_ID);
        }
        ((TalkletApplication) getActivity().getApplication()).getAppComponent().plus(new FavoriteWordsModule(this)).inject(this);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites_words, container, false);
        ButterKnife.bind(this, view);
        setTitle(getString(R.string.favorites_title));
        presenter.getData(babyId);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void initFavoriteWordsRecyclerView(List<FourWordsObj> favWords){
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        favoritesWordsRV.setLayoutManager(linearLayoutManager);

        FavoriteWordsAdapter favoriteWordsAdapter = new FavoriteWordsAdapter(favWords);
        favoritesWordsRV.setAdapter(favoriteWordsAdapter);

//        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
//        itemAnimator.setAddDuration(1000);
//        itemAnimator.setRemoveDuration(1000);
//        favoritesWordsRV.setItemAnimator(itemAnimator);


    }

    @OnClick(R.id.view_all_words)
    public void onOtherWordsClick(){
        if(!isClickedAllWords){
            otherWords.setText(getString(R.string.close_all_words));
            otherWords.setCompoundDrawablesWithIntrinsicBounds(R.drawable.minus,0, 0, 0);

            newWordsSection.setVisibility(View.VISIBLE);
            advanceWordsSection.setVisibility(View.VISIBLE);
            otherWordsSection.setVisibility(View.VISIBLE);
            isClickedAllWords = true;
            return;

        }else{
            otherWords.setText(getString(R.string.view_all_words));
            otherWords.setCompoundDrawablesWithIntrinsicBounds(R.drawable.menu_dark_grey,0, 0, 0);

            newWordsSection.setVisibility(View.GONE);
            advanceWordsSection.setVisibility(View.GONE);
            otherWordsSection.setVisibility(View.GONE);
            isClickedAllWords = false;
            return;
        }


    }

    @Override
    public void initTableWords(List<SpecialWords> tableWords){

        currentCustommView.setRecyclerView(tableWords);
    }

    @Subscribe
    public void onUpdateTableEvent(OpenWordsTableEvent event){
        currentCustommView = event.getCustomView();
        if(event.getWordsType().equals(getString(R.string.new_word))){
            presenter.getNewWords(false);
        } else if(event.getWordsType().equals(getString(R.string.advance_word))){
            presenter.getAdvanceWords();
        }else{
            presenter.getOtherWords();
        }
    }



    @Override
    public void initializeViewPager(List<SpecialWords> ticketList) {
        wordsPager.setPadding(DEFAULT_GAP, 0, DEFAULT_GAP, 0);
        wordsPager.setClipToPadding(false);
        wordsPager.setPageMargin(HALF_GAP);

        WordAdviceAdapter pagerAdapter = new WordAdviceAdapter(
                getChildFragmentManager(), ticketList);
        wordsPager.setAdapter(pagerAdapter);

    }
}
