package com.mygdx.game.oopSrc.Task_1;

import com.mygdx.game.oopSrc.Roles.Archer;

import java.util.ArrayList;

/*
стрелок стреляет, не двигается
 */
public class Sniper extends Archer {
    public Sniper(String nameHero, int posX, int posY) {
        super(75, 75, 2, new int[]{10, 15}, nameHero, posX, posY, 7, 7, 4);
        angryRDD = new Rogue(this.nameHero, this.position.posX, this.position.posY);
        angryRDD.position = this.position;
        angryRDD.damage = new int[] {9, 10};
    }

    @Override
    public String toString() {
        return "Снайпер: " + super.toString();
    }
    @Override
    public String getInfo() {
        return "Снайпер";
    }
}