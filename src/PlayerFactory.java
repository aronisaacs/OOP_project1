/**
 * PlayerFactory class to create Player instances based on the specified type.
 *
 * @author aron isaacs
 * @see Player
 * @see HumanPlayer
 * @see WhateverPlayer
 * @see NaivePlayer
 * @see SmartPlayer
 */
public class PlayerFactory {
	/**
	 * Default constructor.
	 */
	public PlayerFactory() {
	}

	/**
	 * Builds and returns a Player instance based on the specified type.
	 *
	 * @param playerType the type of player to create ("human", "whatever", "naive", or "smart")
	 * @return the created Player instance or null if the type is unrecognized
	 */
	public Player buildPlayer(String playerType) {
		// Create and return the appropriate Player instance based on the playerType
		//note the use of Java 14+ switch expression
		return switch (playerType.toLowerCase()) {
			case "human" -> new HumanPlayer();
			case "whatever" -> new WhateverPlayer();
			case "naive" -> new NaivePlayer();
			case "smart" -> new SmartPlayer();
			default -> null;
		};
	}
}