package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Enemy {

    private String SOUBOR_ENEMy = "src/main/resources/untitled.png";


    private int e_id;
    private int max;
    private int e_x;
    private int e_y;
    private int e_width;
    private int e_height;
    private int speed;

    private Image image;

    public Enemy(int x ,int y ,int e_width, int e_height,int speed) {
        image = new ImageIcon(SOUBOR_ENEMy).getImage();


        this.e_x = x;
        this.e_y = y;
        this.e_width = e_width;
        this.e_height = e_height;
        this.speed = speed;


    }


    public void zaPlayer(Player player) {
        if (player.getPl_x() > e_x) e_x += speed;
        if (player.getPl_x() < e_x) e_x -= speed;
        if (player.getPl_y() > e_y) e_y += speed;
        if (player.getPl_y() < e_y) e_y -= speed;
    }

    public void vykresleniObr(Graphics g) {
        g.drawImage(image,e_x,e_y,e_width,e_height,null);

    }

    public int getMax() {
        return max;
    }
}
