/*
 * ListFilter.java
 * 
 * Version:
 * $Id: ListFilter.java,v 1.1 2014/11/19 21:44:20 lxs8146 Exp $
 * Revisions:
 * $Log: ListFilter.java,v $
 * Revision 1.1 2014/11/19 21:44:20 lxs8146
 * Third revision: Added filter field implementation and comments
 */

import java.util.ArrayList;

/**
 * A class containing a static filter method to be used by AddressBook's filter
 * field.
 * 
 * @author Lucas Stefanic (lxs8146)
 */

public class ListFilter {

    /**
     * Return a new list with the contents of the original list filtered by the
     * given string.
     * 
     * @param list The list to be filtered.
     * @param s The filter string
     * @return A new, filtered list
     */
    public static ArrayList<String> filter(ArrayList<String> list, String s) {
        ArrayList<String> result = new ArrayList<String>();
        for ( String str : list )
            if ( str.toLowerCase().indexOf(s.toLowerCase()) > -1 )
                result.add(str);
        return result;
    }
}
