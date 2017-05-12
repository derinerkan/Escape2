import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The main menu panel with all the buttons in it
 * @author BROJECT
 * @version 2017-05-12
 */
public class MainMenuPanel extends JPanel 
{ 
    //Constants
    private static final int BACKGROUND_WIDTH = Main.getFrameWidth();
    private static final int BACKGROUND_HEIGHT = Main.getFrameHeight();
    
    //Properties
    private Image background;
    private Image logo;
    private JButton playButton;
    private JButton storeButton;
    private JButton settingsButton;
    private JButton leaderboardButton;
    private JButton howToPlayButton; 
    private BufferedImage playImage;
    private BufferedImage storeImage;
    private BufferedImage leaderboardImage;
    private BufferedImage howToPlayImage;
    private BufferedImage settingsImage;

    /**
     * Create a new MainMenuPanel
     */
    public MainMenuPanel()
    {
        setLayout(null);
        
        addImages();
        
        addButtons();
    }

    /**
     * Add the images that we will be using
     */
    private void addImages()
    {
        try
        {
            background = ImageIO.read( new File( "images/menuBackground.jpg") );
            logo = ImageIO.read( new File( "images/logo.png" ) );
            playImage = ImageIO.read( new File( "images/play.png" ) );
            storeImage = ImageIO.read( new File( "images/store.png" ) );
            leaderboardImage = ImageIO.read( new File( "images/leaderboard.png" ) );
            howToPlayImage = ImageIO.read( new File( "images/howToPlay.png" ) );
            settingsImage = ImageIO.read( new File( "images/settingsButton.png" ) );
        }
        
        catch( IOException exception ){exception.printStackTrace();}
    }

    /**
     * Add the buttons that will be used
     */
    private void addButtons()
    {
        playButton = new JButton( new ImageIcon( playImage.getScaledInstance( 300, 100, BufferedImage.TYPE_INT_ARGB ) ) );
        storeButton = new JButton( new ImageIcon( storeImage.getScaledInstance( 300, 100, BufferedImage.TYPE_INT_ARGB ) ) );
        leaderboardButton = new JButton( new ImageIcon( leaderboardImage.getScaledInstance( 300, 100, BufferedImage.TYPE_INT_ARGB ) ) );
        howToPlayButton = new JButton( new ImageIcon( howToPlayImage.getScaledInstance( 300, 100, BufferedImage.TYPE_INT_ARGB ) ) );
        settingsButton = new JButton( new ImageIcon( settingsImage.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        
        playButton.setBounds( 300, 210, 300, 50 );
        storeButton.setBounds( 300, 270, 300, 50 );
        leaderboardButton.setBounds( 300, 330, 300, 50);
        howToPlayButton.setBounds( 300, 390, 300, 50 );
        settingsButton.setBounds( 20, 20, 50, 50 );
        
        playButton.addActionListener( new ClickListener() );
        storeButton.addActionListener( new ClickListener() );
        leaderboardButton.addActionListener( new ClickListener() );
        howToPlayButton.addActionListener( new ClickListener() );
        settingsButton.addActionListener( new ClickListener() );
        
        playButton.setBorderPainted( false );
        playButton.setOpaque( false );
        playButton.setContentAreaFilled( false );
        storeButton.setBorderPainted( false );
        storeButton.setOpaque( false );
        storeButton.setContentAreaFilled( false );
        leaderboardButton.setBorderPainted( false );
        leaderboardButton.setOpaque( false );
        leaderboardButton.setContentAreaFilled( false );
        howToPlayButton.setBorderPainted( false );
        howToPlayButton.setOpaque( false );
        howToPlayButton.setContentAreaFilled( false );
        settingsButton.setBorderPainted( false );
        settingsButton.setOpaque( false );
        settingsButton.setContentAreaFilled( false );
        
        add( playButton );
        add( storeButton );
        add( leaderboardButton );
        add( howToPlayButton );
        add( settingsButton );
    }

    @Override
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        
        g.drawImage( background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null );
        g.drawImage( logo, 150, 25, 600, 150, null );
    }
    
    private class ClickListener implements ActionListener
    {
        @Override
        public void actionPerformed( ActionEvent event ) 
        {
            if ( event.getSource() == playButton )
            {
                Main.getStack().show( Main.getCards(), "game" );
                Main.startGame();
            }
            else if ( event.getSource() == storeButton )
            {
                Main.getStack().show( Main.getCards(), "store" );
            }
            else if ( event.getSource() == leaderboardButton )
            {
                Main.getStack().show( Main.getCards(), "leaderboard" );
            }
            else if ( event.getSource() == howToPlayButton )
            {
                Main.getStack().show( Main.getCards(), "howToPlay" );
            }
            else if ( event.getSource() == settingsButton )
            {
                Main.getStack().show( Main.getCards(), "settings" );
            }
        }
    }
}
