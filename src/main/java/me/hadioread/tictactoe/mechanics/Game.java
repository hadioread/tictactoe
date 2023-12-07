/**
 * Management class for game to save and edit anything settings and game cells.
 * Date released: 25/10/23
 * @author Lachlan Schultz
 * JDK Version 17
 */

package me.hadioread.tictactoe.mechanics;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private final List<Cell> cells;

	private Difficulty difficulty;
	private boolean singlePlayer;
	private boolean playerOneTurn; // For two player mode

	private State playerOne;
	private State playerTwo;

	// If single player, computer is player 2
	private Player firstTurn;

	public Game() {
		cells = new ArrayList<>();
	}

	// Enable cells
	public void setCellsEnabled(boolean enabled) {
		for (Cell button : cells) {
			button.getJButton().setEnabled(enabled);
		}
	}

	// Clear cells
	public void clearCells() {
		for (Cell cell : cells) {
			cell.clear();
		}
	}

	// Getter and setter methods
	public List<Cell> getCells() {
		return cells;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	public boolean isSinglePlayer() {
		return singlePlayer;
	}

	public void setSinglePlayer(boolean singlePlayer) {
		this.singlePlayer = singlePlayer;
	}

	public boolean isPlayerOneTurn() {
		return playerOneTurn;
	}

	public void setPlayerOneTurn(boolean playerOneTurn) {
		this.playerOneTurn = playerOneTurn;
	}

	public State getPlayerOne() {
		return playerOne;
	}

	public void setPlayerOne(State playerOne) {
		this.playerOne = playerOne;
	}

	public State getPlayerTwo() {
		return playerTwo;
	}

	public void setPlayerTwo(State playerTwo) {
		this.playerTwo = playerTwo;
	}

	public Player getFirstTurn() {
		return firstTurn;
	}

	public void setFirstTurn(Player firstTurn) {
		this.firstTurn = firstTurn;
	}
	
}
