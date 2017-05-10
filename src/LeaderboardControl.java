import javax.swing.*;

/**
 * Controls the leaderboards in the Main class using static methods called from elsewhere.
 * @author Derin Erkan
 * @version 2017-05-10
 */
public class LeaderboardControl
{
    /**
     * Ends the game, opening dialog boxes for leaderboards or playing again, depending
     * on how the player scored
     * @param toCheck the player to be checked
     * @return 0 if the player wants to play again
     */
    public static int endGame(Player toCheck)
    {
        if(Main.saveGame.leaderboard.canAdd(toCheck)) addToLeaderboard(toCheck);

        return JOptionPane.showConfirmDialog( Main.getMainFrame(), "Game Over! Play again?\nScore: " +
                    Main.saveGame.player.getScore(), "ESCAPE ~ Game over", 0 );
        //return wantToPlay;
    }

    /**
     * If the player is eligible to enter the leaderboard, enter them
     * @param toEnter the player to be entered
     */
    public static void addToLeaderboard(Player toEnter)
    {
        //JFrame toDisp = Main.getMainFrame(); //the JFrame to display the option pane on
        String name = JOptionPane.showInputDialog("Congratulations! You scored a high score");
        toEnter.setName(name);
        Main.saveGame.leaderboard.addPlayer(toEnter);
        Main.saveGame.saveGame();
    }
}