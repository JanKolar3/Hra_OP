package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Shield implements MouseMotionListener, KeyListener {
    private String SOUBOR_SHIELD = "src/main/resources/shield.png";
    private String SOUBOR_SH = "src/main/resources/shieldPush.png";

    private Image image;
    private Image img;
    Player player;

//    Projectile1 projectyle;
    ProjectileSettings projectileS;
    private int cooldown=60;
    private int x;
    private int y;
    private int s_x ;
    private int s_y ;
    private int s_w ;
    private int s_h;

    private int shieldMode=1;

    private boolean je= false;





    public Shield(int s_w, int s_h) {
        image = new ImageIcon(SOUBOR_SHIELD).getImage();
        img = new ImageIcon(SOUBOR_SH).getImage();

        this.s_w = s_w;
        this.s_h = s_h;

    }

    public Rectangle hitBox() {
            return new Rectangle(s_x + (getS_w() / 4), s_y, s_w / 2, s_h);

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








    public void cooldown(){
        if(je) {
            cooldown--;
        }
    }


    public void vykresleniObr(Graphics g) {

        g.drawImage(image,s_x,s_y,s_w,s_h,null);
//        g.drawRect(s_x+(getS_w()/4),s_y,s_w/2,s_h);

        if (je == true){
            g.drawImage(img,s_x,s_y,s_w,s_h,null);
            shieldMode = 2;
                    if (cooldown == 0) {
                        cooldown =60;
                        shieldMode = 1;
                        je = false;
                    }
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        y=e.getY();
        x=e.getX();
//        s_x = e.getX() - (s_w / 2);
//        s_y = e.getY() - (s_w / 2);

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


//            System.out.println("RRRRR");


        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        char znk = e.getKeyChar();
        if (znk == 'r') {
            je = false;
        }
    }

    public int getShieldMode() {
        return shieldMode;
    }
}
