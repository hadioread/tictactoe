package me.hadioread.tictactoe.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;

import me.hadioread.tictactoe.Window;
import me.hadioread.tictactoe.mechanics.Game;
import me.hadioread.tictactoe.mechanics.Player;

public class MenuTwoPlayer implements ActionListener {
    
    private Game game;
    private Window window;

    public MenuTwoPlayer(Game g, Window w) {
        game = g;
        window = w;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.setSinglePlayer(false);
		window.getMenuDifficulty().setEnabled(false); // Difficulty cannot be changed in two player mode

        game.setCellsEnabled(true); // Board is possible disabled from one player vs ai
        window.getButtonStartGame().setVisible(false); // Disable buton that was used for ai having first turn

        // Change first turn menu to cross vs circle
        window.getMenuFirstTurn().removeAll();
        
	    JCheckBoxMenuItem itemPlayerOneFirst = new JCheckBoxMenuItem("Player one");
        itemPlayerOneFirst.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.setFirstTurn(Player.ONE);
                game.setPlayerOneTurn(true);
            }
        });
	    JCheckBoxMenuItem itemPlayerTwoFirst = new JCheckBoxMenuItem("Player two");
        itemPlayerTwoFirst.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.setFirstTurn(Player.TWO);
                game.setPlayerOneTurn(false);
            }
        });

	    ButtonGroup buttonGroupFirstTurn = new ButtonGroup();
        buttonGroupFirstTurn.add(itemPlayerOneFirst);
        buttonGroupFirstTurn.add(itemPlayerTwoFirst);
        window.getMenuFirstTurn().add(itemPlayerOneFirst);
        window.getMenuFirstTurn().add(itemPlayerTwoFirst);

        // Change icon menu to player one vs player two
        window.getItemPlayerOneCross().setText("Player one = Cross, Player two = Circle");
        window.getItemPlayerOneCircle().setText("Player one = Circle, Player two = Cross");

        // Change point counters to cross vs circle
        window.getLabelPointsTitleOne().setText("Player one:");
        window.getLabelPointsTitleOne().setBounds(410, 200, 100, 100);
        window.getLabelPointsOne().setText("0");

        window.getLabelPointsTitleTwo().setText("Player two:");
        window.getLabelPointsTitleTwo().setBounds(410, 250, 150, 100);
        window.getLabelPointsTwo().setText("0");
	
        itemPlayerOneFirst.setSelected(true); // Default setting
    }
    
}
