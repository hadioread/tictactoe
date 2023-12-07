/**
 * Enum to represent the state and its ImageIcon of each cell in the game.
 * Date released: 8/11/23
 * @author Lachlan Schultz
 * JDK Version 17
 */

package me.hadioread.tictactoe.mechanics;

import javax.swing.ImageIcon;

import me.hadioread.tictactoe.Window;

public enum State {
    
    NONE('N', null),
    CROSS('X', new ImageIcon(new ImageIcon(Window.class.getResource("/images/cross.png")).getImage().getScaledInstance(40, 40, 0))),
    CIRCLE('O', new ImageIcon(new ImageIcon(Window.class.getResource("/images/circle.png")).getImage().getScaledInstance(40, 40, 0)));

    // Convert character to enum
    public static State from(char character) {
        switch (character) {
            case 'X':
                return CROSS;
            case 'O':
                return CIRCLE;
            default:
                return NONE;
        }
    }

    private char character;
    private ImageIcon icon;

    private State(char character, ImageIcon icon) {
        this.character = character;
        this.icon = icon;
    }

    // Getter methods
    public char getCharacter() {
        return character;
    }

    public ImageIcon getIcon() {
        return icon;
    }

}
