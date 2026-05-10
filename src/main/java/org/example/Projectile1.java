package org.example;

import javax.swing.*;
import java.awt.*;

public class Projectile1 extends ProjectileSettings{
    private String SOUBOR_PROJECTYLE1 = "src/main/resources/Projectile/testst.png";
    public Projectile1(int x, int y, int width, int height, int id,EnemySettings enemyS, Player player){
       super(x,y,width,height, id,enemyS,player);
        image1 = new ImageIcon(SOUBOR_PROJECTYLE1).getImage();
    }
}
