package com.maatayim.talklet.screens.mainactivity.childinfo.favorites;

import com.maatayim.talklet.baseline.BaseContract;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.FourWordsObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsrv.SpecialWords;

import java.util.List;

/**
 * Created by Sophie on 6/22/2017
 */

public interface FavoriteWordsContract {

    interface Presenter extends BaseContract.Presenter{
        void getData(int id);

        void getNewWords(boolean dorDisplay);
        void getAdvanceWords();
        void getOtherWords();
    }

    interface View extends BaseContract.View{
        void initFavoriteWordsRecyclerView(List<FourWordsObj> favoriteWords);
        void initTableWords(List<SpecialWords> tableWords);
        void initializeViewPager(List<SpecialWords> ticketList);
    }
}
