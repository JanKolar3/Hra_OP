package org.example;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        setTitle("The Last Shield");
        setSize(655, 675);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameManager gameManager = new GameManager();
        add(gameManager);
    }
}
