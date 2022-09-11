package indicators;
import biuoop.DrawSurface;
import game.Counter;
import game.GameLevel;
import game.Sprite;

/**Indicates the number of "lives" on the screen.
 *
 * @author Hadas Neuman
 *
 */
public class LivesIndicator implements Sprite {

    private Counter lives;

    /**A constructor.
     *
     * @param lives is the number of "lives" the user starts with
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }

    /**A constructor.
     *
     */
    public LivesIndicator() {
        this.lives = new Counter(0);
    }

    /** Draws the level on a given draw surface.
     *
     * @param d is the given draw surface that the level should be drawn on.
     */
    public void drawOn(DrawSurface d) {
        int height = 30;
        String score = String.valueOf(this.lives.getValue());
        d.setColor(java.awt.Color.black);
        d.drawText(20, (height - 10), ("Lives: " + score), 14);
    }

    /**
     * Time passed - not in use.
     * @param dt is the difference in time
     */
    public void timePassed(double dt) {
    }

    /**
     * Adds the ScoreIndicator to a game.
     *
     * @param g is a game the ScoreIndicator should be added to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
