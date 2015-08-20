package com.example.jinglion.nbashoot.Thread;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.example.jinglion.nbashoot.Constants.changL;
import com.example.jinglion.nbashoot.view.zhuView;

/**
 * Created by jinglion on 2015-8-20.
 */
public class zhuThread extends Thread {
    zhuView cc;
    SurfaceHolder holder;
    public zhuThread(zhuView cc){
        this.cc = cc;
        this.holder=cc.getHolder();
    }

    @Override
    public void run() {
        Canvas canvas;
        while (changL.MENU_FLAG){
            canvas = null;

        }

    }
}
