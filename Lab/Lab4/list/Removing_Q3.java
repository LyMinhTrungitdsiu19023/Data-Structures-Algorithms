package list;



import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import Geom.Point2D;

public class Removing_Q3 {
    public static void removeHittedPoints(List<Point2D> list, Point2D testPoint, double radius){
        ListIterator<Point2D> item= list.listIterator();
        if(item.hasNext()){
            Point2D point = item.next();
            if(point.distanceTo(testPoint) <= radius) {
                item.remove();
            }
        }
    }
    public static void print(List<Point2D> list, String string) {
        System.out.print(string + " modification: ");
        for (Object object : list) {
            System.out.print(object + ",  ");
            
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Point2D[] points = {new Point2D(0, 0), new Point2D(2, 4), new Point2D(3, 4), new Point2D(5, 7)}; 
        List<Point2D> list = Arrays.asList(points);
        removeHittedPoints(list, new Point2D(2,4), 3.5);
        print(list, "Before");
        print(list, "After");
    }
}
