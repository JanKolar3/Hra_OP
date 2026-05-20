package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PanelInfo extends JPanel{

//    private final String SOUBOR_POZADI = "src/main/resources/les.png";
//    private final String SOUBRO_OHRANI = "src/main/resources/lesUP.png";
    Image[] HEALTHS = SpriteLoader.getFrames("/Player/healts.png",16,16,3);


//    private final Image image1;
//    private final Image image2;
    Menu menu;
    private int mode1,mode2,mode3;
    private int x,y,w,h;





    public PanelInfo(int x,int y,int w,int h) {
//        menu = new Menu(getX(),getY(),640,640);

//        image1 = new ImageIcon(SOUBOR_POZADI).getImage();
//        image2 = new ImageIcon(SOUBRO_OHRANI).getImage();

        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;



    }

    public void health(int health) {
        switch (health) {
            case 6:
                mode3 = 0;
                break;
            case 5:
                mode3 = 1;
                break;
            case 4:
                mode3 = 2;
                mode2 = 0;
                break;
            case 3:
                mode2 = 1;
                break;
            case 2:
                mode2 = 2;
                mode1 = 0;
                break;
            case 1:
                mode1 = 1;
                break;
            case 0:
                mode1 = 2;
                break;
        }
    }


    public void vykreliseni(Graphics g) {

//        g.drawImage(image2, getX(), getY(), getWidth(), getHeight(), null);

        g.drawImage(HEALTHS[mode3], 20 * 5, 20, 48, 48, null);
        g.drawImage(HEALTHS[mode2], 20 * 3, 20, 48, 48, null);
        g.drawImage(HEALTHS[mode1], 20, 20, 48, 48, null);
//        g.drawImage(image2, getX(), getY(), getWidth(), getHeight(), null);

    }





}