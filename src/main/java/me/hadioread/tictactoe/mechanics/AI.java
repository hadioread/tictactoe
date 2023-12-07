/**
 * Find positions for the computer's turn in tac tac toe, for easy, medium, and hard.
 * Date released: 20/11/23
 * @author Lachlan Schultz
 * JDK Version 17
 */

package me.hadioread.tictactoe.mechanics;

import java.util.Random;

public class AI {

    private Game game;
    private Random random;

    public AI(Game game) {
        this.game = game;
        random = new Random();
    }

    public short findPosition(State ai) {
        if (game.getDifficulty().equals(Difficulty.EASY)) { // Easy difficulty
            return random();
        } else if (game.getDifficulty().equals(Difficulty.MEDIUM)) { // Medium difficulty
            short i = prime(ai);
            if (i > -1) {
                return i; // Prime position found
            }
            
            return random(); // If prime position not found, revert to easy
        } else { // Hard difficulty

            short i = block(ai);

            if (i > -1) {
                return i; // Block found
            }

            i = row(ai);
            if (i > -1) {
                return i; // Winning position found
            } 
            
            i = prime(ai);
            if (i > -1) {
                return i; // Prime position found
            }

            return random(); // If prime position cannot be found, revert to random
        }
    }

    // Random position on the board, easy difficulty
    private short random() {
        short slot = 0;
            do {
                slot = (short) random.nextInt(9); // Find random spot on board
            } while (!game.getCells().get(slot).getState().equals(State.NONE)); // Keep generating new number until slot isn't taken

        return slot;
    }

    // Any prime position, being all corners and centre, medium difficulty
    private short prime(State ai) {
        State[] cells = new State[9];
        for (int i = 0; i < game.getCells().size(); i++) {
            cells[i] = game.getCells().get(i).getState();
        }

        if(cells[0].equals(State.NONE)) { // Top left
            return 0;
		} else if (cells[2].equals(State.NONE)) { // Top right
            return 2;
        } else if (cells[6].equals(State.NONE)) { // Bottom left
            return 6;
        } else if (cells[8].equals(State.NONE)) { //  Bottom right
            return 8;
        } else if (cells[4].equals(State.NONE)) { // Middle
            return 4;
        }
		
        return -1; // No prime position was found
	}

    // Block user when they could win, hard difficulty
    private short block(State ai) {
        State[] cells = new State[9];
        State opp = game.getPlayerOne(); // Opposite team to AI
        for (int i = 0; i < game.getCells().size(); i++) {
            cells[i] = game.getCells().get(i).getState();
        }

        if (cells[0].equals(opp) && cells[1].equals(opp) && cells[2].equals(State.NONE)) {
            return 2;
		} else if (cells[0].equals(opp) && cells[1].equals(State.NONE) && cells[2].equals(opp)) {
			return 1;
		} else if (cells[0].equals(State.NONE) && cells[1].equals(opp) && cells[2].equals(opp)) {
			return 0;
		} else if (cells[3].equals(opp) && cells[4].equals(opp) && cells[5].equals(State.NONE)) {
            return 5;
		} else if (cells[3].equals(opp) && cells[4].equals(State.NONE) && cells[5].equals(opp)) {
            return 4;
		} else if (cells[3].equals(State.NONE) && cells[4].equals(opp) && cells[5].equals(opp)) {
            return 3;
		} else if (cells[6].equals(opp) && cells[7].equals(opp) && cells[8].equals(State.NONE)) {
            return 8;
		} else if (cells[5].equals(opp) && cells[7].equals(State.NONE) && cells[8].equals(opp)) {
            return 7;
		} else if (cells[6].equals(State.NONE) && cells[7].equals(opp) && cells[8].equals(opp)) {
			return 6;
		} else if (cells[2].equals(opp) && cells[4].equals(opp) && cells[6].equals(State.NONE)) {
			return 6;
		} else if (cells[2].equals(opp) && cells[4].equals(State.NONE) && cells[6].equals(opp)) {
			return 4;
		} else if (cells[2].equals(State.NONE) && cells[4].equals(opp) && cells[6].equals(opp)) {
			return 2;
		} else if (cells[0].equals(opp) && cells[4].equals(opp) && cells[8].equals(State.NONE)) {
			return 8;
		} else if (cells[0].equals(opp) && cells[4].equals(State.NONE) && cells[8].equals(opp)) {
			return 4;
		} else if (cells[0].equals(State.NONE) && cells[4].equals(opp) && cells[8].equals(opp)) {
			return 0;
		} else if (cells[0].equals(opp) && cells[3].equals(opp) && cells[6].equals(State.NONE)) {
			return 6;
		} else if (cells[0].equals(opp) && cells[3].equals(State.NONE) && cells[6].equals(opp)) {
			return 3;
		} else if (cells[0].equals(State.NONE) && cells[3].equals(opp) && cells[6].equals(opp)) {
			return 0;
		} else if (cells[1].equals(opp) && cells[4].equals(opp) && cells[7].equals(State.NONE)) {
			return 7;
		} else if (cells[1].equals(opp) && cells[4].equals(State.NONE) && cells[7].equals(opp)) {
			return 4;
		} else if (cells[1].equals(State.NONE) && cells[4].equals(opp) && cells[7].equals(opp)) {
			return 1;
		} else if (cells[2].equals(opp) && cells[5].equals(opp) && cells[8].equals(State.NONE)) {
			return 8;
		} else if (cells[2].equals(opp) && cells[5].equals(State.NONE) && cells[8].equals(opp)) {
			return 5;
		} else if (cells[2].equals(State.NONE) && cells[5].equals(State.NONE) && cells[8].equals(State.NONE)) {
			return 8;
		}

        return -1; // No blocking position was found
    }

    // Winning position on the board, hard difficulty
    private short row(State ai) {
        State[] cells = new State[9];
        for (int i = 0; i < game.getCells().size(); i++) {
            cells[i] = game.getCells().get(i).getState();
        }

        // Check X
        if (cells[0].equals(ai) && cells[1].equals(ai) && cells[2].equals(State.NONE)) { // Row 1
            return 2;
        } else if (cells[1].equals(ai) && cells[2].equals(ai) && cells[0].equals(State.NONE) ) {
            return 0;
        } else if (cells[0].equals(ai) && cells[2].equals(ai) && cells[1].equals(State.NONE)) {
            return 1;
        } else if (cells[3].equals(ai) && cells[4].equals(ai) && cells[5].equals(State.NONE)) { // Row 2
            return 5;
        } else if (cells[4].equals(ai) && cells[5].equals(ai) && cells[3].equals(State.NONE)) {
            return 3;
        } else if (cells[3].equals(ai) && cells[5].equals(ai) && cells[4].equals(State.NONE)) {
            return 4;
        } else if (cells[6].equals(ai) && cells[7].equals(ai) && cells[8].equals(State.NONE)) { // Row 3
            return 8;
        } else if (cells[7].equals(ai) && cells[8].equals(ai) && cells[6].equals(State.NONE)) {
            return 6;
        } else if (cells[6].equals(ai) && cells[8].equals(ai) && cells[7].equals(State.NONE)) {
            return 7;
        }

        // Check Y
        if (cells[0].equals(ai) && cells[3].equals(ai) && cells[6].equals(State.NONE)) { // Column 1
            return 6;
        } else if (cells[3].equals(ai) && cells[6].equals(ai) && cells[0].equals(State.NONE)) {
            return 0;
        } else if (cells[0].equals(ai) && cells[6].equals(ai) && cells[3].equals(State.NONE)) {
            return 3;
        } else if (cells[1].equals(ai) && cells[4].equals(ai) && cells[7].equals(State.NONE)) { // Column 2
            return 7;
        } else if (cells[4].equals(ai) && cells[7].equals(ai) && cells[1].equals(State.NONE)) {
            return 1;
        } else if (cells[1].equals(ai) && cells[7].equals(ai) && cells[4].equals(State.NONE)) {
            return 4;
        } else if (cells[2].equals(ai) && cells[5].equals(ai) && cells[8].equals(State.NONE)) { // Column 3
            return 8;
        } else if (cells[5].equals(ai) && cells[8].equals(ai) && cells[2].equals(State.NONE)) {
            return 2;
        } else if (cells[2].equals(ai) && cells[8].equals(ai) && cells[5].equals(State.NONE)) {
            return 5;
        }

        // Check diag
        if (cells[0].equals(ai) && cells[4].equals(ai) && cells[8].equals(State.NONE)) { // Top left -> bottom right
            return 8;
        } else if (cells[4].equals(ai) && cells[8].equals(ai) && cells[0].equals(State.NONE)) {
            return 0;
        } else if (cells[0].equals(ai) && cells[8].equals(ai) && cells[4].equals(State.NONE)) {
            return 4;
        } else if (cells[2].equals(ai) && cells[4].equals(ai) && cells[6].equals(State.NONE)) { // Top right -> bottom left
            return 6;
        } else if (cells[4].equals(ai) && cells[6].equals(ai) && cells[2].equals(State.NONE)) {
            return 2;
        } else if (cells[2].equals(ai) && cells[6].equals(ai) && cells[4].equals(State.NONE)) {
            return 4;
        }

        return -1; // No winning position was found
    }

}
