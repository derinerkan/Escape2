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
import javax.swing.JScrollBar;

public class HowToPlayPanel extends JPanel 
{
    //Constants
    private static final int BACKGROUND_WIDTH = Main.getFrameWidth();
    private static final int BACKGROUND_HEIGHT = Main.getFrameHeight();
    
    //Properties
    private Image background;
    private Image howToPlayLabel;
    private BufferedImage backArrow;
    private JButton backButton;
    
    public HowToPlayPanel()
    {
        setLayout( null );
        
        addImages();
        
        addButtons();
    }
    
    public void addImages()
    {
        try
        {
            background = ImageIO.read( new File( "howToPlayBackground.jpg" ) );
            howToPlayLabel = ImageIO.read( new File( "howToPlayLabel.png" ) );
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
        g.drawImage( howToPlayLabel, 160, 25, 600, 100, null );
    }
    
    private class BackButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent event )
        {
            Main.setPanel( new MainMenuPanel() );
        }
    }
}
