package Sorting;

import Geom.Point2D;
import Geom.PointComparator;

public class SortingEval {
    /*
     *  Typical value:  nElementMax = 500  nExec = 100;
     */
    public static Point2D[] timeit(Isort algorithm, int nElementMax, int nExec) {
        Point2D[] algPerfomance = new Point2D[nElementMax];
        algPerfomance[0] = new Point2D(0, 0); // list of size =0 => time = 0
        for (int idx = 1; idx < nElementMax; idx++) {
            double timeElapsed = 0;
            for (int c = 0; c < nExec; c++) {
                // Try c times for each size
                Point2D[] points = Point2D.generate(idx, -20, 20);
                long startTime = System.nanoTime();
                algorithm.sort(points, new PointComparator());
                long endTime = System.nanoTime();
                timeElapsed += (double) (endTime - startTime) / (nExec * 1000000);
            }
            algPerfomance[idx] = new Point2D(idx, timeElapsed);
        }
        return algPerfomance;
    } 

}
