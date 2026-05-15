package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class EnemySettings {


    private int OHRANICENI1 = 650;
    private int OHRANICENI2 = -60;

    Random random = new Random();

    Image[] image1;
    Image image2;


    private int cooldown = 240;
    private int shootcooldown = 60;
    private int e_id;
    private int max;
    private int e_x;
    private int e_y;
    private int e_width;
    private int e_height;
    private int speed;
    private EnemySettings enemyS;
    private ProjectileSettings projectileSettings;
    private int index;
    private int animationCooldown=30;
    private boolean mode;
    private int OKOLIK=1,RADIUS=150;
    private int bud;
    private int cooldownMove;







    public EnemySettings(int x ,int y ,int e_width, int e_height,int speed, int id) {
//        image = new ImageIcon(SOUBOR_ENEMy).getImage();

        this.e_x = x;
        this.e_y = y;
        this.e_width = e_width;
        this.e_height = e_height;
        this.speed = speed;
        this.e_id = id;

    }

        public void cooldownProj(Player player, ArrayList<ProjectileSettings> projectilS){

            shootcooldown --;
            if (shootcooldown<=0){


                        projectilS.add(new Projectile1(e_x, e_y, 32, 32, e_id, this, player));


//                    projectilS.add(new Projectile2(e_x, e_y, 32, 32));
//                System.out.println(projectilS);

                shootcooldown = cooldown;
            }

        }





        public void enemyMove(Player player) {
            mode =false;
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


            if (mode == false) {
                cooldownMove--;
                if (cooldownMove <= 0) {
                    bud=random.nextInt(1,6);
                    cooldownMove = 40;
                }
                switch (bud){
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


//            double dx = player.getPl_x() - this.e_x;
//            double dy = player.getPl_y() - this.e_y;
//
//            double distanceSquared = dx * dx + dy * dy;
//            double radius = 200;
//            double radiusSquared = radius * radius;
//
//            if (distanceSquared < radiusSquared) {
//                this.e_x += (Math.random() - 0.5) * 2;
//                this.e_y += (Math.random() - 0.5) * 2;
//            } else {
//
//                double length = Math.sqrt(distanceSquared);
//
//                dx /= length;
//                dy /= length;
//
//                double speed = 2;
//
//                this.e_x += dx * speed;
//                this.e_y += dy * speed;
//            }

        }
    public void ohraniceni() {

        if (e_x < OHRANICENI2-1) {
            e_x = OHRANICENI1;
        }
        if (e_x > OHRANICENI1+1) {
            e_x = OHRANICENI2;
        }
        if (e_y < OHRANICENI2-1) {
            e_y = OHRANICENI1;
        }
        if (e_y > OHRANICENI1+1) {
            e_y = OHRANICENI2;
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
        public boolean collision(Player player){
            return player.hitBox().intersects(hitBox());

        }
        public boolean collision2(EnemySettings enemyS){
        return enemyS.hitBox().intersects(hitBox());
        }


        public void vykresleniObr(Graphics g) {
//            g.drawImage(image1,e_x,e_y,e_width,e_height,null);
//            g.drawImage(image2,e_x,e_y,e_width,e_height,null);
            g.drawImage(image1[index],e_x,e_y,e_width,e_height,null);


        }

        public int getMax() {
            return max;
        }

        public int getE_width() {
            return e_width;
        }

        public int getE_id() {return e_id;}

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

