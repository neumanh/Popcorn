package listeners;
import game.Counter;
import game.GameLevel;
import gameobjects.Ball;
import gameobjects.Block;

/**Removes blocks from the game, as well as keeping count of the number of blocks that remain.
 *
 * @author Hadas Neuman
 *
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**A constructor.
     *
     * @param game is the running game
     * @param removedBalls counts the number of Balls in the game
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**A getter.
     *
     *@return the remaining balls number
     */
    public Counter getCounter() {
        return this.remainingBalls;
    }

    /**Remove the ball when it hits the special block.
     *
     * @param beingHit is the block that been hit
     * @param hitter is the hitting ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //the Ball should be removed
        hitter.removeFromGame(game);
        //hitter.removeHitListener(this);
        this.remainingBalls.decrease(1);
        this.game.setBallCounter(this.remainingBalls);
    }

    /**Updates the ball counter.
     *
     * @param counter is the ball counter
     */
    public void setCounter(Counter counter) {
        this.remainingBalls = counter;

    }

    /**Checks if there are no more balls.
     *
     * @return true is there are no more balls, of false otherwise
     */
    public boolean isOver() {
        return (this.remainingBalls.getValue() <= 0);
    }

}


