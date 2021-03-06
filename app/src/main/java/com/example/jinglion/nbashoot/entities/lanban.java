package com.example.jinglion.nbashoot.entities;

import com.example.jinglion.nbashoot.view.shiwan;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by jinglion on 2015-8-21.
 */
public class lanban {

    private Cylinder lzj;//支架
    private Ring lh;//篮筐
    private Board lb;//篮板

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

        lzj = new Cylinder(3*zuobiaospan,zuobiaospan/5,45,10,lzjTextureid);
        lh = new Ring(18, 45, 6*zuobiaospan, zuobiaospan/5, lhTextureid);
        lb=new Board(zuobiaospan/2,36*zuobiaospan,21*zuobiaospan,lbTextureid);
    }

    public void drawSelf(GL10 gl){
        //绘制篮板
        gl.glPushMatrix();
        gl.glTranslatef(offsetx, offsety, offsetz);
        lb.drawSelf(gl);
        gl.glPopMatrix();

        //绘制篮筐支架
        gl.glPushMatrix();
        gl.glTranslatef
                (
                        offsetx+lh.ring_Radius/2,
                        offsety-lb.height/4,
                        offsetz+lzj.length/2+lb.length/2
                );
        gl.glRotatef(90, 0, 1, 0);
        lzj.drawSelf(gl);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef
                (
                        offsetx-lh.ring_Radius/2,
                        offsety-lb.height/4,
                        offsetz+lzj.length/2+lb.length/2
                );
        gl.glRotatef(90, 0, 1, 0);
        lzj.drawSelf(gl);
        gl.glPopMatrix();

        //绘制篮筐
        gl.glPushMatrix();
        gl.glTranslatef
                (
                        offsetx,
                        offsety-lb.height/4,
                        (float) (offsetz+lb.length/2+lzj.length+Math.sqrt(3)/2*lh.ring_Radius)
                );
        lh.drawSelf(gl);
        gl.glPopMatrix();
    }

    public float[] getRingCentre()
    {
        float[] ringCentre=new float[]
                {
                        offsetx,
                        offsety-lb.height/2,
                        (float) (offsetz+lb.length/2+lzj.length+Math.sqrt(3)/2*lh.ring_Radius)
                };
        return ringCentre;
    }

    public float getRingReduis()
    {
        float ringReduis=lh.ring_Radius;
        return ringReduis;
    }

}
