import java.util.ArrayList;

public class Store
{
    //properties
    private static ArrayList<Ball> balls;
    
    public Store()
    {
        balls = new ArrayList<Ball>();
        balls.add( new SunBall() );
        balls.add( new EarthBall() );
        balls.add( new MoonBall() );
    }
    
    public static ArrayList<Ball> getBalls()
    {
        return balls;
    }
}