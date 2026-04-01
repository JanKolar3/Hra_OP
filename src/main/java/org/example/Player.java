package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener {

    private String SOUBOR_PLAYER = "src/main/resources/Player1.png";
//    Image[] PLAYER = SpriteLoader.getFrames("src/main/resources/PlayerAnimace.png",16,16,8);
    private Image img;
    Enemy enemy;
    private int index = 0;
    private int index_count = 8;
    private int animation_speed = 0;
    private int pl_x;
    private int pl_y;
    private int pl_width;
    private int pl_height;
    private int pl_speed = 5;
    private int health;



    public Player(int x, int y, int width, int height) {

        img = new ImageIcon(SOUBOR_PLAYER).getImage();
//        img = new ImageIcon(PLAYER[index]).getImage();


        this.pl_x = x;
        this.pl_y = y;
        this.pl_width = width;
        this.pl_height = height;
    }

    public Rectangle hitBox() {

        return new Rectangle(pl_x+(getPl_width()/4), pl_y, pl_width/2, pl_height);
    }

//    public boolean collision() {

//        return enemy.collision().intersects(hitBox());
//    }

    public void vykresleniObr(Graphics g) {
        g.drawImage(img,pl_x,pl_y,pl_height,pl_width,null);
        g.drawRect(pl_x+(getPl_width()/4), pl_y, pl_width/2, pl_height);
//        g.drawImage(PLAYER[index],pl_x,pl_y,pl_width,pl_height, null);
    }



    public void playerAnimation() {
        animation_speed++;
        if (animation_speed >= 3) {
            index++;
            if (index >= index_count) {
                index = 0;
            }
            animation_speed = 0;
        }
    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char znak = e.getKeyChar();
        if  (znak == 'w') {
            pl_y-= pl_speed;

        }if  (znak == 's') {
            pl_y+= pl_speed;
        }if  (znak == 'a') {
            pl_x-= pl_speed;
        }if  (znak == 'd') {
            pl_x+= pl_speed;
//            System.out.println("AAAAAAA");
            }


    }

    @Override
    public void keyReleased(KeyEvent e) {
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


}
