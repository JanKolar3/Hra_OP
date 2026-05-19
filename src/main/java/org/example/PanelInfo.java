package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PanelInfo extends JPanel implements MouseListener, MouseMotionListener {

    private final String SOUBOR_POZADI = "src/main/resources/les.png";
    private final String SOUBRO_OHRANI = "src/main/resources/lesUP.png";

    private final Image image1;
    private final Image image2;
    GameManager gameManager;
    Menu menu;
    JLabel jLabel;



    public PanelInfo() {
//        menu = new Menu(getX(),getY(),640,640);
        if (!menu.isMode()) {
            gameManager = new GameManager();
        }

        image1 = new ImageIcon(SOUBOR_POZADI).getImage();
        image2 = new ImageIcon(SOUBRO_OHRANI).getImage();


        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
//        jLabel = new JLabel("SCORE");

//        if (menu.isMode()==false) {
//            add(jLabel);
//        }
    }

    @Override
    protected void paintComponent(Graphics g) {



        if (menu.isMode()){
            menu.vykresleniMenu(g);
        } else if (!menu.isMode()) {

            g.drawImage(image1, getX(), getY(), getWidth(), getHeight(), null);
            gameManager.paintComponents(g);
            g.drawImage(image2, getX(), getY(), getWidth(), getHeight(), null);
        }



    }

    @Override
    public void mouseClicked(MouseEvent e) {
        menu.mouseClicked(e);

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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        menu.mouseMoved(e);

    }
}