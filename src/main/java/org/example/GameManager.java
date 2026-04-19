package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GameManager extends JPanel implements KeyListener, MouseMotionListener, MouseListener {

    private String SOUBOR_POZADI = "src/main/resources/floorTest (1).png";

    ArrayList<Enemy> pole_enemy =new ArrayList<>();
    ArrayList<Projectyle> pole_proj = new ArrayList<>();

    private Image image;
    private Player player;
    private Shield shield;
    private Menu menu;
    private Enemy enemy;
    private int score = 0;
    Projectyle project;
    int health = 6;
    int max =1;
    private boolean gameOver = false;




    private int x,sx;
    private int y,sy;
//    private int width;
//    private int height;


    public GameManager() {
        image = new ImageIcon(SOUBOR_POZADI).getImage();
        menu = new Menu(x,y,640,640);
//        project = new Projectyle(50,40,50,40);
        player = new Player(40,40,70,70);
        shield = new Shield(16*3,16*3);



//        add(menu);



        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);



        new Timer(16, e -> {

//            if(menu.isMode()==false) {

                if (gameOver == false) {

                    shieldRotate();
                    shield.cooldown();


                    player.moveMent();
                    player.playerAnimation();
                    player.setIndex(player.getIndex());
//            addProj();
                    addEnemy();


                    for (Enemy enemy : pole_enemy) {
                        enemy.enemyMove(player);
                        healthBar();
                        enemy.cooldownProj(player, pole_proj);

                        if (shield.collision(enemy)) {
//                    System.out.println("Shield collision");
                        }
                    }
                    for (Projectyle projectyle : pole_proj) {
                        projectyle.direction(player, enemy);
//                healthBar();
                        if (projectyle.collision(player)) {

                            health -= 1;
                            System.out.println(health);
                        }
                        if (shield.collision1(projectyle)) {
                            if (shield.getShieldMode() == 2) {
                                projectyle.setMode(2);
                            }
                            if (shield.getShieldMode() == 1) {
                                projectyle.setMode(1);

                            }


                        }

                    }
                    repaint();
                }
//            }
        }).start();
    }
    public void addEnemy(){
        Random rand = new Random();
        if (score >= 10){
            max =5;
        }
        if (pole_enemy.size() <max){
            enemy = new Enemy(rand.nextInt(1,400),rand.nextInt(1,400),50,50,1);
            pole_enemy.add(enemy);
        }




//    }
//    public void addProj() {
//        Random random = new Random();
//        if (pole_proj.size() < 1){
//            project = new Projectyle(random.nextInt(1, 400), random.nextInt(1, 400), 50, 50);
//        pole_proj.add(project);
//    }
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

//        if (enemy.collision(player)) {
//            health -= 1;
//            System.out.println(health);
//
//        }
//        if (project.collision(player)){
//            health -= 1;
//            System.out.println(health);
//        }

        if (health <= 0) {
            health =6;
            System.out.println("GAME OVER");
            gameOver = true;
        }
    }
    public void reset(){
        gameOver = false;
        menu.setMode(true);
        pole_proj.clear();
        pole_enemy.clear();
        health = 6;
        score = 0;
    }


    public void protection(){

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(image,x,y,getWidth(),getHeight(),this);

        for (Enemy enemy : pole_enemy){
            enemy.vykresleniObr(g);
            if (shield.collision(enemy)){
                System.out.println("Shield collision");
//                    pole_enemy.remove(enemy);
            }


        }
        for (int i = 0; i < pole_proj.size(); i++) {
            Projectyle projectyle = pole_proj.get(i);
            projectyle.draw(g);
            if (shield.getShieldMode() == 1){
                if (shield.collision1(projectyle)) {

                    System.out.println("coll");
                    pole_proj.remove(projectyle);
                    score += 5;
                    i--;
                }
            }
            if (projectyle.collision(player)) {
                pole_proj.remove(projectyle);
                i--;
            }
            if (projectyle.getMode() == 2) {
                if (projectyle.collision1(enemy)){
                    pole_enemy.remove(enemy);
                    pole_proj.remove(projectyle);
                    score +=10;
                    System.out.println("score: "+score);
                }
            }


        }


        player.vykresleniObr(g);
        shield.vykresleniObr(g);




        if (gameOver==true){
            g.setFont(new Font("Arial", Font.BOLD,64));
            g.setColor(Color.RED);
            g.drawString("Game Over", 150, 320);
            g.drawString("Score: "+score, 150, 400);
        }

        if (menu.isMode() == true){
            menu.vykresleniMenu(g);
        }



    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        shield.keyPressed(e);
        player.keyPressed(e);


    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        shield.mouseMoved(e);
        sy=e.getY();
        sx=e.getX();
        menu.mouseMoved(e);

    }

    @Override
    public int getX() {
        return x;
    }


    @Override
    public int getY() {
        return y;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        menu.mouseClicked(e);
        if (gameOver==true) {
            reset();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
