package listeners;
import gameobjects.Ball;
import gameobjects.Block;

/**The HitListener interface.
 *
 * @author Hadas Neuman
 *
 */
public interface HitListener {
    /**Announce the object that a hit happens.
     *
     * @param beingHit the object that been hit
     * @param hitter the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}