/**
 * Renderer interface for rendering the game board.
 * Defines a method for rendering the board state.
 *
 * @author aron isaacs
 * @see Board
 */
public interface Renderer {
	/**
	 * Renders the given game board.
	 *
	 * @param board the game board to be rendered
	 */
	void renderBoard(Board board);
}