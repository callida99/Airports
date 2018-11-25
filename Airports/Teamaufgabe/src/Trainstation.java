public class Trainstation  extends Junction {

    //******************* constructor ********************************
    public Trainstation(String name, double xCoord, double yCoord) {
        super(name, xCoord, yCoord);
    }

    //******************* public Methods ********************************
    @Override
    public String toString(){
        return super.toString() + " type: Trainstation";
    }
}
