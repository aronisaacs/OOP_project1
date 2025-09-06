/**
 * Player interface for a Tic-Tac-Toe game.
 * Defines a method for playing a turn by placing a mark on the board.
 * @see Board
 * @see Mark
 * @author aron isaacs
 */
interface Player{
    /**
     * Plays a turn by placing the given mark on the board.
     * @param board the game board where the mark will be placed
     * @param mark the mark (X or O) to be placed on the board
     */
    void playTurn(Board board, Mark mark);
}