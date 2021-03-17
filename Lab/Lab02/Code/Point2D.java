package Geom;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Point2D extends GeomObject {
    private double x;
    private double y;

    // #1.5
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    // #1.3
    public Point2D() {
        this.x = 0;
        this.y = 0;
    }

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // #1.2
    public Point2D(Point2D oldPoint) {
        this.x = oldPoint.x;
        this.y = oldPoint.y;

    }// #1.4

    public Point2D clone() {
        return new Point2D(this.x, this.y);

    }

    // #1.6
    @Override
    public String toString() {
        return String.format("Point2D(%.2f, %.2f)", this.x, this.y);
    }

    // #1.7
    public static Point2D[] generate(int N, double min, double max) {
        Point2D[] listofN = new Point2D[N];
        for (int i = 0; i < N; i++) {
            double x = min + Math.random() * (max - min);
            double y = min + Math.random() * (max - min);
            listofN[i] = new Point2D(x, y);
        }
        return listofN;

    }

    // #1.8
    public double distanceAB(Point2D a, Point2D b) {
        double distanceX = b.getX() - a.getY();
        double distanceY = b.getY() - b.getY();
        return Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    }

    // #1.9
    public double distanceTo(Point2D point) {
        double distanceX = point.getX() - this.getX();
        double distanceY = point.getY() - this.getY();
        return Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    }

    public static Point2D[] linear(int N, double a, double b, double xMin, double xMax) {
        Point2D[] list = new Point2D[N];
        double step = (xMax - xMin) / (N - 1);
        double x = xMin;
        for (int idx = 0; idx < N; idx++) {
            list[idx] = new Point2D(x, a * x + b);
            x += step;
        }
        return list;
    }

    public static int POINT_HALF_SIZE = 2;

    @Override
    public void draw(Graphics g, SpaceMapping mapper) {
        Graphics2D g2 = (Graphics2D) g;
        Point2D point = mapper.logic2Device(this.getX(), this.getY());

        int x = (int) point.getX() - POINT_HALF_SIZE;
        int y = (int) point.getY() - POINT_HALF_SIZE;

        g2.setColor(this.faceColor);
        g2.fillRect(x, y, 2 * POINT_HALF_SIZE + 1, 2 * POINT_HALF_SIZE + 1);
    }

    public static int getPOINT_HALF_SIZE() {
        return POINT_HALF_SIZE;
    }

    public static void setPOINT_HALF_SIZE(int POINT_HALF_SIZE) {
        Point2D.POINT_HALF_SIZE = POINT_HALF_SIZE;
    }
}
