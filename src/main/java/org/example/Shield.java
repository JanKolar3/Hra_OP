package org.example;

import java.awt.*;
import java.awt.event.*;

public class Shield implements MouseMotionListener, KeyListener {

    Image[] SHIELDPUSH = SpriteLoader.getFrames("/Player/shieldAnimationPush.png",16,16,4);
    Image[] SHIELD = SpriteLoader.getFrames("/Player/shieldAnimation.png",16,16,4);


    private Player player;

    private int cooldown=10,cooldownAktivace=100;
    private int mousex;
    private int mousey;
    private int x;
    private int y;
    private int width;
    private int height;
    private int anglex, angley;
    private int rotate=1, movex=0, hitboxSize =1, hitboxMove;
    private int radius = 50;

    private int index, indexPush;

    private int shieldMode=1;

    private boolean shieldReturn = false;
    private boolean shiueldBounce = false;
    private boolean aktivationBounce = false;
    private boolean repeat =false;





    public Shield(Player player, int s_w, int s_h) {
        this.player = player;
        this.width = s_w;
        this.height = s_h;
    }


    public void shieldRotate(){
        if (player != null) {
            anglex = mousex - player.getX();
            angley = mousey - player.getY();
            double angle = Math.atan2(angley, anglex);

            x = (int) (player.getX() + Math.cos(angle) * radius);
            y = (int) (player.getY() + Math.sin(angle) * radius);
            x += 10;

            cooldown();
            shieldAnimation();
        }
    }
    private void cooldown(){
        if(shieldReturn) {
            cooldown--;
            if (cooldown <= 0) {
                radius--;
                shieldMode = 1;
                if (radius <= 50) {
                    radius = 50;
                    cooldown = 20;
                    shieldReturn = false;
                }
            }
        }
        if (repeat) {
            cooldownAktivace--;
            if (cooldownAktivace <= 0) {
                cooldownAktivace = 100;
                aktivationBounce = false;
                repeat = false;
            }
        }
    }

    private void shieldAnimation(){
            if (y >= player.getY()&& y <= player.getY()+40){
                index=1;
                indexPush=1;
                hitboxSize =2;
                hitboxMove =10;
                if(x <=player.getX()+20){
                    rotate=-1;
                    movex =50;

                }else{ rotate=1;
                    movex =0;}
            }else if(y >=player.getY()){index=0;indexPush=0;
                hitboxSize =1;
                hitboxMove =0;}

        if (y <= player.getY()&& y >= player.getY()-40){
            index=2;
            indexPush=2;
            hitboxSize =2;
            hitboxMove =10;
            if(x <=player.getX()+20){
                rotate=-1;
                movex =50;

            }else {rotate=1;
                movex =0;}
        }else if (y <= player.getY()-40){index=3;indexPush=3;
            hitboxSize =1;
            hitboxMove =0;}
    }


    public Rectangle hitBox() {
            return new Rectangle(x + hitboxMove, y, width / hitboxSize, height);
    }
    public boolean collision(ProjectileSettings projectileS){
        return projectileS.hitBox().intersects(hitBox());
    }
    public void drawShield(Graphics g) {
        if (!shieldReturn) {
            g.drawImage(SHIELD[index], x + movex, y, width *rotate, height, null);
        }
        if (shieldReturn){
            g.drawImage(SHIELDPUSH[indexPush], x + movex, y, width *rotate, height, null);
            if (shiueldBounce) {
                shieldMode = 2;
                radius=65;
                aktivationBounce = true;
                shiueldBounce = false;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousey =e.getY();
        mousex =e.getX();
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char znk = e.getKeyChar();
        if (znk == 'r'&& !aktivationBounce){

            shiueldBounce = true;
            shieldReturn = true;
            repeat = true;

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

    public boolean isAktivationBounce() {
        return aktivationBounce;
    }

    public boolean isShiueldBounce() {
        return shiueldBounce;
    }
}

