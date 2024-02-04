package com.mygdx.game.oopSrc.Task_1;

import com.mygdx.game.oopSrc.Roles.Support;

import java.util.ArrayList;

/*
безлимитная мана, не двигается
 */
public class Monk extends Support {

    public Monk(String nameHero, int posX, int posY) {
        super(50, 50, 1, new int[]{10, 15}, nameHero, posX, posY, 50, 50);
        Vector2 position;
    }



    @Override
    public String toString() {
        return ("Монах: " + super.toString());
    }
    @Override
    public String getInfo() {
        return "Монах";
    }
}