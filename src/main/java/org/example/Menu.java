package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {

    public String SOUBOR_MENU = "src/main/resources/menuBackground.png";
    private Image image;
    Panel panel;
    private int x;
    private int y;
    private int w;
    private int h;

    public Menu(int x,int y,int w,int h) {
        image = new ImageIcon(SOUBOR_MENU).getImage();
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

                image=null;
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

        g.drawImage(image,x,y,w,h,null);

    }
}
