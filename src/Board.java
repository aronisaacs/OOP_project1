/**
 * Board class represents a square board for a game like Tic-Tac-Toe.
 * It supports placing Marks and retrieving marks at specific positions.
 * The board is initialized with a default size of 4x4, but can be created with a custom size.
 * @author aron isaacs
 * @see Mark
 */
public class Board {
    // Size of the board (number of rows and columns)
    private final int size;
    // 2D array to hold the marks on the board
    private final Mark[][]  boardArray;

    /**
     * Default constructor initializes a 4x4 board.
     */
    public  Board() {
        this(4);
    }

    /**
     * Constructor to initialize a board of given size.
     * @param size the size of the board (number of rows and columns)
     */
    public Board(int size){
        this.size = size;
        // Initialize the board array with BLANK marks
        this.boardArray = new Mark[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.boardArray[i][j] = Mark.BLANK;
            }
        }
    }

    /**
     * Returns the size of the board.
     * @return the size of the board
     */
    public int getSize() {
        return size;
    }

    /**
     * Places a mark at the specified row and column if within bounds.
     * @param mark the mark to place on the board
     * @param row the row index (0-based)
     * @param col the column index (0-based)
     * @return true if the mark was placed successfully, false if out of bounds
     */
    public boolean putMark(Mark mark, int row, int col){
        if (row >= 0 && row < size && col >= 0 && col < size) {
            this.boardArray[row][col] = mark;
            return true;
        }
        return false;
    }

    /**
     * Retrieves the mark at the specified row and column.
     * @param row the row index (0-based)
     * @param col the column index (0-based)
     * @return the mark at the specified position, or BLANK if out of bounds
     */
    public Mark getMark(int row, int col){
        if (row >= 0 && row < size && col >= 0 && col < size) {
            return this.boardArray[row][col];
        }
        return Mark.BLANK;
    }
}