package com.maatayim.talklet.screens.login.signup;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maatayim.talklet.MainActivity;
import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.TalkletFragment;
import com.maatayim.talklet.baseline.events.AddLoginFragmentEvent;
import com.maatayim.talklet.repository.LocalData;
import com.maatayim.talklet.repository.RemoteData;
import com.maatayim.talklet.repository.RepositoryImpl;
import com.maatayim.talklet.screens.login.signup.choosephoto.ChoosePhotoSourceFragment;
import com.maatayim.talklet.screens.login.signup.events.DisplayPhotoEvent;
import com.maatayim.talklet.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Sophie on 5/22/2017.
 */

public class SignupFragment extends TalkletFragment implements SignupContract.View {


    @BindView(R.id.camera_image)
    CircleImageView babysPhoto;

    @BindView(R.id.name_edit_text)
    EditText name;

    @BindView(R.id.birthday_edit_text)
    EditText birthday;

    private SignUpPresenter presenter;

    private Calendar birthdayDate = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        ButterKnife.bind(this, view);
        presenter = new SignUpPresenter(this, new RepositoryImpl(new LocalData(), new RemoteData()));
        presenter.checkIfBabysPhotoExists();
        EventBus.getDefault().register(this);
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
       EventBus.getDefault().post(new AddLoginFragmentEvent(new ChoosePhotoSourceFragment()));
   }


    @OnClick(R.id.confirm_signup_details)
    public void onConfirmClick(){
        presenter.saveSignUpDetails(name.getText().toString(), birthdayDate);

        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick({R.id.birthday_edit_text, R.id.calendar_intent})
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

    @Override
    public void onDataReceived(io.reactivex.Observable<Uri> uri) {
        Glide.with(getContext()).load(uri).centerCrop().into(babysPhoto);
    }

    @Override
    public void displayNoBirthdayError() {

    }

    @Subscribe
    public void onSetPhotoEvent(DisplayPhotoEvent event){
        Glide.with(getContext()).load(event.getPhoto()).centerCrop().into(babysPhoto);
    }

}
