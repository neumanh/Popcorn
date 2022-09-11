package graphics;

/**
 * Point class.
 *
 * @author HadasNeuman
 */
public class Point {
    private double x;
    private double y;

    /**
     * A constructor - creates a Point from a specified coordinates.
     *
     * @param x is the x location
     * @param y is the y location
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * A constructor - creates a Point from a specified int coordinates.
     *
     * @param x is the x location
     * @param y is the y location
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between the point and a given point.
     *
     * @param other is other point
     * @return the distance of this point to the other point.
     */
    public double distance(Point other) {
        double dis = Math.sqrt(((this.x - other.x) * (this.x - other.x))
                + ((this.y - other.y) * (this.y - other.y)));
        return dis;
    }

    /**
     * Checks if the point equal to a given point.
     *
     * @param other is other point
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (other != null) {
            return ((this.x == other.x) && (this.y == other.y));
        } else {
            return false;
        }
    }

    /**
     *  Accessor - gets the private variable x.
     *
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     *  Accessor - gets the private variable y.
     *
     * @return the y value of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     *  Accessor - gets the private variable x.
     *
     * @return the x value of this point
     */
    public int getIntX() {
        return (int) Math.round(this.x);
    }

    /**
     *  Accessor - gets the private variable y.
     *
     * @return the y value of this point
     */
    public int getIntY() {
        return (int) Math.round(this.y);
    }

}