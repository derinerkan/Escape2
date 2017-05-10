import java.util.ArrayList;
import java.io.Serializable;

/**
 * Leaderboard class for Escape game
 * @author Escape
 * @version 1.0
 */
public class Leaderboard implements Serializable
{
    ArrayList<Player> list;
    final int SIZE = 10;

    public Leaderboard()
    {
        list = new ArrayList<Player>();
    }
    /**
     * It adds the player to the leaderboard list with the correct place the user belongs.
     * @param toAdd the player to add
     */
    public void addPlayer(Player toAdd)
    {
        System.err.println("Entered add player subroutine");
        System.err.println(canAdd(toAdd));

        if(list.isEmpty()) list.add(toAdd);
        System.err.println(this.toString());

        if(canAdd(toAdd)) //check if there is room for this player to enter the leaderboards
        {
            for (Player p : list)
            {
                if (toAdd.getHighScore() >= p.getHighScore())
                {
                    list.add(list.indexOf(p), toAdd);
                    if(list.size() > SIZE) list.remove(SIZE);
                    return;
                }
            }
        }
    }

    /**
     * Checks if it can add the player to leaderboard.
     * @param toAdd the player to add
     * @return true if the player can be added
     */
    public boolean canAdd(Player toAdd)
    {
        if(list.isEmpty() || list.size() < SIZE) return true;
        return toAdd.getScore() > list.get(list.size() - 1).getScore();
    }

    /**
     * Returns an ArrayList<String of players on the leaderboard
     * @return the players on the list
     */
    public ArrayList<String> printMethod()
    {
        ArrayList<String> output = new ArrayList<String>();
        for(Player i: list)
        {
            output.add(i.getName() + "    " + i.getScore());
        }
        return output;
    }

    /**
     * Returns a string representation of this Leaderboard
     * @return a string
     */
    public String toString()
    {
        StringBuffer output = new StringBuffer("");
        for(String str : printMethod()) output.append(str).append("\n");
        return output.toString();
        //return "TEST YARRAK";
    }

}