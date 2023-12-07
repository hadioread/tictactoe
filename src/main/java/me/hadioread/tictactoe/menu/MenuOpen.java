package me.hadioread.tictactoe.menu;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import me.hadioread.tictactoe.Window;
import me.hadioread.tictactoe.mechanics.Game;
import me.hadioread.tictactoe.mechanics.Cell;
import me.hadioread.tictactoe.mechanics.State;

public class MenuOpen implements ActionListener {

    private Game game;
    private Window window;

    public MenuOpen(Game game, Window window) {
        this.game = game;
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
        
        try {
            FileInputStream stream = new FileInputStream(dir + file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            String save = reader.readLine();
            for (Cell cell : game.getCells()) {
                cell.setState(State.from(save.charAt(0)));
                save = save.substring(1);
            }
            window.getLabelPointsOne().setText(reader.readLine());
            window.getLabelPointsTwo().setText(reader.readLine());

            game.setSinglePlayer(Boolean.parseBoolean(reader.readLine()));

            reader.close();

            window.getMenuPlayers().setEnabled(false);
            window.getMenuDifficulty().setEnabled(false);
            window.getMenuFirstTurn().setEnabled(false);
            game.setCellsEnabled(true);
        } catch (IOException i) {
            JOptionPane.showMessageDialog(window.getFrame(), "An error occured while retrieving");
        }

    }

}
