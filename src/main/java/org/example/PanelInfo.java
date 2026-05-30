package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PanelInfo extends JPanel{

    Image[] HEALTHS = SpriteLoader.getFrames("/Info/healts.png",16,16,3);

    private int mode1,mode2,mode3;
    private int akt=100,akti=100-1;
    int score=0;
    private String level1,wave1,score1;


    public PanelInfo(){
    }

    public void info(int health,boolean aktivation,int level,int wave, int score){
        health(health);
        shieldTimer(aktivation);
        levelInfo(level,wave,score);
    }

    public void health(int health) {
        switch (health) {
            case 6:
                mode1 = 0;
                mode2  =0;
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
    public void shieldTimer(boolean aktivace){
        if (akt<=akti){
            akt++;
        }
        if (aktivace==true){
            akt=0;
        }
    }
    public void levelInfo(int level,int wave,int score) {
        level1 = String.valueOf(level);
        wave1 = String.valueOf(wave);
        score1 = String.valueOf(score);
    }

    public void vykreliseni(Graphics g) {

//        g.drawImage(image2, getX(), getY(), getWidth(), getHeight(), null);


        g.drawImage(HEALTHS[mode3], 100, 20, 48, 48, null);
        g.drawImage(HEALTHS[mode2], 60, 20, 48, 48, null);
        g.drawImage(HEALTHS[mode1], 20, 20, 48, 48, null);
//        g.drawImage(image2, getX(), getY(), getWidth(), getHeight(), null);

        g.setColor(Color.black);
        g.fillRect(500,30,akti+1,20);
        g.setColor(Color.yellow);
        g.fillRect(500,30,akt,20);

        g.setFont(new Font("Arial", Font.BOLD,15));
        g.setColor(Color.BLACK);



        g.drawString("Score: "+score1, 230, 35);
//        g.drawString("Level: "+level1+" / 2", 230, 35);
        g.drawString("Wave: "+wave1+" / 5", 330, 35);






    }





}