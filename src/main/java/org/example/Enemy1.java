package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Enemy1 extends EnemySettings{

//    private String SOUBOR_ENEMY1 = "src/main/resources/enemytest.png";
    Image[] SOUBOR_ENEMY = SpriteLoader.getFrames("/redEnemy.png",24,24,4);


    public Enemy1(int x , int y , int e_width, int e_height, int speed) {
        super(x,y,e_width,e_height,speed);

//        image1 = new Image{new ImageIcon(SOUBOR_ENEMY[getIndex()]).getImage()};
        image1 = SOUBOR_ENEMY;

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
    public void enemyAnimation() {
        super.enemyAnimation();
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
}
