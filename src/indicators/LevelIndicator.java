package indicators;
import biuoop.DrawSurface;
import game.GameLevel;
import game.Sprite;

/**
 * LevelIndicator class.
 *
 * @author Hadas Neuman
 */
public class LevelIndicator implements Sprite {

    private String levelName;

    /**
     * A constructor - Creates new Ball with 2 limits.
     *
     * @param levelName is the level name.
     */
    public LevelIndicator(String levelName) {
        this.levelName = levelName;
    }

    /**
     * A constructor - Creates new Ball with 2 limits.
     */
    public LevelIndicator() {
        this.levelName = new String("Unknown level");
    }

    /** Draws the level on a given draw surface.
     *
     * @param d is the given draw surface that the level should be drawn on.
     */
    public void drawOn(DrawSurface d) {
        int height = 30;
        d.setColor(java.awt.Color.black);
        d.drawText((d.getWidth() - (this.levelName.length() * 13)),
                (height - 10),
                ("Level: " + this.levelName),
                14);
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
