package org.example;

import java.awt.*;
import java.util.ArrayList;

public class Enemy2 extends EnemySettings{

    Image[] SOUBOR_ENEMY1 = SpriteLoader.getFrames("/EnemyStop.png",16,16,6);
//    private final String SOUBOR_ENEMY2 = "src/main/resources/shieldPush.png";
    EnemySettings enemySettings;
    private int animationCooldown;
    private int index;


    public Enemy2(int x, int y, int e_width, int e_height, int speed) {
        super(x, y, e_width, e_height, speed);
        image2 = SOUBOR_ENEMY1;
    }
    @Override
    public void update(Player player, ArrayList<ProjectileSettings> projectilS) {
        super.update(player, projectilS);
        projectilS.add(new Projectile1(getX(), getY(), 32, 32,this, player));
    }




    @Override
    public int enemyAnimation() {
        if(isLeft()){
            animationCooldown--;
            if (animationCooldown <= 0) {
                index++;
                if (index >= 6) {
                    index = 3;
                }
                animationCooldown = 50;
            }
        }
        if(isRight()) {
            animationCooldown--;
            if (animationCooldown <= 0) {
                index++;
                if (index >= 3) {
                    index = 0;
                }
                animationCooldown = 50;
            }
        }
        return index;
    }
}
