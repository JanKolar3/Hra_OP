package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Menu extends JPanel implements MouseListener, MouseMotionListener {

//    private static Image[] BUTTON_START = SpriteLoader.getFrames("src/main/resources/button_play - kopie.png",53,14,2);

    public String SOUBOR_MENU = "src/main/resources/Menu/menuBackground.png";
    private String BUTTON_START = "src/main/resources/Menu/button_play.png";
    private String BUTTON_STARTPUSH = "src/main/resources/Menu/button_playPush.png";
    private String BUTTON_EXIT = "src/main/resources/Menu/button_exit.png";
    private String BUTTON_EXITPUSH = "src/main/resources/Menu/button_exitPush.png";

    private int loccation;

    private Image i_menu;
    private Image i_button_play;
    private Image i_button_playPush;
    private Image i_button_stop;
    private Image i_button_stopPush;
//    private JButton button;
//    private JButton button1;
    private int sx=190,sy=210,sw=26*10,sh=14*10,ex=210,ey=360,ew=22*10,eh=13*10;
    private int x,y,w,h;
    private Rectangle rect;
    private Rectangle rect1;


    public Menu(int x,int y,int w,int h) {
        i_menu = new ImageIcon(SOUBOR_MENU).getImage();
        i_button_play = new ImageIcon(BUTTON_START).getImage();
        i_button_playPush = new ImageIcon(BUTTON_STARTPUSH).getImage();
        i_button_stop = new ImageIcon(BUTTON_EXIT).getImage();
        i_button_stopPush = new ImageIcon(BUTTON_EXITPUSH).getImage();
        rectangle();
        rectangle();



//        setBackground(Color.white);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public Rectangle rectangle() {

        Rectangle rect = new Rectangle();

            return new Rectangle(sx,sy,sw,sh);
    }
    public Rectangle rectangle1() {
        Rectangle rect1 = new Rectangle(ex,ey,ew,eh);
        return rect1;
    }

    public void removeRect(Rectangle rectangle){

        rect1 = null;
    }

//    public void buttons() {
//        button = new JButton("PLAY");
//
//
//        button.setBounds(x, y, w, h);
//        button1 = new JButton("END");
//        button1.setBounds(x, y, w, h);
//        setLayout(new FlowLayout(FlowLayout.CENTER));
//        add(button);
//        add(button1);
//
//
//
//        }
//
//        button.addActionListener(new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                i_menu = null;
//                button.setVisible(false);
//                button1.setVisible(false);
//
//
//            }
//
//        });
//
//        button1.addActionListener(new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        });
//
//    }






    public void vykresleniMenu(Graphics g) {

        g.drawImage(i_menu,x,y,w,h,null);
//        g.drawImage(BUTTON_START[index],100,250,53,14,null );
        g.drawImage(i_button_play,sx,sy,sw,sh,null);
        g.drawImage(i_button_stop,ex,ey,ew,eh,null);

        if (getLoccation()==1) {
            g.drawImage(i_button_playPush, sx, sy, sw, sh, null);

        }

        if (getLoccation()==2) {
            g.drawImage(i_button_stopPush, ex, ey, ew, eh, null);
        }

        if (getLoccation()==0) {
            g.drawImage(i_button_play, sx, sy, sw, sh, null);
            g.drawImage(i_button_stop, ex, ey, ew, eh, null);
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (rectangle().contains(e.getPoint())) {
            loccation = 1;
//            System.out.println("ADAADA");
        }else {
            loccation = 0;
//            System.out.println("kkkkk");
        }
        if (rectangle1().contains(e.getPoint())) {
            loccation = 2;
        }
    }





    @Override
    public void mouseClicked(MouseEvent e) {
        if (rectangle().contains(e.getPoint())) {
            setI_menu(null) ;
            setI_button_play(null) ;
            setI_button_playPush(null) ;
            setI_button_stop(null) ;
            setI_button_stopPush(null);
            removeRect(rectangle());



        }
        if (rectangle1().contains(e.getPoint())) {
            System.exit(0);

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
}
