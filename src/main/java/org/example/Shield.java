package org.example;

import java.awt.*;
import java.awt.event.*;

public class Shield implements MouseMotionListener, KeyListener {

    Image[] SHIELDPUSH = SpriteLoader.getFrames("/shieldAnimationPush.png",16,16,4);
    Image[] SHIELD = SpriteLoader.getFrames("/shieldAnimation.png",16,16,4);


    Player player;

    private int cooldown=10,cooldownAktivace=100;
    private int sx;
    private int sy;
    private int x;
    private int y;
    private int w;
    private int h;
    private int dx,dy;
    private int otoceni, posun, zmenseni=1, posunh;
    private int radius = 50;

    private int index, indexPush;

    private int shieldMode=1;

    private boolean je= false;
    private boolean je1= false;
    private boolean aktivace= false;
    private boolean akt=false;





    public Shield(Player player, int s_w, int s_h) {
        this.player = player;
        this.w = s_w;
        this.h = s_h;
    }


    public void shieldRotate(){
        if (player != null) {
            dx = sx - player.getX();
            dy = sy - player.getY();
            double angle = Math.atan2(dy, dx);

            int shieldX = (int) (player.getX() + Math.cos(angle) * radius);
            int shieldY = (int) (player.getY() + Math.sin(angle) * radius);

            x =shieldX;
            y =shieldY;
            x +=10;

        }
        cooldown();
        shieldAnimation();
    }
    public void cooldown(){
        if(je) {
            cooldown--;

            if (cooldown <= 0) {
                radius--;

                shieldMode = 1;

                if (radius <= 50) {
                    radius = 50;
                    cooldown = 20;
                    je = false;
                }

            }
        }
        if (akt) {
            cooldownAktivace--;
            if (cooldownAktivace <= 0) {
                cooldownAktivace = 100;
                aktivace = false;
                akt = false;
            }
        }
    }

    public void shieldAnimation(){
            if (y >= player.getY()&& y <= player.getY()+40){
                index=1;
                indexPush=1;
                zmenseni=2;
                posunh=10;
                if(x <=player.getX()+20){
                    otoceni=-1;
                    posun=50;

                }else{ otoceni=1;posun=0;}
            }else if(y >=player.getY()){index=0;indexPush=0;zmenseni=1;posunh=0;}

        if (y <= player.getY()&& y >= player.getY()-40){
            index=2;
            indexPush=2;
            zmenseni=2;
            posunh=10;
            if(x <=player.getX()+20){
                otoceni=-1;
                posun=50;

            }else {otoceni=1;posun=0;}
        }else if (y <= player.getY()-40){index=3;indexPush=3;zmenseni=1;posunh=0;}
    }


    public Rectangle hitBox() {
            return new Rectangle(x +posunh, y, w /zmenseni, h);
    }
    public boolean collision(ProjectileSettings projectileS){
        return projectileS.hitBox().intersects(hitBox());
    }
    public void paint(Graphics g) {
        if (!je) {
            g.drawImage(SHIELD[index], x +posun, y, w *otoceni, h, null);
        }

        if (je){
            g.drawImage(SHIELDPUSH[indexPush], x +posun, y, w *otoceni, h, null);
            if (je1) {
                shieldMode = 2;
                radius=65;
                aktivace = true;
                je1 = false;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        sy =e.getY();
        sx =e.getX();
    }

    public int getCooldownAktivace() {
        return cooldownAktivace;
    }

    public int getS_x() {
        return x;
    }

    public void setS_x(int s_x) {
        this.x = s_x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }
    public int getH() {
        return h;
    }
    public void setH(int h) {
        this.h = h;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char znk = e.getKeyChar();
        if (znk == 'r'&& !aktivace){

            je = true;
            je1= true;
            akt = true;


//            System.out.println("RRRRR");


        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        char znk = e.getKeyChar();
        if (znk == 'r') {
        }
    }

    public int getShieldMode() {
        return shieldMode;
    }

    public boolean isAktivace() {
        return aktivace;
    }

    public boolean isJe1() {
        return je1;
    }
}

