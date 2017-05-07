import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main 
{
    public static void main( String [] args )
    {
        Main game = new Main();
    }
    
    //Constants
    private static JFrame mainFrame;
    private static JPanel currentPanel;
    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;
    private static final String TITLE = "ESCAPE";
    
    public Main()
    {
        currentPanel = new MainMenuPanel();
        mainFrame = new JFrame( TITLE );
        mainFrame.add( currentPanel );
        mainFrame.setSize( FRAME_WIDTH, FRAME_HEIGHT );
        mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        mainFrame.setVisible( true );
    }
    
    public static void setPanel( JPanel toSet )
    {
        mainFrame.remove( currentPanel );
        currentPanel = toSet;
        mainFrame.add( toSet );
        mainFrame.revalidate();
    }
    
    public static int getFrameWidth()
    {
        return FRAME_WIDTH;
    }
    
    public static int getFrameHeight()
    {
        return FRAME_HEIGHT;
    }
}
