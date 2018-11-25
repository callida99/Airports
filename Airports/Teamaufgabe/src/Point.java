/**
 * Representates a Point in 2D-Space
 */
public class Point {
    //******************* private Fields ********************************
    private double x;
    private double y;

    //******************* constructor ********************************
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //******************* Getter/Setter ********************************
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    //******************* public Methods ********************************
    @Override
    public String toString(){
        return "X: " + x + ",Y: " + y;
    }
}
