package me.hadioread.tictactoe.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import me.hadioread.tictactoe.Window;
import me.hadioread.tictactoe.mechanics.AI;
import me.hadioread.tictactoe.mechanics.Game;

public class ButtonStartGame implements ActionListener {

    private Game game;
    private Window window;
    private AI ai;

    public ButtonStartGame(Game game, Window window) {
        this.game = game;
        this.window = window;
        ai = new AI(game);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.setCellsEnabled(true); // Enable cells to be used

        short slot = ai.findPosition(game.getPlayerTwo());
        game.getCells().get(slot).setState(game.getPlayerTwo()); // Place new marker

        window.getButtonStartGame().setEnabled(false);
    }
    
}
