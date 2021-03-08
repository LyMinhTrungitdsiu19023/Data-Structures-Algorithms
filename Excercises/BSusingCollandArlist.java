package DSA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
public class BSusingCollandArlist {
    public static void main(String[] args)
    {
        List<Integer> al = new ArrayList<Integer>();
        al.add(1);
        al.add(2);
        al.add(3);
        al.add(10);
        al.add(20);
 
        
        int index = Collections.binarySearch(al, 20);
        if(index > 0 && index < al.size()) {
            System.out.print( "Element at position ");
            System.out.println(index);
        }
        else {
            System.out.printf("Element no have");
        }
    }
}