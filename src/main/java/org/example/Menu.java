package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {

    public String SOUBOR_MENU = "src/main/resources/menuBackground.png";
    private String BUTTON_START = "src/main/resources/button_play - kopie.png";
    private String BUTTON_EXIT = "src/main/resources/button_exit - kopie.png";

    private Image i_menu;
    private Image i_button_play;
    private Image i_button_stop;
    Panel panel;
    private int x;
    private int y;
    private int w;
    private int h;

    public Menu(int x,int y,int w,int h) {
        i_menu = new ImageIcon(SOUBOR_MENU).getImage();
        i_button_play = new ImageIcon(BUTTON_START).getImage();
        i_button_stop = new ImageIcon(BUTTON_EXIT).getImage();
//        setBackground(Color.white);
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        button();
    }

    public void button(){
        JButton button=new JButton("PLAY");
        button.setBounds(x,y,w,h);
        JButton button1=new JButton("END");
        button1.setBounds(x,y,w,h);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(button);
        add(button1);

        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                i_menu=null;
                button.setVisible(false);
                button1.setVisible(false);


            }

        });
        button1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });



    }


    public void vykresleniMenu(Graphics g) {

        g.drawImage(i_menu,x,y,w,h,null);

    }
}
