package DSA.LAB.Lab01;
// Question 2
// 2.1
public class Vector2D {
    private double x; 
    private double y;
    // 2.5
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
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    } 
    // 2.2
    public Vector2D(Vector2D oldVector){
        this.x = oldVector.x; 
        this.y = oldVector.y; 
    }
    // 2.3
    public Vector2D(){
        this.x = 1;
        this.y = 0;
    } 
    // 2.4
    public Vector2D clone(){
        return new Vector2D(this.x, this.y);
    } 
    // 2.6
    @Override
    public String toString() {
        return String.format("Vector2D(%.2f, %.2f)", this.x, this.y);
    }
    // 2.7 
    public double A2B(Vector2D a, Vector2D b){
        double distanceX = b.getX() - a.getY(); 
        double distanceY = b.getY() - b.getY();
        return Math.sqrt(distanceX*distanceX + distanceY*distanceY); 
    } 
    //2.8
    public double Length(Vector2D vector){
        return Math.sqrt(vector.getX()*vector.getX() + vector.getY()*vector.getY());
    } 
    //2.9
    public void Normalize(Vector2D vector ){ 
        double oldlength = this.Length(vector);
        this.setX(vector.getX()/oldlength); 
        this.setY(vector.getY()/oldlength);
    }
    //2.10
    public double Dot(Vector2D a, Vector2D b){
        return a.getX()*b.getX() + a.getY()*b.getY();
    } 
    //2.11
    public double Dotwith(Vector2D vector){
        return this.getX()*vector.getX() + this.getY()*vector.getY();
    }  
    //2.12
    public double Angle(Vector2D a, Vector2D b){
        return this.Dot(a, b)/(Math.sqrt(a.getX()*a.getX()+b.getX()*b.getX())*Math.sqrt(a.getY()*a.getY()+b.getY()*b.getY()));
    }
    //2.13
    public String getPerp(Vector2D vector){
        vector.setX(-this.getY());
        return String.format("New Vector that Perp with 'this' vector is (%.2f, %.2f", vector.getX(), vector.getY());
    }

}
