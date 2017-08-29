package com.maatayim.talklet.screens.loginactivity.signup;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.maatayim.talklet.MainActivity;
import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.events.AddFragmentEvent;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.baseline.events.AddLoginFragmentEvent;
import com.maatayim.talklet.screens.loginactivity.signup.choosephoto.ChoosePhotoFragment;
import com.maatayim.talklet.screens.loginactivity.signup.dagger.SignupModule;
import com.maatayim.talklet.screens.loginactivity.signup.events.DisplayPhotoEvent;
import com.maatayim.talklet.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusException;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Sophie on 5/22/2017
 */

public class SignupFragment extends TalkletFragment implements SignupContract.View {


    public static final String IS_LOGIN_ACTIVITY = "isFromLogin";

    @BindView(R.id.title_fill_details)
    TextView fragmentTitle;

    @BindView(R.id.camera_image)
    CircleImageView babysPhoto;

    @BindView(R.id.name_edit_text)
    EditText name;

    @BindView(R.id.birthday_text_view)
    TextView birthday;

    @Inject
    SignUpPresenter presenter;

    private Calendar birthdayDate = null;
    private boolean isFromLogin;
    private String babysPhotoUrl;

    public static SignupFragment newInstance(boolean isFromLogin) {

        Bundle args = new Bundle();
        args.putBoolean(IS_LOGIN_ACTIVITY, isFromLogin);
        SignupFragment fragment = new SignupFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isFromLogin = getArguments().getBoolean(IS_LOGIN_ACTIVITY);
        }
        ((TalkletApplication) getActivity().getApplication()).getAppComponent().plus(new SignupModule(this)).inject(this);
        EventBus.getDefault().register(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        ButterKnife.bind(this, view);


        if (!isFromLogin){
            setTitle(getString(R.string.add_another_kid));
            fragmentTitle.setVisibility(View.GONE);
        }else{
            fragmentTitle.setVisibility(View.VISIBLE);
        }

        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        // Unregister
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

   @OnClick(R.id.camera_image)
   public void onChoosePhotoClick(){
       if(isFromLogin){
           EventBus.getDefault().post(new AddLoginFragmentEvent(new ChoosePhotoFragment()));
       }else{
           EventBus.getDefault().post(new AddFragmentEvent(new ChoosePhotoFragment()));
       }

   }




    @OnClick({R.id.birthday_text_view, R.id.calendar_intent})
    public void onSetChildsBirthdayClick(){
        hideKeyboard(name);
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

    @Override
    public void onDataReceived(io.reactivex.Observable<Uri> uri) {
        Glide.with(getContext()).load(uri).centerCrop().into(babysPhoto);
    }

    private static final String TAG = "SignupFragment";
    @Subscribe
    public void onSetPhotoEvent(DisplayPhotoEvent event){
        babysPhotoUrl = event.getPhotoUrl();
        Glide.with(getContext())
                .load(event.getPhoto())
                .listener(new RequestListener<Uri, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, Uri model, Target<GlideDrawable> target, boolean isFirstResource) {
                        Log.d(TAG, "onException: ");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, Uri model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        Log.d(TAG, "onResourceReady: ");
                        return false;
                    }
                })
                .centerCrop()
                .into(babysPhoto);

    }


    @OnClick(R.id.confirm_signup_details)
    public void onConfirmClick(){
        if (!checkAllDetailsWereFilled()){
            Toast.makeText(getContext(), "Please fill all the details", Toast.LENGTH_SHORT).show();
        }else{
            presenter.saveSignUpDetails(name.getText().toString(), birthdayDate.getTime(), babysPhotoUrl);
        }
    }

    private boolean checkAllDetailsWereFilled() {
        if (name.getText().toString().equals("") || birthdayDate == null || babysPhotoUrl == null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void  onDataSaveSuccess(){
        if(isFromLogin) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }else{
           getActivity().onBackPressed();
        }
    }

    @Override
    public void onDataSaveFailure(){
        Toast.makeText(getContext(), "Save data failed", Toast.LENGTH_SHORT).show();
    }



    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
