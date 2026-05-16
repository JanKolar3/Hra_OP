package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class Player implements KeyListener {

//    private String SOUBOR_PLAYER = "src/main/resources/Player/Player1.png";
//    private String SOUBOR_PLAYER2 = "src/main/resources/Player/Player_w.png";
//    private String SOUBOR_PLAYER3 = "src/main/resources/Player/Player_a.png";



    Image[] PLAYER_UP = SpriteLoader.getFrames("/Player/playerAnimUp.png",16,16,2);
    Image[] PLAYER_DOWN = SpriteLoader.getFrames("/Player/playerAnimDownts (1).png",16,16,3);
    Image[] HEALTHS = SpriteLoader.getFrames("/Player/healts.png",16,16,3);


    private Image img;
    private Image[] himg1 , himg2, himg3;
    Enemy1 enemy;
    Projectile1 projectyle;
    GameManager gameManager;
    Image image;
    Shield shield;
    private int index = 0;
    private int index_count = 8;
    private int animation_speed = 0;
    private int pl_x;
    private int pl_y;
    private int pl_width;
    private int pl_height;
    private int pl_speed = 2;
    private int health=6;
    private int hp=2;
    private int healthMode=0,mode1,mode2,mode3;
    private int cooldown=20;
    private boolean k = true;
    private boolean up,down,left,right,num,lf,rg,p;
    private int hX,hY,hW,HH;


    private String direction;



    public Player(int x, int y, int width, int height,int hX,int hY,int hW, int hH,int health) {


//        img = new ImageIcon(SOUBOR_PLAYER).getImage();



//        himg1= new Image[mode3];

//        himg1 = new Image[] HEALTH;


//        img2 = new ImageIcon(SOUBOR_PLAYER2).getImage();
//        img3 = new ImageIcon(SOUBOR_PLAYER3).getImage();
//        img = new ImageIcon(PLAYER[index]).getImage();
//        shield = new Shield(pl_x,pl_y,16*3,16*3);


        this.pl_x = x;
        this.pl_y = y;
        this.pl_width = width;
        this.pl_height = height;

        this.health = health;

        this.hX = hX;
        this.hY = hY;
        this.hW = hW;
        this.HH = hH;

    }

    public Rectangle hitBox() {
        return new Rectangle(pl_x+(getPl_width()/4), pl_y, pl_width/2, pl_height);
    }
    public boolean collision(Projectile1 projectyle) {
        return projectyle.hitBox().intersects(hitBox());
    }

    public void health(int health) {
//        System.out.println("health changed to " + health);
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
        System.out.println("health: " + health);
//
//
//        if (health<=6&&health>=4) {
//            mode3 = 1;
//            if (health == 5) {
//                mode3 = 2;
//                if (health == 4) {
//                    mode3 = 3;
//                }
//            }
//
//        } if (health<4) {mode3 = 3;}
//
//        if (health<=4&&health>=2){
//            mode2=1;
//            if (health==3){
//                mode2=2;
//                if (health==2){
//                    mode2=3;
//                }
//            }
//
//        }
//        if (health<=2&&health>=0){
//            mode1=1;
//            if (health==1){
//                mode1=2;
//                if (health==0){
//                    mode1=3;
//                }
//            }
//
//        }
//        if (health<=4) {mode3 = 3;}
//        if (health<=2) {mode2 = 3;}
//        if (health<=0) {mode1 = 3;}


    }

    public void moveMent() {
        if (up) {
            pl_y -= pl_speed;
            direction = "up";
        }
        if (down) {
            pl_y += pl_speed;
            direction = "down";
        }
        if (left) {
            pl_x -= pl_speed;
            direction = "left";
        }
        if (right) {
            pl_x += pl_speed;
            direction = "right";
        }
    }

    public void ohraniceni(){

        if (pl_x<-100){
            pl_x=700;
        }
        if (pl_x>700){
            pl_x=-100;
        }
        if (pl_y<-100){
            pl_y=700;
        }
        if (pl_y>700){
            pl_y=-100;
        }
    }

    public void playerAnimation() {
//        animation_speed++;
//        if (animation_speed >= 3) {
        if (num) {
            cooldown--;
            if (cooldown <= 0) {
                index++;
                if (index >= 2) {
//                    System.out.println("index: " + index);;
                    index = 0;

                }
                cooldown = 20;
            }
        }
    }

    public void vykresleniObr(Graphics g) {
//        g.drawImage(img,pl_x,pl_y,pl_height,pl_width,null);
//        g.drawRect(pl_x+(getPl_width()/4), pl_y, pl_width/2, pl_height);



        if ((k&&!num&&rg)||!p) {
            g.drawImage(PLAYER_DOWN[0], pl_x, pl_y, pl_width, pl_height, null);
        }

        if (k&&!num&&lf) {
            g.drawImage(PLAYER_DOWN[0], pl_x + 80, pl_y, -pl_width, pl_height, null);
        }

        if (direction == "up"&&num) {
            g.drawImage(PLAYER_UP[index], pl_x, pl_y, pl_width, pl_height, null);
            k = false;
            rg = true;
            lf = false;
        }
        if (direction == "right"&&num) {
            g.drawImage(PLAYER_DOWN[index], pl_x, pl_y, pl_width, pl_height, null);
            k = false;
            rg = true;
            lf = false;
        }
        if (direction == "left"&&num) {
            g.drawImage(PLAYER_DOWN[index], pl_x + 80, pl_y, -pl_width, pl_height, null);
            k = false;
            lf = true;
            rg = false;
        }
        if (direction == "down"&&num) {
            g.drawImage(PLAYER_DOWN[index], pl_x, pl_y, pl_width, pl_height, null);
            k = false;
            rg = true;
            lf = false;
        }
        k = true;



//                case "L":
//                direction = "L";
//                break;
//                case "R":
//                direction = "R";
//
//

//
//        g.drawImage(PLAYER[index],pl_x,pl_y,pl_width,pl_height, null);

        g.drawImage(HEALTHS[mode3], hX * 5, hY, hW, HH, null);
        g.drawImage(HEALTHS[mode2], hX * 3, hY, hW, HH, null);
        g.drawImage(HEALTHS[mode1], hX, hY, hW, HH, null);



//        g.drawImage(HEALTHS[mode2],hX*3,hY,hW,HH,null);
//        g.drawImage(HEALTHS[mode3],hX*5,hY,hW,HH,null);

    }




    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char znak = e.getKeyChar();

        if  (znak == 'w') {
            up = true;
            num=true;
            p=true;
//            pl_y-= pl_speed;
//            direction = "up";
        }if  (znak == 's') {
            down = true;
            num=true;
            p=true;
//            pl_y+= pl_speed;
//            direction = "down";
        }if  (znak == 'a') {
            left = true;
            num=true;
            p=true;
//            pl_x-= pl_speed;
//            direction = "left";
        }if  (znak == 'd') {
            right=true;
            num=true;
            p=true;
//            pl_x+= pl_speed;
//            direction = "right";
            }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        char znak = e.getKeyChar();
        if  (znak == 'w') {
            up = false;
            num=false;
        }if  (znak == 's') {
            down = false;
            num=false;
        }if  (znak == 'a') {
            left = false;
            num=false;
        }if  (znak == 'd') {
            right=false;
            num=false;
        }
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

    public String getDirection() {
        return direction;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMode3(int mode3) {
        this.mode3 = mode3;
    }

    public void setMode2(int mode2) {
        this.mode2 = mode2;
    }

    public void setMode1(int mode1) {
        this.mode1 = mode1;
    }
}
