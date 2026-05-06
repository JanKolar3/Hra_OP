package org.example;

import javax.swing.*;
import java.awt.*;

public class Projectile2 extends ProjectileSettings{
    private String SOUBOR_PROJECTYLE2 = "src/main/resources/Projectile/teststblue.png";
    public Projectile2(int x, int y, int width, int height,int id,EnemySettings enemyS, Player player) {
        super(x,y,width,height,id,enemyS,player);
        image2 = new ImageIcon(SOUBOR_PROJECTYLE2).getImage();
    }
    @Override
    public void direction(Player player) {
        super.direction(player);
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
