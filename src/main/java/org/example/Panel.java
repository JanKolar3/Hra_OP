package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

public class Panel extends JPanel implements KeyListener, MouseMotionListener{

    private String SOUBOR_POZADI = "src/main/resources/Player/tilemaptry.png";

    private Image image;
    private Player player;
    private Shield shield;
    private Menu menu;


    private int x,sx;
    private int y,sy;
//    private int width;
//    private int height;


    public Panel() {
        image = new ImageIcon(SOUBOR_POZADI).getImage();
        menu = new Menu(x,y,640,640);

        player = new Player(40,40,70,70);
        shield = new Shield(16*3,16*3);
        add(menu);








        addKeyListener(this);
        addMouseMotionListener(this);
        setFocusable(true);




        new Timer(16, e -> {

            shieldRotate();



            player.playerAnimation();
            player.setIndex(player.getIndex());
            repaint();

        }).start();



    }

    public void shieldRotate(){
        double radius = 50;

        double dx = sx - player.getPl_x();
        double dy = sy - player.getPl_y();
        double angle = Math.atan2(dy, dx);


        double shieldX = player.getPl_x() + Math.cos(angle) * radius;
        double shieldY = player.getPl_y() + Math.sin(angle) * radius;


        shield.setS_x((int) shieldX);
        shield.setS_y((int) shieldY);
    }


    @Override
    protected void paintComponent(Graphics g) {
            super.paintComponents(g);


            g.drawImage(image,x,y,getWidth(),getHeight(),this);
            player.vykresleniObr(g);
            shield.vykresleniObr(g);
        menu.vykresleniMenu(g);




    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        shield.mouseMoved(e);
        sy=e.getY();
        sx=e.getX();

    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

}
