package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class GameScreen extends JPanel {
    private Main window;
    public SettingsPanel settingsPanel;
    private CellHandler cellHandler;


    public GameScreen(Main window){
        this.window = window;
        this.init();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }




    private void init(){
        this.setLayout(new BorderLayout());


        this.settingsPanel = new SettingsPanel();
        this.add(settingsPanel, BorderLayout.WEST);

    }

    public void createNewGame(){
        this.removeAll();
        this.add(this.settingsPanel, BorderLayout.WEST);
        this.cellHandler = new CellHandler();
        this.add(this.cellHandler, BorderLayout.CENTER);
        this.settingsPanel.addCellHandler(this.cellHandler);
        this.repaint();
    }

    public void manualModify(MouseEvent e){
        if (this.cellHandler != null)
            this.cellHandler.manualModify(e);
    }

    public void manualModifyDrag(MouseEvent e){
        if (this.cellHandler != null)
            this.cellHandler.manualModifyDrag(e);
    }

}
