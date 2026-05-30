package org.example;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame() {


        setTitle("Game");
        setSize(655, 675);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//      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

        GameManager gameManager = new GameManager();
        add(gameManager);



    }
}
