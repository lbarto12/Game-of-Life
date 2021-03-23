package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CellHandler extends JPanel {
    public CellHandler(){
        this.init();
    }

    private ArrayList<ArrayList<Boolean>> grid;

    private int tickSpeed = 50;
    public void setTickSpeed(int speed){
        this.tickSpeed = speed;
    }

    public int getTickSpeed() {
        return tickSpeed;
    }

    private boolean paused = false;

    public void pause(){
        this.paused = !this.paused;
    }

    private void init(){
        this.setBackground(Color.WHITE);

        this.grid = new ArrayList<>();

        for (int i = 0; i < GameHandler.boardSize; ++i){
            this.grid.add(new ArrayList<>());
            for (int j = 0; j < GameHandler.boardSize; ++j){
                boolean style = GameHandler.randomStart && (int) (Math.random() * 1000) % 2 == 0;
                this.grid.get(i).add(style);
            }
        }


        new Thread(() ->{
            while (GameHandler.screen == GameHandler.Screen.Game){
                this.logic();
                try {
                    Thread.sleep(this.tickSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        float sizeX = (float)(this.getWidth()) / (GameHandler.boardSize);
        float sizeY = (float)(this.getHeight()) / (GameHandler.boardSize);

        for (int i = 0; i < GameHandler.boardSize; ++i){
            for (int j = 0; j < GameHandler.boardSize; ++j){
                g.setColor((this.grid.get(i).get(j)) ? Color.BLACK : Color.WHITE);
                g.fillRect(
                        (int) (sizeX * j),
                        (int) (sizeY * i),
                        (int) sizeX,
                        (int) sizeY
                );
            }
        }
    }

    private void logic(){

        if (!this.paused){
            var temp = new ArrayList<ArrayList<Boolean>>();


            for (int i = 0; i < GameHandler.boardSize; ++i){
                temp.add(new ArrayList<Boolean>());
                for (int j = 0; j < GameHandler.boardSize; ++j){
                    temp.get(i).add(this.grid.get(i).get(j));
                }
            }


            int slotY; int slotX;

            for (int i = 0; i < GameHandler.boardSize; i++) {
                for (int j = 0; j < GameHandler.boardSize; j++) {
                    // Stores Number of Live Cells around Given Cell
                    int liveCount = 0;
                    //Checks State of Slots around Current Slot
                    for (int k = -1; k < 2; k++){
                        for (int f = -1; f < 2; f++) {
                            // Checks if the Query is Outside of the Board, and if it is it Wraps it
                            if (i + k > GameHandler.boardSize - 1) slotY = 0;
                            else if (i + k < 0) slotY = GameHandler.boardSize - 1;
                            else slotY = i + k;
                            if (j + f > GameHandler.boardSize - 1) slotX = 0;
                            else if (j + f < 0) slotX = GameHandler.boardSize - 1;
                            else slotX = j + f;

                            // If the Cell Being Tested is Alive, Adds 1 to live count -> Excluding itself
                            if (this.grid.get(slotY).get(slotX) && !(k == 0 && f == 0)) liveCount++;

                        }
                    }

                    // Tests for Conditions to Die / Revive
                    if (this.grid.get(i).get(j) && (liveCount < 2 || liveCount > 3)) {
                        temp.get(i).set(j, false);
                    } else if (!this.grid.get(i).get(j) && liveCount == 3) {
                        temp.get(i).set(j, true);
                    }
                }
            }
            this.grid = temp;
        }
            this.repaint();
    }

    public void manualModify(MouseEvent e){
        if (e.getX() > this.getX() + 7 &&
                e.getX() < this.getX() + this.getWidth() + 7 &&
                e.getY() > this.getY() + 30 &&
                e.getY() < this.getY() + this.getHeight() + 30){
            if (e.getButton() == MouseEvent.BUTTON1){
                this.change(true, e.getX(), e.getY());
            }
            else if (e.getButton() == MouseEvent.BUTTON3){
                this.change(false, e.getX(), e.getY());
            }
        }
    }

    public void manualModifyDrag(MouseEvent e) {
        if (e.getX() > this.getX() + 7 &&
                e.getX() < this.getX() + this.getWidth() + 7 &&
                e.getY() > this.getY() + 30 &&
                e.getY() < this.getY() + this.getHeight() + 30){
            if (SwingUtilities.isLeftMouseButton(e))
                this.change(true, e.getX(), e.getY());
            else if (SwingUtilities.isRightMouseButton(e))
                this.change(false, e.getX(), e.getY());
        }

    }

    private void change(boolean to, int posx, int posy){
        // I know there's a better way to do this
        int px = posx - 107;
        int py = posy - 30;

        float factorX = (float)px / this.getWidth();
        int x = (int)(GameHandler.boardSize * factorX);
        float factorY = (float)py / this.getHeight();
        int y = (int)(GameHandler.boardSize * factorY);

        this.grid.get(y).set(x, to);
    }

    public void clear(){
        this.grid.clear();
        for (int i = 0; i < GameHandler.boardSize; ++i){
            this.grid.add(new ArrayList<>());
            for (int j = 0; j < GameHandler.boardSize; ++j){
                this.grid.get(i).add(false);
            }
        }
    }

}