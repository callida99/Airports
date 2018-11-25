public class Airport extends Junction {

    //******************* constructor ********************************
    public Airport(String name, double xCoord, double yCoord) {
        super(name, xCoord, yCoord);
    }

    //******************* public Methods ********************************
    @Override
    public String toString(){
        return super.toString() + " type: Airport";
    }
}
