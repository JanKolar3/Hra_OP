package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class EnemySettings {


    private final int OKOLIK=1;
    private final int RADIUS=120;

    private Random random = new Random();

    Image image1[];
    Image image2[];

    private int x,y,width,height;

    private int barHeight, barWidth;

    private final int cooldown = 240;
    private int shootCooldown = random.nextInt(20,cooldown);

    private final int speed;
    private int randomMove;
    private int cooldownMove;
    private int damage;
    private int borderx=0;
    private int bordery=0;

    private boolean closeMode;
    private boolean damaged=false;
    private boolean oneTime = true;
    private boolean border=false;
    private boolean moving=false;

    private int rotate =-1;
    private int moveX =80;
    private int timer = 50;
    private boolean running=false;

    public EnemySettings(int x ,int y ,int e_width, int e_height,int speed) {
        this.x = x;
        this.y = y;
        this.width = e_width;
        this.height = e_height;
        this.speed = speed;
    }

        public void cooldownProjectile(Player player, ArrayList<ProjectileSettings> projectilS){
            shootCooldown--;
            if (shootCooldown <=0){
                update(player,projectilS);
                shootCooldown = cooldown;
            }
        }
        public void update(Player player, ArrayList<ProjectileSettings> projectilS){}

        public void enemyMove(Player player) {
            if (border == false) {
                closeMode = false;
                moving = false;
                if (x <= player.getX() && x >= player.getX() - RADIUS && y >= player.getY() - RADIUS && y <= player.getY() + RADIUS) {
                        x -= OKOLIK;
                        closeMode =true;
                }
                if (x <= player.getX() + RADIUS && x >= player.getX() && y >= player.getY() - RADIUS && y <= player.getY() + RADIUS) {
                        x += OKOLIK;
                        closeMode =true;
                }
                if (y <= player.getY() && y >= player.getY() - RADIUS && x >= player.getX() - RADIUS && x <= player.getX() + RADIUS) {
                        y -= OKOLIK;
                        closeMode =true;
                }
                if (y <= player.getY() + RADIUS && y >= player.getY() && x >= player.getX() - RADIUS && x <= player.getX() + RADIUS) {
                        y += OKOLIK;
                        closeMode =true;
                }
                if (!closeMode &&!moving) {

                    cooldownMove--;
                if (cooldownMove <= 0) {
                    randomMove = random.nextInt(1, 6);
                    cooldownMove = 200;
                    moving = false;
                }
                if (cooldownMove <= 100) {
                    switch (randomMove) {
                        case 1:
                            x += speed;
                            moving = true;
                            break;
                        case 2:
                            x -= speed;
                            moving = true;
                            break;
                        case 3:
                            y += speed;
                            moving = true;
                            break;
                        case 4:
                            y -= speed;
                            moving = true;
                            break;
                        case 5:
                            moving = false;
                            break;
                    }
                }
            }
        }
            border();
            rotate(player);
        }
    private void border() {
        switch (borderx){
            case 0:
                if (x <= 0&&border==false) {
                    moving = true;
                    x--;
                    if (x <= -40) {
                        x = 630;
                        borderx = 1;
                        border = true;
                    }
                }
                if (x >= 550 && border == false) {
                    moving = true;
                    x++;
                    if (x >= 630) {
                        x = -40;
                        borderx = 2;
                        border = true;
                    }
                }
            break;
            case 1:
                moving=true;
                x--;
                if (x <= 500) {
                    borderx = 0;
                    border=false;
                }
            break;
            case 2:
                moving=true;
                x++;
                if (x >= 50) {
                    borderx = 0;
                    border=false;
                }
            break;
        }
        switch (bordery){
            case 0:
                if (y <= 100&&border==false) {
                    moving=true;
                    y--;
                    if (y <= 0) {
                        y = 650;
                        bordery = 1;
                        border=true;
                    }
                }
                if (y >= 550&&border==false) {
                    moving=true;
                    y++;
                    if (y >= 650) {
                        y = 0;
                        bordery = 2;
                        border=true;
                    }
                }
            break;
            case 1:
                moving=true;
                y--;
                if (y <= 550) {
                    bordery = 0;
                    border=false;
                }
            break;
            case 2:
                moving=true;
                y++;
                if (y >= 100) {
                    bordery = 0;
                    border=false;
                }
            break;
        }
    }

    private void rotate(Player player){
        if (closeMode ==false&&running==false) {
            if (player.getX() > x) {
                rotate = 1;
                moveX = 0;
            }
            if (player.getX() < x) {
                rotate = -1;
                moveX = 80;
            }
        }
        if (closeMode ==true||running==true) {
            timer --;
            running=true;
            moving = true;
            if (player.getX() > x) {
                rotate = -1;
                moveX = 80;
                x--;
            }
            if (player.getX() < x) {
                rotate = 1;
                moveX = 0;
                x++;
            }
            if (timer<=0) {
                timer = 50;
                running=false;
            }
        }
    }

    public int enemyAnimation   () {
        return 0;
    }

        public Rectangle hitBox(){
            return new Rectangle(x +(getWidth()/4), y +(getWidth()/4), width /2, height /2);
        }
        public void damage(int damagecounter){
        if (oneTime){
            damage =damagecounter;
            oneTime =false;
        }
        damage--;
        }


        public void vykresleniObr(Graphics g) {
        if (image1!=null) {
            g.drawImage(image1[enemyAnimation()], x, y, width, height, null);
        }
        if (image2!=null) {
            g.drawImage(image2[enemyAnimation()], x+ moveX, y, width* rotate, height, null);
        }

            if (damage ==1) {

                barWidth = width -2;
                barHeight = height -60;

                g.setColor(Color.gray);
                g.fillRect(x, y - 20, barWidth, barHeight);

                g.setColor(Color.red);
                g.fillRect(x, y - 20, barWidth /2, barHeight);

                g.setColor(Color.black);
                g.drawRect(x - 1, y - 21, barWidth + 1, barHeight +1);
            }
        }

    public boolean isMoving() {
        return moving;
    }

    public int getDamage() {
        return damage;
    }


        public int getWidth() {
            return width;
        }



        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


}

