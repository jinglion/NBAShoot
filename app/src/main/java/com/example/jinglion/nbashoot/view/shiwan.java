package com.example.jinglion.nbashoot.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by jinglion on 2015/8/20.
 */
public class shiwan extends GLSurfaceView {



    public shiwan(Context context, zhuView w) {
        super(context);

    }

    private class SceneRenderer implements GLSurfaceView.Renderer{

        public SceneRenderer(){

        }

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {

        }

        @Override
        public void onDrawFrame(GL10 gl) {

        }
    }

}
