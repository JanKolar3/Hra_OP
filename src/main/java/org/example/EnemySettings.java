package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class EnemySettings {


    private final int OHRANICENI1 = 600;
    private final int OHRANICENI2 = -42;

    Random random = new Random();

    Image[] image1;
    Image image2;


    private final int cooldown = 240;
    private int shootcooldown = 60;
    private int e_id;
    private int max;
    private int e_x;
    private int e_y;

    private int barheight;
    private int barwidth;

    private final int e_width;
    private final int e_height;
    private final int speed;
    private int index;
    private int animationCooldown=30;
    private boolean mode;
    private final int OKOLIK=1;
    private final int RADIUS=150;
    private int bud;
    private int cooldownMove;
    private final int cooldownProjectile=600;
    private boolean damage;
    private int health=2;
    private int health1;
    private int k=0;
    private int k1;
    private boolean test = true;
    private int maxhealth=2;
    private int borderx=0;
    private int bordery=0;
    private boolean border=false;








    public EnemySettings(int x ,int y ,int e_width, int e_height,int speed) {
        this.e_x = x;
        this.e_y = y;
        this.e_width = e_width;
        this.e_height = e_height;
        this.speed = speed;
    }

        public void cooldownProj(Player player, ArrayList<ProjectileSettings> projectilS){

            shootcooldown --;
            if (shootcooldown<=0){
                        projectilS.add(new Projectile1(e_x, e_y, 32, 32,this, player));
                shootcooldown = cooldown;
            }
        }

        public void enemyMove(Player player) {
        if (border==false) {
            mode = false;
            if (e_x <= player.getPl_x() && e_x >= player.getPl_x() - RADIUS && e_y >= player.getPl_y() - RADIUS && e_y <= player.getPl_y() + RADIUS) {
                e_x -= OKOLIK;
                mode = true;
            }
            if (e_x <= player.getPl_x() + RADIUS && e_x >= player.getPl_x() && e_y >= player.getPl_y() - RADIUS && e_y <= player.getPl_y() + RADIUS) {
                e_x += OKOLIK;
                mode = true;
            }
            if (e_y <= player.getPl_y() && e_y >= player.getPl_y() - RADIUS && e_x >= player.getPl_x() - RADIUS && e_x <= player.getPl_x() + RADIUS) {
                e_y -= OKOLIK;
                mode = true;
            }
            if (e_y <= player.getPl_y() + RADIUS && e_y >= player.getPl_y() && e_x >= player.getPl_x() - RADIUS && e_x <= player.getPl_x() + RADIUS) {
                e_y += OKOLIK;
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
                            e_x++;
                            break;
                        case 2:
                            e_x--;
                            break;
                        case 3:
                            e_y++;
                            break;
                        case 4:
                            e_y--;
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

        if (borderx==0) {
            if (e_x <= 0&&border==false) {
                e_x--;
                if (e_x <= -40) {
                    e_x = 630;
                    borderx = 1;
                    border=true;
                }
            }
            if (e_x >= 550&&border==false) {
                e_x++;
                if (e_x >= 630) {
                    e_x = -40;
                    borderx = 2;
                    border=true;
                }
            }
        }
        if (borderx == 1) {
                e_x--;
            if (e_x <= 500) {
                borderx = 0;
                border=false;
            }
        }

        if (borderx == 2) {
                e_x++;
            if (e_x >= 50) {
                borderx = 0;
                border=false;
            }
        }

        if (bordery==0) {
            if (e_y <= 50&&border==false) {
                e_y--;
                if (e_y <= 0) {
                    e_y = 650;
                    bordery = 1;
                    border=true;
                }
            }
            if (e_y >= 550&&border==false) {
                e_y++;
                if (e_y >= 650) {
                    e_y = 0;
                    bordery = 2;
                    border=true;
                }
            }
        }
        if (bordery == 1) {
                e_y--;
            if (e_y <= 600) {
                bordery = 0;
                border=false;
            }
        }

        if (bordery == 2) {
                e_y++;
            if (e_y >= 100) {
                bordery = 0;
                border=false;
            }
        }
    }
    public void enemyAnimation() {
        animationCooldown--;
        if (animationCooldown <= 0) {
            index++;
            if (index >= 2) {
                index = 0;
            }
            animationCooldown = 30;
        }
    }

        public Rectangle hitBox(){
            return new Rectangle(e_x+(getE_width()/4),e_y+(getE_width()/4),e_width/2,e_height/2);

        }
//        public boolean collision(Player player){
//            return player.hitBox().intersects(hitBox());
//
//        }
//        public boolean collision2(EnemySettings enemyS){
//        return enemyS.hitBox().intersects(hitBox());
//        }
        public void damage(boolean damaged,int damagecounter){
        if (test){
            damage =damaged;
            k1=damagecounter;
            test=false;
        }
        k1--;
        health = damagecounter;


        }


        public void vykresleniObr(Graphics g) {
            g.drawImage(image1[index], e_x, e_y, e_width, e_height, null);


            if (damage&&k1==1) {

                barwidth = e_width-2;
                barheight= e_height-60;


                g.setColor(Color.gray);
                g.fillRect(e_x, e_y - 20, barwidth, barheight);

                g.setColor(Color.red);
                g.fillRect(e_x, e_y - 20, barwidth/2, barheight);

                g.setColor(Color.black);
                g.drawRect(e_x - 1, e_y - 21, barwidth + 1, barheight+1);
            }
        }


    public int getK1() {
        return k1;
    }

    public int getMax() {
            return max;
        }

        public int getE_width() {
            return e_width;
        }


        public int getE_height() {
            return e_height;
        }

        public int getE_x() {
            return e_x;
        }

        public int getE_y() {
            return e_y;
        }

        public int getIndex() {
        return index;
    }

    public void setE_x(int e_x) {
        this.e_x = e_x;
    }

    public void setE_y(int e_y) {
        this.e_y = e_y;
    }
}

