import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

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
        drawText();
    }
    
    public void addImages()
    {
        try
        {
            background = ImageIO.read( new File( "images/leaderboardBackground.jpg" ) );
            leaderboardLabel = ImageIO.read( new File( "images/leaderboardLabel.png" ) );
            backArrow = ImageIO.read( new File( "images/backArrow.png" ) );
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

        /*g.setColor(Color.WHITE.darker());
        g.drawString("Yarrak", 100, 100);/**/
    }

    public void drawText()
    {
        JTextArea list = new JTextArea(Main.saveGame.leaderboard.toString());
        //list.setText("TEST YARAK");
        list.setBackground(Color.WHITE);
        list.setBounds(150,150,600,400);
        list.setEditable(false);
        list.setVisible(true);
        add(list);
        Main.saveGame.saveGame();
    }
    
    private class BackButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent event )
        {
            Main.setPanel( new MainMenuPanel() );
        }
    }
}
