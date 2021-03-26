package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Expload extends Actor {
    float x, y;
    Sprite sprite;
    float s;
    public Expload(float x, float y) {
        this.x = x;
        this.y = y;
        s = (float) 0.04;

        sprite = new Sprite(new Texture("b2.png"));
        sprite.setBounds(x, y, 10, 10);
        sprite.setOrigin(5, 5);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        sprite.scale(s);
        s -= (float) 0.01;
        if(s < -0.1) remove();
    }
}
