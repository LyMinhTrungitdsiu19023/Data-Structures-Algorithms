package Sorting;

import java.util.Comparator;

public class BubbleSort<E> implements Isort<E> {

	Boolean isAsc = true;

	public BubbleSort() {
	}

	public BubbleSort(Boolean isAsc) {
		this.isAsc = isAsc;
	}

	@Override
	public void sort(E[] array, Comparator<E> comparator) {
		int current, walker;
		boolean flag;

		current = 0;
		flag = false;
		int factor = 1;
		if (!isAsc) {
			factor = -1;
		}
		while ((current < array.length - 1) && (flag == false)) {
			walker = array.length - 1; // start from the last and backward
			flag = true; // for testing if the input already in ascending order
			while (walker > current) {
				if (comparator.compare(array[walker], array[walker - 1]) * factor < 0) {
					flag = false;
					// swap:
					E temp = array[walker];
					array[walker] = array[walker - 1];
					array[walker - 1] = temp;
				}
				walker -= 1;
			}
			current += 1;
		}
	}
}
