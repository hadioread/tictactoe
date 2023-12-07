/**
 * Object for each cell to store information about button properties and state.
 * Date released: 30/10/23
 * @author Lachlan Schultz
 * JDK Version 17
 */

package me.hadioread.tictactoe.mechanics;

import javax.swing.JButton;

public class Cell {
    
    private JButton button;
    private State state;

    private short magicSquareNumber;

    public Cell(JButton button, short magicSquareNumber) {
        this.button = button;
        state = State.NONE;
        this.magicSquareNumber = magicSquareNumber; 
    }
    
    // Getter and setter methods
    public void clear() {
        button.setIcon(null);
        state = State.NONE;
    }

    public JButton getJButton() {
        return button;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        button.setIcon(state.getIcon());
    }

    public short getMagicSquareNumber() {
        return magicSquareNumber;
    }

}
