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
import java.awt.Font;
import java.awt.Color;

public class StorePanel extends JPanel 
{ 
    //Constants
    private static final int BACKGROUND_WIDTH = Main.getFrameWidth();
    private static final int BACKGROUND_HEIGHT = Main.getFrameHeight();
    
    //Properties
    private Image background;
    private Image storeLabel;
    private Image sunBall;
    private Image moonBall;
    private Image earthBall;
    private BufferedImage rightArrow;
    private BufferedImage leftArrow;
    private BufferedImage backArrow;
    private JButton rightButton;
    private JButton leftButton;
    private JButton backButton;
    private JButton equipButton;
    private int index;
    private Store store;
    private BufferedImage equipImage;
    
    public StorePanel()
    {
        Ball equippedBall = Main.saveGame.getPlayer().currentBall();

        store = new Store();

        for(int i = 0; i<Store.getBalls().size(); i++) //check the balls within the store's inventory and find the one used by the player
        {
            //System.out.println(Store.getBalls().get(i));
            if(Store.getBalls().get(i).getClass() == equippedBall.getClass()) index = i;
        }
        
        setLayout( null );
        
        addImages();
        
        addButtons();
    }
    
    public void addImages()
    {
        try
        {
            background = ImageIO.read( new File( "images/storeBackground.jpg" ) );
            storeLabel = ImageIO.read( new File( "images/storeLabel.png" ) );
            rightArrow = ImageIO.read( new File( "images/rightArrow.png" ) );
            leftArrow = ImageIO.read( new File( "images/leftArrow.png" ) );
            backArrow = ImageIO.read( new File( "images/backArrow.png" ) );
            sunBall = ImageIO.read( new File( "images/sunImage.png" ) );
            moonBall = ImageIO.read( new File( "images/moonImage.png" ) );
            earthBall = ImageIO.read( new File( "images/earthImage.png" ) );
            equipImage = ImageIO.read( new File( "images/equipButton.png" ) );

//            background = ImageIO.read( new File( "storeBackground.jpg" ) );
//            storeLabel = ImageIO.read( new File( "storeLabel.png" ) );
//            rightArrow = ImageIO.read( new File( "rightArrow.png" ) );
//            leftArrow = ImageIO.read( new File( "leftArrow.png" ) );
//            backArrow = ImageIO.read( new File( "backArrow.png" ) );
//            sunBall = ImageIO.read( new File( "sunImage.png" ) );
//            moonBall = ImageIO.read( new File( "moonImage.png" ) );
//            earthBall = ImageIO.read( new File( "earthImage.png" ) );
//            equipImage = ImageIO.read( new File( "equipButton.png" ) );
        }
        
        catch( IOException exception ){}
    }
    
    public void addButtons()
    {
        rightButton = new JButton( new ImageIcon( rightArrow.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        leftButton = new JButton( new ImageIcon( leftArrow.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        backButton = new JButton( new ImageIcon( backArrow.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        equipButton = new JButton( new ImageIcon( equipImage.getScaledInstance( 150, 100, BufferedImage.TYPE_INT_ARGB ) ) );
        
        rightButton.setBounds(  BACKGROUND_WIDTH - 125, 250, 50, 50 );
        leftButton.setBounds( 75, 250, 50, 50 );
        backButton.setBounds( 20, 20, 50, 50 );
        equipButton.setBounds( 375, 465, 150, 50 );
        
        rightButton.setBorderPainted( false );
        leftButton.setBorderPainted( false );
        backButton.setBorderPainted( false );
        equipButton.setBorderPainted( false );
        
        rightButton.addActionListener( new ArrowListener() );
        leftButton.addActionListener( new ArrowListener() );
        backButton.addActionListener( new BackButtonListener() );
        equipButton.addActionListener( new EquipListener());
        
        add( rightButton );
        add( leftButton );
        add( backButton );
        add( equipButton );
    }
    
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        
        g.drawImage( background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null );
        g.drawImage( storeLabel, 250, 25, 400, 100, null );
        
        if ( store.getBalls().get( index ) instanceof SunBall )
        {
            g.drawImage( earthBall, 180, 250, 100, 100, null );
            g.drawImage( sunBall, 300, 150, 300, 300, null );
            g.drawImage( moonBall, 620, 250 , 100, 100, null );
        }
        else if ( store.getBalls().get( index ) instanceof EarthBall )
        {
            g.drawImage( moonBall, 180, 250, 100, 100, null );
            g.drawImage( earthBall, 300, 150, 300, 300, null );
            g.drawImage( sunBall, 620, 250 , 100, 100, null );
        }
        else if ( store.getBalls().get( index ) instanceof MoonBall )
        {
            g.drawImage( sunBall, 180, 250, 100, 100, null );
            g.drawImage( moonBall, 300, 150, 300, 300, null );
            g.drawImage( earthBall, 620, 250 , 100, 100, null );
        }
        
        g.setFont( new Font( "SansSerif", Font.BOLD, 20 ) );
        g.setColor( Color.WHITE );
        g.drawString( "PRICE: " + store.getBalls().get( index ).getPrice(), 390, 540 );
    }
    
    private class BackButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent event )
        {
            Main.setPanel( new MainMenuPanel() );
        }
    }
    
    private class ArrowListener implements ActionListener
    {
        public void actionPerformed( ActionEvent event )
        {
            if ( event.getSource() == rightButton )
            {
                if ( index == store.getBalls().size() - 1 )
                {
                    index = 0;
                }
                else
                {
                    index = index + 1;
                }
                repaint();
            }
            else if ( event.getSource() == leftButton )
            {
                if ( index == 0 )
                {
                    index = store.getBalls().size() - 1;
                }
                else
                {
                    index = index - 1;
                }
                repaint();
            }

        }
    }

    private class EquipListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            System.out.println("Equip Button");
            System.out.println("Set to: " + store.getBalls().get(index).getClass().toString());

            Ball toSet = store.getBalls().get(index);
            if(Main.saveGame.getPlayer().hasBall(toSet) || true) //debug statement
            {
                toSet.setOwned(true);
                Main.saveGame.getPlayer().setBall(toSet);
            }
            else if(Main.saveGame.getPlayer().canSpend(toSet.getPrice()))
            {
                toSet.setOwned(true);
                Main.saveGame.getPlayer().spendMoney(toSet.getPrice());
                Main.saveGame.getPlayer().setBall(toSet);
            }
            Main.saveGame.saveGame();

            System.out.println(Main.saveGame.getPlayer().currentBall().getClass().toString());
        }
    }
}

