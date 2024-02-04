package com.mygdx.game.oopSrc.Roles;

import com.mygdx.game.oopSrc.Task_1.Hero;

import java.util.ArrayList;

public abstract class Archer extends Hero  {

        public int quantityShotsMax;
    public int quantityShots;
    int rangeMaxDamage;
        private int damagePoint;

        public Hero angryRDD = null;


        public Archer(int health, int healthMax, int armor, int[] damage, String nameHero, int posX, int posY, int quantityShots, int quantityShotsMax, int rangeMaxDamage) {
            super(health, healthMax, armor, damage, nameHero, posX, posY, 3);
            this.quantityShots = quantityShots;
            this.quantityShotsMax = quantityShotsMax;
            this.rangeMaxDamage = rangeMaxDamage;
        }

//    public void getShoot(Hero target) {
//        int damagePoint = (this.position.rangeEnemy(target.position) < rangeMaxDamage)? this.random.nextInt(damage[0], damage[1]): damage[0];
//        target.getDamage((this.position.rangeEnemy(target.position) < rangeMaxDamage)? this.random.nextInt(damage[0], damage[1]): damage[0]);
//    }

        protected Hero findBestEnemyRDD(ArrayList<Hero> enemys) { //дописать проверку на жизнь
            Hero heroTMP = enemys.get(0);
            for (int i = 0; i < enemys.size(); i++) {
                if (heroTMP.health < 1) {
                    heroTMP = enemys.get(i);
                }
                if (this.position.rangeEnemy(enemys.get(i).position) < this.position.rangeEnemy(heroTMP.position)) {
                    heroTMP = enemys.get(i);
                }
            }
            if (this.position.rangeEnemy(heroTMP.position) > this.rangeMaxDamage) {
                for (Hero enemy : enemys) {
                    if (heroTMP.health > enemy.health) {
                        heroTMP = enemy;
                    }
                }
            }
            return heroTMP;
        }

        @Override
        public String toString() {
            return (nameHero + " здоровье: " + health + "/" + healthMax + " Выстрелы " + this.quantityShots + "/" + this.quantityShotsMax);
        }

        public void gameStep(ArrayList<Hero> teamEnemy, ArrayList<Hero> teamAllies) {
            if (this.health == 0) return;
            if (this.quantityShots == 0) {
                this.angryRDD.health = health;
                this.angryRDD.gameStep(teamEnemy, teamAllies);
                this.position = angryRDD.position;
                return;
            }
            Hero target = findBestEnemyRDD(teamEnemy);
            target.getDamage((this.position.rangeEnemy(target.position) < rangeMaxDamage)? this.random.nextInt(damage[0], damage[1]): damage[0]);
            //System.out.println("Нанесен урон" + this.damagePoint);
            quantityShots--;
        }

        @Override
        public String getType() {
            return "Range";
        }

    }