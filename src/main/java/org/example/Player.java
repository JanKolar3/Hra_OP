package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener {

    private String SOUBOR_PLAYER = "src/main/resources/Player/Player1.png";
//    private String SOUBOR_PLAYER2 = "src/main/resources/Player/Player_w.png";
//    private String SOUBOR_PLAYER3 = "src/main/resources/Player/Player_a.png";



    Image[] PLAYER_UP = SpriteLoader.getFrames("/Player/playerAnimUp.png",16,16,2);
    Image[] PLAYER_DOWN = SpriteLoader.getFrames("/Player/playerAnimDown.png",16,16,2);
    Image[] HEALTHS = SpriteLoader.getFrames("/Player/healts.png",16,16,3);

    private Image img, img2, img3;
    Enemy1 enemy;
    Projectile1 projectyle;
    GameManager gameManager;
    Image image;
    private int index = 0;
    private int index_count = 8;
    private int animation_speed = 0;
    private int pl_x;
    private int pl_y;
    private int pl_width;
    private int pl_height;
    private int pl_speed = 3;
    private int health;
    private int hp=2;
    private int healthMode=0;
    private int cooldown=15;
    private boolean k = true;
    private boolean up,down,left,right,num;
    private int hX,hY,hW,HH;

    private String direction;



    public Player(int x, int y, int width, int height,int hX,int hY,int hW, int hH,int health) {


        img = new ImageIcon(SOUBOR_PLAYER).getImage();
//        img2 = new ImageIcon(SOUBOR_PLAYER2).getImage();
//        img3 = new ImageIcon(SOUBOR_PLAYER3).getImage();
//        img = new ImageIcon(PLAYER[index]).getImage();


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

//    public void healt(){
//
//        if (health<=6&&health>=4){
//
//        }
//        if (health==4&&health>=2){
//            if(health/hp<=2){
//
//            }
//
//
//        }
//        if (health==2&&health>=0){
//            if(health/hp<=1){
//
//
//            }
//        }
//
//
//
//
//        if(health/hp<=3){
//            healthMode=0;
//            if(health/hp<=2){
//
//                if(health/hp<=1){
//
//                }
//
//            }
//        }
//
//
//    }



    public void vykresleniObr(Graphics g) {
//        g.drawImage(img,pl_x,pl_y,pl_height,pl_width,null);
//        g.drawRect(pl_x+(getPl_width()/4), pl_y, pl_width/2, pl_height);

        if (k&&!num) {
            g.drawImage(img, pl_x, pl_y, pl_height, pl_width, null);

        }
            if (direction == "up"&&num) {
                g.drawImage(PLAYER_UP[index], pl_x, pl_y, pl_width, pl_height, null);
                k = false;
            }
            if (direction == "right"&&num) {
                g.drawImage(PLAYER_DOWN[index], pl_x, pl_y, pl_width, pl_height, null);
                k = false;
            }
            if (direction == "left"&&num) {
                g.drawImage(PLAYER_DOWN[index], pl_x + 80, pl_y, -pl_width, pl_height, null);
                k = false;
            }
            if (direction == "down"&&num) {
                g.drawImage(PLAYER_DOWN[index], pl_x, pl_y, pl_width, pl_height, null);
                k = false;
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
        g.drawImage(HEALTHS[healthMode],hX,hY,hW,HH,null);
    }



    public void playerAnimation() {
//        animation_speed++;
//        if (animation_speed >= 3) {
            cooldown --;
            if (cooldown<=0) {
                index++;
                if (index >= 2) {
                    index = 0;
                }
                cooldown = 10;
            }

//            animation_speed = 0;
//        }
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




    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char znak = e.getKeyChar();
        if  (znak == 'w') {
            up = true;
            num=true;
//            pl_y-= pl_speed;
//            direction = "up";
        }if  (znak == 's') {
            down = true;
            num=true;
//            pl_y+= pl_speed;
//            direction = "down";
        }if  (znak == 'a') {
            left = true;
            num=true;
//            pl_x-= pl_speed;
//            direction = "left";
        }if  (znak == 'd') {
            right=true;
            num=true;
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
}
