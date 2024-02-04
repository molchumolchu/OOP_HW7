package com.mygdx.game.oopSrc.Task_1;

/*
арбалетчик стреляет, не двигается
есть запас стрелл, определяет наименьшее растояние и наносит повреждение на всем поле. учитываь броню
 */

import com.mygdx.game.oopSrc.Roles.Archer;

import java.util.ArrayList;

public class Crossbower extends Archer {


    public Crossbower(String nameHero, int posX, int posY) {
        super(75, 75, 2, new int[]{10, 15}, nameHero, posX, posY, 7, 7, 4);
        Vector2 position;
        angryRDD = new Pikeman(this.nameHero, this.position.posX, this.position.posY);
        angryRDD.position = this.position;
        angryRDD.damage = new int[] {9, 10};
    }

    @Override
    public String toString() {
        return "Арбалетчик: " + super.toString();
    }

    @Override
    public String getInfo() {
        return "Арбалетчик";
    }
}