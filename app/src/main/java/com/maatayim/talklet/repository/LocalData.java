package com.maatayim.talklet.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.util.Pair;

import com.maatayim.talklet.repository.realm.RealmChild;
import com.maatayim.talklet.repository.realm.RealmCountData;
import com.maatayim.talklet.repository.realm.RealmRecording;
import com.maatayim.talklet.repository.realm.RealmTip;
import com.maatayim.talklet.repository.realm.RealmToken;
import com.maatayim.talklet.repository.realm.RealmUser;
import com.maatayim.talklet.repository.realm.RealmWordOfTheDay;
import com.maatayim.talklet.repository.retrofit.model.children.ChildModel;
import com.maatayim.talklet.repository.retrofit.model.general.Recording;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.loginactivity.login.UserDetails;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.callendarrv
        .CalendarWordsObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.WordsCount;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.FourWordsObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsrv.SpecialWords;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.RecordingObj;
import com.facebook.login.LoginResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

import static com.maatayim.talklet.utils.Utils.parserDate;

/**
 * Created by Sophie on 5/28/2017
 */

public class LocalData {
    public static final Uri DEFAULT_URI = Uri.parse("https://s-media-cache-ak0.pinimg" +
            ".com/736x/a2/e0/25/a2e025b30f2e129672b480a54ecc0b6c.jpg");

    public static final String REALM_WORD_OF_THE_DAY_CHILDID = "childId";

    public static final String REALM_CHILD_ID = "id";

    public static final String REALM_TIP_CHILD_ID = "childId";

    public static final String FACEBOOK_ID = "facebookID";

    public static final String EMPTY_FB_ID = "";

    //temp child DB
    String babysName;

    Date birthday = mockBirthday(2017, 5, 1);

    int lastChildConnected = 1;

    Uri babyBossUri = Uri.parse("https://resizing.flixster" +
            ".com/PyDVFygd7owZI0jgdJIFQcJ4Ovg=/300x300" +
            "/v1.bjsxMjYxMjY5O2o7MTczODQ7MTIwMDszMDAwOzE1MDA");

    Uri booUri = Uri.parse("http://animatie.blog.nl/files/2009/11/petedocterideemonstersincpicboo" +
            ".jpg");

    List<Child> children = mockChildrenList();

    private int currentId = 2;


    public static final String TEMP_TOKEN =
            "EAALUm1y1RtcBAAfZCaA91aV9yvbKZCW940qo8gVdGSe1TkVNEgnaRnQt4dgiJft1hvNSs6EfPkLpKqg4MdMLzbT5Api0jY1C9wFP7EmuiJVHf8KejYZBcZAwZAF64wpvfxrZAS5YE2wBbV6SzVyP2gZAOXfsET4JDZCebcF9YJliRw0nCaFZBn24f0mUgYSJ45ql73w1o0YsF7oDMY4RLcC4Q";

    //    public static final String TEMP_TOKEN = "TEST_TOKEN";
    public static final int DEFAULT_CHILD = 0;

    private String babysPhoto = "AA";

    private static LoginResult loginToken;

    private Observable<Integer> lastConnectionChild;

    private Observable<List<String>> languagesList;

    private UserDetails userDetails;

    private String facebookId = ""; // = "123";

    private boolean isSignedUp = false;

    public Single<Boolean> isSignedUp() {
        return Single.just(isSignedUp);
    }

    private Date mockBirthday(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

//    Save

    public Completable saveTipRx(final int tipId, final String text, final String tipType, final
    int childID) {
        return Completable.fromAction(() -> saveTip(tipId, text, tipType, childID))
                          .subscribeOn(Schedulers.io());
    }

    public void saveTip(final int tipId, final String text, final String tipType, final int
            childID) {

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            RealmTip realmTip = new RealmTip(tipId, text, tipType, childID);
            realm1.copyToRealmOrUpdate(realmTip);
        });
        realm.close();

    }

    public Completable saveChildRx(final ChildModel childModel) {
        return Completable.fromAction(() -> saveChild(childModel.getId(), childModel.getName(),
                parserDate(childModel.getBirthday()), childModel.getImage()))
                .subscribeOn(Schedulers.io());
    }

    public void saveChild(final int childId, final String name, final long birthday, final String
            babyImg) {

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            RealmChild realmChild = new RealmChild(childId, name, birthday, babyImg);
            realm1.copyToRealmOrUpdate(realmChild);
        });
        realm.close();

    }

    public Completable saveWordOfDayRx(final int id, final String word, final String subtext,
                                       final int childID, final String topic,
                                       final List<String> infoList, final List<String> questionList,
                                       final List<String> activitiesList, final List<String>
                                               ourFaveList) {
        return Completable.fromAction(() -> saveWordOfDay(id, word, subtext, childID, topic,
                infoList, questionList, activitiesList, ourFaveList));
    }

    public void saveWordOfDay(final int id, final String word, final String subtext, final int
            childID, final String topic,
                              final List<String> infoList, final List<String> questionList,
                              final List<String> activitiesList, final List<String> ourFaveList) {

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            RealmWordOfTheDay realmWordOfTheDay = new RealmWordOfTheDay(id, word, subtext,
                    childID, topic, infoList, questionList, activitiesList, ourFaveList);
            realm1.copyToRealmOrUpdate(realmWordOfTheDay);
        });
        realm.close();

    }


    public Completable saveFacebookLoginToken(LoginResult loginResult, Context context) {
        loginToken = loginResult;
//        facebookId = loginResult.getAccessToken().getUserId();
        saveFacebookIdRx(loginResult.getAccessToken().getUserId(), context);
        return Completable.complete();
    }


    public void saveFacebookIdRx(final String id, Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(FACEBOOK_ID, id);
        editor.apply();
    }


    public Completable updateUsersDataRx(String firstName, String lastName, String birthday,
                                         String languageOne, String languageTwo, String
                                                 languageThree) {
        return Completable.fromAction(() -> saveUsersData(firstName, lastName, birthday,
                languageOne, languageTwo, languageThree));
    }


    public void saveUsersData(String firstName, String lastName, String birthday, String
            languageOne, String languageTwo, String languageThree) {

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            RealmUser realmUser = new RealmUser(firstName, lastName,
                    birthday, languageOne, languageTwo, languageThree);
            realm1.copyToRealmOrUpdate(realmUser);
        });
        realm.close();

    }

    public Completable saveCountDataRx(final int id, final int childId, final int countData,
                                       final int totalData, final long date, final
                                       List<Recording> recordingsList) {
        return Completable.fromAction(() -> saveCountData(id, childId, countData, totalData,
                date, recordingsList)).subscribeOn(Schedulers.io());
    }

    public void saveCountData(final int id, final int childId, final int countData, final int
            totalData, final long date, final List<Recording> recordingsList) {

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            RealmList<RealmRecording> recordList = new RealmList<RealmRecording>();
            for (Recording recording : recordingsList) {
                recordList.add(new RealmRecording(recording.getId(), recording.getNumber(),
                        parserDate(recording
                                .getDate()),
                        recording.getWordCount(), totalData, recording.getDuration()));

            }
            RealmCountData realmCountData = new RealmCountData(id, childId, countData, totalData,
                    date, recordList);
            realm1.copyToRealmOrUpdate(realmCountData);
        });
        realm.close();

    }


//    Get

//    public Single<String> getToken() {
//        return Single.just("SOME_TOKEN");/
//    }


//    public Single<String> getFacebookId() {
//        return Single.fromCallable(() -> facebookId);
//    }


    public Single<List<RealmChild>> getChildrenListRx() {
        return Single.fromCallable(() -> getChildrenList());
    }

    private List<RealmChild> getChildrenList() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<RealmChild> childList = realm.where(RealmChild.class).findAll();
        List<RealmChild> response = new ArrayList<>();
        for (RealmChild realmChild : childList) {
            response.add(new RealmChild(realmChild));
        }
        realm.close();
        return response;
    }


    public Single<List<RealmTip>> getTipsListRx(final int id) {
        return Single.fromCallable(() -> getTipListByChild(id));
    }

    private List<RealmTip> getTipListByChild(final int id) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<RealmTip> tipList = realm.where(RealmTip.class)
                                              .equalTo(REALM_TIP_CHILD_ID, id)
                                              .findAll();
        List<RealmTip> response = new ArrayList<>();
        for (RealmTip realmTip : tipList) {
            response.add(new RealmTip(realmTip));
        }
        realm.close();
        return response;
    }

    public Single<RealmChild> getChildRx(final int id) {
        return Single.fromCallable(() -> getChild(id));
    }

    private RealmChild getChild(final int id) {
        Realm realm = Realm.getDefaultInstance();
        RealmChild child = realm.where(RealmChild.class).equalTo(REALM_CHILD_ID, id).findFirst();
        RealmChild response = new RealmChild(child);
        realm.close();
        return response;
    }


    public Single<List<RealmWordOfTheDay>> getWordsOfTheDayListRx(final int id) {
        return Single.fromCallable(() -> getWordsOfTheDayList(id));
    }

    private List<RealmWordOfTheDay> getWordsOfTheDayList(final int id) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<RealmWordOfTheDay> wordsList = realm.where(RealmWordOfTheDay.class)
                                                         .equalTo(REALM_WORD_OF_THE_DAY_CHILDID, id)
                                                         .findAll();
        List<RealmWordOfTheDay> response = new ArrayList<>();
        for (RealmWordOfTheDay word : wordsList) {
            response.add(new RealmWordOfTheDay(word));
        }
        realm.close();
        return response;
    }


    public Single<String> getFacebookIdRx(Context context) {
        return Single.fromCallable(() -> getFacebookId(context));
    }

    public String getFacebookId(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String facebookId = preferences.getString(FACEBOOK_ID, "");
        if (!facebookId.equalsIgnoreCase("")) {
            return facebookId;
        }
        return EMPTY_FB_ID;
    }


    public Completable logout(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPref.edit().remove(FACEBOOK_ID).apply();
        return Completable.complete();
    }


    public Single<RealmCountData> getWordsCountRx(final int id) {
        return Single.fromCallable(() -> getWordsCount(id)).map(realmCountDatas -> {
            RealmCountData response = new RealmCountData();
            int total = 0;
            int words = 0;
            for (RealmCountData realmCountData : realmCountDatas) {
                total += realmCountData.getExpectedWordCount();
                words += realmCountData.getWordCount();
            }
            response.setExpectedWordCount(total);
            response.setWordCount(words);
            return response;
        });
    }

    private List<RealmCountData> getWordsCount(final int id) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<RealmCountData> countDataList = realm.where(RealmCountData.class)
                                                          .equalTo(REALM_TIP_CHILD_ID, id)
                                                          .findAll();
        List<RealmCountData> response = new ArrayList<>();
        for (RealmCountData countData : countDataList) {
            response.add(realm.copyFromRealm(countData));
        }
        realm.close();
        return response;
    }

    public Single<List<RealmCountData>> getWordsCountByDateRx(final int id) {
        return Single.fromCallable(() -> getWordsCount(id));
    }


    public Completable savePersonalSignUpDetails(String name, Date birthday) {
        currentId++;
        children.add(new Child(currentId, name, birthday, babysPhoto, false));
//        this.babysName = name;
//        this.birthday = birthday;

        return Completable.complete();
    }


    public Completable saveUserFBDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
        return Completable.complete();
    }

    public Completable saveBabysPhoto(String photo) {
        this.babysPhoto = photo; //temp
        return Completable.complete();
    }


//    Getters

    public Observable<String> getName(int id) {
        return Observable.fromCallable(() -> "Sophie");
    }

    public Observable<Date> getBirthday(int id) {
        return Observable.fromCallable(() -> new Date());
    }

    public Observable<String> getBaybsPhoto(int id) {
        return Observable.fromCallable(() -> babysPhoto);
    }


//    private List<RealmTip> generalTipList(String id) {
//        List<RealmTip> tipsList = new ArrayList<>();
////        tipsList.add(new TipTicket("Try to ask about the kids day", false, babyBossUri));
////        tipsList.add(new TipTicket("bla bla bla bla", true, booUri));
////        tipsList.add(new TipTicket("aaaaa aaaaaaaaa aaaaaaaaa", false, babyBossUri));
////        tipsList.add(new TipTicket("bbbbbbbbbbbbbbbbbbb bbb bbbb", true, babyBossUri));
////        tipsList.add(new TipTicket("5 bbb bbbb", false, booUri));
////        tipsList.add(new TipTicket("6 bbb bbbb", true, babyBossUri));
//
//        return tipsList;
//    }


    public Observable<String> getFacebookLoginToken() {
        return Observable.fromCallable(() -> {
//                return loginToken.getAccessToken().getToken();
            return TEMP_TOKEN;
        });
    }

    public Observable<Pair<Integer, Integer>> getSaidWordsCount(int id) {

        return Observable.fromCallable(new Callable<Pair<Integer, Integer>>() {
            @Override
            public Pair<Integer, Integer> call() throws Exception {
                return new Pair<>(new Integer(403), new Integer(500));
            }
        });
    }


    private List<Child> mockChildrenList() {
        List<Child> childrenList = new ArrayList<>();
//        childrenList.add(new ChildModel("1", "Sophie", birthday, Uri.parse
// ("https://s-media-cache-ak0.pinimg.com/736x/a2/e0/25/a2e025b30f2e129672b480a54ecc0b6c.jpg")));
//        childrenList.add(new ChildModel("1", "BabyBoss", birthday, Uri.parse("https://resizing
// .flixster.com/PyDVFygd7owZI0jgdJIFQcJ4Ovg=/300x300
// /v1.bjsxMjYxMjY5O2o7MTczODQ7MTIwMDszMDAwOzE1MDA"), false));
//        childrenList.add(new ChildModel("2", "Boo", birthday, Uri.parse("http://animatie.blog
// .nl/files/2009/11/petedocterideemonstersincpicboo.jpg"), false));
//        childrenList.add(new ChildModel("4444", "Stewie", birthday, Uri.parse
// ("http://vignette1.wikia.nocookie.net/family-guy-the-quest-for-stuff/images/e/ea/Stewie
// .png/revision/latest?cb=20140419144429")));
//        childrenList.add(new ChildModel("5555", "Jack Jack", birthday, Uri.parse("http://www
// .writeups.org/wp-content/uploads/Jack-Jack-The-Incredibles-baby-a.jpg")));
        return childrenList;
    }

    public Observable<Child> getLastConnectionChild() {
        return Observable.fromCallable(new Callable<Child>() {
            @Override
            public Child call() throws Exception {

                //// TODO: 6/6/2017 getSpecific ChildModel from DB - this solution is temporery
                for (Child child : children) {
                    if (child.getId() == lastChildConnected) {
                        return child;
                    }
                }
                return children.get(0);
            }
        });
    }

//    public Observable<Boolean> checkIfSignedUp() {
//        return Observable.just(true);
//    }


    public Observable<List<RecordingObj>> getRecordings(int id) {
        return Observable.fromCallable(new Callable<List<RecordingObj>>() {
            @Override
            public List<RecordingObj> call() throws Exception {
                return mockRecordings();
            }


        });
    }

    private List<RecordingObj> mockRecordings() {
        List<RecordingObj> recordings = new ArrayList<>();

        recordings.add(new RecordingObj(1, 1, new Date(1483726548L), 15, 20, 3600000L, false));
        recordings.add(new RecordingObj(2, 2, new Date(1507659612000L), 4, 10, 3600000L, false));
        recordings.add(new RecordingObj(3, 3, new Date(1507659612010L), 4, 10, 3600000L, false));
        recordings.add(new RecordingObj(4, 4, new Date(1507659612000L), 4, 10, 3600000L, false));
        recordings.add(new RecordingObj(5, 5, new Date(1507659612000L), 4, 10, 3600000L, false));

        return recordings;

    }


    private WordsCount mockWordsData() {

        return new WordsCount(new Pair<>(20, 33), new Pair<>(18, 18), new Pair<>(73, 73));

    }

//    private WordsCount mockWordsData(String id, Calendar date) {
//        Calendar birthdayCalTest = Calendar.getInstance();
//        birthdayCalTest.setTime(birthday);
//        birthdayCalTest.add(Calendar.DATE, 7);
//
//        if (date == birthdayCalTest) {
//            return new WordsCount(new Pair<>(18, 33), new Pair<>(2, 18), new Pair<>(20, 73));
//
//        }
//
//        if (date.after(birthdayCalTest)) {
//            return new WordsCount(new Pair<>(30, 33), new Pair<>(16, 18), new Pair<>(60, 73));
//        }
//
//        return new WordsCount(new Pair<>(19, 33), new Pair<>(5, 18), new Pair<>(25, 73));
//
//    }


    public Observable<WordsCount> getTotalWordsCount(int id) {
        return Observable.fromCallable(new Callable<WordsCount>() {
            @Override
            public WordsCount call() throws Exception {
                return mockWordsData();
            }


        });
    }


    public Observable<List<CalendarWordsObj>> getCalendarData(int id) {
        return Observable.fromCallable(new Callable<List<CalendarWordsObj>>() {
            @Override
            public List<CalendarWordsObj> call() throws Exception {
                return mockCalendarData();
            }


        });
    }

    private List<CalendarWordsObj> mockCalendarData() {
        List<CalendarWordsObj> calendarData = new ArrayList<>();
        Calendar birthdayCal = Calendar.getInstance();
        birthdayCal.setTime(birthday);
        Calendar todaysDate = Calendar.getInstance();

        while (!birthdayCal.after(todaysDate)) {
            Date targetDay = birthdayCal.getTime();
            calendarData.add(new CalendarWordsObj(targetDay, 50, 100, false, mockRecordings()));

            birthdayCal.add(Calendar.DATE, 1);

        }

        return calendarData;

    }

    public Observable<List<String>> getLanguagesList() {
        return Observable.fromCallable(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return mockLanguageData();
            }


        });
    }

    private List<String> mockLanguageData() {
        List<String> lanuages = new ArrayList<>();
        lanuages.add("English");
        lanuages.add("Russian");
        lanuages.add("Hebrew");
        lanuages.add("Spanish");
        lanuages.add("French");
        lanuages.add("German");

        return lanuages;
    }

    public Observable<List<FourWordsObj>> getFavoriteWords(int id) {
        return Observable.fromCallable(new Callable<List<FourWordsObj>>() {
            @Override
            public List<FourWordsObj> call() throws Exception {
                return mockFavoritesWords();
            }


        });
    }

    private List<FourWordsObj> mockFavoritesWords() {
        List<FourWordsObj> favoritesWords = new ArrayList<>();
        favoritesWords.add(new FourWordsObj("House", "Puppy", "Spoon", "Apple"));
        favoritesWords.add(new FourWordsObj("Smile", "Book", "Truck", "Zoom"));

        return favoritesWords;
    }

    public Observable<List<SpecialWords>> getNewWords(int id) {
        return Observable.fromCallable(new Callable<List<SpecialWords>>() {
            @Override
            public List<SpecialWords> call() throws Exception {
                return mockNewWords();
            }


        });

    }

    public Observable<List<SpecialWords>> getAdvanceWords(int id) {
        return Observable.fromCallable(new Callable<List<SpecialWords>>() {
            @Override
            public List<SpecialWords> call() throws Exception {
                return mockAdvanceWords();
            }


        });

    }


    public Observable<List<SpecialWords>> getOtherWords(int id) {
        return Observable.fromCallable(new Callable<List<SpecialWords>>() {
            @Override
            public List<SpecialWords> call() throws Exception {
                return mockOtherWords();
            }


        });

    }

    private List<SpecialWords> mockNewWords() {
        List<String> advice = new ArrayList<>();
        advice.add("Talk to Joe about trees today");
        advice.add("Try to point to a tree");
        advice.add("Talk about the colors of a tree and other things like leaves or nests");


        List<SpecialWords> newWords = new ArrayList<>();
        newWords.add(new SpecialWords("New", "Apple", 4, 18, 41, advice));
        newWords.add(new SpecialWords("New", "Spoon", 18, 15, 20, advice));
        newWords.add(new SpecialWords("New", "Book", 2, 16, 10, advice));
        newWords.add(new SpecialWords("New", "Truck", 9, 12, 5, advice));

        return newWords;
    }


    private List<SpecialWords> mockAdvanceWords() {
        List<SpecialWords> newWords = new ArrayList<>();
        newWords.add(new SpecialWords("Advance", "Cat", 14, 9, 41, null));
        newWords.add(new SpecialWords("Advance", "Puppy", 3, 10, 30, null));

        return newWords;
    }

    private List<SpecialWords> mockOtherWords() {
        List<SpecialWords> newWords = new ArrayList<>();
        newWords.add(new SpecialWords("Other", "Birthday", 1, 18, 50, null));
        newWords.add(new SpecialWords("Other", "Interesting", 6, 19, 20, null));

        return newWords;
    }

    public Single<RealmUser> getUserDetailsRx() {
        return Single.fromCallable(this::getUserDetails);
    }


    private RealmUser getUserDetails() {
        Realm realm = Realm.getDefaultInstance();
        RealmUser realmUser = realm.where(RealmUser.class).findFirst();
        RealmUser response = new RealmUser(realmUser);
        realm.close();
        return response;
    }


    public Completable saveTokenRx(String token) {
        return Completable.fromAction(() -> saveToken(token));
    }

    private void saveToken(String token) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            RealmToken realmToken = new RealmToken(token);
            realm1.copyToRealmOrUpdate(realmToken);
        });
        realm.close();
    }


    public Single<RealmToken> getTokenRx() {
        return Single.fromCallable(() -> getToken())
                     .subscribeOn(Schedulers.io());
    }

    private RealmToken getToken() {
        Realm realm = Realm.getDefaultInstance();
        RealmToken token = realm.where(RealmToken.class).findFirst();
        RealmToken response = new RealmToken(token);
        realm.close();
        return response;
    }


    public Single<Integer> countChildren() {
        return Single.fromCallable(() -> {
            Realm realm = Realm.getDefaultInstance();
            int size = realm.where(RealmChild.class).findAll().size();
            realm.close();
            return size;
        });
    }
}
