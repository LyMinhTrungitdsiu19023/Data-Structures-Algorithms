package Sorting;

import Geom.Point2D;
import Geom.PointComparator;

public class StraightSelectionSortDemo {
	final static Boolean SORT_ASC = true;
	final static Boolean SORT_DSC = false;

	public static void demo() {
		int N = 30;
		Point2D[] points = Point2D.generate(N, -10, 20);

		// Print points
		System.out.println("DEMO FOR SELECTION SORT:");
		System.out.println(new String(new char[80]).replace('\0', '='));
		System.out.println("Unsorted list points:");
		System.out.println(new String(new char[80]).replace('\0', '-'));
		for (int idx = 0; idx < N; idx++) {
			String line = String.format("%3d | %s", idx, points[idx]);
			System.out.println(line);
		}
		// Sort: insertion sort
		StraightSelectionSort<Point2D> sortAlg = new StraightSelectionSort<>(SORT_DSC);
		sortAlg.sort(points, new PointComparator());

		// Print point
		System.out.println("");
		System.out.println("Sorted list of points (sorted by x-cooridinates, ascending)");
		System.out.println(new String(new char[80]).replace('\0', '-'));
		for (int idx = 0; idx < N; idx++) {
			String line = String.format("%3d | %s", idx, points[idx]);
			System.out.println(line);
		}
	}
}
