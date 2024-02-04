package com.mygdx.game.oopSrc.Task_1;

public class Vector2 {
    public int posX, posY;

    public Vector2(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public float rangeEnemy(Vector2 posEnemy){
        double distance = (float) Math.sqrt(Math.pow(posX - posEnemy.posX, 2) + Math.pow(posY-posEnemy.posY, 2));
        return (float) distance;
    }

//    public boolean vectorToMove(Vector2 posEnemy){
//        return posEnemy.posY - posY > posEnemy.posX - posX;
//
//    }
//    public int getX() {
//        return this.posX;
//    }
//
//    public int getY() {
//        return this.posY;
//    }

//    public void moveX(int x) {
//        posX+=x;
//    }
//
//    public void moveY(int y) {
//        posY+=y;
//    }

    public  Vector2 getDelta (Vector2 posEnemy){
        return new Vector2(posX-posEnemy.posX, posY-posEnemy.posY);
    }

    public boolean equals(Vector2 posEnemy){
        return posX == posEnemy.posX & posY == posEnemy.posY;
    }


}
