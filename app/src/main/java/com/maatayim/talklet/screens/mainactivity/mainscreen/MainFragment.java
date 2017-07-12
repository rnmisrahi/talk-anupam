package com.maatayim.talklet.screens.mainactivity.mainscreen;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.maatayim.talklet.R;
import com.maatayim.talklet.baseline.TalkletApplication;
import com.maatayim.talklet.baseline.events.AddFragmentEvent;
import com.maatayim.talklet.baseline.events.DowmloadCompleteEvent;
import com.maatayim.talklet.baseline.fragments.TalkletFragment;
import com.maatayim.talklet.screens.Child;
import com.maatayim.talklet.screens.mainactivity.mainscreen.children.ChildrenAdapter;
import com.maatayim.talklet.screens.mainactivity.mainscreen.dagger.MainModule;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipTicket;
import com.maatayim.talklet.screens.mainactivity.mainscreen.generalticket.TipsAdapter;
import com.maatayim.talklet.screens.mainactivity.record.MediaRecordWrapper;
import com.maatayim.talklet.screens.mainactivity.record.RecordingFragment;
import com.viewpagerindicator.CirclePageIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.media.MediaRecorder.AudioSource.MIC;

/**
 * Created by Sophie on 5/26/2017
 */

public class MainFragment extends TalkletFragment implements MainContract.View {
    public static final String EMPTY_TITLE = "";
    public static final int DEFAULT_GAP = 120;
    public static final int HALF_GAP = DEFAULT_GAP / 2;
    public static final int TOP_MARGIN = 10;
    public static final String TAG = "rec tag";
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1234;
    public static final String RECORDING_FILE_3GPP = "recordingFile.3gpp";

    @Inject
    MainContract.Presenter presenter;

    @BindView(R.id.tips_view_pager)
    ViewPager tipsViewPagerMain;

    @BindView(R.id.tips_view_pager_indicator)
    CirclePageIndicator pageIndicatorMain;

    @BindView(R.id.words_progress_bar)
    ProgressBar wordsProgressBar;

    @BindView(R.id.total_words_value)
    TextView totalWords;

    @BindView(R.id.start_bar_value)
    TextView startVal;

    @BindView(R.id.end_bar_value)
    TextView maxWordsNum;

    @BindView(R.id.total_words_title)
    TextView totalWordsTitle;

    @BindView(R.id.children_recyclerView)
    RecyclerView childrenRecyclerView;
    private MediaRecorder mediaRecorder;
    private File recFile;

    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    AudioRecord recorder;
    private int sampleRate = 16000 ; // 44100 for music
    private int channelConfig = AudioFormat.CHANNEL_CONFIGURATION_MONO;
    private int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
    int minBufSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat);
    private boolean status = true;
    public byte[] buffer;
    public static DatagramSocket socket;
    private int port=50005;
    TipsAdapter pagerAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TalkletApplication) getActivity().getApplication()).getAppComponent().plus(new MainModule(this)).inject(this);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_main, container, false);
        ButterKnife.bind(this, view);
        setTitle(EMPTY_TITLE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            } else {
                Log.d("Home", "Already granted access");
//                initializeView(v);
            }
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        pagerAdapter = null;
    }

    @Override
    public void updateTipsViewPager(List<TipTicket> ticketList, boolean isMoreThanOneChild) {
        tipsViewPagerMain.setPadding(DEFAULT_GAP, TOP_MARGIN, DEFAULT_GAP, TOP_MARGIN);
        tipsViewPagerMain.setClipToPadding(false);
        tipsViewPagerMain.setPageMargin(HALF_GAP);

        initializeViewPager(ticketList, isMoreThanOneChild);
    }

    @Override
    public void updateWordsCount(int numOfWords, int maxNumOfWords) {
        maxWordsNum.setText(String.valueOf(maxNumOfWords));
        totalWords.setText(getString(R.string.progress_bar_value,
                String.valueOf(numOfWords),
                String.valueOf(maxNumOfWords)));
        wordsProgressBar.setMax(maxNumOfWords);
        wordsProgressBar.setProgress((numOfWords * 100) / maxNumOfWords);
    }



    private void initializeViewPager(List<TipTicket> ticketList, boolean isMoreThanOneChild) {

        pagerAdapter = new TipsAdapter(
                getChildFragmentManager(), ticketList, false, isMoreThanOneChild);
        tipsViewPagerMain.setAdapter(pagerAdapter);
        pageIndicatorMain.setViewPager(tipsViewPagerMain);

    }

    @Override
    public void setChildrenRecyclerView(List<MainScreenChild> childrenList) {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        childrenRecyclerView.setLayoutManager(linearLayoutManager);
        ChildrenAdapter childrenAdapter = new ChildrenAdapter(childrenList);
        childrenRecyclerView.setAdapter(childrenAdapter);

        if(childrenList.size() > 1){
            wordsProgressBar.setVisibility(View.GONE);
            totalWordsTitle.setVisibility(View.GONE);
            startVal.setVisibility(View.GONE);
        }
    }


    @Override
    public void onChildLoadError() {
        Toast.makeText(getContext(), "Failed load child", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisplayChildrenError() {
        Toast.makeText(getContext(), "Failed load children", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWordsCountLoadError() {
        Toast.makeText(getContext(), "Failed load words count", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTipsLoadError() {
        Toast.makeText(getContext(), "Failed load Tips", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.recording_mic)
    public void onRecordClick(){
        MediaRecordWrapper mediaRecordWrapper = startRecording();
//        startStreaming();
        EventBus.getDefault().post(new AddFragmentEvent(RecordingFragment.newInstance(mediaRecordWrapper)));
    }


    public void startStreaming() {

        Thread streamThread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {

                    DatagramSocket socket = new DatagramSocket();
                    Log.d("VS", "Socket Created");

                    byte[] buffer = new byte[minBufSize];

                    Log.d("VS","Buffer created of size " + minBufSize);
                    DatagramPacket packet;

                    final InetAddress destination = InetAddress.getByName("192.168.1.5");
                    Log.d("VS", "Address retrieved");


                    recorder = new AudioRecord(MediaRecorder.AudioSource.MIC,sampleRate,channelConfig,audioFormat,minBufSize*10);
                    Log.d("VS", "Recorder initialized");

                    recorder.startRecording();


                    while(status == true) {


                        //reading data from MIC into buffer
                        minBufSize = recorder.read(buffer, 0, buffer.length);

                        //putting buffer in the packet
                        packet = new DatagramPacket (buffer,buffer.length,destination,port);

                        socket.send(packet);
                        System.out.println("MinBufferSize: " +minBufSize);


                    }



                } catch(UnknownHostException e) {
                    Log.e("VS", "UnknownHostException");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("VS", "IOException");
                }
            }

        });
        streamThread.start();
    }




    public MediaRecordWrapper startRecording(){
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MIC);

//        if (AudioManager.getProperty("PROPERTY_SUPPORT_AUDIO_SOURCE_UNPROCESSED")){
//            mediaRecorder.setAudioSource(UNPROCESSED);
//        }

        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        File file = new File(path, "/" + RECORDING_FILE_3GPP);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaRecorder.setOutputFile(file.getAbsolutePath());
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        try{
            mediaRecorder.prepare();
        }catch (IOException exception){
            Log.d(TAG, "startRecording: exception in prepare record");
        }

        return new MediaRecordWrapper(mediaRecorder);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("Home", "Permission Granted");
//                    initializeView(v);
                } else {
                    Log.d("Home", "Permission Failed");
                    Toast.makeText(getActivity().getBaseContext(), "You must allow permission record audio to your mobile device.", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
            }
            // Add additional cases for other permissions you may have asked for
        }
    }

    // for testing only!!
    @OnClick(R.id.play_recording)
    public void onPlayRecClick(){
        if(!isPlaying) {
            mediaPlayer = new MediaPlayer();
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);

            try {
                mediaPlayer.setDataSource(path.getPath() + "/" + RECORDING_FILE_3GPP);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            isPlaying = true;
            return;
        }else{
            isPlaying = false;
            return;
        }

    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        // Unregister
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe
    public void onDownloadComplete(DowmloadCompleteEvent event){
        if(event.isDownloadComplete()){
            presenter.getData();
        }
    }




}
