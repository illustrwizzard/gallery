package com.chat.androidtutorialex;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoFlowEX extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE =1;
    VideoView videoView;
    Button playbutton;

    @SuppressLint("MissingInflatedId")
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_flow_ex);
        videoView =(VideoView)findViewById(R.id.videoView1);
        playbutton=findViewById(R.id.playbutton);

        ActivityCompat.requestPermissions(this,
                new String[]{READ_EXTERNAL_STORAGE,
                        Manifest.permission.MANAGE_EXTERNAL_STORAGE}, 1);


        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (SDK_INT >= Build.VERSION_CODES.R) {
                    if (!Environment.isExternalStorageManager()){
                        //Toast.makeText(VideoFlowEX.this, "Allow permission for storage access!", Toast.LENGTH_SHORT).show();

                        MediaController mediaController= new MediaController(VideoFlowEX.this);
                        mediaController.setAnchorView(videoView);

                        //specify the location of media file
                        Uri uri=Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/media/1.mp4");
                        Log.w(".........dddd", String.valueOf(uri));

                        //Setting MediaController and URI, then starting the videoView
                        videoView.setMediaController(mediaController);
                        videoView.setVideoURI(uri);
                        videoView.requestFocus();
                        videoView.start();



                    }
                    else{
                        Toast.makeText(VideoFlowEX.this, "Allow permission", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }




}