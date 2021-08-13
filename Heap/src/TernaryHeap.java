
/*
 * TernaryHeap.java
 *
 * Version:
 * $Id: TernaryHeap.java,v 1.1 2014/10/25 17:22:57 lxs8146 Exp $
 * Revisions:
 * $Log: TernaryHeap.java,v $
 * Revision 1.1  2014/10/25 17:22:57  lxs8146
 * Initial revision
 *
 */

/**
 * Generic implementation of a ternary heap. Items are arranged such that each
 * item in the heap is less than its children in the heap (if any). Note that
 * behavior with identical items is not defined.
 * 
 * @author Lucas Stefanic (lxs8146)
 */

public class TernaryHeap<E extends java.lang.Comparable<E>> {

    private E[] data;
    private int size;
    
    /**
     * Constructor of an empty heap.
     * 
     * @param init An empty array of the appropriate type, large enough to hold
     * the heap at all times.
     */
    public TernaryHeap(E[] init) {
        data = init;
        size = 0;
    }
    
    /**
     * Return the number of items in the heap.
     * 
     * @return Size of the heap
     */
    public int size() {
        return size;
    }
    
    /**
     * Insert an item into the heap.
     * 
     * @param item to insert
     */
    public void insert(E item) {
        data[size] = item;
        int n = size;
        while ( data[n].compareTo(data[(n-1)/3]) < 0 ) {
            E temp = data[n];
            data[n] = data[(n-1)/3];
            data[(n-1)/3] = temp;
            n = (n-1)/3;
        }
        size++;
    }
    
    /**
     * Remove and return the item at the root of the heap.
     * 
     * @return The smallest item in the heap.
     * @throws UnderflowException If the heap is empty
     */
    public E remove() throws UnderflowException {
        if ( size == 0 )
        throw new UnderflowException("Heap is empty");
        E result = data[0];
        data[0] = data[size-1];
        size--;
        int n = 0;
        E temp;
        while ( 3*n+1 < size ) {
            if ( 3*n+3 < size ) {
                if ( data[n].compareTo(data[3*n+1]) > 0
                        || data[n].compareTo(data[3*n+2]) > 0
                        || data[n].compareTo(data[3*n+3]) > 0 ) {
                    if ( data[3*n+1].compareTo(data[3*n+2]) <= 0
                            && data[3*n+1].compareTo(data[3*n+3]) <= 0 ) {
                        temp = data[n];
                        data[n] = data[3*n+1];
                        data[3*n+1] = temp;
                        n = 3*n+1;
                    }
                    else if ( data[3*n+2].compareTo(data[3*n+1]) <= 0
                            && data[3*n+2].compareTo(data[3*n+3]) <= 0 ) {
                        temp = data[n];
                        data[n] = data[3*n+2];
                        data[3*n+2] = temp;
                        n = 3*n+2;
                    }
                    else {
                        temp = data[n];
                        data[n] = data[3*n+3];
                        data[3*n+3] = temp;
                        n = 3*n+3;
                    }
                }
                else
                return result;
            }
            else if ( 3*n+2 < size ) {
                if ( data[n].compareTo(data[3*n+1]) > 0
                        || data[n].compareTo(data[3*n+2]) > 0 ) {
                    if ( data[3*n+1].compareTo(data[3*n+2]) < 0 ) {
                        temp = data[n];
                        data[n] = data[3*n+1];
                        data[3*n+1] = temp;
                        n = 3*n+1;
                    }
                    else {
                        temp = data[n];
                        data[n] = data[3*n+2];
                        data[3*n+2] = temp;
                        n = 3*n+2;
                    }
                }
                else
                return result;
            }
            else {
                if ( data[n].compareTo(data[3*n+1]) > 0 ) {
                    temp = data[n];
                    data[n] = data[3*n+1];
                    data[3*n+1] = temp;
                    n = 3*n+1;
                }
                else
                return result;
            }
        }
        return result;
    }
    
    /**
     * Return the String representation of the heap for testing. This consists
     * of each element's String representation on a line by itself, in order
     * reading across each level of the heap from top to bottom.
     * 
     * @return the String representation of the heap
     */
    public String toString() {
        String result = "";
        for ( int i = 0 ; i < size ; i++ )
        result += data[i] + "\n";
        return result;
    }
}
