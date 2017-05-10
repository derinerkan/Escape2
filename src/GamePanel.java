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
import java.awt.Color;
import java.util.Arrays;
import java.awt.Color;
import java.awt.Font;

public class GamePanel extends JPanel
{
    //Constants
    private static final int BACKGROUND_WIDTH = Main.getFrameWidth();
    private static final int BACKGROUND_HEIGHT = Main.getFrameHeight();
    private static final float ACC_AMOUNT = 0.4f;
    
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
    private Timer scoreTimer;
    private int delay;
    private int playAgain;
    private int resume;
    private double score;
    private boolean[] pressedKeyList;
    private Point dirVector;
    
    public GamePanel()
    {
        setLayout( null );
        
        addImages();
        
        addButtons();
        
        //keyboard control declarations
        pressedKeyList = new boolean[]{ false, false, false,false};
        dirVector = new Point( 0, 0);
        
        //mainBall = new EarthBall(); //deprecated debug statement
        mainBall = Main.saveGame.getPlayer().currentBall();

        lasers = new ArrayList<Laser>();
        painter = new Visuals();
        motion = 1.5;
        addKeyListener( new BallListener() );
        motionTimer = new Timer( 16, new MotionListener() );
        motionTimer.start();
        delay = 1000;
        gameTimer = new Timer( delay, new TimerListener() );
        gameTimer.start();
        score = 0;
        scoreTimer = new Timer( 100, new ScoreListener() );
        scoreTimer.start();
    }
    
    public void addImages()
    {
        try
        {
            background = ImageIO.read( new File( "images/gameBackground.png" ) );
            backArrow = ImageIO.read( new File( "images/backArrow.png" ) );
            pauseIcon = ImageIO.read( new File( "images/pauseIcon.png" ) );

//            background = ImageIO.read( new File( "gameBackground.png" ) );
//            backArrow = ImageIO.read( new File( "backArrow.png" ) );
//            pauseIcon = ImageIO.read( new File( "pauseIcon.png" ) );
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
        
        g.setFont( new Font( "SansSerif", Font.BOLD, 20 ) );
        g.setColor( Color.WHITE );
        g.drawString( "SCORE: " + score / 100.0, 395, 30 );
        
        painter.drawBall( mainBall, g );
        
        for ( Laser toDraw: lasers )
        {
            painter.drawLaser( toDraw, g );
        }  
        requestFocusInWindow( true );
    }
    
    public void pause()
    {
        gameTimer.stop();
        motionTimer.stop();
        scoreTimer.stop();
    }
    
    public void resume()
    {
        gameTimer.start();
        motionTimer.start();
        scoreTimer.start();
    }
    
    private class PauseButtonListener implements ActionListener
    {
        public void actionPerformed( ActionEvent event )
        {
            pause();
            resume = JOptionPane.showConfirmDialog( GamePanel.this, "Game is paused! Resume?\nCurrent score: " 
                                                       + score / 100.0, "ESCAPE ~ Paused", 0 );
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
            pause();
            Main.setPanel( new MainMenuPanel() );
        }
    }
    
    //Inner class
    private class BallListener extends KeyAdapter
    {        
        @Override
        public void keyPressed( KeyEvent event )
        {
            if ( event.getKeyCode() == KeyEvent.VK_UP )
            {
                pressedKeyList[0] = true;
            }
            if ( event.getKeyCode() == KeyEvent.VK_RIGHT )
            {
                pressedKeyList[1] = true;
            }
            if ( event.getKeyCode() == KeyEvent.VK_DOWN )
            {
                pressedKeyList[2] = true;
            }
            if ( event.getKeyCode() == KeyEvent.VK_LEFT )
            {
                pressedKeyList[3] = true;
            }
        }
        
        public void keyReleased( KeyEvent event )
        {
            if ( event.getKeyCode() == KeyEvent.VK_UP )
            {
                pressedKeyList[0] = false;
            }
            if ( event.getKeyCode() == KeyEvent.VK_RIGHT )
            {
                pressedKeyList[1] = false;
            }
            if ( event.getKeyCode() == KeyEvent.VK_DOWN )
            {
                pressedKeyList[2] = false;
            }
            if ( event.getKeyCode() == KeyEvent.VK_LEFT )
            {
                pressedKeyList[3] = false;
            }
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
                        playAgain = LeaderboardControl.endGame(Main.saveGame.getPlayer());
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
            updateDirectionVector();
            mainBall.accelerate( dirVector);
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
            for ( int i = lasers.size() - 1; i >= 0; i--)
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
    
    private class ScoreListener implements ActionListener
    {
        @Override
        public void actionPerformed( ActionEvent event )
        {
            score = score + 10;
            Main.saveGame.getPlayer().updateScore( score / 100.0 );
            repaint();
        }
    }
    
    private void updateDirectionVector()
    {
        dirVector.setLocation( 0 , 0 );
        
        if( pressedKeyList[0])
        {
            dirVector.translate( 0, -1 );
        }
        if( pressedKeyList[1])
        {
            dirVector.translate( 1, 0);
        }
        if( pressedKeyList[2])
        {
            dirVector.translate( 0, 1 );
        }
        if( pressedKeyList[3])
        {
            dirVector.translate( -1, 0);
        }
        if( dirVector.getX() != 0 && dirVector.getY() != 0)
        {
            dirVector.scalarProduct( 1 / dirVector.getDistanceToOrigin() );
        }
        dirVector.scalarProduct( ACC_AMOUNT );
        
    }
}