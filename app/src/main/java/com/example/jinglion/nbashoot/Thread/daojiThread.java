package com.example.jinglion.nbashoot.Thread;

import static com.example.jinglion.nbashoot.Constants.changL.*;
import com.example.jinglion.nbashoot.LanqiuActivity;

/**
 * Created by jinglion on 2015/8/20.
 */
public class daojiThread extends Thread {
    LanqiuActivity activity;

    public  daojiThread(LanqiuActivity activity){
        this.activity = activity;
    }

    @Override
    public void run() {
        while (DEADTIME_FLAG){
            if (deadtimes>0){
                try {
                    deadtimes -= 1;
                    if (deadtimes==0 && SOUND_FLAG==true){
                        //播放音乐池中的2号音乐，播放次数为0+1次
                        activity.playSound(2,0);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (deadtimes == 0){
                SOUND_FLAG = false;//关闭声音
                DEADTIME_FLAG = false;//关闭倒计时线程
                activity.hd.sendEmptyMessage(GAME_OVER);
            }
        }
    }
}
