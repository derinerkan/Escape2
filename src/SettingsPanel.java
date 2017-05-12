import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Settings panel for Escape
 * @author ESCAPE
 * @version 2017-05-12
 */

public class SettingsPanel extends JPanel
{
    //Constants
    private static final int BACKGROUND_WIDTH = Main.getFrameWidth();
    private static final int BACKGROUND_HEIGHT = Main.getFrameHeight();
    
    //Properties
    private Image background;
    private Image settingsLabel;
    private BufferedImage backArrow;
    private JButton backButton;
    private JSlider soundSlider;

    /**
     * Creates a new SettingsPanel
     */
    public SettingsPanel()
    {
        setLayout( null );
        
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
            background = ImageIO.read( new File( "images/settingsBackground.jpg" ) );
            settingsLabel = ImageIO.read( new File( "images/settingsLabel.png" ) );
            backArrow = ImageIO.read( new File( "images/backArrow.png" ) );
        }
        
        catch( IOException exception ){exception.printStackTrace();}
    }

    /**
     * Add the buttons that we will be using
     */
    private void addButtons()
    {
        soundSlider = new JSlider( 0, 100, 50 );

        
        backButton = new JButton( new ImageIcon( backArrow.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        
        backButton.setBounds( 20, 20, 50, 50 );
        
        backButton.setBorderPainted( false );
        backButton.setOpaque( false );
        backButton.setContentAreaFilled( false );
        
        backButton.addActionListener( new BackButtonListener() );


        soundSlider.setBounds(100,250,500,50);
        soundSlider.setOpaque(false);
        soundSlider.setBackground(new Color(0x00ff00ff, true));
        soundSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                System.err.println(soundSlider.getValue());
            }
        });

        add( soundSlider );
        add( backButton );
    }

    @Override
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        
        g.drawImage( background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null );
        g.drawImage( settingsLabel, 200, 25, 500, 100, null );
    }
    
    private class BackButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed( ActionEvent event )
        {
            Main.getStack().show( Main.getCards(), "menu" );
            //Main.setPanel( new MainMenuPanel() );
        }
    }
}
