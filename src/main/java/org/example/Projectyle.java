package org.example;

import javax.swing.*;
import java.awt.*;

public class Projectyle {
    private String SOUBOR_PROJECTYLE = "src/main/resources/red_proj (1).gif";


    Player player;
    private Image image;
    Shield shield;
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed=2;
    boolean lp = false;
    private double dx, dy;


    public Projectyle(int x, int y,int width, int height){
        image = new ImageIcon(SOUBOR_PROJECTYLE).getImage();


        this.y = y;
        this.x = x;
        this.width = width;
        this.height = height;
//        direction();


    }
    public void direction(Player player,Enemy enemy) {

        if (lp == false) {
            if (player.getPl_x() > x) x += speed;
            if (player.getPl_x() < x) x -= speed;
            if (player.getPl_y() > y) y += speed;
            if (player.getPl_y() < y) y -= speed;
        } else if (lp == true) {
            speed = 5;
            if (enemy.getE_x() > x) x += speed;
            if (enemy.getE_x() < x) x -= speed;
            if (enemy.getE_y() > y) y += speed;
            if (enemy.getE_y() < y) y -= speed;

        }
    }
    public Rectangle hitBox(){
        return new Rectangle(x+(getWidth()/4),y+(getWidth()/4),width/2,height/2);
    }
    public boolean collision(Player player){
        return player.hitBox().intersects(hitBox());
    }
    public boolean collision1(Enemy enemy){
        return enemy.hitBox().intersects(hitBox());
    }



    public void draw(Graphics g){
        g.drawImage(image,x,y,width,height,null);
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

    public void setLp(boolean lp) {
        this.lp = lp;
    }
}
