import java.io.*;

/**
 * @author Derin Erkan
 * @version 2017-05-07
 */
public class SaveGame
{
    private Player player;
    private Leaderboard leaderboard;
    private final String pFilename = "player.txt";
    private final String lFilename = "leaderboard.txt";

    /**
     * Default constructor for the SaveGame. Tries to recall game data from memory,
     * but if the game data is not in memory the relevant objects' default constructors
     * are called.
     */
    public SaveGame() {
        try {
            FileInputStream pInput = new FileInputStream(pFilename);
            FileInputStream lInput = new FileInputStream(lFilename);

            ObjectInputStream pInStr = new ObjectInputStream(pInput);
            ObjectInputStream lInStr = new ObjectInputStream(lInput);

            player = (Player) pInStr.readObject();
            leaderboard = (Leaderboard) lInStr.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            player = new Player();
            leaderboard = new Leaderboard();
            try {
                if (!new File(pFilename).exists()) new File(pFilename).createNewFile();
                if (!new File(lFilename).exists()) new File(lFilename).createNewFile();
            }
            catch(Exception ex){}
        }
    }

    /**
     * Saves the game if it can.
     * @return true if it can
     */
    public boolean saveGame()
    {
        try
        {
            ObjectOutputStream pOutput = new ObjectOutputStream(new FileOutputStream(pFilename));
            ObjectOutputStream sOutput = new ObjectOutputStream(new FileOutputStream(lFilename));

            pOutput.writeObject(player);
            sOutput.writeObject(leaderboard);

            pOutput.close();
            sOutput.close();
            return true;
        }
        catch(Exception e)
        {
            File pFile = new File(pFilename);
            File lFile = new File(lFilename);
            try
            {
                pFile.createNewFile(); //attempt to create a new file, maybe there isn't one
                lFile.createNewFile(); //ditto
            }
            catch(IOException ex){ex.printStackTrace();}

            e.printStackTrace();
            return false;
        }


    }

    /**
     * Get the player retrieved from the savegame
     * @return the player
     */
    public Player getPlayer() {return player;}

    /**
     * Get the leaderboards retrieved from the savegame
     * @return the leaderboards
     */
    public Leaderboard getLeaderboard() {return leaderboard;}
}