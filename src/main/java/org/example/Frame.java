package org.example;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public Frame() {


        setTitle("Game");
        setSize(655, 675);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


//        Panel panel = new Panel();
//
//        add(panel);
        GameManager gameManager = new GameManager();
        add(gameManager);



    }
}
