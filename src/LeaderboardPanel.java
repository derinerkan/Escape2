import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LeaderboardPanel extends JPanel 
{
    //Constants
    private static final int BACKGROUND_WIDTH = 900;
    private static final int BACKGROUND_HEIGHT = 600;
    
    //Properties
    private Image background;
    private Image leaderboardLabel;
    private BufferedImage backArrow;
    private JButton backButton;
    
    public LeaderboardPanel()
    {
        setLayout( null );
        
        addImages();
        
        addButtons();
    }
    
    public void addImages()
    {
        try
        {
            background = ImageIO.read( new File( "leaderboardBackground.jpg" ) );
            leaderboardLabel = ImageIO.read( new File( "leaderboardLabel.png" ) );
            backArrow = ImageIO.read( new File( "backArrow.png" ) );
        }
        
        catch( IOException exception ){}
    }
    
    public void addButtons()
    {
        backButton = new JButton( new ImageIcon( backArrow.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        
        backButton.setBounds( 20, 20, 50, 50 );
        
        backButton.setBorderPainted( false );
        
        backButton.addActionListener( new BackButtonListener() );
        
        add( backButton );
    }
    
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        
        g.drawImage( background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null );
        g.drawImage( leaderboardLabel, 160, 25, 600, 100, null );
    }
    
    private class BackButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent event )
        {
            Main.setPanel( new MainMenuPanel() );
        }
    }
}
