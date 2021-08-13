
/*
 * HeapSort.java
 *
 * Version:
 * $Id: HeapSort.java,v 1.1 2014/10/25 17:22:57 lxs8146 Exp $
 * Revisions:
 * $Log: HeapSort.java,v $
 * Revision 1.1  2014/10/25 17:22:57  lxs8146
 * Initial revision
 *
 */

import java.io.*;
import java.util.*;

/**
 * Class to implement a heapsort.
 * 
 * @author Lucas Stefanic (lxs8146)
 */

public class HeapSort {

    public static void main(String[] args) {
        Integer[] heapArray = new Integer[30];
        TernaryHeap<Integer> heap = new TernaryHeap<Integer>(heapArray);
        try {
            Scanner s = new Scanner(new File(args[0]));
            while ( s.hasNextInt() )
            heap.insert(new Integer(s.nextInt()));
            System.out.println(heap);
            while ( heap.size() > 0 )
            System.out.println(heap.remove());
        } catch ( FileNotFoundException e ) {
            System.err.println(e.getMessage());
        } catch ( UnderflowException e ) {
            System.err.println(e.getMessage());
        }
    }
}