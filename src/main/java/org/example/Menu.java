package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Menu extends JPanel implements MouseListener, MouseMotionListener {

    public String SOUBOR_MENU = "src/main/resources/Menu/menuBackground.png";
    private final String BUTTON_START = "src/main/resources/Menu/button_play.png";
    private final String BUTTON_STARTPUSH = "src/main/resources/Menu/button_playPush.png";
    private final String BUTTON_EXIT = "src/main/resources/Menu/button_exit.png";
    private final String BUTTON_EXITPUSH = "src/main/resources/Menu/button_exitPush.png";

    private int loccation;

    private Image i_menu;
    private Image i_button_play;
    private Image i_button_playPush;
    private Image i_button_stop;
    private Image i_button_stopPush;

    private final int startX =190;
    private final int startY =210;
    private final int startWidth =26*10;
    private final int startHeight =14*10;
    private final int exitX =210;
    private final int exitY =360;
    private final int exitWidht =22*10;
    private final int exitHeight =13*10;
    private final int x=0;
    private final int y=0;
    private final int widht;
    private final int height;

    private boolean mode=true;

    private final Rectangle rectangleStart;
    private final Rectangle rectangleexit;


    public Menu(int w,int h) {
        i_menu = new ImageIcon(SOUBOR_MENU).getImage();
        i_button_play = new ImageIcon(BUTTON_START).getImage();
        i_button_playPush = new ImageIcon(BUTTON_STARTPUSH).getImage();
        i_button_stop = new ImageIcon(BUTTON_EXIT).getImage();
        i_button_stopPush = new ImageIcon(BUTTON_EXITPUSH).getImage();
        rectangleStart = new Rectangle(startX, startY, startWidth, startHeight);
        rectangleexit = new Rectangle(exitX, exitY, exitWidht, exitHeight);

        this.widht = w;
        this.height = h;
    }

    public void drawMenu(Graphics g) {
        g.drawImage(i_menu,x,y, widht, height,null);

        if (loccation==1) {
            g.drawImage(i_button_playPush, startX, startY, startWidth, startHeight, null);
            g.drawImage(i_button_stop, exitX, exitY, exitWidht, exitHeight, null);
        }

        if (loccation==2) {
            g.drawImage(i_button_stopPush, exitX, exitY, exitWidht, exitHeight, null);
            g.drawImage(i_button_play, startX, startY, startWidth, startHeight, null);
        }

        else if (loccation==0) {
            g.drawImage(i_button_play, startX, startY, startWidth, startHeight, null);
            g.drawImage(i_button_stop, exitX, exitY, exitWidht, exitHeight, null);
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

            if (rectangleStart.contains(e.getPoint())) {
                loccation = 1;
            } else {
                loccation = 0;
            }
            if (rectangleexit.contains(e.getPoint())) {
                loccation = 2;
            }

    }





    @Override
    public void mouseClicked(MouseEvent e) {
        if (mode) {
            if (rectangleStart.contains(e.getPoint())) {
                mode = false;
            }
            if (rectangleexit.contains(e.getPoint())) {
                System.exit(0);

            }
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
    public int getLoccation() {
        return loccation;
    }


    public void setI_menu(Image i_menu) {
        this.i_menu = i_menu;
    }

    public void setI_button_play(Image i_button_play) {
        this.i_button_play = i_button_play;
    }

    public void setI_button_playPush(Image i_button_playPush) {
        this.i_button_playPush = i_button_playPush;
    }

    public void setI_button_stop(Image i_button_stop) {
        this.i_button_stop = i_button_stop;
    }

    public void setI_button_stopPush(Image i_button_stopPush) {
        this.i_button_stopPush = i_button_stopPush;
    }

    public boolean isMode() {
        return mode;
    }
    public void setMode(boolean mode) {
        this.mode = mode;
    }
}
