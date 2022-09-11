package listeners;
import game.Counter;
import game.GameLevel;
import gameobjects.Ball;
import gameobjects.Block;

/**
 * Removes blocks from the game, as well as keeping count of the number of
 * blocks that remain.
 *
 * @author Hadas Neuman
 *
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * A constructor.
     *
     * @param game
     *            is the running game
     * @param removedBlocks
     *            counts the number of blocks in the game
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Remove the block when it is being hit.
     *
     * @param beingHit
     *            is the block that been hit
     * @param hitter
     *            is the hitting ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() <= 1) { // the block should be removed
            beingHit.removeFromGame(game);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
            this.game.setBlockCounter(this.remainingBlocks);
        }
    }

    /**
     * Updates the counter.
     *
     * @param counter
     *            is the blocks counter
     */
    public void setCounter(Counter counter) {
        this.remainingBlocks = counter;

    }

    /**
     * Checks if there are no more blocks.
     *
     * @return true is there are no more blocks, of false otherwise
     */
    public boolean isOver() {
        return (this.remainingBlocks.getValue() <= 0);
    }
}
