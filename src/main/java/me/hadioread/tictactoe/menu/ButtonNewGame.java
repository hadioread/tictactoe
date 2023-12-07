package me.hadioread.tictactoe.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import me.hadioread.tictactoe.Window;
import me.hadioread.tictactoe.mechanics.Game;
import me.hadioread.tictactoe.mechanics.Player;

public class ButtonNewGame implements ActionListener {

    private Game game;
    private Window window;

    public ButtonNewGame(Game game, Window window) {
        this.game = game;
        this.window = window;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Reset and enable cells
        game.clearCells();
        game.setCellsEnabled(true); 
        
        // Enable settings
        window.getMenuDifficulty().setEnabled(true); 
        window.getMenuPlayers().setEnabled(true);
        window.getMenuFirstTurn().setEnabled(true);

        window.getItemSave().setEnabled(false); // Empty game cannot be saved

        if (!game.isSinglePlayer()) { // Change game turn if in two player mode
            if (game.getFirstTurn().equals(Player.ONE)) {
                game.setPlayerOneTurn(true);
            } else {
                game.setPlayerOneTurn(false);
            }
        }

        // Enable start button and disable cells if single player and computer goes first
        if (game.isSinglePlayer() && game.getFirstTurn().equals(Player.TWO)) {
            window.getButtonStartGame().setEnabled(true);
            window.getButtonStartGame().setVisible(true);
            game.setCellsEnabled(false);
        }

    }

}
