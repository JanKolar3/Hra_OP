package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Shield implements MouseMotionListener {
    private String SOUBOR_SHIELD = "src/main/resources/shield.png";
    private Image image;
    Player player;
//    Projectyle projectyle;

    private int x;
    private int y;
    private int s_x ;
    private int s_y ;
    private int s_w ;
    private int s_h;





    public Shield(int s_w, int s_h) {
        image = new ImageIcon(SOUBOR_SHIELD).getImage();

        this.s_w = s_w;
        this.s_h = s_h;

    }

    public Rectangle hitBox() {
        return new Rectangle(s_x+(getS_w()/4),s_y,s_w/2,s_h);
    }
    public boolean collision(Enemy enemy) {
        return enemy.hitBox().intersects(hitBox());
    }
    public boolean collision1(Projectyle projectyle){
        return projectyle.hitBox().intersects(hitBox());
    }

    public void vykresleniObr(Graphics g) {
        g.drawImage(image,s_x,s_y,s_w,s_h,null);
        g.drawRect(s_x+(getS_w()/4),s_y,s_w/2,s_h);
    }
    public void pohyb(){

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
}
