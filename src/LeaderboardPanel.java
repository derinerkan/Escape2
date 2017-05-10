import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

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
    //private JTextArea list;
    private JTextArea names;
    private JTextArea scores;
    
    public LeaderboardPanel()
    {
        setLayout( null );
        
        addImages();
        
        addButtons();
        drawText();
        //drawDebugBox();
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

        //list.setText(Main.saveGame.leaderboard.toString());
        names.setText(Main.saveGame.leaderboard.namesToString());
        scores.setText(Main.saveGame.leaderboard.scoresToString());

        g.setColor(Color.WHITE);

        //g.drawLine(500,150,500,550);
        /*g.setColor(Color.WHITE.darker());*/

    }

    public void drawText()
    {
        /*
        list = new JTextArea(Main.saveGame.leaderboard.toString());
        Font font = list.getFont();
        font = new Font(font.getName(),Font.ITALIC + Font.BOLD, 25);
        list.setFont(font);
        list.setForeground(new Color(0xAAAAAA));
        list.setBackground(new Color(0x00ff00ff,true));
        list.setBounds(150,150,600,400);
        list.setEditable(false);
        list.setOpaque(false);
        list.setVisible(true);
        add(list);
        Main.saveGame.saveGame();/**/

        names = new JTextArea(Main.saveGame.leaderboard.namesToString());
        names.setFont(new Font("Dialog", Font.ITALIC + Font.BOLD, 25));
        names.setForeground(Color.WHITE.darker());
        names.setBackground(new Color(0x00ff00ff, true));
        names.setBounds(150, 150, 300, 400);
        names.setEditable(false);
        names.setOpaque(false);

        scores = new JTextArea(Main.saveGame.leaderboard.scoresToString());
        scores.setFont(new Font("Dialog", Font.ITALIC + Font.BOLD, 25));
        scores.setForeground(Color.WHITE.darker());
        scores.setBackground(new Color(0x00ff00ff, true));
        scores.setBounds(550, 150, 300, 400);
        scores.setEditable(false);
        scores.setOpaque(false);

        add(names);
        add(scores);
    }

    @Deprecated
    public void drawDebugBox()
    {
        final JTextField field = new JTextField("Test");
        JPanel debugPanel = new JPanel(new GridLayout(1,2));
        JButton debugButton = new JButton("Clicc me");
        debugPanel.setBounds(50,50,200,100);
        debugPanel.add(field);
        debugPanel.add(debugButton);
        debugButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                Player debugPlayer = new Player();
                debugPlayer.setName(field.getText());
                debugPlayer.updateScore(Double.MAX_VALUE);
                Main.saveGame.leaderboard.addPlayer(debugPlayer);
                repaint();
                System.out.println(field.getText());
            }});
        add(debugPanel);
    }
    
    private class BackButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent event )
        {
            Main.setPanel( new MainMenuPanel() );
        }
    }
}
