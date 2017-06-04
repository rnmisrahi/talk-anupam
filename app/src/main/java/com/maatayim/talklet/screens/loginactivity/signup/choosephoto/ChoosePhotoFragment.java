package com.maatayim.talklet.screens.loginactivity.signup.choosephoto;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.screens.loginactivity.signup.choosephoto.dagger.ChoosePhotoModule;
import com.maatayim.talklet.screens.loginactivity.signup.events.DisplayPhotoEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Sophie on 5/24/2017.
 */

public class ChoosePhotoFragment extends TalkletFragment implements ChoosePhotoContract.View{

    public static final int REQUEST_CODE_PICK_FROM_GALLERY = 101;
    public static final int REQUEST_CODE_TAKE_PHOTO = 102;

    private static final String BABYS_PHOTO = "Baby's Profile Photo";

    private Uri imageUri;

    @Inject
    ChoosePhotoPresenter presenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((TalkletApplication) getActivity().getApplication()).getAppComponent().plus(new ChoosePhotoModule(this)).inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_photo_source, container, false);
        ButterKnife.bind(this, view);

//        presenter = new ChoosePhotoPresenter(this, new RepositoryImpl());
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onDestroy() {
        // Unregister
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public boolean hasPermissions() {
        int permissionCheckWrite = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionCheckRead = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE);

        return permissionCheckWrite == android.content.pm.PackageManager.PERMISSION_GRANTED
                && permissionCheckRead == android.content.pm.PackageManager.PERMISSION_GRANTED;
    }


    @OnClick(R.id.gallery_button)
    public void onGalleryClick(){
        if (hasPermissions()){
            Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
            galleryIntent.setType("image/*");
//            imageUri = getContext().getContentResolver()
//                    .insert(
//                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//            galleryIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(galleryIntent, REQUEST_CODE_PICK_FROM_GALLERY);
        }

    }


    @OnClick(R.id.camera_button)
    public void onCameraClick(){
        if (hasPermissions()){
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, BABYS_PHOTO);
            imageUri = getContext().getContentResolver()
                    .insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(cameraIntent, REQUEST_CODE_TAKE_PHOTO);
        }
    }

    @Subscribe
    public void onPhotoTakenEvent(ActivityResultEvent event){
        if (event.getResultCode() == RESULT_OK){
            if (event.getRequestCode() == REQUEST_CODE_TAKE_PHOTO) {
                presenter.saveImage(imageUri);
                EventBus.getDefault().post(new DisplayPhotoEvent(imageUri));
                finish();
            }

            if (event.getRequestCode() == REQUEST_CODE_PICK_FROM_GALLERY) {
                presenter.saveImage(event.getData().getData());
                EventBus.getDefault().post(new DisplayPhotoEvent(event.getData().getData()));
                finish();
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            if (requestCode == REQUEST_CODE_TAKE_PHOTO) {
                presenter.saveImage(imageUri);
                EventBus.getDefault().post(new DisplayPhotoEvent(imageUri));
                finish();
            }

            if (requestCode == REQUEST_CODE_PICK_FROM_GALLERY) {
                presenter.saveImage(data.getData());
                EventBus.getDefault().post(new DisplayPhotoEvent(data.getData()));
                finish();
            }
        }

    }

    @OnClick(R.id.exit_screen)
    public void onExitClick(){
        finish();
    }
}
