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
            if (true){
                try {
                    canvas = this.holder.lockCanvas();
                    synchronized (this.holder){
                        cc.onDraw(canvas);//用户按下标签后，重绘按钮，菜单栏标签享有突出，产生动画效果
                    }
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    if (canvas!=null){
                        this.holder.unlockCanvasAndPost(canvas);
                    }
                }
            }
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
