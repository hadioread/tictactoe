package me.hadioread.tictactoe.menu;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import me.hadioread.tictactoe.Window;
import me.hadioread.tictactoe.mechanics.Game;
import me.hadioread.tictactoe.mechanics.Cell;

public class MenuSave implements ActionListener {
    
    private Game game;
    private Window window;

    public MenuSave(Game game, Window window) {
        this.game = game;
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FileDialog dialog = new FileDialog(window.getFrame(), "Select Output", FileDialog.SAVE);
        dialog.setSize(450, 300);
		dialog.setVisible(true);

        String file = dialog.getFile();
        String dir = dialog.getDirectory();

        // If menu was cancelled
        if (file == null || dir == null)
            return;

        String save = ""; 
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(dir + file), true);
            
            for (Cell gameButton : game.getCells()) {
                save += gameButton.getState().getCharacter();  // Save game progress into single string
            }
            save += "\n";

            save += window.getLabelPointsOne().getText() + "\n";
            save += window.getLabelPointsTwo().getText() + "\n";
            save += game.isSinglePlayer() ? "true" : "false"; // Is single player

            writer.print(save);
            writer.close();
            JOptionPane.showMessageDialog(window.getFrame(), "File saved successfully");
        } catch (FileNotFoundException i ) {
            JOptionPane.showMessageDialog(window.getFrame(), "An error occured while saving");
        }

    }

}
