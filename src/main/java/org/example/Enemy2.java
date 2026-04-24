package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Enemy2 extends EnemySettings{


    private String SOUBOR_ENEMY2 = "src/main/resources/teststblue.png";
    //    private Image image = new ImageIcon(SOUBOR_ENEMy).getImage();
    Projectyle projectyle;
    Shield shield;
    Player player;
    //    ArrayList<Projectyle> pole_proj = new ArrayList<>();
    private int cooldown = 240;
    private int shootcooldown = 0;
    //    private int x,y,pspeed = 1;
    private int e_id;
    private int max;
    private int e_x;
    private int e_y;
    private int e_width;
    private int e_height;
    private int speed;



    public Enemy2(int x, int y, int e_width, int e_height, int speed) {
        super(x, y, e_width, e_height, speed);


        image2 = new ImageIcon(SOUBOR_ENEMY2).getImage();

    }

    @Override
    public void cooldownProj(Player player, ArrayList<Projectyle> projectyl) {
        super.cooldownProj(player, projectyl);
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
