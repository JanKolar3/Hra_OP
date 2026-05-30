package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Enemy1 extends EnemySettings{

    Image[] SOUBOR_ENEMY = SpriteLoader.getFrames("/Enemy/EnemyEye.png",16,16,4);
    Random random = new Random();
    private int animationCooldown=random.nextInt(20);
    private int index=0;

    public Enemy1(int x , int y , int e_width, int e_height, int speed) {
        super(x,y,e_width,e_height,speed);
        image1 = SOUBOR_ENEMY;
    }

    @Override
    public void update(Player player, ArrayList<ProjectileSettings> projectilS) {
        super.update(player, projectilS);
        projectilS.add(new Projectile1(getX(),getY(), 32, 32,this,player));
    }

    @Override
    public int enemyAnimation() {
        animationCooldown--;
        if (animationCooldown <= 0) {
            index++;
            if (index >= 4) {
                index = 0;
            }
            animationCooldown = 20;
        }
        return index;
    }
}
