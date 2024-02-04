package com.mygdx.game.oopSrc.Task_1;

import com.mygdx.game.oopSrc.Roles.Support;

import java.util.ArrayList;

/*
Маг - аналог монаха
 */
public class Wizard extends Support {

    public Wizard(String nameHero, int posX, int posY) {
        super(50, 50, 1, new int[]{10, 15}, nameHero, posX, posY, 50, 50);
        Vector2 position;
    }

    @Override
    public String toString() {
        return ("Колдун: " + super.toString());
    }

    @Override
    public String getInfo() {
        return "Колдун";
    }

}