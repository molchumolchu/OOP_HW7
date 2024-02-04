package com.mygdx.game.oopSrc.Task_1;

import java.util.ArrayList;
//import java.util.Random;
import java.util.Random;
import java.util.Scanner;


public class Main {

    public static ArrayList<Hero> heroesWhite= new ArrayList<>();
    public static ArrayList<Hero> heroesBlack= new ArrayList<>();

    public static ArrayList<Hero> heroOrder = new ArrayList<>();


    @SuppressWarnings("NewApi")
    public static void main(String[] args) {
        heroesWhite = generateCommand(0,1);
        heroesBlack = generateCommand(3,10);
        heroOrder.addAll(heroesBlack);
        heroOrder.addAll(heroesWhite);
        heroOrder.sort(((o1, o2) -> o2.getInitiative()- o1.getInitiative())); //сортировка лямбдой

        Scanner scanner = new Scanner(System.in);
        boolean flag;
        while (true){
            View.view();
            if (gameOverBlack()){
                flag = true;
                break;
            }
            if (gameOverWhite()){
                flag = false;
                break;
            }
            for (Hero h: heroOrder) {
                if (heroesBlack.contains(h)){
                    h.gameStep(heroesWhite, heroesBlack);
                } else {
                    h.gameStep(heroesBlack, heroesWhite);
                }
            }
            scanner.nextLine();
        }
        if (flag){
            System.out.println("Победила команда белых");
        } else {
            System.out.println("Победила команда черных");
        }



//        twoCommands.forEach(n-> System.out.println(n.getInitiative())); // Печать инициативы
//
//        heroesWhite.forEach(n -> System.out.println(n.toString())); // Печать команды
//        System.out.println("**************************");
//        heroesBlack.forEach(n -> n.printEnemysDistance(heroesWhite)); // Печать расстояния до противника



    }
    public static boolean gameOverWhite(){
        for (Hero hero : heroesWhite) {
            if (hero.health > 0) return false;
        }
        return true;
    }
    public static boolean gameOverBlack(){
        for (Hero hero : heroesBlack) {
            if (hero.health > 0) return false;
        }
        return true;
    }



    public static ArrayList<Hero> generateCommand(int n, int y) {
        ArrayList<Hero> commandHeroes = new ArrayList<>();
        Random random = new Random();
        int rand;


        for (int i = 1; i < 11; i++) {
            rand = random.nextInt(6)+ n;

            switch (rand) {
                case 1:
                    commandHeroes.add(new Crossbower(getName(), i, y));
                    break;
                case 2:
                    commandHeroes.add(new Monk(getName(), i, y));
                    break;
                case 4:
                    commandHeroes.add(new Peasant(getName(), i, y));
                    break;
                case 3:
                    commandHeroes.add(new Pikeman(getName(), i, y));
                    break;
                case 5:
                    commandHeroes.add(new Rogue(getName(), i, y));
                    break;
                case 6:
                    commandHeroes.add(new Sniper(getName(), i, y));
                    break;
                case 7:
                    commandHeroes.add(new Wizard(getName(), i, y));
                    break;

            }
        }
        return commandHeroes;
    }

    static String getName() {
        return NamesOfHeroes.values()[new Random().nextInt(NamesOfHeroes.values().length - 1)].name();
    }
}