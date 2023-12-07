package me.hadioread.tictactoe.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import me.hadioread.tictactoe.Window;
import me.hadioread.tictactoe.mechanics.AI;
import me.hadioread.tictactoe.mechanics.Game;
import me.hadioread.tictactoe.mechanics.Cell;
import me.hadioread.tictactoe.mechanics.State;

public class CellListener implements ActionListener {

    private Game game;
    private Window window;

	private Cell cell;

    public CellListener(Game game, Window window, Cell cell) {
        this.game = game;
        this.window = window;
		this.cell = cell;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        window.getMenuPlayers().setEnabled(false);
        window.getMenuDifficulty().setEnabled(false);
		window.getMenuFirstTurn().setEnabled(false);

		// Check if box is empty
		if (!cell.getState().equals(State.NONE))
			return;

		// Place checker on board
		State state = null; 
		if (game.isPlayerOneTurn()) { // Player one turn
			cell.setState(state = game.getPlayerOne());
		} else { // Player two turn
			cell.setState(state = game.getPlayerTwo());
		}

		// Check if anyone has won
		if (hasWon(state)) {
			win(state); // Display win message and disable GUI
			return;
		}

		// If no one has won and all the checkers are full, it's a tie
		if (isFull()) { 
			tie();
			return;
		}

		window.getItemSave().setEnabled(true);

		if (game.isSinglePlayer()) { // Play against the computer
			AI ai = new AI(game);
			game.getCells().get(ai.findPosition(game.getPlayerTwo())).setState(game.getPlayerTwo());

			if (hasWon(game.getPlayerTwo())) {
				win(game.getPlayerTwo());
				return;
			}

			if (isFull()) { // Game is a tie
				tie();
				return;
			}

		} else { // Two player
			game.setPlayerOneTurn(!game.isPlayerOneTurn()); // Set the turn to the other player
		}
		
   	 }

	// Magic square method
	// https://mathworld.wolfram.com/MagicSquare.html
	private boolean hasWon(State player) {
		short[] found = new short[9];
		short arrayCounter = 0;
		for (Cell cell : game.getCells()) {
			if (cell.getState().equals(player)) {
				found[arrayCounter] = cell.getMagicSquareNumber();
				arrayCounter++;
			}
		}

		// Determine if any combination of magic square numbers is equal to 15
		for (int x = 0; x < found.length; x++) {
			for (int y = 0; y < found.length; y++) {
				for (int z = 0; z < found.length; z++) {
					if (found[x] == found[y] || found[y] == found[z] || found[x] == found[z]) // Take duplicate squares into consideration
						break;

					if (found[x] == 0 || found[y] == 0 || found[z] == 0) // Take empty squares into consideration
						break;

					if ((found[x] + found[y] + found[z]) == 15) { // Found 3 in a row
						return true;
					}
				}
			}
		}

		return false;
	}

	// Check if board is full 
	private boolean isFull() {
		boolean full = true;

		for (Cell button : game.getCells()) {
			if (button.getState().equals(State.NONE)) {
				full = false;
				break;
			}
		}

		return full;
	 }

	 private void win(State winner) {
		game.setCellsEnabled(false);

		if (game.getPlayerOne().equals(winner)) { // Update point counter
			window.getLabelPointsOne().setText(Integer.parseInt(window.getLabelPointsOne().getText()) + 1 + "");
		} else {
			window.getLabelPointsTwo().setText(Integer.parseInt(window.getLabelPointsTwo().getText()) + 1 + "");
		}


		JOptionPane.showMessageDialog(null, winner.name() + " is the winner!");
	 }

	 private void tie() {
		game.setCellsEnabled(false);
		JOptionPane.showMessageDialog(null, "It's a tie!");
	}

}
