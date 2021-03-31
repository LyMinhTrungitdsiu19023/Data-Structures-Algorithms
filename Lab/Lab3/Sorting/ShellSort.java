package Sorting;

import java.util.Comparator;

public class ShellSort<E> implements Isort<E> {

	int[] num_segment;

	public ShellSort(int[] num_segment) {
		this.num_segment = num_segment;
	}

	@Override
	public void sort(E[] array, Comparator<E> comparator) {
		for (int k = num_segment.length - 1; k > 0; k--) {
			for (int segment_idx = 0; segment_idx < k; segment_idx++)
				sort_segment(array, segment_idx, k, comparator);
		}
	}

	private void sort_segment(E[] points, int segment_idx, int num_segment, Comparator<E> comparator) {
		int current;
		int walker;
		E temp;
		current = segment_idx + num_segment;
		while (current < points.length) {
			temp = points[current];
			walker = current - num_segment;
			while ((walker >= 0) && (comparator.compare(temp, points[walker]) < 0)) {
				points[walker + num_segment] = points[walker]; // shift to right
				walker -= num_segment;
			}
			points[walker + num_segment] = temp;
			current += num_segment;
		}
	}
}
