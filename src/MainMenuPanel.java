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

public class MainMenuPanel extends JPanel 
{ 
    //Constants
    private static final int BACKGROUND_WIDTH = 900;
    private static final int BACKGROUND_HEIGHT = 600;
    
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
    
    public MainMenuPanel()
    {
        setLayout(null);
        
        addImages();
        
        addButtons();
    }
    
    public void addImages()
    {
        try
        {
            background = ImageIO.read( new File( "menuBackground.jpg") );
            logo = ImageIO.read( new File( "logo.png" ) );
            playImage = ImageIO.read( new File( "play.png" ) );
            storeImage = ImageIO.read( new File( "store.png" ) );
            leaderboardImage = ImageIO.read( new File( "leaderboard.png" ) );
            howToPlayImage = ImageIO.read( new File( "howToPlay.png" ) );
            settingsImage = ImageIO.read( new File( "settingsButton.png" ) );
        }
        
        catch( IOException exception ){}
    }
    
    public void addButtons()
    {
        playButton = new JButton( new ImageIcon( playImage.getScaledInstance( 300, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        storeButton = new JButton( new ImageIcon( storeImage.getScaledInstance( 300, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        leaderboardButton = new JButton( new ImageIcon( leaderboardImage.getScaledInstance( 300, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        howToPlayButton = new JButton( new ImageIcon( howToPlayImage.getScaledInstance( 300, 50, BufferedImage.TYPE_INT_ARGB ) ) );
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
        
        settingsButton.setBorderPainted( false );
        
        add( playButton );
        add( storeButton );
        add( leaderboardButton );
        add( howToPlayButton );
        add( settingsButton );
    }
    
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
            if ( ( JButton )event.getSource() == playButton )
            {
                Main.setPanel( new GamePanel() );
            }
            else if ( ( JButton )event.getSource() == storeButton )
            {
                Main.setPanel( new StorePanel() );
            }
            else if ( ( JButton )event.getSource() == leaderboardButton )
            {
                Main.setPanel( new LeaderboardPanel() );
            }
            else if ( ( JButton )event.getSource() == howToPlayButton )
            {
                Main.setPanel( new HowToPlayPanel() );
            }
            else if ( ( JButton )event.getSource() == settingsButton )
            {
                Main.setPanel( new SettingsPanel() );
            }
        }
    }
}
