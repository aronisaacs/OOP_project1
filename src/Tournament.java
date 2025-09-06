/*
make sure to check that the switch cases in the factories is supported in the java version of the course!!
 */



/**
 * Class representing a tournament of multiple Games between two players.
 * It manages the rounds, keeps track of wins, and displays the final results.
 * It alternates the starting player for each game to ensure fairness.
 * The tournament can be configured via command-line arguments.
 * While this project is a tic-tac-toe variant, this class is designed
 * to be flexible enough to accommodate any two-player turn-based game that ends in a win or a tie,
 * with a configurable board size. The flexibility of this class relies on the `Game` and `Player` APIs,
 * which are designed to be generic and extensible, allowing different types of games and players
 * to be implemented without modifying the `Tournament` class.
 *
 * @author Aron Isaacs
 * @see Game
 */
class Tournament {
    // Number of rounds in the tournament
    private final int rounds;
    // Renderer to display the game board
    private final Renderer renderer;
    // Array of two players participating in the tournament
    private final Player[] players;

    /**
     * Main method to start the tournament.
     * Expects command-line arguments to configure the tournament as follows:
     * java Tournament [round count] [board size] [win streak]
     * [render target: console/void]
     * [first player: human/whatever/clever/genius]
     * [second player: human/whatever/clever/genius]
     * Example:
     * java Tournament 100 4 3 console genius clever
     *
     * @param args Command-line arguments:
     *             args[0] - Number of rounds to play (positive integer).
     *             args[1] - Size of the game board (e.g., 3 for 3x3).
     *             args[2] - Number of consecutive marks needed to win.
     *             args[3] - Render target (e.g., "console" or "void").
     *             args[4] - Type of the first player (e.g., "human" or "genius").
     *             args[5] - Type of the second player (e.g., "human" or "clever").
     */
    public static void main(String[] args){
        //parse args
        int rounds = Integer.parseInt(args[0]);
        int size = Integer.parseInt(args[1]);
        int winStreak = Integer.parseInt(args[2]);

        //create factories and build objects
        PlayerFactory playerFactory = new PlayerFactory();
        RendererFactory rendererFactory = new RendererFactory();
        Player player1 = playerFactory.buildPlayer(args[4]);
        Player player2 = playerFactory.buildPlayer(args[5]);
        Renderer renderer = rendererFactory.buildRenderer(args[3], size);
        Tournament tournament = new Tournament(rounds, renderer, player1, player2 );

        //start tournament
        tournament.playTournament(size,winStreak,args[4] , args[5]);
    }

    /**
     * Constructor for the Tournament class.
     * @param rounds number of rounds to be played in the tournament
     * @param renderer renderer to display the game board
     * @param player1 first player
     * @param player2 second player
     */
    public Tournament(int rounds, Renderer renderer, Player player1, Player player2){
        // Initialize the tournament with the given parameters
        this.rounds = rounds;
        this.renderer = renderer;
        this.players = new Player[]{player1, player2};
    }

    /**
     * Plays the tournament with the specified parameters.
     * Alternates the starting player for each game and keeps track of wins and ties.
     * At the end, it displays the results of the tournament.
     * @param size dimensions of the game board
     * @param winStreak number of consecutive marks needed to win
     * @param playerName1 name of the first player
     * @param playerName2 name of the second player
     */
    void playTournament(int size, int winStreak, String playerName1, String playerName2) {
        // Initialize win counters
        int player1Wins = 0;
        int player2Wins = 0;
        int ties = 0;

        // Play the specified number of rounds, alternating starting players
        for (int round = 0; round < rounds; round++) {
            Player currentPlayer1 = players[round % 2];
            Player currentPlayer2 = players[(round + 1) % 2];

            Game game = new Game(currentPlayer1, currentPlayer2, size, winStreak, renderer);
            Mark winner = game.run();

            // Update win counters based on the game result:
            if (winner == Mark.X) {
                if (currentPlayer1 == players[0]) {
                    player1Wins++;
                } else {
                    player2Wins++;
                }
            } else if (winner == Mark.O) {
                if (currentPlayer2 == players[0]) {
                    player1Wins++;
                } else {
                    player2Wins++;
                }
            } else {
                ties++;
            }
        }

        // Display the final results of the tournament, in the format:
        // ######### Results #########
        // Player 1, [playerName1] won: [player1Wins]
        // Player 2, [playerName2] won: [player2Wins]
        // Ties: [ties]
        System.out.println("######### Results #########");
        System.out.printf("Player 1, %s won: %d%n", playerName1, player1Wins);
        System.out.printf("Player 2, %s won: %d%n", playerName2, player2Wins);
        System.out.printf("Ties: %d%n", ties);
    }
}