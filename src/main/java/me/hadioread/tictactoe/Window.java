package me.hadioread.tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import me.hadioread.tictactoe.mechanics.Cell;
import me.hadioread.tictactoe.mechanics.Difficulty;
import me.hadioread.tictactoe.mechanics.Game;
import me.hadioread.tictactoe.mechanics.Player;
import me.hadioread.tictactoe.mechanics.State;
import me.hadioread.tictactoe.menu.ButtonNewGame;
import me.hadioread.tictactoe.menu.ButtonResetPoints;
import me.hadioread.tictactoe.menu.ButtonStartGame;
import me.hadioread.tictactoe.menu.CellListener;
import me.hadioread.tictactoe.menu.MenuLoadImage;
import me.hadioread.tictactoe.menu.MenuOnePlayer;
import me.hadioread.tictactoe.menu.MenuOpen;
import me.hadioread.tictactoe.menu.MenuSave;
import me.hadioread.tictactoe.menu.MenuSelectColor;
import me.hadioread.tictactoe.menu.MenuTwoPlayer;

public class Window {

	private JFrame frame;
	
	private JButton newGame;
	private JButton resetPoints;
	private JButton startGame; // For when the computer goes first

	private JMenuBar menuBar;
	
	// File menu
	private JMenu menuFile;
	private JMenuItem itemExit;
	private JMenuItem itemOpen;
	private JMenuItem itemSave;
	
	// Difficulty menu
	private JMenu menuDifficulty;
	private JCheckBoxMenuItem itemEasy;
	private JCheckBoxMenuItem itemMedium;
	private JCheckBoxMenuItem itemHard;
	private ButtonGroup buttonGroupDifficulty;
	
	// Players menu
	private JMenu menuPlayers;
	private JCheckBoxMenuItem itemOnePlayer;
	private JCheckBoxMenuItem itemTwoPlayer;
	private ButtonGroup buttonGroupPlayer;

	// Points label
	private JLabel labelPointsTitleOne;
	private JLabel labelPointsOne;
	private JLabel labelPointsTitleTwo;
	private JLabel labelPointsTwo;
	
	// First turn menu
	private JMenu menuFirstTurn;
	private JMenuItem itemPlayerOneFirst;
	private JMenuItem itemPlayerTwoFirst;

	// Icon menu
	private JMenu menuIcon;
	private JCheckBoxMenuItem itemPlayerOneCross;
	private JCheckBoxMenuItem itemPlayerOneCircle;
	private ButtonGroup buttonGroupIcon;

	// Background menu
	private JMenu menuBackground;
	private JMenuItem menuImage;
	private JMenu menuColor;
	private ButtonGroup buttonGroupBackground;

	private JLabel backgroundImage;

	private Game game;
	
	public Window(String title, int width, int height, Game game) {
		// JFrame initialization
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		this.game = game;

		// Load GUI
		initializeGUI();
		frame.setVisible(true);
	}
	
	private void initializeGUI() {
		loadButtons();
		
		// Menu bar initialization
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		loadFileMenu();
		loadDifficultyMenu();
		loadPlayerMenu();
		loadFirstTurnMenu();
		loadIconMenu();
		loadBackgroundMenu();
		loadCells();
		loadPointLabels();
		enableDefaultOptions();
	}

	// Button initialization
	private void loadButtons() {
		newGame = new JButton("New Game");
		newGame.setBounds(350, 150, 100, 60);
		newGame.addActionListener(new ButtonNewGame(game, this));
		frame.getContentPane().add(newGame);

		resetPoints = new JButton("Reset Points");
		resetPoints.setBounds(470, 150, 100, 60);
		resetPoints.addActionListener(new ButtonResetPoints(this));
		frame.getContentPane().add(resetPoints);

		startGame = new JButton("Start Game");
		startGame.setBounds(370, 75, 150, 60);
		startGame.setVisible(false);
		startGame.addActionListener(new ButtonStartGame(game, this));
		frame.getContentPane().add(startGame);
	}

	// File menu initialization
	private void loadFileMenu() {
		menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		itemOpen = new JMenuItem("Open");
		itemOpen.addActionListener(new MenuOpen(game, this));
		menuFile.add(itemOpen);

		itemSave = new JMenuItem("Save");
		itemSave.addActionListener(new MenuSave(game, this));
		menuFile.add(itemSave);

		itemExit = new JMenuItem("Exit");
		itemExit.addActionListener(e -> System.exit(0));
		menuFile.add(itemExit);
	}

	// Difficulty menu initialization
	private void loadDifficultyMenu() {
		menuDifficulty = new JMenu("Difficulty");
		menuBar.add(menuDifficulty);
		buttonGroupDifficulty = new ButtonGroup();
		
		itemEasy = new JCheckBoxMenuItem("Easy");
		itemEasy.addActionListener(e -> game.setDifficulty(Difficulty.valueOf(itemEasy.getText().toUpperCase())));
		buttonGroupDifficulty.add(itemEasy);
		menuDifficulty.add(itemEasy);

		itemMedium = new JCheckBoxMenuItem("Medium");
		itemMedium.addActionListener(e -> game.setDifficulty(Difficulty.valueOf(itemMedium.getText().toUpperCase())));
		buttonGroupDifficulty.add(itemMedium);
		menuDifficulty.add(itemMedium);

		itemHard = new JCheckBoxMenuItem("Hard");
		itemHard.addActionListener(e -> game.setDifficulty(Difficulty.valueOf(itemHard.getText().toUpperCase())));
		buttonGroupDifficulty.add(itemHard);
		menuDifficulty.add(itemHard);
	}

	// First turn menu initialization
	private void loadFirstTurnMenu() {
		menuFirstTurn = new JMenu("First Turn");
		menuBar.add(menuFirstTurn);
		ButtonGroup buttonGroupFirstTurn = new ButtonGroup();

		itemPlayerOneFirst = new JCheckBoxMenuItem("User");
		itemPlayerOneFirst.addActionListener(i -> {
			startGame.setVisible(false);
			game.setCellsEnabled(true);
			game.setFirstTurn(Player.ONE);
		});
        itemPlayerTwoFirst = new JCheckBoxMenuItem("Computer");
		itemPlayerTwoFirst.addActionListener(i -> {
			startGame.setVisible(true);
			game.setCellsEnabled(false);
			game.setFirstTurn(Player.TWO);
		});

		buttonGroupFirstTurn.add(itemPlayerOneFirst);
        buttonGroupFirstTurn.add(itemPlayerTwoFirst);
		menuFirstTurn.add(itemPlayerOneFirst);
        menuFirstTurn.add(itemPlayerTwoFirst);
	}

	// Players menu initialization
	private void loadPlayerMenu() {
		menuPlayers = new JMenu("Players");
		menuBar.add(menuPlayers);
		buttonGroupPlayer = new ButtonGroup();
		
		itemOnePlayer = new JCheckBoxMenuItem("One Player");
		itemOnePlayer.addActionListener(new MenuOnePlayer(game, this));
		buttonGroupPlayer.add(itemOnePlayer);
		menuPlayers.add(itemOnePlayer);

		itemTwoPlayer = new JCheckBoxMenuItem("Two Player");
		itemTwoPlayer.addActionListener(new MenuTwoPlayer(game, this));
		buttonGroupPlayer.add(itemTwoPlayer);
		menuPlayers.add(itemTwoPlayer);
	}

	private void loadIconMenu() {
		menuIcon = new JMenu("Icon");
		menuBar.add(menuIcon);
		buttonGroupIcon = new ButtonGroup();

		itemPlayerOneCross = new JCheckBoxMenuItem("User = Cross, Computer = Circle");
		itemPlayerOneCross.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setPlayerOne(State.CROSS);
				game.setPlayerTwo(State.CIRCLE);
			}
		});
		buttonGroupIcon.add(itemPlayerOneCross);
		menuIcon.add(itemPlayerOneCross);

		itemPlayerOneCircle = new JCheckBoxMenuItem("User = Circle, Computer = Cross");
		itemPlayerOneCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setPlayerOne(State.CIRCLE);
				game.setPlayerTwo(State.CROSS);
			}
		});
		buttonGroupIcon.add(itemPlayerOneCircle);
		menuIcon.add(itemPlayerOneCircle);
	}
	
	// Background menu initialization
	private void loadBackgroundMenu() {
		menuBackground = new JMenu("Background");
		menuBar.add(menuBackground);

		menuImage = new JMenuItem("Load Image");
		menuImage.addActionListener(new MenuLoadImage(this));
		menuBackground.add(menuImage);

		menuColor = new JMenu("Select Color");
		menuBackground.add(menuColor);
		buttonGroupBackground = new ButtonGroup();

		MenuSelectColor mbc = new MenuSelectColor(this);
		for (String color : new String[] {"Default", "Gray", "Red", "Pink", "Orange", "Yellow", "Green", "Blue"}) { // Create a menu item for each color
			JRadioButtonMenuItem i = new JRadioButtonMenuItem(color);
			i.addActionListener(mbc); // Apply listener to color menu item
			buttonGroupBackground.add(i);
			menuColor.add(i);

			if (color.equals("Default")) // Enable the default option
				i.setSelected(true);
		}
	}

	// Place game buttons in a 3x3 pattern
	private void loadCells() {
		int x = 60;
		int y = 60;

		String magicSquare = "816357492";
		for (int i = 0; i < 9; i++) { 
			Cell button = new Cell(new JButton(), Short.parseShort(magicSquare.charAt(0) + "")); // Give each button a unique magic square number
			button.getJButton().setBounds(x, y, 70, 70);
			button.getJButton().addActionListener(new CellListener(game, this, button));
			game.getCells().add(button);
			frame.getContentPane().add(button.getJButton());
			magicSquare = magicSquare.substring(1);

			x += 80;
			if (i == 2 || i == 5 || i == 8) {
				y += 80;
				x -= 80 * 3;
			}
		}
	}

	// Point system initialization
	private void loadPointLabels() {
		labelPointsTitleOne = new JLabel("User points:");
		labelPointsTitleOne.setBounds(407, 200, 100, 100);
		frame.add(labelPointsTitleOne);

		labelPointsOne = new JLabel("0");
		labelPointsOne.setBounds(500, 200, 100, 100);
		frame.add(labelPointsOne);

		labelPointsTitleTwo = new JLabel("Computer points:");
		labelPointsTitleTwo.setBounds(375, 250, 150, 100);
		frame.add(labelPointsTitleTwo);

		labelPointsTwo = new JLabel("0");
		labelPointsTwo.setBounds(500, 250, 100, 100);
		frame.add(labelPointsTwo);
	}

	// Default settings
	private void enableDefaultOptions() {
		itemEasy.setSelected(true);
		game.setDifficulty(Difficulty.EASY);
		itemOnePlayer.setSelected(true);
		game.setSinglePlayer(true);
		itemPlayerOneFirst.setSelected(true);
		game.setFirstTurn(Player.ONE);
		itemSave.setEnabled(false); // Cannot save on an empty game
		game.setPlayerOneTurn(true);

		itemPlayerOneCross.setSelected(true);
		game.setPlayerOne(State.CROSS);
		game.setPlayerTwo(State.CIRCLE);
	}

	// Getter methods
	public JFrame getFrame() {
		return frame;
	}

	public JButton getButtonStartGame() {
		return startGame;
	}

	public JMenuItem getItemSave() {
		return itemSave;
	}

	public JMenu getMenuDifficulty() {
		return menuDifficulty;
	}

	public JMenu getMenuPlayers() {
		return menuPlayers;
	}

	public JMenu getMenuFirstTurn() {
		return menuFirstTurn;
	}

	public JCheckBoxMenuItem getItemPlayerOneCross() {
		return itemPlayerOneCross;
	}

	public JCheckBoxMenuItem getItemPlayerOneCircle() {
		return itemPlayerOneCircle;
	}

	public JLabel getLabelPointsTitleOne() {
		return labelPointsTitleOne;
	}

	public JLabel getLabelPointsOne() {
		return labelPointsOne;
	}

	public JLabel getLabelPointsTitleTwo() {
		return labelPointsTitleTwo;
	}
	
	public JLabel getLabelPointsTwo() {
		return labelPointsTwo;
	}

	public JLabel getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(JLabel backgroundImage) { 
		this.backgroundImage = backgroundImage;
	}

}
