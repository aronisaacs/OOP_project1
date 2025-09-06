/**
 * A player that blocks horizontal threats and otherwise picks the last available square.
 * Implements the Player interface.
 * @see Player
 * @author aron isaacs
 */
public class GeniusPlayer implements Player {

    /**
     * Plays a turn by first checking for horizontal threats from the opponent and blocking them.
     * If no threats are found, it places the mark in the last available blank position on the board,
     * scanning from bottom-right to top-left.
     * @param board the game board where the mark will be placed
     * @param mark the mark (X or O) to be placed on the board
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        int size = board.getSize();
        Mark opponentMark = (mark == Mark.X) ? Mark.O : Mark.X;

        // Check for horizontal threats and block
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board.getMark(row, col) == Mark.BLANK) {
                    // Check the two squares to the left
                    if (col >= 2 && board.getMark(row, col - 1) == opponentMark && board.getMark(row, col - 2) == opponentMark) {
                        board.putMark(mark, row, col);
                        return;
                    }

                }
            }
        }

        // Fallback: Pick the last available square
        for (int row = size - 1; row >= 0; row--) {
            for (int col = size - 1; col >= 0; col--) {
                if (board.getMark(row, col) == Mark.BLANK) {
                    board.putMark(mark, row, col);
                    return;
                }
            }
        }
    }
}