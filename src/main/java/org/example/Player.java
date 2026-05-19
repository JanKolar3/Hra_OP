package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class Player implements KeyListener {

    Image[] PLAYER_UP = SpriteLoader.getFrames("/Player/playerAnimUp.png",16,16,2);
    Image[] PLAYER_DOWN = SpriteLoader.getFrames("/Player/playerAnimDownts (1).png",16,16,3);

    private int index = 0;
//    private int index_count = 8;
//    private int animation_speed = 0;
    private int pl_x;
    private int pl_y;
    private int pl_width;
    private int pl_height;
    private int pl_speed = 2;

    private int cooldown=20;
    private boolean k = true;
    private boolean up,down,left,right,num,lf,rg,p;



    private String direction;



    public Player(int x, int y, int width, int height) {

        this.pl_x = x;
        this.pl_y = y;
        this.pl_width = width;
        this.pl_height = height;

    }

    public Rectangle hitBox() {
        return new Rectangle(pl_x+(getPl_width()/4), pl_y, pl_width/2, pl_height);
    }
//    public boolean collision(Projectile1 projectyle) {
//        return projectyle.hitBox().intersects(hitBox());
//    }

    public void moveMent() {
        if (up) {
            pl_y -= pl_speed;
            direction = "up";
        }
        if (down) {
            pl_y += pl_speed;
            direction = "down";
        }
        if (left) {
            pl_x -= pl_speed;
            direction = "left";
        }
        if (right) {
            pl_x += pl_speed;
            direction = "right";
        }
    }

    public void ohraniceni(){

        if (pl_x<-100){
            pl_x=700;
        }
        if (pl_x>700){
            pl_x=-100;
        }
        if (pl_y<-100){
            pl_y=700;
        }
        if (pl_y>700){
            pl_y=-100;
        }
    }

    public void playerAnimation() {
//        animation_speed++;
//        if (animation_speed >= 3) {
        if (num) {
            cooldown--;
            if (cooldown <= 0) {
                index++;
                if (index >= 2) {
                    index = 0;

                }
                cooldown = 20;
            }
        }
    }

    public void vykresleniObr(Graphics g) {


        if ((k&&!num&&rg)||!p) {
            g.drawImage(PLAYER_DOWN[0], pl_x, pl_y, pl_width, pl_height, null);
        }

        if (k&&!num&&lf) {
            g.drawImage(PLAYER_DOWN[0], pl_x + 80, pl_y, -pl_width, pl_height, null);
        }

        if (direction == "up"&&num) {
            g.drawImage(PLAYER_UP[index], pl_x, pl_y, pl_width, pl_height, null);
            k = false;
            rg = true;
            lf = false;
        }
        if (direction == "right"&&num) {
            g.drawImage(PLAYER_DOWN[index], pl_x, pl_y, pl_width, pl_height, null);
            k = false;
            rg = true;
            lf = false;
        }
        if (direction == "left"&&num) {
            g.drawImage(PLAYER_DOWN[index], pl_x + 80, pl_y, -pl_width, pl_height, null);
            k = false;
            lf = true;
            rg = false;
        }
        if (direction == "down"&&num) {
            g.drawImage(PLAYER_DOWN[index], pl_x, pl_y, pl_width, pl_height, null);
            k = false;
            rg = true;
            lf = false;
        }
        k = true;
    }




    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char znak = e.getKeyChar();

        if  (znak == 'w') {
            up = true;
            num=true;
            p=true;

        }if  (znak == 's') {
            down = true;
            num=true;
            p=true;

        }if  (znak == 'a') {
            left = true;
            num=true;
            p=true;

        }if  (znak == 'd') {
            right=true;
            num=true;
            p=true;

            }



    }

    @Override
    public void keyReleased(KeyEvent e) {
        char znak = e.getKeyChar();
        if  (znak == 'w') {
            up = false;
            num=false;
        }if  (znak == 's') {
            down = false;
            num=false;
        }if  (znak == 'a') {
            left = false;
            num=false;
        }if  (znak == 'd') {
            right=false;
            num=false;
        }
    }





    public int getPl_x() {
        return pl_x;
    }

    public void setPl_x(int pl_x) {
        this.pl_x = pl_x;
    }

    public int getPl_y() {
        return pl_y;
    }

    public void setPl_y() {
        this.pl_y = pl_y;
    }

    public int getPl_width() {
        return pl_width;
    }

    public void setPl_width(int pl_width) {
        this.pl_width = pl_width;
    }

    public int getPl_height() {
        return pl_height;
    }

    public void setPl_height(int pl_height) {
        this.pl_height = pl_height;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDirection() {
        return direction;
    }

}
