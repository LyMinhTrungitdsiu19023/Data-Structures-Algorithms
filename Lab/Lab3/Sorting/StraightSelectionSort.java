package Sorting;

import java.util.Comparator;

public class StraightSelectionSort<E> implements Isort<E> {
	Boolean isAsc = true;

	public StraightSelectionSort() {
	}

	public StraightSelectionSort(Boolean isAsc) {
		this.isAsc = isAsc;
	}

	@Override
	public void sort(E[] array, Comparator<E> comparator) {
		int current, smallest, walker;

		current = 0;
		while (current < array.length - 1) {
			smallest = current;
			walker = current + 1;
			int factor = 1;
			if (!isAsc) {
				factor = -1;
			}
			while (walker < array.length) {
				if (comparator.compare(array[walker], array[smallest]) * factor < 0) {
					smallest = walker;
				}
				walker += 1;
			}
			if (smallest != current) {
				
				E temp = array[smallest];
				array[smallest] = array[current];
				array[current] = temp;
			}
			current += 1;
		}
	}
}