package com.example.jinglion.nbashoot.entities;

import com.example.jinglion.nbashoot.view.shiwan;

/**
 * Created by jinglion on 2015-8-21.
 */
public class lanban {


    float offsetx;
    float offsety;
    float offsetz;

    float zuobiaospan;

    public lanban(float zuobiaospan, float offsetx, float offsety, float offsetz,
        shiwan mySurface, int lzjTextureid,int lhTextureid,int lbTextureid){
        this.zuobiaospan = zuobiaospan;
        this.offsetx = offsetx;
        this.offsety = offsety;
        this.offsetz = offsetz;


    }



}
