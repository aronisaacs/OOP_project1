/**
 * A player that plays the first available move on the board, scanning horizontally from top-left to
 * bottom-right.
 * This is a simple and naive strategy, making it less effective against more strategic opponents,
 * but better than a random player.
 * Implements the Player interface.
 * @see Player
 * @author aron isaacs
 */
public class CleverPlayer implements Player {

    /**
     * Plays a turn by placing the given mark in the first available blank position on the board,
     * scanning from top-left to bottom-right.
     * @param board the game board where the mark will be placed
     * @param mark the mark (X or O) to be placed on the board
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if (board.getMark(row, col) == Mark.BLANK) {
                    board.putMark(mark, row, col);
                    return;
                }
            }
        }
    }
}