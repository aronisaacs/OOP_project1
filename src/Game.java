/**
 * Class representing a game of Tic-Tac-Toe with customizable board size and win conditions.
 * It manages the players, the game board, and checks for winning conditions.
 * The game alternates turns between two players until a player wins or the board is full (a tie).
 *
 * @author Aron Isaacs
 * @see Player
 * @see Board
 * @see Renderer
 */
public class Game {
	private final Player[] players;
	private final int winStreak;
	private final Renderer renderer;
	private final Board board;
	private final int size;

	/**
	 * Default constructor initializing a standard 4x4 board with a win streak of 3.
	 *
	 * @param playerX  the player using the X mark
	 * @param playerO  the player using the O mark
	 * @param renderer the renderer to display the game board
	 */
	public Game(Player playerX, Player playerO, Renderer renderer) {
		this(playerX, playerO, 4, 3, renderer);
	}

	/**
	 * Constructor to initialize a game with specified board size and win streak.
	 *
	 * @param playerX   the player using the X mark
	 * @param playerO   the player using the O mark
	 * @param size      the size of the board (number of rows and columns)
	 * @param winStreak the number of consecutive marks needed to win
	 * @param renderer  the renderer to display the game board
	 */
	public Game(Player playerX, Player playerO, int size, int winStreak, Renderer renderer) {
		this.players = new Player[]{playerX, playerO};
		this.renderer = renderer;
		this.winStreak = winStreak;
		this.size = size;
		this.board = new Board(size);
	}

	/**
	 * Returns the number of consecutive marks needed to win.
	 *
	 * @return the win streak length
	 */
	public int getWinStreak() {
		return this.winStreak;
	}

	/**
	 * Returns the size of the game board.
	 *
	 * @return the board size
	 */
	public int getBoardSize() {
		return size;
	}

	/**
	 * Runs the game until a player wins or the board is full (tie).
	 * Alternates turns between the two players and checks for a winning streak after each turn.
	 * Renders the board state after each turn.
	 *
	 * @return the mark of the winning player (X or O), or BLANK in case of a tie
	 * @see Player#playTurn(Board, Mark)
	 * @see Renderer#renderBoard(Board)
	 */
	public Mark run() {
		Mark[] marks = Mark.values();
		for (int i = 0; i < size * size; i++) {
			// Current player plays their turn
			players[i % 2].playTurn(board, marks[i % 2]);
			// Render the current state of the board
			renderer.renderBoard(board);
			// Check for a winning streak after each turn
			if (hasStreak(marks[i % 2])) {
				return marks[i % 2];
			}
		}
		// If the board is full and no player has won, it's a tie, and we return BLANK
		return Mark.BLANK;
	}

	/**
	 * Checks if the specified mark has a winning streak on the board.
	 * Uses dynamic programming to track horizontal, vertical, and diagonal streaks.
	 *
	 * @param markToCheck the mark to check for a winning streak (X or O)
	 * @return true if the mark has a winning streak, false otherwise
	 * @see Mark
	 */
	private boolean hasStreak(Mark markToCheck) {
		// Dynamic programming tables to track streaks in four directions
		int[][] horizontal = new int[size][size]; // horizontal
		int[][] vertical = new int[size][size]; // vertical
		int[][] upLeftDiagonal = new int[size][size]; // main diagonal "\"
		int[][] downLeftDiagonal = new int[size][size]; // anti-diagonal "/"

		// Iterate through each cell in the board
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				Mark mark = board.getMark(row, column);
				// If the cell contains the mark we're checking
				if (mark == markToCheck) {
					// Update streak counts based on previous cells
					horizontal[row][column] = (column > 0 ? horizontal[row][column - 1] : 0) + 1;
					vertical[row][column] = (row > 0 ? vertical[row - 1][column] : 0) + 1;
					upLeftDiagonal[row][column] =
							(row > 0 && column > 0 ? upLeftDiagonal[row - 1][column - 1] : 0) + 1;
					downLeftDiagonal[row][column] =
							(row > 0 && column + 1 < size ? downLeftDiagonal[row - 1][column + 1] : 0) + 1;

					// Check if any streak meets or exceeds the win streak length
					if (horizontal[row][column] >= winStreak || vertical[row][column] >= winStreak ||
							upLeftDiagonal[row][column] >= winStreak
							|| downLeftDiagonal[row][column] >= winStreak) {
						return true;
					}
					// If the cell does not contain the mark, reset streak counts
				} else {
					horizontal[row][column] = vertical[row][column] =
							upLeftDiagonal[row][column] = downLeftDiagonal[row][column] = 0;
				}
			}
		}
		// No winning streak found
		return false;
	}
}