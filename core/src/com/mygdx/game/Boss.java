package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

import sun.rmi.runtime.Log;

public class Boss extends Actor {
    Sprite gunLeft, gunRight, spinLeft, spinRight, main;
    Texture gunLeftt, gunRightt, spinLeftt, spinRightt, maint;
    float x, y, nextX, nextY, step, stepY, test1, test2;
    float nRLeft, nRRight;
    Ship ship;
    Vector2 v1, v2;
    BitmapFont font = new BitmapFont();
    BitmapFont font2 = new BitmapFont();
    BitmapFont font3 = new BitmapFont();
   // BitmapFont font4 = new BitmapFont();
    String str = "";
    String str2 = "";
    String str3 = "";
   // String str4 = "";
    WeaponRotator wR, wL;

    public Boss(Ship ship) {
        gunLeftt = new Texture("boss/shot.png");
        gunRightt = new Texture("boss/shot.png");
        spinLeftt = new Texture("boss/spin.png");
        spinRightt = new Texture("boss/spin.png");
        maint = new Texture("boss/boss1.png");

        v1 = new Vector2();
        v2 = new Vector2();

        this.ship = ship;

        gunLeft = new Sprite(gunLeftt);
        gunRight = new Sprite(gunRightt);
        spinLeft = new Sprite(spinLeftt);
        spinRight = new Sprite(spinRightt);
        main = new Sprite(maint);

        x = 100;
        y = 400;
        nextX = 0;
        nextY = 0;
        step = (float) 0.7;
        stepY = (float) 0.5;
        nRLeft = 0;
        nRRight = 0;

        spinLeft.setOrigin(25, 25);
        spinRight.setOrigin(25, 25);
        gunLeft.setOrigin(12, 16);
        gunRight.setOrigin(12, 16);
        main.setOrigin(79,0);

        spinRight.setBounds(x + 106 - 79, y + 91, 50, 50);
        spinLeft.setBounds(x + 4 - 79, y + 91, 50, 50);
        gunRight.setBounds(x + 94 - 79, y + 2, 20, 26);
        gunLeft.setBounds(x + 45 - 79, y + 2, 20, 26);
        main.setBounds(x, y, 160, 200);

        wL = new WeaponRotator(gunLeft);
        wR = new WeaponRotator(gunRight);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        gunRight.draw(batch);
        gunLeft.draw(batch);
        spinRight.draw(batch);
        spinLeft.draw(batch);
        main.draw(batch);

        font.draw(batch, str, 100, 100);
        font2.draw(batch, str2, 100, 80);
        font3.draw(batch, str3, 100, 60);

    }

    @Override
    public void act(float delta) {
        spinLeft.rotate(7);
        spinRight.rotate(7);

        if(nextX > 30) step -= (float) 0.05;
        if(nextX < -80) step += (float) 0.05;
       // nextX += step;
        if(nextY > 40) stepY -= (float) 0.02;
        if(nextY < -20) stepY += (float) 0.02;
        //nextY += stepY;

        spinRight.setBounds(x + 106 + nextX - 79, y + 91 + nextY, 50, 50);
        spinLeft.setBounds(x + 4 + nextX - 79, y + 91 + nextY, 50, 50);
        gunRight.setBounds(x + 94 + nextX - 79, y + 2 + nextY, 20, 26);
        gunLeft.setBounds(x + 45 + nextX - 79, y + 2 + nextY, 20, 26);
        main.setBounds(x + nextX - 79, y + nextY, 160, 200);

        wL.rtt(x + 45 + nextX - 79, y + 2 + nextY, ship.x + 20, ship.y + 30, -45, 15);
        wR.rtt(x + 94 + nextX - 79, y + 2 + nextY, ship.x + 20, ship.y + 30, -15, 45);

       // str = String.valueOf(ship.x + 20);
       //str2 = String.valueOf(ship.y + -20);
       // str3 = String.valueOf(x + nextX);
    }
    float strike(float bx, float by){
        if(by >= y + nextY){
            if(bx > x + nextX - 8 && bx < x + nextX + 8) return by - 5;
            if(bx >= x + nextX + 8 && by >= bx * 2.2) return (float) (by);
            if(by >= y + nextY + 20){
                if(bx > x + nextX + 13 && bx < x + nextX + 115 - 79) return y + nextY + 16;
                if(bx >= x + nextX + 115 - 79 && bx <= x + nextX + 134 - 79 && by >= bx * 2) return (float)(bx * 2);
            }
            if(bx <= x + nextX - 8 && by >= 79 - bx * 2.2){
                test1 = (float) (79 - bx * 2.2);
                str = String.valueOf(test1);
                return (float) (79 - bx * 2.2);
            }

        }

        return -1;
    }
}
