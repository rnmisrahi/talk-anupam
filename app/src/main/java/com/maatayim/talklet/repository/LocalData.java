package com.maatayim.talklet.repository;

import android.net.Uri;
import android.support.v4.util.Pair;

import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.bydate.callendarrv.CalendarWordsObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.dataTab.tabs.general.WordsCount;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.FourWordsObj;
import com.maatayim.talklet.screens.mainactivity.childinfo.favorites.favwords.wordsrv.SpecialWords;
import com.maatayim.talklet.screens.mainactivity.childinfo.generaltab.RecordingObj;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipTicket;
import com.facebook.login.LoginResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Sophie on 5/28/2017
 */

public class LocalData {
    public static final Uri DEFAULT_URI = Uri.parse("https://s-media-cache-ak0.pinimg.com/736x/a2/e0/25/a2e025b30f2e129672b480a54ecc0b6c.jpg");
    //temp child DB
    String babysName;
    Date birthday = mockBirthday(2017, 5, 1);
    String lastChildConnected = "1";

    List<Child> children = mockChildrenList();

    private int currentId = 2;



//    public static final String TEMP_TOKEN = "EAALUm1y1RtcBAAfZCaA91aV9yvbKZCW940qo8gVdGSe1TkVNEgnaRnQt4dgiJft1hvNSs6EfPkLpKqg4MdMLzbT5Api0jY1C9wFP7EmuiJVHf8KejYZBcZAwZAF64wpvfxrZAS5YE2wBbV6SzVyP2gZAOXfsET4JDZCebcF9YJliRw0nCaFZBn24f0mUgYSJ45ql73w1o0YsF7oDMY4RLcC4Q";
    public static final String TEMP_TOKEN = "TEST_TOKEN";
    public static final int DEFAULT_CHILD = 0;
    private Uri babysPhoto = DEFAULT_URI;
    private static LoginResult loginToken;
    private Observable<Integer> lastConnectionChild;
    private Observable<List<String>> languagesList;

    private Date mockBirthday(int year, int month, int day){
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


    public Completable savePersonalSignUpDetails(String name, Date birthday) {
        currentId++;
        children.add(new Child(String.valueOf(currentId), name, birthday, babysPhoto, false));
//        this.babysName = name;
//        this.birthday = birthday;

        return Completable.complete();
    }

    public Completable saveBabysPhoto(Uri photo) {
        this.babysPhoto = photo; //temp
        return Completable.complete();
    }


    public Completable saveFacebookLoginToken(LoginResult loginResult) {
        loginToken = loginResult;
        return Completable.complete();
    }


//    Getters

    public Observable<String> getName(String id) {
        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Sophie";
            }
        });
    }

    public Observable<Date> getBirthday(String id) {
        return Observable.fromCallable(new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return new Date();
            }
        });
    }

    public Observable<Uri> getBaybsPhoto(String id) {
        return Observable.fromCallable(new Callable<Uri>() {
            @Override
            public Uri call() throws Exception {
                return babysPhoto;
            }
        });
    }


    public Observable<List<TipTicket>> getTipsList() {
        return Observable.fromCallable(new Callable<List<TipTicket>>() {
            @Override
            public List<TipTicket> call() throws Exception {
                return generalTipList();
            }
        });
    }

    private List<TipTicket> generalTipList() {
        List<TipTicket> tipsList = new ArrayList<>();
        tipsList.add(new TipTicket("Try to ask about the kids day", false));
        tipsList.add(new TipTicket("bla bla bla bla", true));
        tipsList.add(new TipTicket("aaaaa aaaaaaaaa aaaaaaaaa", false));
        tipsList.add(new TipTicket("bbbbbbbbbbbbbbbbbbb bbb bbbb", true));
        tipsList.add(new TipTicket("5 bbb bbbb", false));
        tipsList.add(new TipTicket("6 bbb bbbb", true));

        return tipsList;
    }


    public Observable<String> getFacebookLoginToken() {
        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
//                return loginToken.getAccessToken().getToken();
                return TEMP_TOKEN;
            }
        });
    }

    public Observable<Pair<Integer, Integer>> getSaidWordsCount(String id) {

        return Observable.fromCallable(new Callable<Pair<Integer, Integer>>() {
            @Override
            public Pair<Integer, Integer> call() throws Exception {
                return new Pair<>(new Integer(403), new Integer(500));
            }
        });
    }

    public Observable<List<Child>> getChildrenList() {
        return Observable.fromCallable(new Callable<List<Child>>() {
            @Override
            public List<Child> call() throws Exception {
                return children;
            }
        });
    }

    private List<Child> mockChildrenList() {
        List<Child> childrenList = new ArrayList<>();
//        childrenList.add(new Child("1", "Sophie", birthday, Uri.parse("https://s-media-cache-ak0.pinimg.com/736x/a2/e0/25/a2e025b30f2e129672b480a54ecc0b6c.jpg")));
        childrenList.add(new Child("1", "BabyBoss", birthday, Uri.parse("https://resizing.flixster.com/PyDVFygd7owZI0jgdJIFQcJ4Ovg=/300x300/v1.bjsxMjYxMjY5O2o7MTczODQ7MTIwMDszMDAwOzE1MDA"), false));
        childrenList.add(new Child("2", "Boo", birthday, Uri.parse("http://animatie.blog.nl/files/2009/11/petedocterideemonstersincpicboo.jpg"), false));
//        childrenList.add(new Child("4444", "Stewie", birthday, Uri.parse("http://vignette1.wikia.nocookie.net/family-guy-the-quest-for-stuff/images/e/ea/Stewie.png/revision/latest?cb=20140419144429")));
//        childrenList.add(new Child("5555", "Jack Jack", birthday, Uri.parse("http://www.writeups.org/wp-content/uploads/Jack-Jack-The-Incredibles-baby-a.jpg")));
        return childrenList;
    }

    public Observable<Child> getLastConnectionChild() {
        return Observable.fromCallable(new Callable<Child>() {
            @Override
            public Child call() throws Exception {

                //// TODO: 6/6/2017 getSpecific Child from DB - this solution is temporery
                for (Child child : children) {
                    if (child.getId().equals(lastChildConnected)) {
                        return child;
                    }
                }
                return children.get(0);
            }
        });
    }

    public Observable<Boolean> checkIfSignedUp() {
        return Observable.just(true);
    }

    public Observable<Child> getChild(final String id) {

        return Observable.fromCallable(new Callable<Child>() {
            @Override
            public Child call() throws Exception {
                for (Child child : children) {
                    if (child.getId().equals(id)) {
                        return child;
                    }
                }
                return children.get(0); //// TODO: 6/6/2017 What default value????
            }
        });
    }

    public Observable<List<RecordingObj>> getRecordings(String id) {
        return Observable.fromCallable(new Callable<List<RecordingObj>>() {
            @Override
            public List<RecordingObj> call() throws Exception {
                return mockRecordings();
            }


        });
    }

    private List<RecordingObj> mockRecordings() {
        List<RecordingObj> recordings = new ArrayList<>();

        recordings.add(new RecordingObj("1111", 1, new Date(1483726548L), new android.util.Pair<Integer, Integer>(15,20), 3600000L, false));
        recordings.add(new RecordingObj("2222", 2, new Date(1507659612000L), new android.util.Pair<Integer, Integer>(4,100), 3600000L, false));
        recordings.add(new RecordingObj("3333", 3, new Date(1507659612010L), new android.util.Pair<Integer, Integer>(4,100), 3600000L, false));
        recordings.add(new RecordingObj("4444", 4, new Date(1507659612000L), new android.util.Pair<Integer, Integer>(4,100), 3600000L,false));
        recordings.add(new RecordingObj("5555", 5, new Date(1507659612000L), new android.util.Pair<Integer, Integer>(4,100), 3600000L, false));

        return recordings;

    }


    private WordsCount mockWordsData(){

        return new WordsCount(new Pair<>(18,33), new Pair<>(2, 18), new Pair<>(20, 73));

    }

    private WordsCount mockWordsData(String id, Calendar date){
        Calendar birthdayCalTest = Calendar.getInstance();
        birthdayCalTest.setTime(birthday);
        birthdayCalTest.add(Calendar.DATE, 7);

        if (date == birthdayCalTest){
            return new WordsCount(new Pair<>(18,33), new Pair<>(2, 18), new Pair<>(20, 73));

        }

        if(date.after(birthdayCalTest)){
            return new WordsCount(new Pair<>(30,33), new Pair<>(16, 18), new Pair<>(60, 73));
        }

        return new WordsCount(new Pair<>(19,33), new Pair<>(5, 18), new Pair<>(25, 73));

    }


    public Observable<WordsCount> getTotalWordsCount(String id) {
        return Observable.fromCallable(new Callable<WordsCount>() {
            @Override
            public WordsCount call() throws Exception {
                return mockWordsData();
            }


        });
    }

    public Observable<List<CalendarWordsObj>> getCalendarData(String id) {
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

        while(!birthdayCal.after(todaysDate)){
            Date targetDay = birthdayCal.getTime();
            calendarData.add(new CalendarWordsObj(targetDay, mockWordsData("id", birthdayCal), false, mockRecordings()));

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

    public Observable<List<FourWordsObj>> getFavoriteWords(String id) {
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

    public Observable<List<SpecialWords>> getNewWords(String id) {
        return Observable.fromCallable(new Callable<List<SpecialWords>>() {
            @Override
            public List<SpecialWords> call() throws Exception {
                return mockNewWords();
            }


        });

    }

    public Observable<List<SpecialWords>> getAdvanceWords(String id) {
        return Observable.fromCallable(new Callable<List<SpecialWords>>() {
            @Override
            public List<SpecialWords> call() throws Exception {
                return mockAdvanceWords();
            }


        });

    }


    public Observable<List<SpecialWords>> getOtherWords(String id) {
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
        newWords.add(new SpecialWords("Advance", "Puppy",3, 10, 30, null));

        return newWords;
    }

    private List<SpecialWords> mockOtherWords() {
        List<SpecialWords> newWords = new ArrayList<>();
        newWords.add(new SpecialWords("Other", "Birthday", 1, 18, 50, null));
        newWords.add(new SpecialWords("Other", "Interesting", 6, 19, 20, null));

        return newWords;
    }
}
