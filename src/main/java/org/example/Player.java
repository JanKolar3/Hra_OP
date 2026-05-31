package org.example;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener {

    Image[] PLAYER = SpriteLoader.getFrames("/Player/Player.png",16,16,4);

    private int rotate = 1;
    private int moveX = 0;

    private int index = 0;

    private int x,y;
    private int width,height;
    private final int speed = 2;
    private int cooldown=20, cooldownStay =0;
    private boolean up=false,down=false,left=false,right=false;
    private int move;

    public Player(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle hitBox() {
        return new Rectangle(x +(getWidth()/4), y, width /2, height);
    }
    public void moveMent() {

        if (up) {
            y -= speed;
        }
        if (down) {
            y += speed;
        }
        if (left) {
            x -= speed;
        }
        if (right) {
            x += speed;
        }
    playerAnimation();
    border();
    }

    private void border(){

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
    private void rotate(){
        if (left) {
            moveX = 80;
            rotate = -1;
        }else if (right){
            moveX = 0;rotate=1;}
    }
    private void animation(){
        cooldown--;
        if (cooldown <= 0) {
            index++;
            if (index >= 3) {
                index = 0;
            }
            cooldown = 10;
        }
        cooldownStay =0;
    }

    private void playerAnimation() {
        switch (move) {
            case 0:
                cooldownStay++;
                if (cooldownStay >=0){
                if (cooldownStay > 20&& cooldownStay < 40) {
                    index = 0;
                }
                    if (cooldownStay > 100) {
                        index = 3;
                        cooldownStay = 0;
                    }
                }else {index = 1;
                    cooldownStay =0;}
                cooldown=0;
                break;
            case 1:
                animation();
                break;
            case 2:
                animation();
                break;
            case 3:
                animation();
                break;
            case 4:
                animation();
                break;
        }
            rotate();
    }



    public void drawPlayer(Graphics g) {
        g.drawImage(PLAYER[index],x+ moveX,y,width*rotate,height,null );
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char znak = e.getKeyChar();

        if  (znak == 'w') {
            up = true;
            move =1;
        }if  (znak == 's') {
            down = true;
            move =2;
        }if  (znak == 'a') {
            left = true;
            move =3;
        }if  (znak == 'd') {
            right=true;
            move =4;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char znak = e.getKeyChar();
        if  (znak == 'w') {
            up = false;
            move =0;
        }if  (znak == 's') {
            down = false;
            move =0;
        }if  (znak == 'a') {
            left = false;
            move =0;
        }if  (znak == 'd') {
            right=false;
            move =0;
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


}
