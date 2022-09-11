package listeners;
import game.Counter;
import game.GameLevel;
import gameobjects.Ball;
import gameobjects.Block;

/** A hit listener that tracks the score.
 *
 * @author Hadas Neuman
 *
 */
public class ScoreTrackingListener implements HitListener {

    private GameLevel game;
    private Counter currentScore;

    /** A constructor.
     *
     * @param game is the game the score listener listen to
     * @param scoreCounter is the initial score
     */
    public ScoreTrackingListener(GameLevel game, Counter scoreCounter) {
        this.game = game;
        this.currentScore = scoreCounter;
    }

    /**Updates the score when a hit happens.
     *
     * @param beingHit is the block that been hit
     * @param hitter is the hitting ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        int score;
        if (beingHit.getHitPoints() <= 1) { //the block should be removed
            score = 10;
        } else {
            score = 5;
        }
        this.currentScore.increase(score);
        this.game.setScore(this.currentScore);
    }

}
