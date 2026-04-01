package org.example;

import javax.swing.*;
import java.awt.*;

public class Projectyle {
    private String SOUBOR_PROJECTYLE = "src/main/resources/red_proj (1).gif";



    private Image image;
//    Player player;
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed=1;

    public Projectyle(int x,int y,int width, int height){
        image = new ImageIcon(SOUBOR_PROJECTYLE).getImage();


        this.y = y;
        this.x = x;
        this.width = width;
        this.height = height;


    }
    public void direction(Player player){



        if (player.getPl_x() > x) x += speed;

        if (player.getPl_x() < x) x -= speed;
        if (player.getPl_y() > y) y += speed;
        if (player.getPl_y() < y) y -= speed;





    }

    public void draw(Graphics g){
        g.drawImage(image,x,y,width,height,null);
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
