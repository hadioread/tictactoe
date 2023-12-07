package me.hadioread.tictactoe.menu;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import me.hadioread.tictactoe.Window;

public class MenuSelectColor implements ActionListener {

    private Window window;

    public MenuSelectColor(Window window) {
        this.window = window;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JLabel backgroundImage = window.getBackgroundImage();
        if (backgroundImage != null) { // Remove old background image and reload
            Container parent = backgroundImage.getParent();
            parent.remove(backgroundImage);
            parent.validate();
            parent.repaint();
            window.setBackgroundImage(null);
        }

        Container pane = window.getFrame().getContentPane();
        switch (e.getActionCommand()) { // Color selected
            case "Default":
                pane.setBackground(null); break;
            case "Gray":
                pane.setBackground(Color.GRAY); break;
            case "Red":
                pane.setBackground(Color.RED); break;
            case "Pink":
                pane.setBackground(Color.PINK); break;
            case "Orange":
                pane.setBackground(Color.ORANGE); break;
            case "Yellow":
                pane.setBackground(Color.YELLOW); break;
            case "Green":
                pane.setBackground(Color.GREEN); break;
            case "Blue":
                pane.setBackground(Color.BLUE); break;
            default:
        }
    }

}
