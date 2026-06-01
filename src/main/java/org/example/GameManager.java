package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GameManager extends JPanel implements KeyListener, MouseMotionListener, MouseListener {

    private final String SOUBOR_POZADI = "src/main/resources/Map/map.png";
    private final String SOUBRO_OHRANI = "src/main/resources/Map/lesUP.test.png";

    private final ArrayList<EnemySettings> pole_enemy =new ArrayList<>();
    private final ArrayList<ProjectileSettings> pole_projectile = new ArrayList<>();
    private final Random random = new Random();

    private EnemySettings enemyS;
    private final LevelSettings levelS;
    private final Player player;
    private final Shield shield;
    private final Menu menu;
    private final PanelInfo info;

    private final Image image1;
    private final Image image2;

    private int count =1;
    private int score = 0;
    private int health = 6;
    private int type =0;

    private int x=0;
    private int y=0;

    public GameManager() {
        image1 = new ImageIcon(SOUBOR_POZADI).getImage();
        image2 = new ImageIcon(SOUBRO_OHRANI).getImage();

        menu = new Menu(640,640);
        player = new Player(200,200,16*5,16*5);
        shield = new Shield(player,16*3,16*3);

        levelS = new LevelSettings();
        info = new PanelInfo();


        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);



        new Timer(16, e -> {
            if(!menu.isMode()) {
                if (!info.isGameOver()&&!levelS.isVictory()&&!info.isPause()) {
                    addEnemy();

                    collision();
                    player.moveMent();
                    shield.shieldRotate();

                    count =pole_enemy.size();
                   for (EnemySettings enemyS : pole_enemy) {
                        enemyS.enemyAnimation();
                        enemyS.enemyMove(player);
                        enemyS.cooldownProjectile(player, pole_projectile);
                    }
                    info.info(health,shield.isShiueldBounce(),levelS.getWave(),score,levelS.isVictory());
                }
            }
            repaint();
        }).start();
    }
    public void collision() {
            for (int i = 0; i < pole_projectile.size(); i++) {
                ProjectileSettings projectyleS = pole_projectile.get(i);
                projectyleS.direction(player);

                if (shield.getShieldMode() == 1) {
                    if (shield.collision(projectyleS)) {
                        pole_projectile.remove(i);
                        score += 5;
                        i--;
                        continue;
                    }
                }

                if (projectyleS.isDamage()) {
                    if (projectyleS.collisionPlayer(player)) {
                        health -= 1;
                        pole_projectile.remove(i);
                        i--;
                        continue;
                    }
                }

                if (projectyleS.collisionShield(shield)) {
                    if (shield.getShieldMode() == 2) {
                        projectyleS.setMode(2);
                        continue;
                    }
                }

                if (projectyleS.isDestroy()) {
                    pole_projectile.remove(i);
                    i--;
                }

                if (projectyleS.getMode() == 2) {

                    for (int j = 0; j < pole_enemy.size(); j++) {
                        EnemySettings enemyS = pole_enemy.get(j);

                        if (projectyleS.collisionEnemy(enemyS)) {


                            enemyS.damage(levelS.getDamage());
                            pole_projectile.remove(i);
                            i--;

                            if (enemyS.getDamage() <=0){
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
        count = pole_enemy.size();
        if (count <=0) {
            levelS.waveSettings();
            levelS.enemyMax();

            while (pole_enemy.size() != levelS.getMax()) {
                if (pole_enemy.size() < levelS.getMax()) {
                    type++;
                    if (type %2==0){
                        enemyS = new Enemy2(random.nextInt(100, 500), random.nextInt(100, 500), 72, 72, 1);
                        pole_enemy.add(enemyS);
                    } else if (type %2==1) {
                        enemyS = new Enemy1(random.nextInt(100, 500), random.nextInt(100, 500), 72, 72, 1);
                        pole_enemy.add(enemyS);
                    }
                }
            }
        }
        }

    public void reset(){
        levelS.reset();
        info.reset();
        menu.setMode(true);
        pole_projectile.clear();
        pole_enemy.clear();
        health = 6;
        score = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(image1,x,y,getWidth(),getHeight(),this);

        for (int j = 0; j < pole_enemy.size();j++) {
            EnemySettings enemyS = pole_enemy.get(j);
            enemyS.vykresleniObr(g);
            for (int i = 0; i < pole_projectile.size(); i++) {
                ProjectileSettings projectyleS = pole_projectile.get(i);
                projectyleS.drawProjectile(g);
            }
        }
        player.drawPlayer(g);
        shield.drawShield(g);

        g.drawImage(image2,x,y,getWidth(),getHeight(),null);

        info.vykreliseni(g);

        if (menu.isMode()){
            menu.drawMenu(g);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!info.isPause()) {
            shield.keyPressed(e);
            player.keyPressed(e);
        }
            info.keyPressed(e);



    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
        shield.keyReleased(e);
        info.keyReleased(e);

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
        if (info.isGameOver()||levelS.isVictory()) {
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
