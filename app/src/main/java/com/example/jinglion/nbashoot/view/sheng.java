package com.example.jinglion.nbashoot.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.jinglion.nbashoot.Constants.changL;
import com.example.jinglion.nbashoot.LanqiuActivity;
import com.example.jinglion.nbashoot.R;

/**
 * Created by jinglion on 2015-8-19.
 */
public class sheng extends SurfaceView implements SurfaceHolder.Callback {
    LanqiuActivity activity;
    Paint paint;
    Bitmap background;
    Bitmap soundsOn;
    Bitmap soundsOff;
    Bitmap sounds;

    public sheng(LanqiuActivity activity) {
        super(activity);
        this.activity = activity;
        this.getHolder().addCallback(this);

        //设置画笔的基本参数
        paint = new Paint();
        paint.setAntiAlias(true);

        initBitmap();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(soundsOff, 276, 420, paint);
        canvas.drawBitmap(soundsOn, 4, 420, paint);
        canvas.drawBitmap(sounds, 30, 70, paint);
    }

    private void initBitmap() {
        background= BitmapFactory.decodeResource(activity.getResources(), R.mipmap.icon);
        soundsOn=BitmapFactory.decodeResource(activity.getResources(), R.mipmap.yes);
        soundsOff=BitmapFactory.decodeResource(activity.getResources(), R.mipmap.no);
        sounds=BitmapFactory.decodeResource(activity.getResources(), R.mipmap.sounds);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                if(x>=276&&x<=316&&y>=420&&y<=460)
                {
                    activity.hd.sendEmptyMessage(changL.GAME_MENU);
                    changL.SOUND_FLAG = false;//设置声音标记位为false，表示关闭声音
                    //禁止声音方法
                }else if(x>=4&&x<=44&&y>=420&&y<=460)
                {
                    activity.hd.sendEmptyMessage(changL.GAME_MENU);
                    changL.SOUND_FLAG=true;//设置声音标记位为true，表示开启声音
                    //添加声音方法
                }
                changL.SOUND_MEMORY = changL.SOUND_FLAG;//记录下用户是否开启声音的选择
                break;
        }
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Canvas canvas = holder.lockCanvas();
        try {
            synchronized (holder){
                onDraw(canvas);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (canvas!=null){
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
