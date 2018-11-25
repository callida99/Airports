/**
 * Representates a sinly linked list that holds elements of type ListNode
 */
public class SinglyLinkedList {

    //******************* private Fields ********************************
    private ListNode head;
    private ListNode last;
    private int size = 0;

    //******************* Getter/Setter ********************************
    public int getSize(){
        return size;
    }

    //******************* public Methods ********************************
    public void add(Junction p){
        ListNode node = new ListNode(p, null);
        if (head == null){
            head = node;
            last = node;
        }
        else{
            last.setNext(node);
            last = node;
        }
        size++;
    }

    /**
     * Returns song in list at given index
     * @param index of type Integer.
     * @return Song at given index, or null if index out of range.
     */
    public Junction get(int index){
        for(ListNode node = head; node != null; node = node.getNext()){
            if (index == 0){
                return node.getTransitionPoint();
            }
            index--;
        }
        return null;
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
    public int[] findJunctionsInRadius(double radius, double startX, double startY){  //Laufzeit O(n)
        int[]counters = new int[2];

        //Iterates over list and calculates distance from start point to current point
        for(ListNode node = head; node != null; node = node.getNext()){
            // Calculates Hypotenuseof the triangle between starting point and current point (Pythagoras)
            double deltaX = Math.sqrt(Math.pow((startX - node.getTransitionPoint().getxCoord()),2)
                    +Math.pow((startY-node.getTransitionPoint().getyCoord()),2));

            //Adds Junction to return Array if it is in range
            if(deltaX <= radius) {
                if (node.getTransitionPoint() instanceof Airport) {
                    counters[0]++;
                } else if (node.getTransitionPoint() instanceof Trainstation){
                    counters[1]++;
                }
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
    public int findAirports(double radius, int n ){
        int counter = 0;

        //iterates over list and calls findJunctionsInRadius() for all Junctions of type airport to
        // get the number of Trainstations in radius.
        for(ListNode node = head; node != null; node = node.getNext()){
            if(node.getTransitionPoint() instanceof Airport){
                int arr[]= findJunctionsInRadius(radius,node.getTransitionPoint().getxCoord(),
                        node.getTransitionPoint().getyCoord());
                int  numberOfTrainstations = arr[1];

                if(numberOfTrainstations >= n){
                    counter++;
                }

            }
        }
        return counter;
    }

}




