package graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hadas Neuman
 *
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private  Line[] lines; //holds the four lines of the rectangle

    /**
     * A constructor - creates a new rectangle with location and width/height.
     *
     * @param upperLeft is the upper left point
     * @param width is the width of the rectangle
     * @param height is the width of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;

        Point upperRight = new Point((upperLeft.getX() + width), upperLeft.getY());
        Point downLeft = new Point(upperLeft.getX(), (upperLeft.getY() + height));
        Point downRight = new Point((upperLeft.getX() + width), (upperLeft.getY() + height));

        lines = new Line[4];
        //Top line
        this.lines[0] = new Line(upperLeft, upperRight);
        //Right line
        this.lines[1] = new Line(upperRight, downRight);
        //Bottom line
        this.lines[2] = new Line(downLeft, downRight);
        //Left line
        this.lines[3] = new Line(upperLeft, downLeft);
    }

    /**A copy constructor.
     *
     * @param rect is another rectangle
     */
    public Rectangle(Rectangle rect) {
        this(rect.getUpperLeft(), rect.getWidth(), rect.getHeight());
    }

    /**
     * Calculates intersection points with a given line.
     * @param line is the line that the Rectangle may intersect
     * @return the list of the intersection points or null if there are not any intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> pointList = new ArrayList<Point>();

        for (int i = 0; i < 4; i++) {
            if (lines[i].isIntersecting(line)) {
                pointList.add(lines[i].intersectionWith(line));
            }
        }
        if (pointList.size() == 0) {
            return null;
        } else {
            return pointList;
        }
    }

    /**
     * Gets the width of the rectangle.
     * @return the width parameter
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets the height of the rectangle.
     * @return the height parameter
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets the upper-left point of the rectangle.
     * @return the upperLeft parameter
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets the top line of the rectangle.
     * @return the top line of the rectangle
     */
    public Line getTopLine() {
        return this.lines[0];
    }

    /**
     * Gets the right line of the rectangle.
     * @return the right line of the rectangle
     */
    public Line getRightLine() {
        return this.lines[1];
    }

    /**
     * Gets the bottom line of the rectangle.
     * @return the bottom line of the rectangle
     */
    public Line getBottomLine() {
        return this.lines[2];
    }

    /**
     * Gets the left line of the rectangle.
     * @return the left line of the rectangle
     */
    public Line getLeftLine() {
        return this.lines[3];
    }
}
