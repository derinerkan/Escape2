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

public class StorePanel extends JPanel 
{ 
    //Constants
    private static final int BACKGROUND_WIDTH = 900;
    private static final int BACKGROUND_HEIGHT = 600;
    
    //Properties
    private Image background;
    private Image storeLabel;
    private BufferedImage rightArrow;
    private BufferedImage leftArrow;
    private BufferedImage backArrow;
    private JButton rightButton;
    private JButton leftButton;
    private JButton backButton;
    
    public StorePanel()
    {
        setLayout( null );
        
        addImages();
        
        addButtons();
    }
    
    public void addImages()
    {
        try
        {
            background = ImageIO.read( new File( "storeBackground.jpg" ) );
            storeLabel = ImageIO.read( new File( "storeLabel.png" ) );
            rightArrow = ImageIO.read( new File( "rightArrow.png" ) );
            leftArrow = ImageIO.read( new File( "leftArrow.png" ) );
            backArrow = ImageIO.read( new File( "backArrow.png" ) );
        }
        
        catch( IOException exception ){}
    }
    
    public void addButtons()
    {
        rightButton = new JButton( new ImageIcon( rightArrow.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        leftButton = new JButton( new ImageIcon( leftArrow.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        backButton = new JButton( new ImageIcon( backArrow.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        
        rightButton.setBounds(  BACKGROUND_WIDTH - 125, 250, 50, 50 );
        leftButton.setBounds( 75, 250, 50, 50 );
        backButton.setBounds( 20, 20, 50, 50 );
        
        rightButton.setBorderPainted( false );
        leftButton.setBorderPainted( false );
        backButton.setBorderPainted( false );
        
        backButton.addActionListener( new BackButtonListener() );
        
        add( rightButton );
        add( leftButton );
        add( backButton );
    }
    
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        
        g.drawImage( background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null );
        g.drawImage( storeLabel, 250, 25, 400, 100, null );
    }
    
    private class BackButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent event )
        {
            Main.setPanel( new MainMenuPanel() );
        }
    }
}

