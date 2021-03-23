package com.company;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    private CellHandler cellHandler;
    public SettingsPanel(){
        this.init();
    }

    public JButton back;
    private JButton changeTickSpeed;
    private JButton pause;
    private JButton clear;

    private void init(){

        this.setLayout(new GridLayout(10, 1));

        this.setOpaque(true);
        this.setPreferredSize(new Dimension(100, 0));


        this.back = new JButton("Back");
        this.add(back);


        this.changeTickSpeed = new JButton("Delay: 50ms");
        this.changeTickSpeed.addActionListener(e -> {
            this.cellHandler.setTickSpeed(
                    this.cellHandler.getTickSpeed() + 10
            );
            if (this.cellHandler.getTickSpeed() > 100)
                this.cellHandler.setTickSpeed(10);
            this.changeTickSpeed.setText("Delay: " + String.valueOf(this.cellHandler.getTickSpeed()) + "ms");
        });
        this.add(this.changeTickSpeed);


        this.pause = new JButton("Pause");
        this.pause.addActionListener(e ->{
            this.cellHandler.pause();
        });
        this.add(this.pause);

        this.clear = new JButton("Reset");
        this.clear.addActionListener(e -> {
            this.cellHandler.clear();
        });
        this.add(clear);

        this.add(new JLabel("<html>Left Click to<br>Place Cell</html>"));
        this.add(new JLabel("<html>Right Click to<br>Delete Cell</html>"));
    }

    public void addCellHandler(CellHandler cellHandler){
        this.cellHandler = cellHandler;
        this.changeTickSpeed.setText("Delay: " + String.valueOf(this.cellHandler.getTickSpeed()) + "ms");
    }

}
