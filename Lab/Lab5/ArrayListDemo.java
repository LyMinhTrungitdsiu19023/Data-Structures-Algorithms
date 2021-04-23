package Lab5DSA;

import java.util.ArrayList;

public class ArrayListDemo {
    public static void main(String[] args) {
        // QUESTION 1:
        // List<String> list = new ArrayList<>();
        // for (int idx = 0; idx < 10; idx++) {
        // list.add("" + idx);
        // }

        // // (1)Print elements - Use Index, travel forward
        // System.out.printf("%-32s", "Go forward, use index:");
        // for (int idx = 0; idx < list.size(); idx++) {
        // System.out.printf("%s ", list.get(idx));
        // }
        // System.out.println();

        // // (2)Print elements - Use Index, travel backward
        // System.out.printf("%-32s", "Go backward, use index:");
        // for (int idx = list.size() - 1; idx >= 0; idx--) {
        // System.out.printf("%s ", list.get(idx));
        // }
        // System.out.println();

        // // (3)Print elements - Use Iterator, travel forward
        // System.out.printf("%-32s", "Go forward, use Iterator:");
        // Iterator<String> it = list.iterator();
        // while (it.hasNext()) {
        // String item = it.next();
        // System.out.printf("%s ", item);
        // }
        // System.out.println();
        // // (4)Print elements - Use ListIterator, travel forward
        // System.out.printf("%-32s", "Go forward, use ListIterator:");
        // ListIterator<String> fwd = list.listIterator();
        // while (fwd.hasNext()) {
        // String item = fwd.next();
        // System.out.printf("%s ", item);
        // }
        // System.out.println();

        // // (5)Print elements - Use ListIterator, travel backward
        // System.out.printf("%-32s", "Go backward, use ListIterator:");
        // ListIterator<String> bwd = list.listIterator(list.size());
        // while (bwd.hasPrevious()) {
        // String item = bwd.previous();
        // System.out.printf("%s ", item);
        // }
        // System.out.println();
        // ---------------------------END Q1----------------------------------------------------------------------------
        // Question 2
        // List<Integer> list = new ArrayList<>();
        // // Add elements
        // for (int idx = 0; idx < 10; idx++) {
        // list.add(idx);
        // }

        // // (1)Print elements - Use Index, travel forward
        // System.out.printf("%-25s", "Before modification:");
        // for (int idx = 0; idx < list.size(); idx++) {
        // System.out.printf("%s ", list.get(idx));
        // }
        // System.out.println();
        // // (2)Remove odd numbers Using FOR LOPP
        // ListIterator<Integer> it = list.listIterator();
        // for (int i = 0; i < list.size(); i++) {
        // if (it.hasNext()) {
        // int item = it.next();
        // if (item % 2 != 0) {
        // it.remove();
        // i--;
        // } else {
        // it.set(item * 10);
        // }
        // }
        // }
        // // (3) Print after changing
        // System.out.printf("%-25s", "After modification:");
        // it = list.listIterator();
        // while (it.hasNext()) {
        // System.out.printf("%s ", it.next());
        // }
        // System.out.println();

        // ---------------------------END Q2-----------------------------------------------------------------------------
        // Question 3
        // Point2D[] points = { new Point2D(0, 0), new Point2D(2, 4), new Point2D(3, 4),
        // new Point2D(5, 7) };
        // List<Point2D> list = ArrayList.asList(points);
        // removeHittedPoints(list, new Point2D(2, 4), 3.5);
        // print(list, "Before");
        // print(list, "After");

        // }

        // private static void removeHittedPoints(List<Point2D> list, Point2D testPoint,
        // double radius) {
        // ListIterator<Point2D> it = list.listIterator();
        // for (int i = 0; i < list.size(); i++) {
        // if (it.hasNext()) {
        // int item = it.next();
        // if (item % 2 != 0) {
        // it.remove();
        // i--;
        // } else {
        // it.set(item * 10);
        // }
        // }
        // }
        // }
        // ---------------------------END Q3-----------------------------------------------------------------------------

        // Question 6 - Test SLinkedList
        SLinkedList<Integer> list = new SLinkedList<>();

        // ADD ELEMENTS
        for (int idx = 0; idx < 10; idx++) {
            list.add(idx);
        }

        // (1)PRINT ELEMENTS - USE INDEX, TRAVEL FORWARD
        System.out.printf("%-25s", "Before modification:");
        for (int idx = 0; idx < list.size(); idx++) {
            System.out.printf("%s ", list.get(idx));
        }
        System.out.println();

        // (2)REMOVE ODD NUMBERS
        SLinkedList<Integer>.MyListIterator it = list.new MyListIterator(0);

        while (it.hasNext()) {
            int item = it.next();
            if (item % 2 != 0)
                it.remove();
            else
                it.set(item * 10);
        }

        // (3)PRINT AFTER CHANGING
        System.out.printf("%-25s", "After modification:");
        SLinkedList<Integer>.MyListIterator it2 = list.new MyListIterator(0);
        while (it2.hasNext()) {
            System.out.printf("%d ", it2.next());
        }
        System.out.println();
    }
}
