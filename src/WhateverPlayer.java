import java.util.Random;

/**
 * A player that chooses its moves randomly from the available empty squares on the board.
 * Implements the Player interface.
 *
 * @author aron isaacs
 * @see Player
 */
public class WhateverPlayer implements Player {

	// Random number generator for selecting random moves
	private final Random rand = new Random();

	/**
	 * Plays a turn by randomly selecting an empty square on the board and placing the given mark there.
	 *
	 * @param board the game board where the mark will be placed
	 * @param mark  the mark (X or O) to be placed on the board
	 */
	@Override
	public void playTurn(Board board, Mark mark) {
		int size = board.getSize();
		// Collect all blank squares and store their indices
		int[] emptySquares = new int[size * size];
		int index = 0;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (board.getMark(row, col) == Mark.BLANK) {
					emptySquares[index] = row * size + col;
					index++;
				}
			}
		}
		// Select a random index from the collected empty squares
		int randomIndex = rand.nextInt(index); //check this is what I want
		int boardIndex = emptySquares[randomIndex];
		board.putMark(mark, boardIndex / size, boardIndex % size);
	}
}