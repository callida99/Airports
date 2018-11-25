/**
 * Abstract Parent Class of Airport and Trainstation
 */
public abstract class Junction {
    //******************* protected Fields ********************************
    protected String name;
    protected double xCoord;
    protected double yCoord;

     //******************* constructor ********************************
    public Junction(String name, double xCoord, double yCoord) {
        this.name = name;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    //******************* Getter/Setter ********************************
    public String getName() {
        return name;
    }
    public double getxCoord() {
        return xCoord;
    }
    public double getyCoord() {
        return yCoord;
    }

    //******************* public Methods ********************************
    @Override
    public String toString(){
        return String.format("Name: %s, x: %f, y: %f", name, xCoord, yCoord);
    }
}
