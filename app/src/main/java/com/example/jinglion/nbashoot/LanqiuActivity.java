package com.example.jinglion.nbashoot;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import static com.example.jinglion.nbashoot.Constants.changL.*;

import java.util.HashMap;

public class LanqiuActivity extends AppCompatActivity {

    public Handler hd;
    MediaPlayer mpBack;//游戏背景音乐
    SoundPool soundPool;//声音,l可以播一些短的反应速度要求高的声音，比如游戏中的爆破声
    HashMap<Integer, Integer> soundPoolMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置全屏显示
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去除标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置窗口全屏显示
        setContentView(R.layout.mian);

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

                        break;
                }
            }
        };
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


}
