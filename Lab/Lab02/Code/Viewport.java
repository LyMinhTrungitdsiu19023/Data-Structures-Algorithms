package Geom;

public class Viewport {

    public double getyMin() {
        return yMin;
    }

    public void setyMin(double yMin) {
        this.yMin = yMin;
    }

    public double getyMax() {
        return yMax;
    }

    public void setyMax(double yMax) {
        this.yMax = yMax;
    }

    public double getxMin() {
        return xMin;
    }

    public void setxMin(double xMin) {
        this.xMin = xMin;
    }

    public double getxMax() {
        return xMax;
    }

    public void setxMax(double xMax) {
        this.xMax = xMax;
    }

    // Data fields
    private double xMin, xMax;
    private double yMin, yMax;

    // Constructors
    public Viewport(double xMin, double xMax, double yMin, double yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    public Viewport clone() {
        return new Viewport(this.xMin, this.xMax, this.yMin, this.yMax);
    }

    public void combine(Viewport vp) {
        this.xMin = Math.min(this.xMin, vp.xMin);
        this.xMax = Math.max(this.xMax, vp.xMax);
        this.yMin = Math.min(this.yMin, vp.yMin);
        this.yMax = Math.max(this.yMax, vp.yMax);
    }

    public void addPoint(Geom.Point2D points) {
        this.xMin = Math.min(this.xMin, points.getX());
        this.xMax = Math.max(this.xMax, points.getX());
        this.yMin = Math.min(this.yMin, points.getY());
        this.yMax = Math.max(this.yMax, points.getY());
    }
}
