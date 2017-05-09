import java.io.Serializable;

/**
 * This class represents the Ball, which the user controls in the game. It involves methods to move the Ball and access
 * relevant information.
 * @author BROJECT
 * @version 1.0
 */
public class Ball implements Serializable
{
    //Constants
    private final int PRICE;
    private final int RADIUS;
    private final double MAX_SPEED;
    
    //Properties
    private boolean isOwned;
    private boolean touchable;
    private Point location;
    private Point velocity;
    private double ballFriction;
    
    /**
     * The constructor of the Ball class; takes the price, radius and maximum speed as parameters. Initializes the 
     * other properties.
     * @param price is the specified price of the Ball.
     * @param radius is the specified radius of the Ball.
     * @param maxSpeed is the specified maximum spped of the Ball.
     */
    public Ball( int price, int radius, double maxSpeed )
    {
        PRICE = price;
        RADIUS = radius;
        MAX_SPEED = maxSpeed;
        ballFriction = 0.1;
        isOwned = false;
        touchable = true;
        location = new Point( Main.getFrameWidth() / 2, Main.getFrameHeight() / 2 ); 
        velocity = new Point( 0, 0 );
    }
    
    /**
     * The copy constructor of the Ball, it takes the Ball to be copied as a parameter.
     * @param other is the Ball to be copied.
     */
    public Ball( Ball other )
    {
        PRICE = other.PRICE;
        RADIUS = other.RADIUS;
        MAX_SPEED = other.MAX_SPEED;
        isOwned = false;
        location = other.getLocation();
        velocity = other.getVelocity();
    }
    
    /**
     * Sets the location of the Ball to a specified Point.
     * @param position is the specified Point which will be the location of the Ball.
     */
    public void setPosition( Point position )
    {
        if ( position != null ) //in case of a Null-Pointer
        {
            location = position;
        }
    }
    
    /**
     * Translates the location of the Ball with respect to the specified translation vector.
     * @param vector is the specified translation vector.
     */
    public void translate( Point vector )
    {
        location.translate( vector.getX(), vector.getY() );
    }
       
    /**
     * Accelerates the ball with respect to a specified acceleration vector.
     * @param vector is the specified acceleration vector.
     */
    public void accelerate( Point vector )
    {
        if ( new Point( velocity.getX() + vector.getX(), velocity.getY() + vector.getY() ).getDistanceToOrigin() <=
            MAX_SPEED )
        {
            velocity.translate( vector.getX(), vector.getY() );
        }
    }
    
    /**
     * Sets the velocity of the ball to a specified velocity vector.
     * @param vector is the specified velocity vector.
     */
    public void setVelocity( Point vector )
    {
        if ( vector.getDistanceToOrigin() <= MAX_SPEED && vector != null ) //in case of a Null-Pointer
        {
            velocity = vector;
        }
    }
    
    /**
     * Accessor method for the location, returns the location of the Ball.
     * @return location is the current location of the Ball.
     */
    public Point getLocation()
    {
        return location;
    }
    
    /**
     * Accessor method for the velocity, returns the velocity of the Ball.
     * @return velocity is the current velocity of the Ball.
     */
    public Point getVelocity()
    {
        return velocity;
    }
    
    /**
     * Accessor method for the radius, returns the radius of the Ball.
     * @return radius is the radius of the Ball.
     */
    public int getRadius()
    {
        return RADIUS;
    }
    
    /**
     * Accessor method for the touchable property, returns the touchable state of the Ball.
     * @return touchable is the touchable state of the Ball.
     */
    public boolean isTouchable()
    {
        return touchable;
    }
    
    /**
     * Accessor method for the price of the Ball.
     * @return PRICE is the price of the Ball.
     */
    public int getPrice()
    {
        return PRICE;
    }
    
    /**
     * Sets the touchable state of the Ball.
     * @param toSet is the boolean expression which will be set as the touchable property.
     */
    public void setTouchable( boolean toSet )
    {
        touchable = toSet;
    }
    
    /**
     * Sets the owned state of the Ball.
     * @param toSet is the boolean expression which will be set as the isOwned property.
     */
    public void setOwned( boolean toSet )
    {
        isOwned = toSet;
    }
    
    /**
     * Accessor method for the isOwned property of the Ball.
     * @return is the isOwned property of the Ball.
     */
    public boolean isOwned()
    {
        return isOwned;
    }
    
    /**
     * Accessor method for the speed, computes and returns the speed of the Ball.
     * @return is the current speed of the Ball.
     */
    public double getSpeed()
    {
        return velocity.getDistanceToOrigin();
    }
    
    //****************************************************************************
    public void accelerate( char direction, double amount )
    {
        if ( velocity.getY() >=  -6 )
        {
            if ( direction == 'u' )
            {
                velocity.translate( 0, - amount );
            }
        }
        if ( velocity.getY() <= 6 )
        {
            if ( direction == 'd' )
            {
                velocity.translate( 0, amount );
            }
        }
        if ( velocity.getX() <= 6 )
        {
            if ( direction == 'r' )
            {
                velocity.translate( amount, 0 );
            }
        }
        if ( velocity.getX() >= -6 )
        {
            if ( direction == 'l' )
            {
                velocity.translate( - amount, 0 );
            }
        }
    }
    
    public void move( ) //char direction, double amount )
    {
        location.translate( velocity.getX(), velocity.getY() ); 
        
        if ( velocity.getX() < 0 ) 
        {
            velocity.translate( ballFriction, 0 );
        }
        else if ( velocity.getX() > 0 ) 
        {
            velocity.translate( - ballFriction, 0 );
        }
        
        if ( velocity.getY() < 0 ) 
        {
            velocity.translate( 0, ballFriction );
        }
        else if ( velocity.getY() > 0 )
        {
            velocity.translate( 0, - ballFriction );
        }
    }
}