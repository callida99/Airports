/**
 * Representates a grid in 2D-Space. The grid is implemented as a 2D-Array, where
 * each cell holds a SinglyLinkedList that contains all Junctions that are associated
 * to that specific cell.
 */
public class Grid2D {
    //******************* private Fields ********************************
    private SinglyLinkedList grid[][];
    private int gridSize;

    // these two points bound the plane
    private Point topLeft;
    private Point bottomRight;

    //******************* constructor ********************************

    /**
     * Constructor with given boundaries, initialises empty lists in grid cells.
     * @param topLeft top left Point of the plane.
     * @param bottomRight bottom right Point of the plane.
     */
    public Grid2D(Point topLeft, Point bottomRight){
        double planeSize = (bottomRight.getX() - topLeft.getX());

        //round plane size up to an integer value
        gridSize = (int)Math.ceil(Math.sqrt(planeSize));
        grid = new SinglyLinkedList[gridSize][gridSize];
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                grid[i][j] = new SinglyLinkedList();
            }
        }
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    //******************* private Methods ********************************
    /**
     * Calculates the index of a cell in the 2D-array for a given Juction based
     * an it's coordinates.
     * @param p of type Junction.
     * @return index of type Point P=(x,y).
     */
    private Point calcIndex(Junction p){
        int m = gridSize - 1;

        double minX = topLeft.getX();
        double maxX = bottomRight.getX();
        double minY = bottomRight.getY();
        double maxY = topLeft.getY();

        //calculates a value between 0 and gridSize-1 and rounds it down to integer value.
        int x = (int)Math.floor(m*((p.xCoord - minX)/(maxX -minX)));
        int y = m - (int)Math.floor(m*((p.yCoord - minY)/(maxY -minY)));

        return new Point(x,y);
    }

    /**
     * Calculates the index of a cell in the 2D-array for a given Point based
     * an it's coordinates. Overloaded method to help find Junctions.
     * @param p of type Point.
     * @return index of type Point P=(x,y).
     */
    private Point calcIndex(Point p){
        int m = gridSize - 1;
        double minX = topLeft.getX();
        double maxX = bottomRight.getX();
        double minY = bottomRight.getY();
        double maxY = topLeft.getY();

        //calculates a value between 0 and gridSize-1 and rounds it down to integer value.
        int x = (int)Math.floor(m*((p.getX() - minX)/(maxX -minX)));
        int y = m - (int)Math.floor(m*((p.getY() - minY)/(maxY -minY)));

        return new Point(x,y);
    }

    /**
     * Adds a Junction to the 2d-Grid at a calculated index.
     * @param p of type Junction.
     */
    public void add(Junction p){
        Point index = calcIndex(p);
        grid[(int)index.getY()][(int)index.getX()].add(p);
    }

    /**
     * Calculates the number of Trainstations and Airports inside a specific range from a
     * startingPoint P.
     * @param radius defines the radius of the Range.
     * @param startX X-coordinate of starting point.
     * @param startY Y-coordinate of starting point.
     * @return Array of size 2, where Arr[0] represents the number of Airports in
     * given range and Arr[1] representates the number of Trainstations.
     */
    public int[] findJunctionsInRadius(double radius, double startX, double startY) {
        int[] counters = new int[2];

        //Draws rectangular Quad around Point, to reduce the number of points that have to
        // be checked.
        Point topLeftPointIndex = calcIndex(new Point(startX - radius, startY + radius));
        Point bottomRightPointIndex = calcIndex(new Point(startX + radius, startY - radius));

        //iterates over the cells of the 2D-Array that are touched by the Quad.
        for (int y = (int) topLeftPointIndex.getY(); y <= (int) bottomRightPointIndex.getY(); y++) {
            for (int x = (int) topLeftPointIndex.getX(); x <= (int) bottomRightPointIndex.getX(); x++) {
               int[]temp = grid[y][x].findJunctionsInRadius(radius, startX, startY); // delegates task to list
               counters[0] += temp[0];
               counters[1] += temp[1];
            }
        }
        return counters;
    }

    /**
     * Calculates number of airports with at least n Trainstations inside of
     * a given radius.
     * @param radius of type double.
     * @param n Minimum number of Trainstations that have to be in radius.
     * @return returns Integer number of Airports matching the criteria.
     */
    public int findAirports(double radius, int n){
        int counter = 0;
        //Iterates over each SinglyLinkedList in the 2D-Array and calls findJunctionsInRadius() for
        // each Juction of type Airport.
        for(int y = 0; y < grid.length; y++){
            for(int x = 0; x < grid[0].length; x++){
                for (int i = 0; i < grid[y][x].getSize(); i++){
                    Junction p = grid[y][x].get(i);
                    if (p instanceof Airport) {
                        int[] arr = findJunctionsInRadius(radius, p.getxCoord(), p.getyCoord());

                        int num = arr[1];
                        if (num >= n){
                            counter++;
                        }
                    }
                }
            }
        }
        return counter;
    }

}
