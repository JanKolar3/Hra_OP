package org.example;

import javax.swing.*;
import java.awt.*;

public class ProjectileSettings {

//    private String SOUBOR_PROJECTYLE = "src/main/resources/testst.png";



    Player player;
    EnemySettings enemyS;
    private Image image;
    Shield shield;
    Image image1;
    Image image2;
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed=2;
    private int mode=1;
    private double dx, dy;
    private int id;


    public ProjectileSettings(int x, int y,int width, int height,int id,EnemySettings enemyS,Player player){
//        image = new ImageIcon(SOUBOR_PROJECTYLE).getImage();


        this.y = y;
        this.x = x;
        this.width = width;
        this.height = height;
        this.id = id;

        this.enemyS = enemyS;
        this.player = player;
//        direction();


    }
    public void direction(Player player) {

        if (mode == 1) {
            if (player.getPl_x() > x) x += speed;
            if (player.getPl_x() < x) x -= speed;
            if (player.getPl_y() > y) y += speed;
            if (player.getPl_y() < y) y -= speed;
        } else if (mode == 2 && enemyS != null) {
            speed = 7;
            if (enemyS.getE_x()+(enemyS.getE_width()/4) > x) x += speed;
            if (enemyS.getE_x()+(enemyS.getE_width()/4) < x) x -= speed;
            if (enemyS.getE_y()+(enemyS.getE_width()/4) > y) y += speed;
            if (enemyS.getE_y()+(enemyS.getE_width()/4) < y) y -= speed;

        }
    }
    public Rectangle hitBox(){
        return new Rectangle(x+(getWidth()/4),y+(getWidth()/4),width/2,height/2);
    }
    public boolean collision(Player player){
        return player.hitBox().intersects(hitBox());
    }
    public boolean collision1(EnemySettings enemyS){
        return enemyS.hitBox().intersects(this.hitBox());
    }
    public boolean collision2(Shield shield){
        return shield.hitBox().intersects(hitBox());
    }



    public void draw(Graphics g){
        g.drawImage(image1,x,y,width,height,null);
        g.drawImage(image2,x,y,width,height,null);
//        g.drawRect(x+(getWidth()/4),y+(getWidth()/4),width/2,height/2);
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

//    public void setLp(boolean lp) {
//        this.lp = lp;
//    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }


}
