import java.awt.*;

/**
 * This class represents a Laser in the game. The Laser consists of the Points and a line connecting them. Thus, this
 * class involves methods to form this line segment and access relevant information.
 * @author BROJECT
 * @version 1.0
 */
public class Laser
{
    //Properties
    private Point p1;
    private Point p2;
    private double timeAlive; //now represents the time this laser starts to exist
    private static double timeToLive;
    private double length;
    private double slope; //additional, not in the UML
    private final double lifespan = 3;//additional, not in the UML
    private boolean kill;
    
    /**
     * The default constructor of the Laser. Produces a line segment of random position and random length.
     */
    public Laser()
    {
        p1 = new Point();
        
        int xTranslation;
        int yTranslation;
        double x2;
        double y2;
        
        xTranslation = 180 + (int) ( Math.random() * 81 );
        yTranslation = 180 + (int) ( Math.random() * 81 );
        
        int chance = ( int ) Math.pow( - 1, ( int ) ( Math.random() * 2 ) );
        
        if ( Math.random() > 0.5 )
        {
            x2 = p1.getX() + xTranslation;
            y2 = p1.getY() + chance * yTranslation;
        }
        
        else
        {
            x2 = p1.getX() +  chance * xTranslation;
            y2 = p1.getY() + yTranslation;
        }
        
        p2 = new Point( x2, y2 );
        
        slope = ( p2.getY() - p1.getY() ) / ( p2.getX() - p1.getX() );
        timeAlive = timeToLive;
        //timeToLive = 10; //or another constant, it should be determined
        kill = false;
    }
    
    /**
     * Determines whether a specified Ball and the Laser intersects or not, using the fundamentals of analytic 
     * geometry. Returns true if there is an intersection, returns false otherwise.
     * @param toCheck is the specified Ball that will be checked for intersection.
     * @return is the boolean expression indicating whether an intersection occurs or not.
     */
    public boolean isTouched( Ball toCheck )
    {
        if(kill) return true; //if the ball had been touched at any point so far, it will always be touched
        if ( toCheck.isTouchable() )
        {
            double m = this.getSlope();
            double x = toCheck.getLocation().getX();
            double y = toCheck.getLocation().getY();
            double r = toCheck.getRadius();
            double y1 = this.p1.getY();
            double x1 = this.p1.getX();
            double x2 = this.p2.getX();
            double y2 = this.p2.getY();
            double dif = Math.abs( y - m * x - y1 + m * x1 ) / Math.sqrt( m * m + 1 );
            
            if ( m != Math.PI && m >= 0 )
            {
                if ( dif <= r && x + r >= x1 && x - r <= x2 && y + r >= y1 && y - r <= y2 )
                {
                    kill = true;
                    return true;
                }
                return false;
            }
            else if ( m < 0 ) 
            {
                if ( dif <= r && x + r >= x2 && x - r <= x1 && y + r >= y1 && y - r <= y2 )
                {
                    kill = true;
                    return true;
                }
                return false;
            }
            if ( Math.abs( x - x1 ) <= r && y + r >= y1 && y - r <= y2 )
            {
                kill = true;
                return true;
            }
            return false;
        }
        return false;
    }
    
    /**
     * Accessor method for p1, returns beginning point of the Laser.
     * @return is the beginning point of the Laser.
     */
    public Point getP1()
    {
        return p1;
    }
    
    /**
     * Accessor method for p2, returns ending point of the Laser.
     * @return is the ending point of the Laser.
     */
    public Point getP2()
    {
        return p2;
    }
    
    /**
     * Accessor method for the length, returns the length of the Laser.
     * @return is the length of the Laser.
     */
    public double getLength()
    {
        return length;
    }
    
    /**
     * Accessor method for the slope, returns the slope of the Laser.
     * @return is the slope of the Laser.
     */
    public double getSlope()
    {
        return slope;
    }
    
    /**
     * @deprecated
     * Increments timeAlive by a specified specified amount.
     * @param increase is the specified amunt of incrementation.
     */
    public void incrementTimeAlive( double increase )
    {
        timeAlive = timeAlive + increase;
    }
    
    /**
     * Increments timeToLive by a specified specified amount.
     * @param increase is the specified amunt of incrementation.
     */
    public static void incrementTimeToLive( double increase )
    {
        timeToLive = timeToLive + increase;
    }
    
    /**
     * Determines whether a Laser is alive or not by comparing timeToLive and timeAlive. Returns a boolean expression 
     * indicating it.
     * @return is the boolean, which indicates whether the Laser is alive or not.
     */
    public boolean isAlive()
    {
        return (timeToLive - timeAlive) < lifespan;
    }

    /**
     * Determines the color of this lazer based on its lifetime
     * @return red or blue
     */
    public Color laserColor()
    {
        if((timeToLive-timeAlive)/lifespan <= 0.1) return Color.BLUE;
        else return Color.RED;
    }
}