package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Panel extends JPanel implements KeyListener, MouseMotionListener{

    private String SOUBOR_POZADI = "src/main/resources/Player/tilemaptry.png";

    ArrayList<Enemy> pole_enemy =new ArrayList<>();

    private Image image;
    private Player player;
    private Shield shield;
    private Menu menu;
    private Enemy enemy;
    private Projectyle project;
    int health = 6;




    private int x,sx;
    private int y,sy;
//    private int width;
//    private int height;


    public Panel() {
        image = new ImageIcon(SOUBOR_POZADI).getImage();
        menu = new Menu(x,y,640,640);
        project = new Projectyle(50,40,50,50);
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
            project.direction(player);


            addEnemy();
            for(Enemy enemy : pole_enemy){
                enemy.zaPlayer(player);
                healthBar();
                if (shield.collision(enemy)){

//                    System.out.println("Shield collision");

                }




            }

            repaint();



        }).start();



    }



    public void addEnemy(){
        Random rand = new Random();
        if (pole_enemy.size() <1){
            enemy = new Enemy(rand.nextInt(1,400),rand.nextInt(1,400),50,50,1);
            pole_enemy.add(enemy);

        }
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
    public void healthBar() {

        if (enemy.collision(player)) {
            health -= 1;
            System.out.println(health);
        }
        if (health == 0) {
            System.out.println("GAME OVER");
        }
    }
    public void protection(){

    }


    @Override
    protected void paintComponent(Graphics g) {
            super.paintComponents(g);
            g.drawImage(image,x,y,getWidth(),getHeight(),this);

            project.draw(g);

            for (Enemy enemy : pole_enemy){
                enemy.vykresleniObr(g);
                if (shield.collision(enemy)){
                    System.out.println("Shield collision");
                    pole_enemy.remove(enemy);


                }
            }
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
