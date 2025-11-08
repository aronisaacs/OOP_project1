/**
 * An enum representing the possible marks in a Tic-Tac-Toe game.
 * Includes X, O, and BLANK (no mark).
 * Each mark has a string representation for display purposes.
 *
 * @author aron isaacs
 * @see Board
 * @see Player
 */
public enum Mark {
	/**
	 * The X mark
	 */
	X("X"),
	/**
	 * The O mark
	 */
	O("O"),
	/**
	 * The blank mark (no mark)
	 */
	BLANK(null);

	// String representation of the mark
	private final String string;

	/**
	 * Constructor to initialize the mark with its string representation.
	 *
	 * @param string the string representation of the mark
	 */
	Mark(String string) {
		this.string = string;
	}

	/**
	 * Returns the string representation of the mark.
	 *
	 * @return the string representation of the mark, or null for BLANK
	 */
	public String toString() {
		return string;
	}


}

