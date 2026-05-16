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
    Random rand = new Random();

    private EnemySettings enemyS;
    private LevelSettings levelS;

    private Image image;
    private Image image2;
    private JLabel jLabel,txtlevel,txtwave;
    private Player player;
    private Shield shield;
    private Menu menu;
    private int id;
    private  int timer = 600,timer1=100;
    private int pocet=1;

    private boolean konecWave=false;




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
        levelS = new LevelSettings();
//        image2 = new ImageIcon(SOUBOR_HELTH).getImage();
        menu = new Menu(x,y,640,640);
//        levelS = new LevelSettings(pocet,konecWave);
//        project = new Projectyle(50,40,50,40);
        player = new Player(40,40,16*5,16*5,20,20,48,48,health);
        shield = new Shield(player,16*3,16*3);

        jLabel = new JLabel("SCORE");
        txtlevel = new JLabel("Level: "+levelS.getLevel());
        txtwave = new JLabel("Wave: "+levelS.getWave());

        add(txtlevel);
        add(txtwave);


        if (menu.isMode()==false) {


            add(jLabel);
}

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





                    player.moveMent();
                    player.playerAnimation();
                    player.ohraniceni();
//                    player.setIndex(player.getIndex());

                    shield.shieldRotate();
                    shield.shieldAnimation();
                    shield.Cooldown();
//            addProj();

                    pocet=pole_enemy.size();
                    if (pocet<=0) {
                        levelS.waveSettings();
                    }



                    addEnemy();


//                    if (pocet<=0 || score==0) {
//                        System.out.println("pocet "+pocet);
//                        addEnemy();
//                    }

//                    repaint();


                    for (EnemySettings enemyS : pole_enemy) {



                        enemyS.enemyAnimation();
                        enemyS.enemyMove(player);
                        enemyS.ohraniceni();

                        healthBar();
                        enemyS.cooldownProj(player, pole_proj);

//                        pocet ++;
//                        for (int i = 0; i < pole_enemy.size(); i++) {
//                            for (int j = i+1; j < pole_proj.size(); j++) {
//
//                                EnemySettings ememy1 = pole_enemy.get(i);
//                                EnemySettings enemy2 = pole_enemy.get(j);
//
//                                if (ememy1.collision2(enemy2)) {
//                                    ememy1.setE_x(ememy1.getE_x()+1);
//                                    ememy1.setE_y(ememy1.getE_y()-1);
//                                    System.out.println("coolll");
//                                }
//                            }

//                        }
//                        if (enemyS.collision2(this.enemyS)) {
//                            if (this.enemyS.collision2(enemyS)) {
//                                enemyS.setE_x(enemyS.getE_x() + rand.nextInt(1,5));
//                                enemyS.setE_y(enemyS.getE_y() + rand.nextInt(2));
//                            }
//                        }



                    }
                    for (ProjectileSettings projectyleS : pole_proj) {
                        projectyleS.direction(player);
                        if (pole_proj.contains(projectyleS)) {
                            timer--;
                            System.out.println("timer: "+timer);
                        }

//                        System.out.println("timer:"+timer);
//                        projectyleS.direction1(player, enemyS);

//                healthBar();

//                            player.setHealth(health);
//                            player.setIndex(player.getIndex()+1);
                            if (projectyleS.isDamage()) {
                                if (projectyleS.collision(player)) {
                                health -= 1;
                                player.health(health);
                                System.out.println("HP: " + health);
                            }
                        }
                        if (projectyleS.collision2(shield)) {
                            if (shield.getShieldMode() == 2) {
                                projectyleS.setMode(2);
                            }
//                            if (shield.getShieldMode() == 1) {
//                                projectyleS.setMode(1);
//
//                            }


                        }
//                        if ((enemyS.collision2(enemyS))){
//                            System.out.println("ADADA");
//                        }

                    }

                }
            repaint();
//            }
        }).start();
    }
    public void addEnemy(){


//        if (enemyS != null){
//            id++;
//        }
        if (pocet<=0) {
            levelS.enemyMax();
            System.out.println(levelS.getMax());
            max = levelS.getMax();


            while (pole_enemy.size() != levelS.getMax()) {


//                if (score >= 10) {
//                    max = 2;
//                }
//                if (score >= 50) {
//                    max = 4;
//                }
//                if (score >= 200) {
//                    max = 5;
//                }
                if (pole_enemy.size() < levelS.getMax()) {


                    enemyS = new Enemy1(rand.nextInt(1, 600), rand.nextInt(1, 600), 24 * 3, 24 * 3, 1, id);
                    pole_enemy.add(enemyS);
                    pocet++;

//            System.out.println(enemyS);

//            enemyS = new Enemy2(rand.nextInt(1,400),rand.nextInt(1,400),50,50,1);
//            pole_enemy.add(enemyS);
                }
            }
        }

        }




//    }
//    public void addProj() {
//        Random random = new Random();
//        if (pole_proj.size() < 1){
//            project = new Projectyle(random.nextInt(1, 400), random.nextInt(1, 400), 50, 50);
//        pole_proj.add(project);
//    }

//    public void shieldRotate(){
//        double radius = 50;
//
//        double dx = sx - player.getPl_x();
//        double dy = sy - player.getPl_y();
//        double angle = Math.atan2(dy, dx);
//
//        double shieldX = player.getPl_x() + Math.cos(angle) * radius;
//        double shieldY = player.getPl_y() + Math.sin(angle) * radius;
//
//        shield.setS_x((int) shieldX);
//        shield.setS_y((int) shieldY);
//    }
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
        player.setMode1(0);
        player.setMode2(0);
        player.setMode3(0);
        max=1;
    }


    public void protection(){

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(image,x,y,getWidth(),getHeight(),this);


        for (int j = 0; j < pole_enemy.size();j++){
            EnemySettings enemyS =pole_enemy.get(j);
            enemyS.vykresleniObr(g);
            for (int i = 0; i < pole_proj.size(); i++) {
                ProjectileSettings projectyleS =pole_proj.get(i);
                projectyleS.draw(g);

            if (shield.getShieldMode() == 1){
                if (shield.collision1 (projectyleS)) {
                    pole_proj.remove(projectyleS);
                    score += 5;
                    i--;
                    timer = 600;


                }
            }
            if (projectyleS.isDamage()) {
                if (projectyleS.collision(player)) {
                    pole_proj.remove(projectyleS);
                    i--;
                    timer =600;

                }
            }
            if (projectyleS.getMode() == 2) {
                timer =600;
                timer1--;
                    if (projectyleS.collision1(enemyS)) {
                        pole_enemy.remove(enemyS);
                        pole_proj.remove(projectyleS);
                        pocet--;
                        j--;
                        i--;
                        score += 10;
                        jLabel.setText(String.valueOf(score));
//                        System.out.println(" pda"+pocet);
                        System.out.println("score: "+ score);
                        timer1=100;


                    }else if (timer1 <=0){
                        pole_proj.remove(projectyleS);
                        i--;
                        timer1=100;
                    }

//                if (timer <=0){
//                    pole_proj.remove(projectyleS);
//                    i--;
//                    timer =600;
//                }
            }
            if (timer <=0){
                timer =600;
                pole_proj.remove(projectyleS);
                i--;
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
        shield.keyReleased(e);

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        shield.mouseMoved(e);
//        sy=e.getY();
//        sx=e.getX();
        menu.mouseMoved(e);
        shield.mouseMoved(e);

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

    public int getPocet() {
        return pocet;
    }
}
