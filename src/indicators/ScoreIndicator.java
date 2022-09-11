package indicators;
import biuoop.DrawSurface;
import game.Counter;
import game.GameLevel;
import game.Sprite;

/**Indicates the score on the screen.
 *
 * @author Hadas Neuman
 *
 */
public class ScoreIndicator implements Sprite {

    private Counter scores;

    /**A constructor.
     *
     * @param scores is the score
     */
    public ScoreIndicator(Counter scores) {
        this.scores = scores;
    }

    /**A constructor.
     *
     */
    public ScoreIndicator() {
        this.scores = new Counter(0);
    }

    /** Draws the level on a given draw surface.
     *
     * @param d is the given draw surface that the level should be drawn on.
     */
    public void drawOn(DrawSurface d) {
        int height = 30;
        String score = String.valueOf(this.scores.getValue());
        d.setColor(java.awt.Color.lightGray);
        d.fillRectangle(0, 0, d.getWidth(), height);
        d.setColor(java.awt.Color.black);
        d.drawText((d.getWidth() / 2 - 30), (height - 10), ("Score: " + score), 14);
    }

    /**Time passed - not in use.
     *
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
