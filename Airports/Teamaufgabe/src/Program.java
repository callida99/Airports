import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * this program reads .csv file and wraps each set of connected attributes in an object of
 * type junction and adds them to two datastructures. Followed by test-cases of datastructure implementation.
 */
public class Program {

    public static void main(String[] args) {

        //Creation of 2D-Grid
        Point topLeft = new Point(-200033,15000);
        Point bottomRight = new Point(20033,-15000);
        Grid2D junctionGrid = new Grid2D(topLeft, bottomRight);

        //Creation of List
        SinglyLinkedList junctionList = new SinglyLinkedList();

        //Read file input
        try(Scanner s = new Scanner(
                new File(System.getProperty("user.dir") +
                        "/data/junctions.csv"), "UTF-8")) {

            Pattern pt = Pattern.compile(";|\n");
          s.useDelimiter(pt);

          //Adding Transitionpoints to 2D-grid and list
            while(s.hasNext()){
                Junction p;
                String name = s.next();
                double x = Double.parseDouble(s.next());
                double y = Double.parseDouble(s.next());
                String z = s.next();
                if (z.equals("AIRPORT"))
                {
                    p = new Airport(name, x, y);
                }
                else{
                    p = new Trainstation(name, x, y);
                }
                junctionList.add(p);
                junctionGrid.add(p);
            }
        } catch(FileNotFoundException e) {
            System.exit(1);
        }


        // *************************** List Implementation *****************************************
        // Laufzeit findJunctionsInRadius(): O(n)
        // Laufzeit findAirports(): O(n^2)
        System.out.println("******************* List Implementation ********************");

        int []test1 = junctionList.findJunctionsInRadius(100.0,1818.54657,5813.29982);
        System.out.println(String.format("Junctions less than 100.0 units from x=1818.54657 y=5813.29982:\n> Airports: %d  Trainstations: %d",test1[0],test1[1]));

        int []test2 = junctionList.findJunctionsInRadius(575.0,0.0,0.0);
        System.out.println(String.format("Junctions less than 575.0 units from x=0.0 y=0.0:\n> Airports: %d  Trainstations: %d",test2[0],test2[1]));

        System.out.println("pls wait.....");

        System.out.println("Airports with at least 20 Trainstations less than 15.0 units away:\n> " + junctionList.findAirports(15.0,20));

        System.out.println("pls wait.....");

        System.out.println("Airports with at least 5 Trainstations less than 1.0 units away:\n> " + junctionList.findAirports(1.0,5));


        // *************************** 2D Grid Implementation *****************************************
        // Laufzeit findJunctionsInRadius(): Worst Case wie Liste + Overhead, nur besser wenn n Punkte gleichmäßig verteilt.
        // Laufzeit findAirports(): Worst Case wie Liste + Overhead, nur besser wenn n Punkte gleichmäßig verteilt.
        System.out.println("\n******************* 2D Grid Implementation ********************");

        int []test3 = junctionGrid.findJunctionsInRadius(100.0,1818.54657,5813.29982);
        System.out.println(String.format("Junctions less than 100.0 units from x=1818.54657 y=5813.29982:\n> Airports: %d  Trainstations: %d",test3[0],test3[1]));

        int []test4 = junctionGrid.findJunctionsInRadius(575.0,0.0,0.0);
        System.out.println(String.format("Junctions less than 575.0 units from x=0.0 y=0.0:\n> Airports: %d  Trainstations: %d",test4[0],test4[1]));

        System.out.println("Airports with at least 20 Trainstations less than 15.0 units away:\n> " + junctionGrid.findAirports(15.0,20));

        System.out.println("Airports with at least 5 Trainstations less than 1.0 units away:\n> " + junctionGrid.findAirports(1.0,5));

    }
}
