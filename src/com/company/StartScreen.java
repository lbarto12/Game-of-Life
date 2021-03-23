package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Vector;

public class StartScreen extends JPanel {
    private Main window;
    public JButton startButton;

    public StartScreen(Main window){
        this.window = window;
        this.init();
    }

    private void init(){
        this.setLayout(new GridLayout(4, 1));

        // TITLE
        var title = new JLabel("Game of Life", JLabel.CENTER);
        title.setBackground(Color.LIGHT_GRAY);
        title.setOpaque(true);
        title.setFont(new Font(this.getFont().getName(), Font.PLAIN, 50));
        this.add(title);
        // !TITLE

        // RANGE PANEL
        var rangePanel = new JPanel(new GridLayout(1, 2));

        var rangeTitle = new JLabel("RANGE", JLabel.CENTER);
        rangeTitle.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        rangeTitle.setBackground(Color.GRAY);
        rangeTitle.setFont(new Font(this.getFont().getName(), Font.PLAIN, 20));
        rangeTitle.setOpaque(true);

        var rangeEnter = new JButton(String.valueOf(GameHandler.boardSize));
        rangeEnter.setToolTipText("Click to change board dimensions");
        rangeEnter.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        rangeEnter.setBackground(Color.GRAY);
        rangeEnter.setFont(new Font(this.getFont().getName(), Font.PLAIN, 20));
        rangeEnter.setFocusPainted(false);
        rangeEnter.addActionListener(e -> {
            GameHandler.boardSize += 50;
            if (GameHandler.boardSize > 500)
                GameHandler.boardSize = 50;
            rangeEnter.setText(String.valueOf(GameHandler.boardSize));
        });

        rangePanel.add(rangeTitle);
        rangePanel.add(rangeEnter);
        this.add(rangePanel);
        // !RANGE PANEL

        // START MODE
        var swapMode = new JButton((GameHandler.randomStart) ? "Random Grid": "Blank Grid");
        swapMode.setToolTipText("Click to change starting style");
        swapMode.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        swapMode.setBackground(Color.GRAY);
        swapMode.setFont(new Font(this.getFont().getName(), Font.PLAIN, 20));
        swapMode.setFocusPainted(false);
        swapMode.addActionListener(e -> {
            GameHandler.randomStart = !GameHandler.randomStart;
            swapMode.setText((GameHandler.randomStart) ? "Random Grid": "Blank Grid");
        });
        this.add(swapMode);
        // !START MODE


        // START BUTTON
        this.startButton = new JButton("Start");
        this.startButton.setFont(new Font(this.getFont().getName(), Font.PLAIN, 20));
        this.startButton.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        this.startButton.setBackground(Color.DARK_GRAY);
        this.startButton.setForeground(Color.white);
        this.startButton.setFocusPainted(false);
        this.add(startButton);
        // !START BUTTON
    }

}
