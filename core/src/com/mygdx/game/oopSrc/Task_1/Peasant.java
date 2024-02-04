package com.mygdx.game.oopSrc.Task_1;

import com.mygdx.game.oopSrc.Roles.Archer;

import java.util.ArrayList;


/*
крестьянин, не может лечиться, носит стрелы для арбалетчика и снайпера
 */
public class Peasant extends Hero {

    public Peasant(String nameHero, int posX, int posY) {
        super(100, 100, 0, new int[]{0, 0}, nameHero, posX, posY, 0);
    }

    protected Vector2 position;

    @Override
    public String toString() {
        return ("Крестьянин: " + nameHero + " здоровье: " + health + "/" + healthMax);
    }


    protected void getArrow(Hero target) {
        if (target == null) {
            return;
        }

        if (((Archer)target).quantityShots < ((Archer) target).quantityShotsMax) {
            ((Archer) target).quantityShots++;
        }
    }

    protected Hero findMoreEmptyRDD(ArrayList<Hero> allies) {
        ArrayList<Archer> rdd = new ArrayList<>();
        for (Hero hero : allies) {
            if (hero instanceof Archer && hero.health > 0) {
                rdd.add((Archer) hero);
            }
        }
        Archer rddTMP = null;
        if (!rdd.isEmpty()) {
            rddTMP = rdd.get(0);
            for (Archer hero : rdd) {
                if (rddTMP.quantityShots > hero.quantityShots) {
                    rddTMP = hero;
                }
            }
        }
        return rddTMP;
    }

    @Override
    public void gameStep(ArrayList<Hero> teamEnemy, ArrayList<Hero> teamAllies) {
        if (this.health > 0) {
            getArrow(findMoreEmptyRDD(teamAllies));
        }
    }

    @Override
    public String getInfo() {
        return "Крестьянин";
    }

    @Override
    public String getType() {
        return "Peasant";
    }
}