package com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutyou.injection.AboutYouModule;
import com.maatayim.talklet.utils.Utils;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sophie on 6/18/2017.
 */

public class AboutYouFragment extends TalkletFragment implements AboutYouContract.View {

    @Inject
    AboutYouContract.Presenter presenter;

    private Calendar birthdayDate = null;
    private PopupWindow mDropdown = null;
    private int languageCounter = 1;

    @BindView(R.id.birthday_text_view)
    TextView birthday;

    @BindView(R.id.first_language)
    TextView langugeField1;

    @BindView(R.id.second_language)
    TextView langugeField2;

    @BindView(R.id.third_language)
    TextView langugeField3;




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

            layout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            mDropdown = new PopupWindow(layout, textView.getMeasuredWidth(),
                    FrameLayout.LayoutParams.WRAP_CONTENT, true);
            mDropdown.showAsDropDown(textView, 0, 0);
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.triangle2, 0);

//            setLanguageOnView(layout, R.id.language_default);
            presenter.setLanguageList(layout, textView);





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



    @OnClick(R.id.birthday_text_view)
    public void onSetChildsBirthdayClick(){
        birthdayDate = Calendar.getInstance(); /// todo is it????
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
}
