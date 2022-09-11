package levels;
import java.util.List;

import game.Sprite;
import game.Velocity;
import gameobjects.Block;

/**
 * LevelInformation interface.
 *
 * @author Hadas Neuman
 */
public interface LevelInformation {
    /**Gets the number of balls.
     *
     * @return the number of balls
     */

    int numberOfBalls();
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    /**Get the desired initial velocity of each ball.
     *
     * @return the initial velocity list
     */
    List<Velocity> initialBallVelocities();

    /**Gets the desired paddle speed.
     *
     * @return the desired paddle speed
     */
    int paddleSpeed();

    /**Gets the desired paddle width.
     *
     * @return the desired paddle width
     */
    int paddleWidth();

    /**Gets the level name that should be displayed at the top of the screen.
     *
     * @return the desired level name
     */
    String levelName();

    /**Gets the background of the level.
     *
     * @return the sprite with the background of the level
     */
    Sprite getBackground();

    /**Gets the Blocks that make up this level.
     *
     * @return blocks list
     */
    List<Block> blocks();

    /**The number of blocks that should be removed.
     *
     * @return number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();
}