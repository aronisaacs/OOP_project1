/**
 * Class representing a game of Tic-Tac-Toe with customizable board size and win conditions.
 * It manages the players, the game board, and checks for winning conditions.
 * The game alternates turns between two players until a player wins or the board is full (a tie).
 * @author Aron Isaacs
 * @see Player
 * @see Board
 * @see Renderer
 */
class Game {
    private final Player[] players;
    private final int winStreak;
    private final Renderer renderer;
    private final Board board;
    private final int size;

    /**
     * Default constructor initializing a standard 4x4 board with a win streak of 3.
     * @param playerX the player using the X mark
     * @param playerO the player using the O mark
     * @param renderer the renderer to display the game board
     */
    public Game(Player playerX, Player playerO, Renderer renderer) {
        this(playerX, playerO, 4, 3, renderer);
    }

    /**
     * Constructor to initialize a game with specified board size and win streak.
     * @param playerX  the player using the X mark
     * @param playerO the player using the O mark
     * @param size the size of the board (number of rows and columns)
     * @param winStreak the number of consecutive marks needed to win
     * @param renderer the renderer to display the game board
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
     * @return the win streak length
     */
    public int getWinStreak() {
        return this.winStreak;
    }

    /**
     * Returns the size of the game board.
     * @return the board size
     */
    public int getBoardSize() {
        return size;
    }

    /**
     * Runs the game until a player wins or the board is full (tie).
     * Alternates turns between the two players and checks for a winning streak after each turn.
     * Renders the board state after each turn.
     * @see Player#playTurn(Board, Mark)
     * @see Renderer#renderBoard(Board)
     * @return the mark of the winning player (X or O), or BLANK in case of a tie
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
     * @see Mark
     * @param markToCheck the mark to check for a winning streak (X or O)
     * @return true if the mark has a winning streak, false otherwise
     */
    private boolean hasStreak(Mark markToCheck) {
        // Dynamic programming tables to track streaks in four directions
        int[][] H = new int[size][size]; // horizontal
        int[][] V = new int[size][size]; // vertical
        int[][] D = new int[size][size]; // main diagonal "\"
        int[][] A = new int[size][size]; // anti-diagonal "/"

        // Iterate through each cell in the board
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Mark mark = board.getMark(i, j);
                // If the cell contains the mark we're checking
                if (mark == markToCheck) {
                    // Update streak counts based on previous cells
                    H[i][j] = (j > 0 ? H[i][j - 1] : 0) + 1;
                    V[i][j] = (i > 0 ? V[i - 1][j] : 0) + 1;
                    D[i][j] = (i > 0 && j > 0 ? D[i - 1][j - 1] : 0) + 1;
                    A[i][j] = (i > 0 && j + 1 < size ? A[i - 1][j + 1] : 0) + 1;

                    // Check if any streak meets or exceeds the win streak length
                    if (H[i][j] >= winStreak || V[i][j] >= winStreak ||
                            D[i][j] >= winStreak || A[i][j] >= winStreak) {
                        return true;
                    }
                    // If the cell does not contain the mark, reset streak counts
                } else {
                    H[i][j] = V[i][j] = D[i][j] = A[i][j] = 0;
                }
            }
        }
        // No winning streak found
        return false;
    }
}