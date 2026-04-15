package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Enemy {

    private String SOUBOR_ENEMy = "src/main/resources/untitled.png";

    Projectyle projectyle;
    Shield shield;
    Player player;
//    ArrayList<Projectyle> pole_proj = new ArrayList<>();
    private int cooldown = 240;
    private int shootcooldown = 0;
//    private int x,y,pspeed = 1;
    private int e_id;
    private int max;
    private int e_x;
    private int e_y;
    private int e_width;
    private int e_height;
    private int speed;


    Image image;

    public Enemy(int x ,int y ,int e_width, int e_height,int speed) {

        image = new ImageIcon(SOUBOR_ENEMy).getImage();


        this.e_x = x;
        this.e_y = y;
    this.e_width = e_width;
    this.e_height = e_height;
    this.speed = speed;



    }

    public void cooldownProj(Player player,ArrayList<Projectyle> projectyl){
//        zaPlayer(player);
//        projectyle.direction(player);




        shootcooldown --;
        if (shootcooldown<=0){

            projectyl.add(new Projectyle(e_x,e_y,50,50));

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
    public Rectangle hitBox(){
        return new Rectangle(e_x+(getE_width()/4),e_y+(getE_width()/4),e_width/2,e_height/2);

    }
    public boolean collision(Player player){
        return player.hitBox().intersects(hitBox());

    }

    public void vykresleniObr(Graphics g) {
        g.drawImage(image,e_x,e_y,e_width,e_height,null);
        g.drawRect(e_x+(getE_width()/4),e_y+(getE_width()/4),e_width/2,e_height/2);
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
}
