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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GamePanel extends JPanel 
{
    //Constants
    private static final int BACKGROUND_WIDTH = 900;
    private static final int BACKGROUND_HEIGHT = 600;
    
    //Properties
    private Image background;
    private BufferedImage backArrow;
    private BufferedImage pauseIcon;
    private JButton backButton;
    private JButton pauseButton;
    private Ball mainBall;
    private ArrayList<Laser> lasers;
    private Visuals painter;
    private double motion;
    private Timer motionTimer;
    private Timer gameTimer;
    private int delay;
    private int playAgain;
    private int resume;
    
    public GamePanel()
    {        
        setLayout( null );
        
        addImages();
        
        addButtons();
        
        mainBall = new Ball( 0, 20, 50 );
        lasers = new ArrayList<Laser>();
        painter = new Visuals();
        motion = 1.5;
        addKeyListener( new BallListener() );
        motionTimer = new Timer( 16, new MotionListener() );
        motionTimer.start();
        delay = 1000;
        gameTimer = new Timer( delay, new TimerListener() );
        gameTimer.start();
    }
    
    public void addImages()
    {
        try
        {
            background = ImageIO.read( new File( "gameBackground.png" ) );
            backArrow = ImageIO.read( new File( "backArrow.png" ) );
            pauseIcon = ImageIO.read( new File( "pauseIcon.png" ) );
        }
        
        catch( IOException exception ){}
    }
    
    public void addButtons()
    {
        backButton = new JButton( new ImageIcon( backArrow.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        pauseButton = new JButton( new ImageIcon( pauseIcon.getScaledInstance( 50, 50, BufferedImage.TYPE_INT_ARGB ) ) );
        
        backButton.setBounds( 20, 20, 50, 50 );
        pauseButton.setBounds( BACKGROUND_WIDTH - 70, 20, 50, 50 );
        
        backButton.setBorderPainted( false );
        pauseButton.setBorderPainted( false );
        
        backButton.addActionListener( new BackButtonListener() );
        pauseButton.addActionListener( new PauseButtonListener() );
        
        add( backButton );
        add( pauseButton );
    }
    
    public void restartGame()
    {
        Main.setPanel( new GamePanel() );
    }
    
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        
        g.drawImage( background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null );
        
        painter.drawBall( mainBall, g );
        
        for ( Laser toDraw: lasers )
        {
            painter.drawLaser( toDraw, g );
        }  
        requestFocusInWindow(true);
    }
    
    public void pause()
    {
        gameTimer.stop();
        motionTimer.stop();
    }
    
    public void resume()
    {
        gameTimer.start();
        motionTimer.start();
    }
    
    private class PauseButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent event )
        {
            pause();
            resume = JOptionPane.showConfirmDialog( GamePanel.this, "Game is paused! Resume?", 
                                                   "ESCAPE ~ Paused", 0 );
            if ( resume == 0 )
            {
                resume();
            }
        }
    }
    
    private class BackButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent event )
        {
            Main.setPanel( new MainMenuPanel() );
        }
    }
    
    //Inner class
    private class BallListener extends KeyAdapter
    {
        @Override
        public void keyPressed( KeyEvent event )
        {
            if ( event.getKeyCode() == KeyEvent.VK_RIGHT )
            {
                mainBall.accelerate( 'r', motion ); 
            }
            else if ( event.getKeyCode() == KeyEvent.VK_LEFT )
            {
                mainBall.accelerate( 'l', motion );
            }
            else if ( event.getKeyCode() == KeyEvent.VK_UP )
            {
                mainBall.accelerate( 'u', motion );
            }
            else if ( event.getKeyCode() == KeyEvent.VK_DOWN )
            {
                mainBall.accelerate( 'd', motion );
            }
            else
            {
                mainBall.accelerate( 'e', motion );
            }
            //mainBall.move();
            repaint();
        }
    } 
    
    //Inner class
    private class MotionListener implements ActionListener
    {
        @Override
        public void actionPerformed( ActionEvent event ){
            {
                for ( Laser check : lasers )
                {
                    if ( check.isTouched( mainBall ) ) 
                    {
                        pause();
                        playAgain = JOptionPane.showConfirmDialog( GamePanel.this, "Game Over! Play again?", 
                                                                  "ESCAPE ~ Game over", 0 );
                        if ( playAgain == 0 )
                        {
                            restartGame();
                        }
                        else
                        {
                            Main.setPanel( new MainMenuPanel() );
                        }
                    }
                }
            }
            mainBall.move();
            repaint();
        }
    }  
    
    //Inner class
    private class TimerListener implements ActionListener
    {
        @Override
        public void actionPerformed( ActionEvent event )
        {
            lasers.add( new Laser() );
            repaint();
            if ( delay >= 100 ) 
            {
                delay = delay - 1;
            }
            for ( int i = 0; i < lasers.size(); i++ )
            {
                lasers.get( i ).incrementTimeAlive( 1 );
                if ( !lasers.get( i ).isAlive() )
                {
                    lasers.remove( i );
                } 
            }
            Laser.incrementTimeToLive( 0.3 );
        }
    }    
}

