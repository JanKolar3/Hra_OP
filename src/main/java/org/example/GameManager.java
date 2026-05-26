package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GameManager extends JPanel implements KeyListener, MouseMotionListener, MouseListener {

    private final String SOUBOR_POZADI = "src/main/resources/pozadi.png";
    private final String SOUBRO_OHRANI = "src/main/resources/lesUP.png";
    Image[] HEALTHS = SpriteLoader.getFrames("/Player/healts.png",16,16,3);
//    private String SOUBOR_HELTH = "src/main/resources/healthtest1.png";

    ArrayList<EnemySettings> pole_enemy =new ArrayList<>();
    ArrayList<ProjectileSettings> pole_proj = new ArrayList<>();
    Random rand = new Random();

    private EnemySettings enemyS;
    private final LevelSettings levelS;

    private final Image image;
    private final Image image2;
    private final JLabel jLabel;
    private final JLabel txtlevel;
    private final JLabel txtwave;
    private final Player player;
    private final Shield shield;
    private final Menu menu;
    private PanelInfo info;
    private  int timer = 600,timer1=100;
    private int pocet=1;



    private int score = 0;

    int health = 6;
    int max =1;
    private boolean gameOver = false;

    private boolean damaged = false;
    private int damage=0;
    private int damage2=0;





    private int x,sx;
    private int y,sy;



    public GameManager() {
        image = new ImageIcon(SOUBOR_POZADI).getImage();
        info = new PanelInfo(x,y,getWidth(),getHeight());
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

                    player.moveMent();
                    player.playerAnimation();
                    player.ohraniceni();
                    healthBar();

                    shield.shieldRotate();
                    shield.shieldAnimation();
                    shield.Cooldown();

                    pocet = pole_enemy.size();

                    addEnemy();
                    collision();

                    info.shieldTimer(shield.getCooldownAktivace(),shield.isJe1());

                    for (EnemySettings enemyS : pole_enemy) {
                        enemyS.enemyAnimation();
                        enemyS.enemyMove(player);
//                        enemyS.ohraniceni();
                        enemyS.cooldownProj(player, pole_proj);
//                        enemyS.damage(damaged, levelS.getDamage());
                    }
                    if (pocet <= 0) {
                        levelS.waveSettings();
//                        damage= levelS.getDamage();

                    }

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
                    if (shield.collision1(projectyleS)) {
                        pole_proj.remove(projectyleS);
                        score += 5;
                        timer = 600;
                        i--;
                    }
                }

                if (projectyleS.isDamage()) {
                    if (projectyleS.collision(player)) {
                        health -= 1;
                        pole_proj.remove(projectyleS);
                        timer = 600;
                        i--;
                    }
                }

                if (projectyleS.collision2(shield)) {
                    if (shield.getShieldMode() == 2) {
                        projectyleS.setMode(2);
                    }
                }

                if (projectyleS.getMode() == 2) {
                    timer = 600;
                    timer1--;

                    for (int j = 0; j < pole_enemy.size(); j++) {
                        EnemySettings enemyS = pole_enemy.get(j);

                        if (projectyleS.collision1(enemyS)) {


                            enemyS.damage(true,levelS.getDamage());
                            pole_proj.remove(projectyleS);
                            i--;

                            if (enemyS.getK1() <=0){
                                pole_enemy.remove(enemyS);
                                pocet--;
                                score += 10;
//                                jLabel.setText(String.valueOf(score));

                                timer1 = 100;
                                j--;
                        }
                        }
                    }

                    if (timer1 <= 0) {
                        pole_proj.remove(projectyleS);
                        timer1 = 100;
                        i--;
                    }
                }
                 if (timer <= 0) {
                    timer = 600;
                    pole_proj.remove(projectyleS);
                    i--;
                }
                timer--;
        }

    }
    public void addEnemy(){
        if (pocet<=0) {
            levelS.enemyMax();
            max = levelS.getMax();

            while (pole_enemy.size() != levelS.getMax()) {
                if (pole_enemy.size() < levelS.getMax()) {

                    enemyS = new Enemy1(rand.nextInt(1, 600), rand.nextInt(1, 600), 72, 72, 1);
                    pole_enemy.add(enemyS);
                    pocet++;

//            enemyS = new Enemy2(rand.nextInt(1,400),rand.nextInt(1,400),50,50,1);
//            pole_enemy.add(enemyS);
                }
            }
        }

        }


    public void healthBar() {
        info.health(health);

        if (health <= 0) {
            health =6;
            System.out.println("GAME OVER");
            gameOver = true;
        }
    }
    public void reset(){
        gameOver = false;
        levelS.setVictory(false);
        menu.setMode(true);
        pole_proj.clear();
        pole_enemy.clear();
        health = 6;
        score = 0;
        max=1;
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
                projectyleS.draw(g);
            }
        }

        player.vykresleniObr(g);
        shield.vykresleniObr(g);
        g.drawImage(image2,x,y,getWidth(),getHeight(),null);
        info.vykreliseni(g);


        if (gameOver){
            g.setFont(new Font("Arial", Font.BOLD,64));
            g.setColor(Color.RED);
            g.drawString("Game Over", 150, 320);
            g.drawString("Score: "+score, 150, 400);
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
