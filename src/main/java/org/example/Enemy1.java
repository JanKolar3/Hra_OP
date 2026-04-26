package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Enemy1 extends EnemySettings{

    private String SOUBOR_ENEMY1 = "src/main/resources/untitled.png";

    public Enemy1(int x , int y , int e_width, int e_height, int speed) {
        super(x,y,e_width,e_height,speed);



        image1 = new ImageIcon(SOUBOR_ENEMY1).getImage();

    }

    @Override
    public void cooldownProj(Player player, ArrayList<ProjectileSettings> projectilS) {
        super.cooldownProj(player, projectilS);
    }

    @Override
    public void enemyMove(Player player) {
        super.enemyMove(player);
    }

    @Override
    public Rectangle hitBox() {
        return super.hitBox();
    }

    @Override
    public boolean collision(Player player) {
        return super.collision(player);
    }

    @Override
    public void vykresleniObr(Graphics g) {
        super.vykresleniObr(g);




    }
//
//    public int getMax() {
//        return max;
//    }
//
//    public int getE_width() {
//        return e_width;
//    }
//
//    public int getE_height() {
//        return e_height;
//    }
//
//    public int getE_x() {
//        return e_x;
//    }
//
//    public int getE_y() {
//        return e_y;
//    }
}
