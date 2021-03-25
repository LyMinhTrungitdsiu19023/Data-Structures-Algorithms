package DSA.LAB.Lab01;
//Question 1

// #1.1
public class Point2D {
    private double x ;
    private double y ;
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
    public Point2D(){
        this.x = 0;
        this.y = 0;
    }
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    } 
    // #1.2
    public Point2D(Point2D oldPoint){
        this.x = oldPoint.x; 
        this.y = oldPoint.y;

    }// #1.4
    public Point2D clone(){
        return new Point2D(this.x, this.y);

    }
    // #1.6
    @Override
    public String toString() {
        return String.format("Point2D(%.2f, %.2f)", this.x, this.y);
    }
    // #1.7
    public static Point2D[] generate(int N, double min, double max){ 
        Point2D[] listofN = new Point2D[N];
        for(int i = 0; i < N; i++){
            double x = min + Math.random() * (max - min);
            double y = min + Math.random() * (max - min);
            listofN[i] = new Point2D(x, y);
        }
        return listofN;

    } 
    // #1.8
    public double distanceAB(Point2D a, Point2D b){
        double distanceX = b.getX() - a.getY(); 
        double distanceY = b.getY() - b.getY();
        return Math.sqrt(distanceX*distanceX + distanceY*distanceY); 
    } 

    // #1.9
    public double distanceTo(Point2D point){
        double distanceX = point.getX() - this.getX();
        double distanceY = point.getY() - this.getY(); 
        return Math.sqrt(distanceX*distanceX + distanceY*distanceY);
    } 
    public static void main(String[] args) {
        Point2D pointA = new Point2D(2.00, 1.00); 
        Point2D pointB = new Point2D();
        System.out.println(pointA.toString()); 
        System.out.println(pointB.toString()); 
        
    }
}
