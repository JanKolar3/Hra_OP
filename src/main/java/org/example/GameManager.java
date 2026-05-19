package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GameManager extends JPanel implements KeyListener, MouseMotionListener, MouseListener {

    private String SOUBOR_POZADI = "src/main/resources/les.png";
    private String SOUBRO_OHRANI = "src/main/resources/lesUP.png";
    Image[] HEALTHS = SpriteLoader.getFrames("/Player/healts.png",16,16,3);
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

    private int mode1,mode2,mode3;



    private int score = 0;

    int health = 6;
    int max =1;
    private boolean gameOver = false;




    private int x,sx;
    private int y,sy;



    public GameManager() {
        image = new ImageIcon(SOUBOR_POZADI).getImage();
        image2 = new ImageIcon(SOUBRO_OHRANI).getImage();
        levelS = new LevelSettings();

        menu = new Menu(x,y,640,640);

        player = new Player(40,40,16*5,16*5);
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
            if(menu.isMode()==false) {
                if (gameOver == false) {

                    player.moveMent();
                    player.playerAnimation();
                    player.ohraniceni();

                    shield.shieldRotate();
                    shield.shieldAnimation();
                    shield.Cooldown();

                    pocet = pole_enemy.size();
                    if (pocet <= 0) {
                        levelS.waveSettings();
                    }

                    addEnemy();
                    healthBar();

                    for (EnemySettings enemyS : pole_enemy) {


                        enemyS.enemyAnimation();
                        enemyS.enemyMove(player);
                        enemyS.ohraniceni();


                        enemyS.cooldownProj(player, pole_proj);

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
                    }
                    for (ProjectileSettings projectyleS : pole_proj) {
                        projectyleS.direction(player);
                        if (pole_proj.contains(projectyleS)) {
                            timer--;
                        }
                        if (projectyleS.isDamage()) {
                            if (projectyleS.collision(player)) {
                                health -= 1;
                            }
                        }
                        if (projectyleS.collision2(shield)) {
                            if (shield.getShieldMode() == 2) {
                                projectyleS.setMode(2);
                            }

                        }
                    }

                }
            }
                repaint();

        }).start();
    }
    public void addEnemy(){
        if (pocet<=0) {
            levelS.enemyMax();
            max = levelS.getMax();

            while (pole_enemy.size() != levelS.getMax()) {
                if (pole_enemy.size() < levelS.getMax()) {

                    enemyS = new Enemy1(rand.nextInt(1, 600), rand.nextInt(1, 600), 24 * 3, 24 * 3, 1);
                    pole_enemy.add(enemyS);
                    pocet++;

//            enemyS = new Enemy2(rand.nextInt(1,400),rand.nextInt(1,400),50,50,1);
//            pole_enemy.add(enemyS);
                }
            }
        }

        }

    public void healthBar() {

            switch (health) {
                case 6:
                    mode3 = 0;
                    break;
                case 5:
                    mode3 = 1;
                    break;
                case 4:
                    mode3 = 2;
                    mode2 = 0;
                    break;
                case 3:
                    mode2 = 1;
                    break;
                case 2:
                    mode2 = 2;
                    mode1 = 0;
                    break;
                case 1:
                    mode1 = 1;
                    break;
                case 0:
                    mode1 = 2;
                    break;
            }
            System.out.println("health: " + health);

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
        mode1=0;
        mode2=0;
        mode3=0;
        max=1;
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
                        timer1=100;
                    }else if (timer1 <=0){
                        pole_proj.remove(projectyleS);
                        i--;
                        timer1=100;
                    }

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
        g.drawImage(image2,x,y,getWidth(),getHeight(),null);
        g.drawImage(HEALTHS[mode3],20*5,20,48,48, null);
        g.drawImage(HEALTHS[mode2], 20 * 3,20, 48, 48, null);
        g.drawImage(HEALTHS[mode1], 20, 20, 48, 48, null);



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
        shield.keyReleased(e);

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
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
