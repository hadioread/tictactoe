package me.hadioread.tictactoe.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;

import me.hadioread.tictactoe.Window;
import me.hadioread.tictactoe.mechanics.Game;

public class MenuOnePlayer implements ActionListener {
    
    private Game game;
    private Window window;

    public MenuOnePlayer(Game g, Window w) {
        game = g;
        window = w;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
		game.setSinglePlayer(true);
        window.getMenuDifficulty().setEnabled(true); // Difficulty can be changed in player vs ai

        // Change first turn menu to user vs computer
        window.getMenuFirstTurn().removeAll();
        JCheckBoxMenuItem itemUserFirst = new JCheckBoxMenuItem("User");
        itemUserFirst.addActionListener(i -> window.getButtonStartGame().setVisible(false));
        JCheckBoxMenuItem itemComputerFirst = new JCheckBoxMenuItem("Computer");
        itemComputerFirst.addActionListener(i -> {
            window.getButtonStartGame().setEnabled(true);
            window.getButtonStartGame().setVisible(true); // Enable start button when the computer is going first
            game.setCellsEnabled(false); // Block the player from going first
        });
        ButtonGroup buttonGroupFirstTurn = new ButtonGroup();
        buttonGroupFirstTurn.add(itemUserFirst);
        buttonGroupFirstTurn.add(itemComputerFirst);
        window.getMenuFirstTurn().add(itemUserFirst);
        window.getMenuFirstTurn().add(itemComputerFirst);

        // Change icon menu to user vs computer
        window.getItemPlayerOneCross().setText("User = Cross, Computer = Circle");
        window.getItemPlayerOneCircle().setText("User = Circle, Computer = Cross");

        // Reset and change point system
        window.getLabelPointsTitleOne().setText("User points:");
		window.getLabelPointsTitleOne().setBounds(407, 200, 100, 100);
        window.getLabelPointsOne().setText("0");

        window.getLabelPointsTitleTwo().setText("Computer points:");
		window.getLabelPointsTitleTwo().setBounds(375, 250, 150, 100);
        window.getLabelPointsTwo().setText("0");

        itemUserFirst.setSelected(true); // Default setting
    }

}
