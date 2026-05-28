package org.example;

import java.awt.*;

public class ProjectileSettings {

    Player player;
    EnemySettings enemyS;
    Image image1;
    Image image2;

    private int x;
    private int y;
    private final int width;
    private final int height;
    private int speed=4;
    private int mode=1;
    private boolean damage=true;
    private int cooldown=320;
    private boolean destroy=false;

    public ProjectileSettings(int x, int y,int width, int height,EnemySettings enemyS,Player player){
        this.y = y;
        this.x = x;
        this.width = width;
        this.height = height;

        this.enemyS = enemyS;
        this.player = player;
    }
    public void direction(Player player) {
        if (mode == 1) {
            cooldown --;
            if (player.getX() > x) x += speed;
            if (player.getX() < x) x -= speed;
            if (player.getY() > y) y += speed;
            if (player.getY() < y) y -= speed;
        } else if (mode == 2 && enemyS != null) {
            if (damage) {
                cooldown =100;
            }
            damage = false;
            cooldown --;
            speed = 7;
            if (enemyS.getX()+(enemyS.getWidth()/4) > x) x += speed;
            if (enemyS.getX()+(enemyS.getWidth()/4) < x) x -= speed;
            if (enemyS.getY()+(enemyS.getWidth()/4) > y) y += speed;
            if (enemyS.getY()+(enemyS.getWidth()/4) < y) y -= speed;
        }
        if (cooldown <= 0) {
            destroy = true;
        }
    }
    public Rectangle hitBox(){
        return new Rectangle(x+(getWidth()/4),y+(getWidth()/4),width/2,height/2);
    }

    public boolean collision(Player player){return player.hitBox().intersects(hitBox());}

    public boolean collision1(EnemySettings enemyS){return enemyS.hitBox().intersects(this.hitBox());}

    public boolean collision2(Shield shield){return shield.hitBox().intersects(hitBox());}



    public void paintComponents(Graphics g){
        if (damage) {
            g.drawImage(image1, x, y, width, height, null);
        }
        else if (!damage){
            g.drawImage(image2, x, y, width, height, null);}
    }


    public boolean isDestroy() {
        return destroy;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public boolean isDamage() {
        return damage;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
