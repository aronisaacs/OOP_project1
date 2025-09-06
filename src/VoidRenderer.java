/**
 * A renderer that does nothing. Useful for testing or headless operation.
 * implements the Renderer interface but provides an empty implementation of renderBoard.
 * @see Renderer
 * @author aron isaacs
 */
class VoidRenderer implements Renderer {

    /**
     * Renders the board state. This implementation does nothing.
     * @param board the game board to be rendered
     */
    @Override
    public void renderBoard(Board board){}
}