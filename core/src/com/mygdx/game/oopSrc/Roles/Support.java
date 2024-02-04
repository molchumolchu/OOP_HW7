package com.mygdx.game.oopSrc.Roles;

import com.mygdx.game.oopSrc.Task_1.Hero;

import java.util.ArrayList;


public abstract class Support extends Hero  {

    int mana, manaMax, healingPoint, manaCost;
    int manaRes = 6;
    boolean flagRes = false;

    /**
     * Конструктор
     */
    public Support(int health, int healthMax, int armor, int[] damage, String nameHero, int posX, int posY, int mana, int manaMax) {
        super(health, healthMax, armor, damage, nameHero, posX, posY, 1);
        this.mana = mana;
        this.manaMax = manaMax;
    }

    /**
     * Метод лечения цели
     */
    protected void getHealing(Hero target) {
        manaCost = 10;
        if (this.mana >= manaCost) {
            if (target.health > 0 && target.health < target.healthMax) {
                healingPoint = this.random.nextInt(damage[0], damage[1]);
                target.health = target.health + healingPoint;
                if (target.health > target.healthMax) {
                    target.health = target.healthMax;
                }
            }
            this.mana -= manaCost;
        }
    }

    protected void getResp(ArrayList<Hero> teamAllies, ArrayList<Hero> teamEnemy) {
        if (!flagRes && random.nextInt(5) != 4){
            return;
        }
        manaCost = manaMax / 2;
        if (teamAllies.stream().allMatch(n -> !n.getType().equals("Melee") || n.health < 1)) {
            flagRes = true;
            if(this.mana < manaMax) return;
            for (Hero h : teamAllies) {
                if (h.getType().equals("Melee")) {
                    for (Hero enemy : teamEnemy) {
                        if (h.position.rangeEnemy(enemy.position) == 0) {
                            return;
                        }
                    }
                    for (Hero allies : teamAllies) {
                        if (h.position.rangeEnemy(allies.position) == 0) {
                            return;
                        }
                    }
                    h.health = h.healthMax;
                    this.mana -= manaCost;
                    flagRes = false;
                    return;
                }
            }
        }
    }

    protected Hero findMinHealthAllies(ArrayList<Hero> allies) {
        Hero heroTMP = allies.get(0);
        for (Hero ally : allies) {
            if (heroTMP.health > ally.health && ally.health > 0) {
                heroTMP = ally;
            }
        }
        return heroTMP;
    }

    @Override
    public String toString() {
        return (nameHero + " здоровье: " + health + "/" + healthMax + " мана: " + mana + "/" + manaMax);
    }

    @Override
    public void gameStep(ArrayList<Hero> teamAllies, ArrayList<Hero> teamEnemy) {
        if (this.health > 0) {
            getResp(teamAllies,teamEnemy);
            if (!flagRes){
                getHealing(findMinHealthAllies(teamAllies));
            }
            this.mana += manaRes;
            if (mana > manaMax) {
                mana = manaMax;
            }
            //System.out.println("Нанесен урон" + this.healingPoint);
        }
    }

    @Override
    public String getType() {
        return "Healer";
    }
}