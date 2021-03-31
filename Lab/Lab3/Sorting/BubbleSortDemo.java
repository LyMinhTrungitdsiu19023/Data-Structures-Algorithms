package Sorting;

import Geom.Point2D;
import Geom.PointComparator;

public class BubbleSortDemo {
	final static Boolean SORT_ASC = true;
	final static Boolean SORT_DSC = false;

	public static void demo() {
		int N = 30;
		Point2D[] points = Point2D.generate(N, -10, 20);

		// Print points
		System.out.println("DEMO FOR BUBBLE SORT:");
		System.out.println(new String(new char[80]).replace('\0', '='));
		System.out.println("Unsorted list points:");
		System.out.println(new String(new char[80]).replace('\0', '-'));
		for (int idx = 0; idx < N; idx++) {
			String line = String.format("%3d | %s", idx, points[idx]);
			System.out.println(line);
		}
		// Sort: insertion sort
		// Compute the center
		double cx=0,cy=0;
		for (int idx = 0; idx < N; idx++) {
			cx += points[idx].getX();
			cy += points[idx].getY();
		}
		cx = cx/N; cy = cy/N;
		Point2D centerPoint = new Point2D(cx, cy);
		BubbleSort<Point2D> sortAlg = new BubbleSort<>();

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