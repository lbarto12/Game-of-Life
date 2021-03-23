package com.company;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public Main(){
        this.init();
    }

    private void init(){
        this.setTitle("Game of Life");
        this.setSize(new Dimension(700, 600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    public static void main(String[] args) {
        new GameHandler(new Main());
    }

}
