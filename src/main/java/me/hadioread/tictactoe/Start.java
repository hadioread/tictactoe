package me.hadioread.tictactoe;

import java.awt.EventQueue;

import me.hadioread.tictactoe.mechanics.Game;

public class Start {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Window("Tic Tac Toe", 600, 400, new Game());
			}
		});
	}

}
