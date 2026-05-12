package org.example;

import java.awt.*;
import java.util.ArrayList;

public class EnemySettings {

//        private String SOUBOR_ENEMy = "";
//        Image image = new ImageIcon(SOUBOR_ENEMy).getImage();
        Image[] image1;
        Image image2;

//        Projectile1 projectyle;
        Shield shield;
        Player player;
        //    ArrayList<Projectyle> pole_proj = new ArrayList<>();
        private int cooldown = 240;
        private int shootcooldown = 60;
        //    private int x,y,pspeed = 1;
        private int e_id;
        private int max;
        private int e_x;
        private int e_y;
        private int e_width;
        private int e_height;
        private int speed;
        private EnemySettings enemyS;
        private int index;
        private int animationCooldown=30;






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

                    projectilS.add(new Projectile1(e_x, e_y, 32, 32,e_id,this,player));

//                    projectilS.add(new Projectile2(e_x, e_y, 32, 32));
//                System.out.println(projectilS);

                shootcooldown = cooldown;
            }

        }





        public void enemyMove(Player player) {
            double dx = player.getPl_x() - this.e_x;
            double dy = player.getPl_y() - this.e_y;

            double distanceSquared = dx * dx + dy * dy;
            double radius = 200;
            double radiusSquared = radius * radius;

            if (distanceSquared < radiusSquared) {
                this.e_x += (Math.random() - 0.5) * 2;
                this.e_y += (Math.random() - 0.5) * 2;
            } else {

                double length = Math.sqrt(distanceSquared);

                dx /= length;
                dy /= length;

                double speed = 2;

                this.e_x += dx * speed;
                this.e_y += dy * speed;
            }

//        rozmezi = player.getPl_x()+player.getPl_y();
//        rozmezix = player.getPl_x()+e_x/2;
//        rozmeziy = player.getPl_y()+e_y/2;
//        System.out.println(player.getPl_x()+" "+player.getPl_y()+" "+ e_x+" "+e_y+" "+rozmezix+" "+rozmeziy);
//        if (player.getPl_x() > e_x){
//            e_x += speed;
//
//            if (rozmeziy < e_x){
//                e_x -= 1;
//            }
//        }
//
//        if (player.getPl_x() < e_x){
//            e_x -= speed;
//            if (rozmeziy > e_x){
//                e_x += 1;
//            }
//        }
//        if (player.getPl_y() > e_y){
//            e_y += speed;
//            if (rozmezix < e_y){
//                e_y -= 1;
//            }
//        }
//        if (player.getPl_y() < e_y){
//            e_y -= speed;
//            if (rozmezix > e_y){
//                e_y += 1;
//            }
//        }
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
        return hitBox().intersects(hitBox());
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

