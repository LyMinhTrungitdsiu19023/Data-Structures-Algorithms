package Sorting;
import java.util.Comparator;
public interface Isort<E> {
    public void sort(E[] array, Comparator<E> comparator);
}
