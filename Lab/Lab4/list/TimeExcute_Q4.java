package list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import java.util.Vector;

public class TimeExcute_Q4 {
    public static void main(String[] args) {
        
        List<Integer> arrayList = new ArrayList<>(); 
        for(int i = 0; i < 10000; i ++){                    // Neu chay ADD thi xoa For loop nay
            arrayList.add(i);
        }
        long startTime1 = System.currentTimeMillis();
        //ADD(0 ,arrayList);                                //Neu muon tinh time cua ADD thi thay bo get va cho chay ADD
        get(arrayList);
        long endTime1 = System.currentTimeMillis();
        System.out.println("\n => Total execution time for ArrayList: " + (endTime1-startTime1) + "ms"); 
        
        List<Integer> linkedList = new LinkedList<>();
        for(int i = 0; i < 10000; i ++){                    // Neu chay ADD thi xoa For loop nay
            linkedList.add(i);
        }
        long startTime2 = System.currentTimeMillis();
        //ADD(0, linkedList);
        get(linkedList); 
        long endTime2 = System.currentTimeMillis();
        System.out.println("\n => Total execution time for Linked List: " + (endTime2-startTime2) + "ms"); 

        List<Integer> vector = new Vector<>();  
        for(int i = 0; i < 10000; i ++){                    // Neu chay ADD thi xoa For loop nay
            vector.add(i);
        }
        long startTime3 = System.currentTimeMillis();
        //ADD(0 ,vector);  
        get(vector);
        long endTime3 = System.currentTimeMillis();
        System.out.println("\nTotal execution time for Vector: " + (endTime3-startTime3) + "ms"); 
    } 
    public static void ADD(int idex, List<Integer> object){
        
        for (int idx = 0; idx < 1000; idx++) {
            for(int y = 0; y < 50000; y++){
            object.add(0, y);
            }
        }
        //System.out.print( string + ": "+  object);

    } 
    public static void get(List<Integer> n){
        
        for (int idx = 0; idx < 1000; idx++) {
            for(int y = 0; y < 1000; y++){
                n.get(y);
            }
        }
    
    
    

    }
}
