package org.example;

import javax.swing.*;
import java.awt.*;

public class Projectile1 extends ProjectileSettings{
    private String SOUBOR_PROJECTYLE1 = "src/main/resources/Projectile/testst.png";
    public Projectile1(int x, int y, int width, int height, int id){
       super(x,y,width,height, id);
        image1 = new ImageIcon(SOUBOR_PROJECTYLE1).getImage();
    }
    @Override
    public void direction(Player player,EnemySettings enemyS) {
        super.direction(player,enemyS);
    }
    @Override
    public Rectangle hitBox(){
        return super.hitBox();
    }
    @Override
    public boolean collision(Player player){
        return super.collision(player);
    }
    @Override
    public boolean collision1(EnemySettings enemyS){
        return super.collision1(enemyS);
    }
    @Override
    public boolean collision2(Shield shield){
        return super.collision2(shield);
    }
    @Override
    public void draw(Graphics g){
        super.draw(g);
    }
}
