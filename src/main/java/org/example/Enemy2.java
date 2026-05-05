package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Enemy2 extends EnemySettings{


    private String SOUBOR_ENEMY2 = "src/main/resources/Projectile/teststblue.png";

    public Enemy2(int x, int y, int e_width, int e_height, int speed, int id) {
        super(x, y, e_width, e_height, speed,id);
        image2 = new ImageIcon(SOUBOR_ENEMY2).getImage();
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
}
