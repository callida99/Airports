/**
 * Representates an Element in a list
 */
public class ListNode {
    //******************* private Fields ********************************
    private Junction point;
    private ListNode next;

    //******************* constructor ********************************
    public ListNode(Junction point, ListNode next){
        this.point = point;
        this.next = next;
    }

    //******************* Getter/Setter ********************************
    public Junction getTransitionPoint(){
        return point;
    }

    public void setSong(Junction point){
        this.point = point;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next){
        this.next = next;
    }

    //******************* public Methods ********************************
    @Override
    public String toString(){
        return "ListNode " + point;
    }
}
