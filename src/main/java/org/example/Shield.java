package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Shield implements MouseMotionListener, KeyListener {
//    private String SOUBOR_SHIELD = "src/main/resources/shield.png";
    Image[] SHIELDPUSH = SpriteLoader.getFrames("/shieldAnimationPush.png",16,16,4);
    Image[] SHIELD = SpriteLoader.getFrames("/shieldAnimation.png",16,16,4);

    private Image image;
    private Image img;
    Player player;

//    Projectile1 projectyle;
    ProjectileSettings projectileS;
    private int cooldown=10,cooldown1=1;
    private int sx;
    private int sy;
    private int s_x ;
    private int s_y ;
    private int s_w ;
    private int s_h;
    private int x,y;
    private int otoceni,posun,zmenseni=1,posunh;
    private int radius = 50;

    private int index,indexPush;

    private int shieldMode=1;

    private boolean je= false;
    private boolean je1= false;





    public Shield(Player player, int s_w, int s_h) {
//        image = new ImageIcon(SOUBOR_SHIELD).getImage();
//        img = new ImageIcon(SOUBOR_SH).getImage();
        this.player = player;

        this.s_w = s_w;
        this.s_h = s_h;

        this.x = x;
        this.y = y;


    }


    public void shieldRotate(){
        if (player != null) {
            int dx = sx - player.getPl_x();
            int dy = sy - player.getPl_y();
            double angle = Math.atan2(dy, dx);

            int shieldX = (int) (player.getPl_x() + Math.cos(angle) * radius);
            int shieldY = (int) (player.getPl_y() + Math.sin(angle) * radius);

            s_x=shieldX;
            s_y=shieldY;
            s_x+=10;

        }
    }
    public void Cooldown(){
        if(je) {
            cooldown--;

            if (cooldown <= 0) {
                radius--;
                System.out.println(radius);

                shieldMode = 1;

                if (radius <= 50) {
                    radius = 50;
//                    shieldMode = 1;
                    cooldown = 20;
                    cooldown1 = 30;
                    je = false;
                }
            }
        }
    }

    public void shieldAnimation(){
            if (s_y>= player.getPl_y()&&s_y<= player.getPl_y()+40){
                index=1;
                indexPush=1;
                zmenseni=2;
                posunh=10;
                if(s_x<=player.getPl_x()+20){
                    otoceni=-1;
                    posun=50;

                }else{ otoceni=1;posun=0;}
            }else if(s_y>=player.getPl_y()){index=0;indexPush=0;zmenseni=1;posunh=0;}

        if (s_y<= player.getPl_y()&&s_y>= player.getPl_y()-40){
            index=2;
            indexPush=2;
            zmenseni=2;
            posunh=10;
            if(s_x<=player.getPl_x()+20){
                otoceni=-1;
                posun=50;

            }else {otoceni=1;posun=0;}
        }else if (s_y<= player.getPl_y()-40){index=3;indexPush=3;zmenseni=1;posunh=0;}

    }


    public Rectangle hitBox() {
            return new Rectangle(s_x+posunh, s_y, s_w/zmenseni, s_h);

    }
    public Rectangle hitBoxodr(){
            return new Rectangle(s_x + (getS_w() / 4), s_y, s_w / 2, s_h);
    }

    public boolean collision(EnemySettings enemyS) {
        return enemyS.hitBox().intersects(hitBox());
    }
    public boolean collision1(ProjectileSettings projectileS){
        return projectileS.hitBox().intersects(hitBox());
    }



    public void vykresleniObr(Graphics g) {


//        g.drawImage(image,s_x+10,s_y,s_w,s_h,null);
//        g.drawRect(s_x+(getS_w()/4),s_y,s_w/2,s_h);
//        g.drawRect(s_x+posunh , s_y, s_w/zmenseni , s_h);
        if (je==false) {
            g.drawImage(SHIELD[index], s_x+posun, s_y, s_w*otoceni, s_h, null);
        }

        if (je == true){
//            g.drawImage(img,s_x,s_y,s_w,s_h,null);
            g.drawImage(SHIELDPUSH[indexPush],s_x+posun, s_y, s_w*otoceni, s_h, null);
            if (je1) {
                shieldMode = 2;
                radius=65;

                je1 = false;
            }
//            radius=60;

        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        sy=e.getY();
        sx=e.getX();
    }



    public int getS_x() {
        return s_x;
    }

    public void setS_x(int s_x) {
        this.s_x = s_x;
    }

    public int getS_y() {
        return s_y;
    }

    public void setS_y(int s_y) {
        this.s_y = s_y;
    }
    public int getS_w() {
        return s_w;
    }

    public void setS_w(int s_w) {
        this.s_w = s_w;
    }
    public int getS_h() {
        return s_h;
    }
    public void setS_h(int s_h) {
        this.s_h = s_h;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char znk = e.getKeyChar();
        if (znk == 'r'){

            je = true;
            je1= true;


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
}
