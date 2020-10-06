package ducthuan.com.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import ducthuan.com.appnhac.Adapter.ViewPagerPlayListNhac;
import ducthuan.com.appnhac.Fragment.DiaNhacFragment;
import ducthuan.com.appnhac.Fragment.PlayDanhSachBaiHatFragment;
import ducthuan.com.appnhac.Model.BaiHat;
import ducthuan.com.appnhac.R;

public class PlayNhacActivity extends AppCompatActivity {

    Toolbar toolbarPlayNhac;
    TextView txtTimeSong, txtTotalTimeSong;
    SeekBar seekbarSong;
    ImageButton imgShuffle, imgPreview, imgPlay,imgNext,imgRepeat;
    ViewPager viewpagerPlayNhac;
    public static ArrayList<BaiHat>baiHats = new ArrayList<>();
    public static ViewPagerPlayListNhac viewPagerPlayListNhac;

    DiaNhacFragment diaNhacFragment;
    PlayDanhSachBaiHatFragment playDanhSachBaiHatFragment;

    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        GetIntent();
        AnhXa();
        Init();
        eventClick();

    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(viewPagerPlayListNhac.getItem(1)!=null){
                    if(baiHats.size() > 0){
                      diaNhacFragment.PhatNhac(baiHats.get(0).getHinhbaihat());
                      handler.removeCallbacks(this);
                    }else {
                        handler.postDelayed(this,300);
                    }
                }
            }
        },500);
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgPlay.setImageResource(R.drawable.iconplay);
                }else {
                    mediaPlayer.start();
                    imgPlay.setImageResource(R.drawable.iconpause);
                }
            }
        });

        imgRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(repeat == false){
                    if(checkrandom == true){
                        checkrandom = false;
                        imgShuffle.setImageResource(R.drawable.iconsuffle);
                        imgRepeat.setImageResource(R.drawable.iconsyned);
                    }
                    imgRepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                }else {
                    imgRepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });

        imgShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkrandom == false){
                    if(repeat == true){
                        repeat = false;
                        imgShuffle.setImageResource(R.drawable.iconshuffled);
                        imgRepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imgShuffle.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                }else {
                    imgShuffle.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });

        seekbarSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(baiHats.size()>0){
                    if(mediaPlayer.isPlaying() || mediaPlayer!= null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(position < baiHats.size()){
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if(repeat==true){
                            if(position==0){
                                position = baiHats.size();
                            }
                            position-=1;
                        }
                        if(checkrandom==true){
                            Random random = new Random();
                            int index = random.nextInt(baiHats.size());
                            if(index == position){
                                position = index-1;
                            }
                            position = index;
                        }
                        if (position>baiHats.size()-1){
                            position = 0;
                        }
                        new PlayMp3().execute(baiHats.get(position).getLinkbaihat());
                        diaNhacFragment.PhatNhac(baiHats.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(baiHats.get(position).getTenbaihat());
                        UpdateTime();
                    }
                }
                imgPreview.setClickable(false);
                imgNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPreview.setClickable(true);
                        imgNext.setClickable(true);
                    }
                },5000);
            }
        });

        imgPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(baiHats.size()>0){
                    if(mediaPlayer.isPlaying() || mediaPlayer!= null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(position < baiHats.size()){
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position--;
                        if (position<0){
                            position = baiHats.size()-1;
                        }
                        if(repeat==true){
                            position+=1;
                        }
                        if(checkrandom==true){
                            Random random = new Random();
                            int index = random.nextInt(baiHats.size());
                            if(index == position){
                                position = index-1;
                            }
                            position = index;
                        }

                        new PlayMp3().execute(baiHats.get(position).getLinkbaihat());
                        diaNhacFragment.PhatNhac(baiHats.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(baiHats.get(position).getTenbaihat());
                        UpdateTime();
                    }
                }
                imgPreview.setClickable(false);
                imgNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPreview.setClickable(true);
                        imgNext.setClickable(true);
                    }
                },5000);
            }
        });

    }

    private void Init() {
        setSupportActionBar(toolbarPlayNhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlayNhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                mediaPlayer.stop();
                baiHats.clear();
            }
        });
        toolbarPlayNhac.setTitleTextColor(Color.WHITE);

        diaNhacFragment = new DiaNhacFragment();
        playDanhSachBaiHatFragment = new PlayDanhSachBaiHatFragment();
        viewPagerPlayListNhac = new ViewPagerPlayListNhac(getSupportFragmentManager());
        viewPagerPlayListNhac.addFragment(playDanhSachBaiHatFragment);
        viewPagerPlayListNhac.addFragment(diaNhacFragment);
        viewpagerPlayNhac.setAdapter(viewPagerPlayListNhac);

        diaNhacFragment= (DiaNhacFragment) viewPagerPlayListNhac.getItem(1);

        if(baiHats.size()>0){
            getSupportActionBar().setTitle(baiHats.get(0).getTenbaihat());
            new PlayMp3().execute(baiHats.get(0).getLinkbaihat());
            imgPlay.setImageResource(R.drawable.iconpause);
        }
    }

    private void AnhXa() {
        toolbarPlayNhac = findViewById(R.id.toolbarPlayNhac);
        txtTimeSong = findViewById(R.id.txtTimeSong);
        txtTotalTimeSong = findViewById(R.id.txtTotalTimeSong);
        seekbarSong = findViewById(R.id.seekbarSong);
        imgShuffle = findViewById(R.id.imgShuffle);
        imgPreview = findViewById(R.id.imgPreview);
        imgPlay = findViewById(R.id.imgPlay);
        imgNext = findViewById(R.id.imgNext);
        imgRepeat = findViewById(R.id.imgRepeat);
        viewpagerPlayNhac = findViewById(R.id.viewpagerPlayNhac);
    }

    private void GetIntent() {
        Intent intent = getIntent();
        baiHats.clear();
        if(intent!=null){
            if(intent.hasExtra("itembaihat")){
                BaiHat baiHat = intent.getParcelableExtra("itembaihat");
                baiHats.add(baiHat);
            }
            if(intent.hasExtra("baihats")){
                ArrayList<BaiHat>mangBaiHat = intent.getParcelableArrayListExtra("baihats");
                baiHats = mangBaiHat;
            }
        }
    }

    class PlayMp3 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                //1 so truong hop khi chay vao media player bat dong bo, khi dua vao 1 ca khuc neu cap nhap lau se co the nhay vao 1 cai j do gay loi neen ta dung ham duoi day dung no lai
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });

                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotalTimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekbarSong.setMax(mediaPlayer.getDuration());
    }

    private void UpdateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer!=null){
                    seekbarSong.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next = true;

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(next == true){
                    if(position < baiHats.size()){
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if(repeat==true){
                            if(position==0){
                                position = baiHats.size();
                            }
                            position-=1;
                        }
                        if(checkrandom==true){
                            Random random = new Random();
                            int index = random.nextInt(baiHats.size());
                            if(index == position){
                                position = index-1;
                            }
                            position = index;
                        }
                        if (position>baiHats.size()-1){
                            position = 0;
                        }
                        new PlayMp3().execute(baiHats.get(position).getLinkbaihat());
                        diaNhacFragment.PhatNhac(baiHats.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(baiHats.get(position).getTenbaihat());
                }
                imgPreview.setClickable(false);
                imgNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPreview.setClickable(true);
                        imgNext.setClickable(true);
                    }
                },5000);
                next = false;
                handler1.removeCallbacks(this);
                }else {
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);
    }
}
