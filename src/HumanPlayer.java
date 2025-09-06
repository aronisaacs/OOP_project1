/**
 * HumanPlayer class represents a human player in the game.
 * It implements the Player interface and allows the user to input their move via the console.
 * The player is prompted to enter coordinates in the format "rowcol" (e.g., "12" for row 1, column 2).
 * The input is validated to ensure it is within the board's bounds and the chosen position is not already occupied.
 * If the input is invalid, the player is prompted to enter a valid position.
 * @see Player
 * @see KeyboardInput
 * @author aron isaacs
 */
class HumanPlayer implements Player {

    /**
     * Default constructor.
     */
    HumanPlayer() {}

    /**
     * Plays a turn by prompting the user to input coordinates for their mark.
     * Validates the input to ensure it is within bounds and the position is not already occupied.
     * If the input is invalid, prompts the user to enter a valid position until a valid move is made.
     * @param board the game board where the mark will be placed
     * @param mark the mark (X or O) to be placed on the board
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        System.out.printf("Player %s, type coordinates: \n", mark.toString());
        while (true) {
            int input = KeyboardInput.readInt();

            // Extract row and column from the input
            int row = input / 10;
            int col = input % 10;

            // Validate the input
            if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
                System.out.println("Invalid mark position. Please choose a valid position:");
                continue;
            }

            // Check if the position is already occupied
            if (board.getMark(row, col) != Mark.BLANK) {
                System.out.println("Mark position is already occupied. Please choose a valid position:");
                continue;
            }

            // Place the mark and exit the loop
            board.putMark(mark, row, col);
            break;
        }
    }
}