import java.awt.CardLayout;
import javax.sound.sampled.DataLine;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Main class of the project: starts the game.
 * @author BROJECT
 * @version 2017-05-12
 */
public class Main 
{
    public static void main( String [] args )
    {
        Main game = new Main();
    }

    public static SaveGame saveGame;

    //Constants
    private static JPanel cards;
    private static CardLayout stack;
    private static JFrame mainFrame;
    private static JPanel gamePanel;
    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;
    private static final String TITLE = "ESCAPE";

    /**
     * Create the main menu
     */
    private Main()
    {
        saveGame = new SaveGame();
        gamePanel = new GamePanel();
        
        stack = new CardLayout();
        cards = new JPanel( stack );
        cards.add( new MainMenuPanel(), "menu" );
        cards.add( new SettingsPanel(), "settings" );
        cards.add( new HowToPlayPanel(), "howToPlay" );
        cards.add( gamePanel, "game" );
        cards.add( new StorePanel(), "store" );
        cards.add( new LeaderboardPanel(), "leaderboard" );   
        
        mainFrame = new JFrame( TITLE );
        mainFrame.add( cards );
        mainFrame.setSize( FRAME_WIDTH, FRAME_HEIGHT );
        mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        mainFrame.setVisible( true );
        mainFrame.setResizable(false);
        startMusic();
    }

    /**
     * Get the stack as a CardLayout
     * @return the card layout
     */
    public static CardLayout getStack()
    {
        return stack;
    }

    /**
     * Start the game.
     */
    public static void startGame()
    {
        ( (GamePanel) gamePanel).restartGame();
    }

    /**
     * Get the cards in this layout.
     * @return the cards
     */
    public static JPanel getCards()
    {
        return cards;
    }

    /**
     * Return the frame width.
     * @return the frame width
     */
    public static int getFrameWidth()
    {
        return FRAME_WIDTH;
    }

    /**
     * Return the frame height
     * @return the frame height
     */
    public static int getFrameHeight()
    {
        return FRAME_HEIGHT;
    }

    /**
     * Gets the main frame in this game
     * @return the main frame
     */
    public static JFrame getMainFrame()
    {
        return mainFrame;
    }

    /**
     * Start playing music.
     */
    private void startMusic()
    {
        try
        {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("music/theme.wav"));
            DataLine.Info info = new DataLine.Info(Clip.class, inputStream.getFormat());
            Clip clip = (Clip)AudioSystem.getLine(info);
            clip.open(inputStream);
            clip.start();

            Thread.sleep(1);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}