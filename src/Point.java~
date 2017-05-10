/**
 * This class represents a Point on the cartesian plane. It involves methods to control the Point and access relevant
 * information.
 * @author BROJECT
 * @version 1.0
 */
public class Point 
{
    //Properties
    private double x;
    private double y;
    
    /**
     * The default constructor of the Point class. It produces a random Point on the cartesian plane.
     */
    public Point()
    {
        x = ( int ) ( Math.random() * Main.getFrameWidth() );    
        y = ( int ) ( Math.random() * Main.getFrameHeight() );
        
        //will be implemented after GameScreen class, that is a GUI class
    }
    
    /**
     * The constructor of the Point class. It produces a Point on the cartesian plane with the specified coordinates.
     * @param x is the specified x coordinate.
     * @param y is the specified y coordinate.
     */
    public Point( double x, double y )
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Sets the location of the Point to the specified coordinates.
     * @param x is the specified x coordinate.
     * @param y is the specified y coordinate.
     */
    public void setLocation( double x, double y )
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Translates the Point by the given amounts. In other words, moves the Point by a specified amount in the x axis 
     * and the y axis.
     * @param x is the specified amount of translation in the x axis.
     * @param y is the specified amount of translation in the y axis.
     */
    public void translate( double x, double y )
    {
        this.x = this.x + x;
        this.y = this.y + y;
    }
    
    /**
     * Multiplies the coordinates of the Point by a specified scalar.
     * @param scalar is the specified scalar
     */
    public void scalarProduct( double scalar )
    {
        this.x = this.x * scalar;
        this.y = this.y * scalar;
    }
    
    /**
     * Accessesor method for the x coordinate, returns the x coordinate of the Point.
     * @return x is the x coordinate of the Point.
     */
    public double getX()
    {
        return x;
    }
    
    /**
     * Accessesor method for the y coordinate, returns the y coordinate of the Point.
     * @return y is the y coordinate of the Point.
     */
    public double getY()
    {
        return y;
    }
    
    /**
     * Computes and returns the distance between the Point with this reference and another specified Point.
     * @param other is the specified Point that will be used when computing the distance.
     * @return is the distance between the Point with this reference and another specified Point.
     */
    public double getDistance( Point other )
    {
        return Math.sqrt( ( x - other.getX() ) * ( x - other.getX() ) + ( y - other.getY() ) * ( y - other.getY() ) );
    }
    
    /**
     * Computes and returns the distance between the Point and the origin.
     * @return is the distance between the Point and the origin.
     */
    public double getDistanceToOrigin( )
    {
        return Math.sqrt( x * x + y * y );
    }  
}