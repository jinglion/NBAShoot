package com.example.jinglion.nbashoot;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.jinglion.nbashoot.Thread.daojiThread;
import com.example.jinglion.nbashoot.view.sheng;
import com.example.jinglion.nbashoot.view.zhuView;

import static com.example.jinglion.nbashoot.Constants.changL.*;

import java.util.HashMap;

public class LanqiuActivity extends Activity {

    private zhuView gamemenu;

    private sheng gamesound;  //声音类界面

    public Handler hd;
    MediaPlayer mpBack;//游戏背景音乐
    SoundPool soundPool;//声音,l可以播一些短的反应速度要求高的声音，比如游戏中的爆破声
    HashMap<Integer, Integer> soundPoolMap;//声音池中声音ID与自定义声音ID的Map

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置全屏显示
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去除标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置窗口全屏显示
        setContentView(R.layout.main);

        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hd.sendEmptyMessage(GAME_SOUND);
            }
        }.start();

        initSounds();//初始化游戏背景音乐

        hd = new Handler(){//消息处理器初始化

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case GAME_SOUND:
                        gamesound = new sheng(LanqiuActivity.this);
                        setContentView(gamesound);
                        break;
                    case GAME_MENU:
                        MENU_FLAG=true;//设置MenuThread标志位为true
                        gamemenu=new zhuView(LanqiuActivity.this);
                        setContentView(gamemenu);
                        break;
                    case GAME_LOAD:
                        MENU_FLAG = false;//设置MenuThread标志位为false
                        setContentView(R.layout.loading);
                        new Thread(){
                            @Override
                            public void run() {
                                try {
                                    sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                hd.sendEmptyMessage(GAME_PLAY);
                            }
                        }.start();
                        break;
                    case  GAME_PLAY:
                        score = 0;//还原得分
                        deadtimes = 60;//还原倒计时
                        SOUND_FLAG = SOUND_MEMORY;//还原声音选择
                        DEADTIME_FLAG = true;//开启倒计时
                        new daojiThread(LanqiuActivity.this).start();//开启倒计时线程

                        break;
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * 初始化游戏背景音乐
     */
    private void initSounds() {
        mpBack = MediaPlayer.create(this, R.raw.gameback);
        /**
         * maxStream —— 同时播放的流的最大数量
         * streamType —— 流的类型，一般为STREAM_MUSIC(具体在AudioManager类中列出)
         * srcQuality —— 采样率转化质量，当前无效果，使用0作为默认值
         */
        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap = new HashMap<Integer, Integer>();
        soundPoolMap.put(1, soundPool.load(this, R.raw.collision, 1));
        soundPoolMap.put(2, soundPool.load(this, R.raw.over, 1));
    }

    public void playSound(int sound, int loop){
        AudioManager mgr = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        float streamVoluneCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        float volume = streamVoluneCurrent/streamVolumeMax;
        /**
         * 指定当前播放的音效
         * 左声道
         * 右声道
         * 流的优先级，值越大优先级高，影响当同时播放数量超出了最大支持数时SoundPool对该流的处理
         * 循环播放的次数，0为值播放一次，-1为无限循环，其他值为播放loop+1次（例如，3为一共播放4次）.
         * 播放的速率，范围0.5-2.0(0.5为一半速率，1.0为正常速率，2.0为两倍速率)
         */
        soundPool.play(soundPoolMap.get(sound), volume, volume, 1, loop, 0.5f);
    }

    public void waitTwoSeconds()
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
