package com.mygdx.game.oopSrc.Roles;

import com.mygdx.game.oopSrc.Task_1.Hero;
import com.mygdx.game.oopSrc.Task_1.Vector2;

import java.util.ArrayList;

public abstract class Melee extends Hero {
    int step, damagePoint;

    public Melee(int health, int healthMax, int armor, int[] damage, String nameHero, int posX, int posY, int step) {
        super(health, healthMax, armor, damage, nameHero, posX, posY, 2);
        this.step = step;
    }

    protected void getDamage(Hero target) {
        damagePoint = this.random.nextInt(damage[0], damage[1]);
        target.health = target.health - damagePoint;
        if (target.health < 0){
            target.health = 0;
        }
    }

    @Override
    public String getType() {
        return "Melee";
    }

    protected Hero findBestEnemyMDD(ArrayList<Hero> enemys) {
        Hero heroTMP = null;
        for (int i = 0; i < enemys.size(); i++) {
            if (enemys.get(i).health>0) {
                if (heroTMP == null || this.position.rangeEnemy(enemys.get(i).position) < this.position.rangeEnemy(heroTMP.position)) {
                    heroTMP = enemys.get(i);
                }
            }
        }
        return heroTMP;
    }



    protected Vector2 getStepMDD(Hero enemy) {
        Vector2 delta = position.getDelta(enemy.position); //return new Vector2(posX - posEnemy.posX, posY - posEnemy.posY);
        Vector2 tmpVector2 = new Vector2(position.posX, position.posY);
        if (delta.posX < 0) {
            tmpVector2.posX++;
            return tmpVector2;
        }
        if (delta.posX > 0) {
            tmpVector2.posX--;
            return tmpVector2;
        }
        if (delta.posY < 0) {
            tmpVector2.posY++;
            return tmpVector2;
        }
        if (delta.posY > 0) {
            tmpVector2.posY--;
            return tmpVector2;
        }
        return tmpVector2;

    }

    @Override
    public String toString() {
        return (nameHero + " здоровье: " + health + "/" + healthMax);
    }

    @Override
    public void gameStep(ArrayList<Hero> teamEnemy, ArrayList<Hero> teamAllies) {
        if (this.health == 0) return;
        Hero tmpHero = findBestEnemyMDD(teamEnemy);
        if (tmpHero == null) return;
        if (position.rangeEnemy(tmpHero.position) < 2) {
            getDamage(tmpHero);
            //System.out.println("Нанесен урон" + this.damagePoint);
        } else {
            Vector2 tmpVec = getStepMDD(tmpHero);
            boolean step = true;
            for (Hero hero : teamAllies) {
                if (tmpVec.equals(hero.position) && hero.health>0) step = false;
            }
            if (step) position = tmpVec;
        }
    }
}