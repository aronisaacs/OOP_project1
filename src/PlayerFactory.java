/**
 * PlayerFactory class to create Player instances based on the specified type.
 * @see Player
 * @see HumanPlayer
 * @see WhateverPlayer
 * @see CleverPlayer
 * @see GeniusPlayer
 * @author aron isaacs
 */
public class PlayerFactory {
    /**
     * Default constructor.
     */
    public PlayerFactory() {
    }

    /**
     * Builds and returns a Player instance based on the specified type.
     * @param playerType the type of player to create ("human", "whatever", "clever", or "genius")
     * @return the created Player instance or null if the type is unrecognized
     */
    Player buildPlayer(String playerType) {
        // Create and return the appropriate Player instance based on the playerType
        //note the use of Java 14+ switch expression
        return switch (playerType.toLowerCase()) {
            case "human" -> new HumanPlayer();
            case "whatever" -> new WhateverPlayer();
            case "clever" -> new CleverPlayer();
            case "genius" -> new GeniusPlayer();
            default -> null;
        };
    }
}