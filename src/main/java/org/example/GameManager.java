package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GameManager extends JPanel implements KeyListener, MouseMotionListener, MouseListener {

    private String SOUBOR_POZADI = "src/main/resources/floorTest (1).png";
//    private String SOUBOR_HELTH = "src/main/resources/healthtest1.png";

    ArrayList<EnemySettings> pole_enemy =new ArrayList<>();
    ArrayList<ProjectileSettings> pole_proj = new ArrayList<>();

    private EnemySettings enemyS;
    private Image image;
    private Image image2;
    private JLabel jLabel;
    private Player player;
    private Shield shield;
    private Menu menu;
    private int id;




    private int score = 0;
//    Projectile1 project;
    int health = 6;
    int max =1;
    private boolean gameOver = false;




    private int x,sx;
    private int y,sy;
//    private int width;
//    private int height;


    public GameManager() {
        image = new ImageIcon(SOUBOR_POZADI).getImage();
//        image2 = new ImageIcon(SOUBOR_HELTH).getImage();
        menu = new Menu(x,y,640,640);
//        project = new Projectyle(50,40,50,40);
        player = new Player(40,40,16*5,16*5,20,20,48,48,health);
        shield = new Shield(16*3,16*3);
        jLabel = new JLabel("SCORE");

        if (menu.isMode()==false) {


            add(jLabel);
}

//        add(menu);



        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);



        new Timer(16, e -> {


//
//                for (ProjectileSettings proeSjectyl : pole_proj) {
//                    if (enemyS.getE_id() != proeSjectyl.getId()){
//                        pole_proj.remove(proeSjectyl);
//                    }
//                }




//            if(menu.isMode()==false) {

                if (gameOver == false) {

                    shieldRotate();
                    shield.cooldown();


                    player.moveMent();
                    player.playerAnimation();
                    player.setIndex(player.getIndex());
//            addProj();
                    addEnemy();
                    repaint();


                    for (EnemySettings enemyS : pole_enemy) {
                        enemyS.enemyAnimation();
                        enemyS.enemyMove(player);

                        healthBar();
                        enemyS.cooldownProj(player, pole_proj);

                    }
                    for (ProjectileSettings projectyleS : pole_proj) {
                        projectyleS.direction(player, enemyS);
//                        projectyleS.direction1(player, enemyS);

//                healthBar();
                        if (projectyleS.collision(player)) {
//                            player.setHealth(health);
                            player.setIndex(player.getIndex()+1);
                            health -= 1;
                            player.health(health);


                            System.out.println(health);
                        }
                        if (projectyleS.collision2(shield)) {
                            if (shield.getShieldMode() == 2) {
                                projectyleS.setMode(2);
                            }
                            if (shield.getShieldMode() == 1) {
                                projectyleS.setMode(1);

                            }


                        }

                    }

                }
//            }
        }).start();
    }
    public void addEnemy(){
        if (enemyS != null){
            id++;
        }

        Random rand = new Random();
        if (score >= 10){
            max =5;
        }
        if (pole_enemy.size() <max){



            enemyS = new Enemy1(rand.nextInt(1,400),rand.nextInt(1,400),24*3,24*3,1,id);
            pole_enemy.add(enemyS);
            System.out.println(enemyS);

//            enemyS = new Enemy2(rand.nextInt(1,400),rand.nextInt(1,400),50,50,1);
//            pole_enemy.add(enemyS);
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


        for (EnemySettings enemyS : pole_enemy){
            enemyS.vykresleniObr(g);
            if (shield.collision(enemyS)){
                System.out.println("Shield collision");
//                    pole_enemy.remove(enemy);
            }
        }

        for (int i = 0; i < pole_proj.size(); i++) {
            ProjectileSettings projectyleS = (ProjectileSettings) pole_proj.get(i);
            projectyleS.draw(g);

                if (enemyS.getE_id() != projectyleS.getId()){
                    pole_proj.remove(projectyleS);

                }

////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
            if (shield.getShieldMode() == 1){
                if (shield.collision1 ((ProjectileSettings) projectyleS)) {

                    System.out.println("coll");
                    pole_proj.remove(projectyleS);
                    score += 5;
                    i--;
                }
            }
            if (projectyleS.collision(player)) {
                pole_proj.remove(projectyleS);
                i--;
            }
            if (projectyleS.getMode() == 2) {
                if (projectyleS.collision1(enemyS)){
                    pole_enemy.remove(enemyS);
                    pole_proj.remove(projectyleS);
                    score +=10;
                    jLabel.setText(String.valueOf(score));
                    System.out.println("score: "+score);
                }
            }


        }





        player.vykresleniObr(g);
        shield.vykresleniObr(g);
//        g.drawImage(image2,50,50,16*3,16*3,this);
//        g.drawImage(image2,200,200,16*5,16*5,this);




        if (gameOver==true){
            g.setFont(new Font("Arial", Font.BOLD,64));
            g.setColor(Color.RED);
            g.drawString("Game Over", 150, 320);
            g.drawString("Score: "+score, 150, 400);
        }

        if (menu.isMode() == true){
            menu.vykresleniMenu(g);
        }
//        g.setColor(Color.red);
//        g.drawRect(200,200,6,2);
//


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

    public ArrayList<EnemySettings> getPole_enemy() {
        return pole_enemy;
    }
}
