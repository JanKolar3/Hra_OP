package org.example;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener {

    Image[] PLAYER_UP = SpriteLoader.getFrames("/Player/playerAnimUp.png",16,16,2);
    Image[] PLAYER_DOWN = SpriteLoader.getFrames("/Player/playerAnimDownts (1).png",16,16,3);

    private int index = 0;

    private int x,y,width,height;
    private final int pl_speed = 2;
    private int cooldown=20;
    private boolean k = true;
    private boolean up,down,left,right,num,lf,rg,p;



    private String direction;



    public Player(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle hitBox() {
        return new Rectangle(x +(getWidth()/4), y, width /2, height);
    }
//    public boolean collision(Projectile1 projectyle) {
//        return projectyle.hitBox().intersects(hitBox());
//    }

    public void moveMent() {
        if (up) {
            y -= pl_speed;
            direction = "up";
        }
        if (down) {
            y += pl_speed;
            direction = "down";
        }
        if (left) {
            x -= pl_speed;
            direction = "left";
        }
        if (right) {
            x += pl_speed;
            direction = "right";
        }
    }

    public void ohraniceni(){

        if (x <-70){
            x =650;
        }
        if (x >650){
            x =-70;
        }
        if (y <0){
            y =700;
        }
        if (y >700){
            y =0;
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

    public void paint(Graphics g) {


        if ((k&&!num&&rg)||!p) {
            g.drawImage(PLAYER_DOWN[0], x, y, width, height, null);
        }

        if (k&&!num&&lf) {
            g.drawImage(PLAYER_DOWN[0], x + 80, y, -width, height, null);
        }

        if (direction == "up"&&num) {
            g.drawImage(PLAYER_UP[index], x, y, width, height, null);
//            g.setColor(Color.black);
//            g.fillOval(pl_x-20, pl_y+50, pl_width, pl_height-20);
            k = false;
            rg = true;
            lf = false;
        }
        if (direction == "right"&&num) {
            g.drawImage(PLAYER_DOWN[index], x, y, width, height, null);
            k = false;
            rg = true;
            lf = false;
        }
        if (direction == "left"&&num) {
            g.drawImage(PLAYER_DOWN[index], x + 80, y, -width, height, null);
            k = false;
            lf = true;
            rg = false;
        }
        if (direction == "down"&&num) {
            g.drawImage(PLAYER_DOWN[index], x, y, width, height, null);
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





    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setPl_y() {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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
