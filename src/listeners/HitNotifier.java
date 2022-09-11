package listeners;
/**The HitNotifier interface.
 *
 * @author Hadas Neuman
 *
 */
public interface HitNotifier {

    /**Adds a hit listener to hit events.
     *
     * @param hl is the hit listener to add
     */
    void addHitListener(HitListener hl);

    /**Remove hit listener from the list of listeners to hit events.
     *
     * @param hl is the hit listener to remove
     */
    void removeHitListener(HitListener hl);
}
