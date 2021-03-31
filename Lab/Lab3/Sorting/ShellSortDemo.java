package Sorting;

import Geom.Point2D;
import Geom.PointComparator;

public class ShellSortDemo {

	public static void demo() {
		int N = 30;
		Point2D[] points = Point2D.generate(N, -10, 20);

		// Print points
		System.out.println("DEMO FOR SHELL SORT:");
		System.out.println(new String(new char[80]).replace('\0', '='));
		System.out.println("Unsorted list points:");
		System.out.println(new String(new char[80]).replace('\0', '-'));
		for (int idx = 0; idx < N; idx++) {
			String line = String.format("%3d | %s", idx, points[idx]);
			System.out.println(line);
		}
		// Sort: insertion sort
		ShellSort<Point2D> sortAlg = new ShellSort<>(new int[] { 1, 3, 7 });
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
