package org.example;

import javax.swing.*;
import java.awt.*;

public class Projectile2 extends ProjectileSettings{
    private final String SOUBOR_PROJECTYLE2 = "src/main/resources/Projectile/teststblue.png";
    public Projectile2(int x, int y, int width, int height,EnemySettings enemyS, Player player) {
        super(x,y,width,height,enemyS,player);
        image2 = new ImageIcon(SOUBOR_PROJECTYLE2).getImage();
    }
}
