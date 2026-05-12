package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Enemy1 extends EnemySettings{

//    private String SOUBOR_ENEMY1 = "src/main/resources/enemytest.png";
    Image[] SOUBOR_ENEMY = SpriteLoader.getFrames("/redEnemy.png",24,24,4);


    public Enemy1(int x , int y , int e_width, int e_height, int speed, int id) {
        super(x,y,e_width,e_height,speed,id);

//        image1 = new Image{new ImageIcon(SOUBOR_ENEMY[getIndex()]).getImage()};
        image1 = SOUBOR_ENEMY;
    }
}
