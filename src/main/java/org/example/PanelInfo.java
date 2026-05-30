package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelInfo extends JPanel implements KeyListener {

    Image[] HEALTHS = SpriteLoader.getFrames("/Info/healts.png",16,16,3);
    private final String OVER = "src/main/resources/Info/GameOver.png";
    private final String POUSE = "src/main/resources/Info/pause.png";
    private int mode1,mode2,mode3;
    private int akt=100,akti=100-1;
    private boolean gameOver = false;
    private boolean victory = false;
    private boolean pause = false;
    private boolean poused=false;
    private Image over;
    private Image pouse;

    private String wave1,score1;
    public PanelInfo(){
        over = new ImageIcon(OVER).getImage();
        pouse = new ImageIcon(POUSE).getImage();

    }

    public void info(int health,boolean aktivation,int wave, int score,boolean victory){
        health(health);
        shieldTimer(aktivation);
        levelInfo(wave,score);
        this.victory = victory;
    }

    private void health(int health) {
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
        if (health <= 0) {
            gameOver = true;
        }
    }
    private void shieldTimer(boolean aktivace){
        if (akt<=akti){
            akt++;
        }
        if (aktivace==true){
            akt=0;
        }
    }
    private void levelInfo(int wave,int score) {
        wave1 = String.valueOf(wave);
        score1 = String.valueOf(score);
    }

    public void reset(){
        gameOver=false;
    }

    public void vykreliseni(Graphics g) {

        g.drawImage(HEALTHS[mode3], 100, 20, 48, 48, null);
        g.drawImage(HEALTHS[mode2], 60, 20, 48, 48, null);
        g.drawImage(HEALTHS[mode1], 20, 20, 48, 48, null);

        g.setColor(Color.black);
        g.fillRect(500,30,akti+1,20);
        g.setColor(Color.yellow);
        g.fillRect(500,30,akt,20);

        g.setFont(new Font("Arial", Font.BOLD,15));
        g.setColor(Color.BLACK);
        g.drawString("Score: "+score1, 230, 35);
        g.drawString("Wave: "+wave1+" / 10", 330, 35);

        if (gameOver){
            g.drawImage(over,0,0,640,640,null);
            g.setFont(new Font("Arial", Font.BOLD,50));
            g.setColor(Color.RED);
            g.drawString("Game Over", 200, 320);
            g.drawString("Score: "+score1, 200, 400);
        }

        if (victory) {
            g.setFont(new Font("Arial", Font.BOLD,64));
            g.setColor(Color.GREEN);
            g.drawString("Victory", 150, 320);
            g.drawString("Score: "+score1, 150, 400);
        }
        if (pause) {
            g.drawImage(pouse,0,0,655,675,null);
        }



    }


    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        if (key=='q'){
            if (poused==false) {
                pause = true;
                poused=true;

            }
            else if(poused==true){
                pause = false;
                poused=false;
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public boolean isPause() {
        return pause;
    }
}