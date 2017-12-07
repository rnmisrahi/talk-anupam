package com.maatayim.talklet.repository;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * Created by Sophie on 7/18/2017
 */

public class AsyncCloudinaryUploader extends AsyncTask<Uri, Void, Void> {

    public static final String CLOUD_NAME = "talklet";
    private static final String API_KEY = "523964439529678";
    private static final String API_SECRET = "nxeP6TdBiB3yMPAHkQ8cf1rIc5A";
//    public static final String URL_START = "https://api.cloudinary.com/v1_1/talklet/image/upload/";
    public static final String URL_START = "http://res.cloudinary.com/talklet/image/upload/";
    public static final String TAG = "uploadPhotoToCloud: ";

    private final String uniqueImagePath;
    private Context context;


    public AsyncCloudinaryUploader(Context context, String uniqueImagePath) {
        this.context = context;
        this.uniqueImagePath = uniqueImagePath;
    }


    public static String uploadPhotoToCloud(Context context, Uri[] uris) {
        String cloudUrlKey = UUID.randomUUID().toString();
        Log.d(TAG, cloudUrlKey);
        new AsyncCloudinaryUploader(context, cloudUrlKey).execute(uris);
        return URL_START + cloudUrlKey + ".jpg";
    }


    @Override
    protected Void doInBackground(Uri... urls) {
        try {

            Map<String, String> config = new HashMap<>();
            config.put("cloud_name", CLOUD_NAME);
            config.put("api_key", API_KEY);
            config.put("api_secret", API_SECRET);
            Cloudinary cloudinary = new Cloudinary(config);
            try {
                Map options = ObjectUtils.asMap("public_id", uniqueImagePath);
                InputStream imageStream = context.getContentResolver().openInputStream(urls[0]);
                if (imageStream != null) {
                    Uploader uploader = cloudinary.uploader();
                    uploader.upload(imageStream, options);
                    Log.d(TAG, uploader.getUploadUrl(options));
                    imageStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }


    public static String uploadBitmapToCloud(Context context, Bitmap bitmap) {
        return uploadPhotoToCloud(context, new Uri[]{storeBitmap(context, bitmap)});
    }

    public static Uri storeBitmap(Context context, Bitmap bitmap) {
        String path = Environment.getExternalStorageDirectory().toString();
        OutputStream fOut = null;
        Integer counter = 0;
        File file = new File(path,
                "temp.png"); // the File to save , append increasing numeric counter
        // to prevent files from getting overwritten.
        try {
            fOut = new FileOutputStream(file);
            Bitmap pictureBitmap = Bitmap.createBitmap(bitmap); // obtaining the Bitmap
            pictureBitmap.compress(Bitmap.CompressFormat.JPEG,
                    85,
                    fOut); // saving the Bitmap to a file compressed as a JPEG
            // with 85% compression rate
            fOut.flush(); // Not really required
            fOut.close(); // do not forget to close the stream
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(),
                    file.getName(),
                    file.getName());
            return Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
