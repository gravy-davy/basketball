
package basketball;

import java.util.ArrayList;


public class Team {
    
    private ArrayList<Player> roster; // everyone not in the bench or game squad
    private Player[] squad; // dudes in the game right now.
    private ArrayList<Player> bench;
    
    private int gameScore;

    public Team() {
        roster = new ArrayList<>();
        squad = new Player[5];
        gameScore = 0;
    }

    public ArrayList<Player> getRoster() {
        return roster;
    }

    public void setRoster(ArrayList<Player> roster) {
        this.roster = roster;
    }

    public Player[] getSquad() {
        return squad;
    }

    public void setSquad(Player[] squad) {
        this.squad = squad;
    }

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }

    public ArrayList<Player> getBench() {
        return bench;
    }

    public void setBench(ArrayList<Player> bench) {
        this.bench = bench;
    }
    
}
