
package basketball;

import java.util.ArrayList;


public class Team {
    
    private ArrayList<Player> roster;
    private ArrayList<Player> squad; // dudes in the game right now.
    
    private int gameScore;

    public ArrayList<Player> getRoster() {
        return roster;
    }

    public void setRoster(ArrayList<Player> roster) {
        this.roster = roster;
    }

    public ArrayList<Player> getSquad() {
        return squad;
    }

    public void setSquad(ArrayList<Player> squad) {
        this.squad = squad;
    }

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }
    
    
    
}
