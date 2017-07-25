package com.maatayim.talklet.screens.loginactivity.signup.choosephoto;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.repository.AsyncCloudinaryUploader;
import com.maatayim.talklet.screens.loginactivity.signup.choosephoto.dagger.ChoosePhotoModule;
import com.maatayim.talklet.screens.loginactivity.signup.events.DisplayPhotoEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import javax.inject.Inject;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Sophie on 5/24/2017
 */

public class ChoosePhotoFragment extends TalkletFragment implements ChoosePhotoContract.View{

    public static final int REQUEST_CODE_PICK_FROM_GALLERY = 101;
    public static final int REQUEST_CODE_TAKE_PHOTO = 102;

    private static final String BABYS_PHOTO = "Baby's Profile Photo";
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1022;

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

//        EventBus.getDefault().register(this);


        return view;
    }

//    @Override
//    public void onDestroy() {
//        // Unregister
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//    }

    public boolean hasPermissions() {
        int permissionCheckWrite = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionCheckRead = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionOpenCamera = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.CAMERA);

        return permissionCheckWrite == android.content.pm.PackageManager.PERMISSION_GRANTED
                && permissionCheckRead == android.content.pm.PackageManager.PERMISSION_GRANTED
                && permissionOpenCamera == android.content.pm.PackageManager.PERMISSION_GRANTED;
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
        if (!hasPermissions()){
            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);
        }else{
            takePhotoWhenPermissionGranted();
        }
    }

    private void takePhotoWhenPermissionGranted(){
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, BABYS_PHOTO);
        imageUri = getContext().getContentResolver()
                .insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, REQUEST_CODE_TAKE_PHOTO);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_CAMERA){
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                takePhotoWhenPermissionGranted();

            } else {
                // permission denied
                Toast.makeText(getContext(), "Can't use camera. Please permit or choose a photo from gallery", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        // other 'case' lines to check for other
        // permissions this app might request
    }


//    @Subscribe
//    public void onPhotoTakenEvent(ActivityResultEvent event){
//        if (event.getResultCode() == RESULT_OK){
//            if (event.getRequestCode() == REQUEST_CODE_TAKE_PHOTO) {
////                presenter.saveImage(imageUri);
//                EventBus.getDefault().post(new DisplayPhotoEvent(imageUri));
//                finish();
//            }
//
//            if (event.getRequestCode() == REQUEST_CODE_PICK_FROM_GALLERY) {
////                presenter.saveImage(event.getData().getData());
//                EventBus.getDefault().post(new DisplayPhotoEvent(event.getData().getData()));
//                finish();
//            }
//        }

//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri[] image = new Uri[1];
        if (resultCode == RESULT_OK){
            if (requestCode == REQUEST_CODE_TAKE_PHOTO) {

                image[0] = imageUri;
                String photoUrl = AsyncCloudinaryUploader.uploadPhotoToCloud(getContext(), image);
//                presenter.saveImage(photoUrl);//Dont think we should save the photo here
                EventBus.getDefault().post(new DisplayPhotoEvent(imageUri, photoUrl));
                finish();
            }

            if (requestCode == REQUEST_CODE_PICK_FROM_GALLERY) {
//                presenter.saveImage(data.getData());


                image[0] = data.getData();
                String photoUrl = AsyncCloudinaryUploader.uploadPhotoToCloud(getContext(), image);
//                presenter.saveImage(photoUrl);
                EventBus.getDefault().post(new DisplayPhotoEvent(data.getData(), photoUrl));
                finish();
            }
        }

    }

    @OnClick(R.id.exit_screen)
    public void onExitClick(){
        finish();
    }
}
