package Sorting;

import java.util.Comparator;

public class StraightInsertionSort<E> implements Isort<E> {


    // T123456789 = O(3N + 3 + 2X), with X is the number of loops in T5
    @Override
    public void sort(E[] array, Comparator<E> comparator) {
        int current, walker;
        E temp;
        current = 1;
        while (current < array.length) {
            temp = array[current];
            walker = current - 1;
            while ((walker >= 0) && comparator.compare(temp, array[walker]) < 0) {
                array[walker + 1] = array[walker]; // shift to right
                walker -= 1;
            }
            array[walker + 1] = temp;
            current += 1;
        }
    }
}
