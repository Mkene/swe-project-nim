package nimgame.results;

import java.time.Duration;

/**
 * This class sends an example of a player that won the game to our database.
 */
public class GameResultsExampl {

    /**
     * Contains the players information.
     * @param args argument.
     */
    public static void main(String[] args) {
        GameResultDao gameResultDao = GameResultDao.getInstance();
        GameResult gameResult = GameResult.builder()
                .player("person")
                .solved(true)
                .steps(36)
                .duration(Duration.ofMinutes(3))
                .build();
        gameResultDao.persist(gameResult);
        System.out.println(gameResult);
        System.out.println(gameResultDao.findBest(5));
    }

}
