package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameHandler extends JPanel implements MouseListener, MouseMotionListener {
    private Main window;
    private StartScreen startScreen;
    private GameScreen gameScreen;

    public GameHandler(Main window){
        this.window = window;
        this.setLayout(new GridLayout(1, 1));
        this.init();
    }


    public void startGame(){
        this.removeAll();

        GameHandler.screen = Screen.Game;
        this.gameScreen.createNewGame();
        this.add(this.gameScreen);
        this.gameScreen.setVisible(true);
        this.validate();
        this.repaint();


    }

    public void backToStart() {
        GameHandler.screen = Screen.Start;
        this.removeAll();
        this.add(this.startScreen);
        this.repaint();
    }



    public enum Screen{
        Start,
        Game
    }
    public static Screen screen = Screen.Start;

    public static boolean randomStart = true;

    public static int boardSize = 50;



    private void init(){
        this.window.addMouseListener(this);
        this.window.addMouseMotionListener(this);

        this.startScreen = new StartScreen(this.window);
        this.gameScreen = new GameScreen(this.window);



        // START SCREEN
        this.startScreen.startButton.addActionListener(e -> {
            this.startGame();
        });

        this.add(this.startScreen);
        // !START SCREEN


        // GAME SCREEN
        this.gameScreen.settingsPanel.back.addActionListener(e -> {
            this.backToStart();
        });
        // !GAME SCREEN

        this.window.setLayout(new BorderLayout());
        this.window.setFocusable(true);
        this.window.requestFocus();
        this.window.add(this, BorderLayout.CENTER);
        this.window.setVisible(true);
    }




    @Override
    public void mousePressed(MouseEvent e) {
        this.gameScreen.manualModify(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.gameScreen.manualModifyDrag(e);
    }


    public void mouseMoved(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) {}
}
