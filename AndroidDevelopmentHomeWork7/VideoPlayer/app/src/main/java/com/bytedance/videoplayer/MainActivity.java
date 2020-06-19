package com.bytedance.videoplayer;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;


public class MainActivity extends AppCompatActivity {

    private SurfaceHolder holder;
    private MediaPlayer player;
    private Button button;
    private SeekBar seekBar;
    private SurfaceView surfaceView;
    private TextView progressTextView;
    private static int now;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView=findViewById(R.id.surfaceview);
        button=findViewById(R.id.buttonPlay_Pause);
        seekBar=findViewById(R.id.seekBar);
        progressTextView=findViewById(R.id.timeProgress);

        player=new MediaPlayer();


        try {
            player.setDataSource(getResources().openRawResourceFd(R.raw.bytedance));
            holder=surfaceView.getHolder();
            holder.addCallback(new PlayerCallBack());
            player.prepare();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    player.start();

                    Log.d("tt", "onPrepared: ");
                }
            });
            player.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                @Override
                public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                    //changeVideoSize(mp);
                    Log.d("tt", "onVideoSizeChanged: ");
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

        final int duration=player.getDuration();
        seekBar.setMax(duration);


        class mythread extends Thread{
            @Override
            public void run() {
                super.run();
                while (seekBar.getProgress()<=seekBar.getMax()){
                    int current=player.getCurrentPosition();
                    now=current;
                    seekBar.setProgress(current);
                    try{
                        Thread.sleep(1000);
                        progressTextView.setText((int)(current/1000+1)+":"+(int)(duration/1000));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }

        new mythread().start();

        //进度条监听
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress=seekBar.getProgress();
                player.seekTo(progress);
            }
        });

        //按钮监听
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.isPlaying()){
                    player.pause();
                    button.setText("PLAY");
                }else{
                    player.start();
                    button.setText("PAUSE");
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(player!=null){
            player.pause();
            button.setText("PLAY");
        }
    }

    private class PlayerCallBack implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            player.setDisplay(holder);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }

    private boolean portrait;


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        portrait = newConfig.orientation == Configuration.ORIENTATION_PORTRAIT;
        tryFullScreen(!portrait);
    }

    private void tryFullScreen(boolean fullScreen) {
        if (MainActivity.this instanceof AppCompatActivity) {
            ActionBar supportActionBar = ((AppCompatActivity) MainActivity.this).getSupportActionBar();
            if (supportActionBar != null) {
                ViewGroup.LayoutParams layoutParams = surfaceView.getLayoutParams();

                if (fullScreen) {
                    supportActionBar.hide();
                    layoutParams.height =WindowManager.LayoutParams.WRAP_CONTENT;

                } else {
                    supportActionBar.show();
                    layoutParams.height =((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                            250,
                            getResources().getDisplayMetrics()));
                }
                player.seekTo(now);
            }
        }
        setFullScreen(fullScreen);
    }


    private void setFullScreen(boolean fullScreen) {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        if (fullScreen) {
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(attrs);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

    }

}
