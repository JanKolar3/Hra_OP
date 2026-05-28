package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class EnemySettings {


    private final int OKOLIK=1;
    private final int RADIUS=150;

    private final int OHRANICENI1 = 600;
    private final int OHRANICENI2 = -42;

    Random random = new Random();

    Image image1[];
    Image image2[];

    private int x,y,width,height;

    private int barheight,barwidth;

    private final int cooldown = 240;
    private int shootcooldown = random.nextInt(cooldown);

    private final int speed;
    private int animationCooldown=random.nextInt(30);
    private int index1;
    private int bud;
    private int cooldownMove;
    private int k1;
    private int borderx=0;
    private int bordery=0;

    private boolean mode;
    private boolean damage;
    private boolean test = true;
    private boolean border=false;

    private boolean right =false;
    private boolean left=false;

    public EnemySettings(int x ,int y ,int e_width, int e_height,int speed) {
        this.x = x;
        this.y = y;
        this.width = e_width;
        this.height = e_height;
        this.speed = speed;
    }

        public void cooldownProj(Player player, ArrayList<ProjectileSettings> projectilS){
            shootcooldown --;
            if (shootcooldown<=0){
//                        projectilS.add(new Projectile1(x, y, 32, 32,this, player));
                update(player,projectilS);
                shootcooldown = cooldown;
            }
        }
        public void update(Player player, ArrayList<ProjectileSettings> projectilS){}

        public void enemyMove(Player player) {
        if (border==false) {
            mode = false;
            if (x <= player.getX() && x >= player.getX() - RADIUS && y >= player.getY() - RADIUS && y <= player.getY() + RADIUS) {
                x -= OKOLIK;
                right=false;
                left=true;
                mode = true;
            }
            if (x <= player.getX() + RADIUS && x >= player.getX() && y >= player.getY() - RADIUS && y <= player.getY() + RADIUS) {
                x += OKOLIK;
                right=true;
                left=false;
                mode = true;

            }
            if (y <= player.getY() && y >= player.getY() - RADIUS && x >= player.getX() - RADIUS && x <= player.getX() + RADIUS) {
                y -= OKOLIK;
                mode = true;
            }
            if (y <= player.getY() + RADIUS && y >= player.getY() && x >= player.getX() - RADIUS && x <= player.getX() + RADIUS) {
                y += OKOLIK;
                mode = true;
            }

            if (!mode) {
                cooldownMove--;
                if (cooldownMove <= 0) {
                    bud = random.nextInt(1, 6);
                    cooldownMove = 200;
                }
                if (cooldownMove <= 100) {
                    switch (bud) {
                        case 1:
                            x +=speed;
                            right=true;
                            left=false;
                            break;
                        case 2:
                            x -=speed;
                            right=false;
                            left=true;
                            break;
                        case 3:
                            y +=speed;
                            break;
                        case 4:
                            y -=speed;
                            break;
                        case 5:
                            break;
                    }
                }
            }
        }
        ohraniceni();
        }
    public void ohraniceni() {
        switch (borderx){
            case 0:
                if (x <= 0&&border==false) {
                    x--;
                    left=true;
                    right=false;
                    if (x <= -40) {
                        right=true;
                        left=false;
                        x = 630;
                        borderx = 1;
                        border = true;
                    }
                }
                if (x >= 550 && border == false) {
                    x++;
                    right=true;
                    left=false;
                    if (x >= 630) {
                        right=false;
                        left=true;
                        x = -40;
                        borderx = 2;
                        border = true;

                    }
                }
            break;
            case 1:
                x--;
                if (x <= 500) {
                    borderx = 0;
                    border=false;
                }
            break;
            case 2:
                x++;
                if (x >= 50) {
                    borderx = 0;
                    border=false;
                }
            break;
        }
        switch (bordery){
            case 0:
                if (y <= 50&&border==false) {
                    y--;
                    if (y <= 0) {
                        y = 650;
                        bordery = 1;
                        border=true;
                    }
                }
                if (y >= 550&&border==false) {
                    y++;
                    if (y >= 650) {
                        y = 0;
                        bordery = 2;
                        border=true;
                    }
                }
            break;
            case 1:
                y--;
                if (y <= 550) {
                    bordery = 0;
                    border=false;
                }
            break;
            case 2:
                y++;
                if (y >= 100) {
                    bordery = 0;
                    border=false;
                }
            break;
        }
    }

    public int enemyAnimation() {
        return 0;
    }

        public Rectangle hitBox(){
            return new Rectangle(x +(getWidth()/4), y +(getWidth()/4), width /2, height /2);
        }
        public void damage(boolean damaged,int damagecounter){
        if (test){
            damage =damaged;
            k1=damagecounter;
            test=false;
        }
        k1--;
        }


        public void vykresleniObr(Graphics g) {
        if (image1!=null) {
            g.drawImage(image1[enemyAnimation()], x, y, width, height, null);
        }
        if (image2!=null) {
            g.drawImage(image2[enemyAnimation()], x, y, width, height, null);
        }

            if (damage&&k1==1) {

                barwidth = width -2;
                barheight= height -60;

                g.setColor(Color.gray);
                g.fillRect(x, y - 20, barwidth, barheight);

                g.setColor(Color.red);
                g.fillRect(x, y - 20, barwidth/2, barheight);

                g.setColor(Color.black);
                g.drawRect(x - 1, y - 21, barwidth + 1, barheight+1);
            }
        }


    public int getK1() {
        return k1;
    }


        public int getWidth() {
            return width;
        }


        public int getHeight() {
            return height;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getIndex() {
        return index1;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }
}

