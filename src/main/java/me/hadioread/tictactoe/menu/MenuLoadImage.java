package me.hadioread.tictactoe.menu;

import java.awt.Container;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import me.hadioread.tictactoe.Window;

public class MenuLoadImage implements ActionListener {

    private Window window;

    public MenuLoadImage(Window window) {
        this.window = window;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        FileDialog dialog = new FileDialog(window.getFrame(), "Select Input", FileDialog.LOAD);
        dialog.setSize(450, 300);
		dialog.setVisible(true);

        String file = dialog.getFile();
        String dir = dialog.getDirectory();

        // If menu was cancelled
        if (file == null || dir == null) 
            return;

        JLabel oldBackgroundImage = window.getBackgroundImage();
        if (oldBackgroundImage != null) { // Remove old background image and reload
            Container parent = oldBackgroundImage.getParent();
            parent.remove(oldBackgroundImage);
            parent.validate();
            parent.repaint();
            window.setBackgroundImage(null);
        }

        JLabel backgroundImage = new JLabel();
        backgroundImage.setSize(window.getFrame().getSize()); // Adjust image to fit screen
        backgroundImage.setIcon(new ImageIcon(dir + file));
        
        window.getFrame().getContentPane().setBackground(null); // Clear old colour
        window.getFrame().getContentPane().add(backgroundImage);
        window.setBackgroundImage(backgroundImage);
    }

}
