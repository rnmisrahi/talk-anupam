package com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou.injection.AboutYouModule;
import com.maatayim.talklet.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

/**
 * Created by Sophie on 6/18/2017
 */

public class AboutYouFragment extends TalkletFragment implements AboutYouContract.View {

    public static final String EMPTY_LANG = "";
    public static final String WMPTY_STR = "";
    @Inject
    AboutYouContract.Presenter presenter;

    private Calendar birthdayDate = Calendar.getInstance();
    private PopupWindow mDropdown = null;
    private int languageCounter = 1;

    @BindView(R.id.first_name)
    TextView firstName;

    @BindView(R.id.last_name)
    TextView lastName;

    @BindView(R.id.birthday_text_view)
    TextView birthday;

    @BindView(R.id.first_language)
    TextView langugeField1;

    @BindView(R.id.second_language)
    TextView langugeField2;

    @BindView(R.id.third_language)
    TextView langugeField3;

    @BindView(R.id.another_language_button)
    TextView addLanguageButton;

    private List<String> chosenLanguages = new ArrayList<>();



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TalkletApplication) getActivity().getApplication()).getAppComponent().plus(new AboutYouModule(this)).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_you, container, false);
        ButterKnife.bind(this, view);
        setTitle(getString(R.string.about_you_title));
        presenter.getData();


        setListenerOnView(langugeField1, inflater);
        setListenerOnView(langugeField2, inflater);
        setListenerOnView(langugeField3, inflater);


        return view;
    }


    @OnFocusChange({R.id.first_name, R.id.last_name})
    public void hideKeyBoardOnFocuseChanged(View v, boolean hasFocus){
        if (!hasFocus) {
            hideKeyboard(v);
        }
    }


    public void setListenerOnView(final TextView view, final LayoutInflater inflater){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow dropDownMenu = initiatePopupWindowLanguages(inflater, view);
                dropDownMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        view.setCompoundDrawablesWithIntrinsicBounds(0,
                                0,
                                R.drawable.triangle2,
                                0);
                    }
                });
            }
        });
    }



    private PopupWindow initiatePopupWindowLanguages(LayoutInflater inflater, TextView textView) {

        try {
            View layout = inflater.inflate(R.layout.dropdown_lang_menu, null);
            View linearLayout = layout.findViewById(R.id.vendor_filter_linear_layout);
            linearLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            mDropdown = new PopupWindow(layout, textView.getMeasuredWidth(),
                    FrameLayout.LayoutParams.WRAP_CONTENT, true);
            mDropdown.showAsDropDown(textView, 0, 0);
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.triangle2, 0);

//            setLanguageOnView(layout, R.id.language_default);
            presenter.setLanguageList(linearLayout, textView);





        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDropdown;

    }



    @Override
    public void setLanguageOnView(View layout, int statusID, final TextView view){
        View.OnClickListener spinnerListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedProfession = ((TextView) v).getText().toString();
                view.setText(selectedProfession);
                mDropdown.dismiss();
            }
        };

        final TextView item = (TextView) layout.findViewById(statusID);
        item.setOnClickListener(spinnerListener);
    }

    @Override
    public void loadUserDetails(AboutUserObj userDetails) {
        firstName.setText(userDetails.getFirstName());
        lastName.setText(userDetails.getLastName());
        birthdayDate.setTime(userDetails.getBirthday());
        birthday.setText(Utils.getFormattedDate(userDetails.getBirthday()));

        if(userDetails.getLanguage1()!= null){
            langugeField1.setText(userDetails.getLanguage1());
            languageCounter = 1;
        }


        if(userDetails.getLanguage2()!= null) {
            langugeField2.setText(userDetails.getLanguage2());
            langugeField2.setVisibility(View.VISIBLE);
            languageCounter = 2;
        }

        if(userDetails.getLanguage3()!= null) {
            langugeField3.setText(userDetails.getLanguage3());
            langugeField3.setVisibility(View.VISIBLE);
            addLanguageButton.setVisibility(View.GONE);
            languageCounter = 3;
        }
    }

    @Override
    public void onLoadUserDetailsFailure() {

    }

    @Override
    public void onUpdateDataSuccess() {
    }


    @OnClick(R.id.birthday_text_view)
    public void onSetChildsBirthdayClick(){
        setDay(birthday, birthdayDate);
    }



    private void setDay(final TextView dateTextView, final Calendar dateToSave){
        final Calendar date = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date.set(year, month, day);
                dateTextView.setText(Utils.getFormattedDate(date.getTime()));
                dateToSave.set(Calendar.YEAR, year);
                dateToSave.set(Calendar.MONTH, month);
                dateToSave.set(Calendar.DAY_OF_MONTH, day);
            }
        }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
        dialog.show();



    }



    @OnClick(R.id.another_language_button)
    public void onAddLanguageClick(View view){
        if(languageCounter == 1){
            langugeField2.setVisibility(View.VISIBLE);
            languageCounter++;
            return;
        }
        if(languageCounter==2){
            langugeField3.setVisibility(View.VISIBLE);
            languageCounter++;
            view.setVisibility(View.GONE);
            return;
        }
    }


    @OnClick(R.id.save_new_data)
    public void onSaveData() {
        String firstNameStr = firstName.getText().toString();
        String lastNameStr = lastName.getText().toString();
        Date birthdayStr = birthdayDate.getTime();


        AboutUserObj aboutUserObj = new AboutUserObj(firstNameStr, lastNameStr, birthdayStr,
                getLanguage(langugeField1), getLanguage(langugeField2), getLanguage(langugeField3));

        presenter.updateAboutYouData(aboutUserObj);
        finish();

    }

    private String getLanguage(TextView langView){
        String langStr = langView.getText().toString();
        if(langStr.equals(WMPTY_STR)){
            return null;
        }else{
            for (String chosenLanguage : chosenLanguages) {
                if (chosenLanguage.equals(langStr)){
                    return null;
                }
            }
            chosenLanguages.add(langStr);
            return langStr;


        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



}
