package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GameManager extends JPanel implements KeyListener, MouseMotionListener, MouseListener {

    private final String SOUBOR_POZADI = "src/main/resources/Map/map.png";
    private final String SOUBRO_OHRANI = "src/main/resources/Map/lesUP.test.png";
    private final String OVER = "src/main/resources/Info/GameOver.png";

    private final ArrayList<EnemySettings> pole_enemy =new ArrayList<>();
    private final ArrayList<ProjectileSettings> pole_proj = new ArrayList<>();
    private final Random rand = new Random();

    private EnemySettings enemyS;
    private final LevelSettings levelS;

    private final JLabel jLabel;
//    private final JLabel txtlevel;
//    private final JLabel txtwave;
    private final Player player;
    private final Shield shield;
    private final Menu menu;
    private final PanelInfo info;

    private final Image over;
    private final Image image;
    private final Image image2;

    private int pocet=1;
    private int score = 0;
    private int health = 6;
    private int druh=0;


    private int x;
    private int y;

    private boolean gameOver = false;



    public GameManager() {
        image = new ImageIcon(SOUBOR_POZADI).getImage();
        image2 = new ImageIcon(SOUBRO_OHRANI).getImage();
        over = new ImageIcon(OVER).getImage();

        menu = new Menu(x,y,640,640);
        player = new Player(200,200,16*5,16*5);
        shield = new Shield(player,16*3,16*3);


        levelS = new LevelSettings();
        info = new PanelInfo();


        jLabel = new JLabel("SCORE");
//        txtlevel = new JLabel("Level: "+levelS.getLevel());
//        txtwave = new JLabel("Wave: "+levelS.getWave());

//        add(txtlevel);
//        add(txtwave);



        if (!menu.isMode()) {


            add(jLabel);
}

        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);



        new Timer(16, e -> {
            if(!menu.isMode()) {
                if (!gameOver&&!levelS.isVictory()) {
                    addEnemy();
                    pocet=pole_enemy.size();

                    collision();
                    healthBar();

                    player.moveMent();

                    shield.shieldRotate();

                    info.info(health,shield.isShiueldBounce(),levelS.getLevel(),levelS.getWave(),score);
//                    info.shieldTimer(shield.isShiueldBounce());
                    for (EnemySettings enemyS : pole_enemy) {
                        enemyS.enemyAnimation();
                        enemyS.enemyMove(player);
                        enemyS.cooldownProj(player, pole_proj);
//                        pocet = pole_enemy.size();
                    }
//                    if (pocet <= 0) {
//                        levelS.waveSettings();
//                    }


                }
            }
            repaint();


        }).start();
    }
    public void collision() {
            for (int i = 0; i < pole_proj.size(); i++) {
                ProjectileSettings projectyleS = pole_proj.get(i);
                projectyleS.direction(player);

                if (shield.getShieldMode() == 1) {
                    if (shield.collision(projectyleS)) {
                        pole_proj.remove(i);
                        score += 5;
                        i--;
                        continue;
                    }
                }

                if (projectyleS.isDamage()) {
                    if (projectyleS.collision(player)) {
                        health -= 1;
                        pole_proj.remove(i);
                        i--;
                        continue;
                    }
                }

                if (projectyleS.collision2(shield)) {
                    if (shield.getShieldMode() == 2) {
                        projectyleS.setMode(2);
                        continue;
                    }
                }

                if (projectyleS.isDestroy()) {
                    pole_proj.remove(i);
                    i--;
                }

                if (projectyleS.getMode() == 2) {

                    for (int j = 0; j < pole_enemy.size(); j++) {
                        EnemySettings enemyS = pole_enemy.get(j);

                        if (projectyleS.collision1(enemyS)) {


                            enemyS.damage(true,levelS.getDamage());
                            pole_proj.remove(i);
                            i--;

                            if (enemyS.getDmg() <=0){
                                pole_enemy.remove(enemyS);
                                score += 10;
                            }
                            break;
                        }
                    }
                }
        }
    }
    public void addEnemy(){
        pocet = pole_enemy.size();
        if (pocet<=0) {
            levelS.waveSettings();
            levelS.enemyMax();

            while (pole_enemy.size() != levelS.getMax()) {
                if (pole_enemy.size() < levelS.getMax()) {
                    druh++;
                    if (druh%2==0){
                        enemyS = new Enemy2(rand.nextInt(100, 500), rand.nextInt(100, 500), 72, 72, 1);
                        pole_enemy.add(enemyS);
                    } else if (druh%2==1) {
                        enemyS = new Enemy1(rand.nextInt(100, 500), rand.nextInt(100, 500), 72, 72, 1);
                        pole_enemy.add(enemyS);
                    }
                }
            }
        }

        }


    public void healthBar() {
//        info.health(health);

        if (health <= 0) {
            health =6;
            System.out.println("GAME OVER");
            gameOver = true;
        }
    }
    public void reset(){
        levelS.reset();
        gameOver = false;
        menu.setMode(true);
        pole_proj.clear();
        pole_enemy.clear();
        health = 6;
        score = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(image,x,y,getWidth(),getHeight(),this);

        for (int j = 0; j < pole_enemy.size();j++) {
            EnemySettings enemyS = pole_enemy.get(j);
            enemyS.vykresleniObr(g);
            for (int i = 0; i < pole_proj.size(); i++) {
                ProjectileSettings projectyleS = pole_proj.get(i);
                projectyleS.paintComponents(g);
            }
        }
        player.drawPlayer(g);
        shield.drawShield(g);

        g.drawImage(image2,x,y,getWidth(),getHeight(),null);

        info.vykreliseni(g);

        if (gameOver){
            g.drawImage(over,x,y,getWidth(),getHeight(),null);
            g.setFont(new Font("Arial", Font.BOLD,50));
            g.setColor(Color.RED);
            g.drawString("Game Over", 200, 320);
            g.drawString("Score: "+score, 200, 400);
        }
        if (levelS.isVictory()) {
            g.setFont(new Font("Arial", Font.BOLD,64));
            g.setColor(Color.GREEN);
            g.drawString("Victory", 150, 320);
            g.drawString("Score: "+score, 150, 400);
        }
        if (menu.isMode()){
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
        if (gameOver||levelS.isVictory()) {
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
