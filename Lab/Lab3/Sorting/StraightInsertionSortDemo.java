package Sorting;

import Geom.Point2D;
import Geom.PointComparator;

public class StraightInsertionSortDemo {
    public static void Demo(){
        int N = 30;
        int min = - 10;
        int max = 20;  
        Point2D[] points = Point2D.generate(N, min, max);

        //Print points
        System.out.println("DEMO FOR INSERTION SORT:"); 
        System.out.println(new String(new char[80]).replace('\0', '-')); //create dau gach ngang
        System.out.println("Unsorted list point:");
        System.out.println(new String(new char[80]).replace('\0', '-')); //create dau gach ngang
        for(int idx = 0; idx < N; idx ++){                               //method print
            String line = String.format("%3d | %s", idx, points[idx]);
            System.out.println(line);
        }
        //Sort: insertion sort
        StraightInsertionSort<Point2D> sortAlg = new StraightInsertionSort<>();
        sortAlg.sort(points, new PointComparator());

        //Print point
        System.out.println("");
        System.out.println("Sorted list of points (sorted by x-cooridinates,cending)");  
        System.out.println(new String(new char[80]).replace('\0', '-'));
        for(int idx=0; idx < N; idx++){                                                 //print point sorted
            String line = String.format("%3d | %s", idx, points[idx]);
            System.out.println(line); 


        }

        System.out.println("\nStraigt Insertion Sort: Time measurement"); 
        System.out.println("Size Time (msec)");
        System.out.println(new String(new char[80]).replace('\0', '-')); 

        StraightInsertionSort<Point2D> point2d = new StraightInsertionSort<>(); 
        Point2D[] timing = new Point2D[500];
        timing = SortingEval.timeit(point2d, 500, 100);
        for(int idx=0; idx < N; idx++){                                                 
            String line = String.format("%02.0f | %.8f", timing[idx].getX(), timing[idx].getY());
            System.out.println(line);
    }
}
}
