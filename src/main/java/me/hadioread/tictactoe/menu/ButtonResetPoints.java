package me.hadioread.tictactoe.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import me.hadioread.tictactoe.Window;

public class ButtonResetPoints implements ActionListener {

    private Window window;

    public ButtonResetPoints(Window window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Reset points
        window.getLabelPointsOne().setText("0");
        window.getLabelPointsTwo().setText("0");
    }
    
}
