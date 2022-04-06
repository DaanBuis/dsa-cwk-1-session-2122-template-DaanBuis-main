package datastructures;
/**
 *
 * @author rla
 */
public interface IADTLinkedList <E>{
    int length ();
    boolean isEmpty();
    void createList ( );
    void printList ( );
    listNode<E> front();
    void insert ( E theNode );
    void remove ( E theNode );
    listNode<E> remove (int index);
    boolean find ( E theNode );
    listNode<E> find( String theLastName);
}
