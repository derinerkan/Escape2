//necessary imports
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class is responsible for drawing the items of the game.
 * @author BROJECT
 * @version 1.0
 */
public class Visuals
{
    //properties
    private BufferedImage sunImage;
    private BufferedImage moonImage;
    private BufferedImage earthImage;
    
    public Visuals()
    {
        try
        {
            sunImage = ImageIO.read( new File( "images/sunImage.png" ) );
            moonImage = ImageIO.read( new File( "images/moonImage.png" ) );
            earthImage = ImageIO.read( new File( "images/earthImage.png" ) );

//            sunImage = ImageIO.read( new File( "sunImage.png" ) );
//            moonImage = ImageIO.read( new File( "moonImage.png" ) );
//            earthImage = ImageIO.read( new File( "earthImage.png" ) );
        }
        catch( IOException exception ){exception.printStackTrace();}
    }
    
    /**
     * Represents the ball visually, gets the Ball that will be drawn as a parameter. Also, takes the Graphics object
     * that will draw the Ball as a parameter.
     * @param toDraw is the Ball that will be drawn.
     * @param g is the Graphics object that will draw the Ball.
     */
    public void drawBall( Ball toDraw, Graphics g )
    {
        if ( toDraw instanceof SunBall )
        {
            g.drawImage( sunImage,( int ) toDraw.getLocation().getX() - toDraw.getRadius() , 
                        ( int ) toDraw.getLocation().getY() - toDraw.getRadius(), 
                        2 * toDraw.getRadius(), 2 * toDraw.getRadius(), null );
        }
        else if ( toDraw instanceof MoonBall )
        {
            g.drawImage( moonImage,( int ) toDraw.getLocation().getX() - toDraw.getRadius() , 
                        ( int ) toDraw.getLocation().getY() - toDraw.getRadius(), 
                        2 * toDraw.getRadius(), 2 * toDraw.getRadius(), null );
        }
        else if ( toDraw instanceof EarthBall )
        {
            g.drawImage( earthImage,( int ) toDraw.getLocation().getX() - toDraw.getRadius() , 
                        ( int ) toDraw.getLocation().getY() - toDraw.getRadius(), 
                        2 * toDraw.getRadius(), 2 * toDraw.getRadius(), null );
        }
    }
    
    /**
     * Represents the laser visually, get the Laser that will be drawn as a parameter. Also, takes the Graphics object
     * that will draw the Laser as a parameter.
     * @param toDraw is the Laser that will be drawn.
     * @param g is the Graphics object that will draw the Laser.
     */
    public void drawLaser( Laser toDraw, Graphics g )
    {
        Graphics2D g2 = ( Graphics2D ) g;
        g2.setStroke( new BasicStroke( 4 ) );
        g.setColor( Color.BLUE );
        g.drawLine( ( int ) toDraw.getP1().getX(), ( int ) toDraw.getP1().getY(), ( int ) toDraw.getP2().getX(),
                   ( int ) toDraw.getP2().getY() );
    }
}