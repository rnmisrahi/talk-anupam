package com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutbabyf;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.sidemenu.settings.aboutbabyf.injection.AboutBabyModule;
import com.maatayim.talklet.utils.Utils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Sophie on 6/27/2017
 */

public class AboutBabyFragment extends TalkletFragment implements AboutBabyContract.View {

    private String babyId;
    public static final String ARG_ID = "babyId";

    @Inject
    AboutBabyContract.Presenter presenter;

    @BindView(R.id.title_fill_details)
    TextView title;

    @BindView(R.id.camera_image)
    CircleImageView babysImg;

    @BindView(R.id.upload_photo)
    TextView uploadPhotoText;

    @BindView(R.id.name_edit_text)
    EditText babysName;

    @BindView(R.id.birthday_text_view)
    TextView birthday;

    @BindView(R.id.confirm_signup_details)
    Button confirmButton;

    public static AboutBabyFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString(ARG_ID, id);
        AboutBabyFragment fragment = new AboutBabyFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            babyId = getArguments().getString(ARG_ID);
        }
        ((TalkletApplication) getActivity().getApplication()).getAppComponent().plus(new AboutBabyModule(this)).inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        ButterKnife.bind(this, view);
        presenter.getData(babyId);

        return view;
    }

    @Override
    public void onDataReceived(Child child) {
        setTitle(getString(R.string.about_kid, child.getName()));
        title.setVisibility(View.GONE);
        Glide.with(getContext()).load(child.getImg()).centerCrop().into(babysImg);
        uploadPhotoText.setText(getString(R.string.upload_new_photo));
        babysName.setText(child.getName());
        birthday.setText(Utils.getFormattedDate(child.getBirthday()));
        confirmButton.setVisibility(View.GONE);

    }

    @Override
    public void onChildLoadError() {

    }
}
