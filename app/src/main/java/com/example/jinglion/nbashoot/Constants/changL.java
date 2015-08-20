package com.example.jinglion.nbashoot.Constants;

/**
 * Created by jinglion on 2015-8-19.
 */
public class changL {

    public static int score=0;//得分
    public static int deadtimes=60;//游戏倒计时

    //菜单界面
    public static final int GAME_SOUND=1;
    public static final int GAME_MENU=2;
    public static final int GAME_LOAD=3;
    public static final int GAME_HELP=4;
    public static final int GAME_ABOUT=5;
    public static final int GAME_PLAY=6;
    public static final int GAME_OVER=7;
    public static final int RETRY=8;

    public static float LEFT=-55f; //菜单位置

    //线程标志位
    public static boolean SOUND_FLAG=true;//声音	标记
    public static boolean SOUND_MEMORY=false;//用于记录声音玩家的选择
    public static boolean DEADTIME_FLAG=false;//倒计时线程标记
    public static boolean MENU_FLAG=false;//菜单按钮绘制线程标记
    public static boolean BALL_GO_FLAG=true;//球运动线程标记
}
