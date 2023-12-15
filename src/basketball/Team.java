
package basketball;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Team {
    
    private String name;
    private ArrayList<Player> roster; // everyone not in the bench or game squad
    private Player[] squad; // dudes in the game right now.
    private Player[] bench;
    private ArrayList<Player> incomingFreeAgents;
    private int gameScore;
    private int wins;
    private int losses;
    
    // if team doesnt have enough moneyTotal to sign all their players in the very beginning of league init, increase it till they can afford it.
    private int moneyTotal; // gonna just be a whole number 75-200 or sumfin. players will get paid like 24 but put an m next to it for 24m
    private int moneyAvailable; // set to money total after the playoffs, reduced by: resigning, drafted rookies, free agents

    private ArrayList<Team> schedule;
    
    public Team() {
        roster = new ArrayList<>();
        squad = new Player[5];
        bench = new Player[5];
        incomingFreeAgents = new ArrayList<>();
        gameScore = 0;
        wins = 0;
        losses = 0;
        
        name = "";
        moneyTotal = 0;
        moneyAvailable = 0;
        schedule = new ArrayList<>();
    }
    
    public String getTeamInfo() {
        String text = String.format("%s - Wins: %d, Losses: %d<br>", name, wins, losses);
        return text;
    }
    
    public void autoSortLineups(){
        
        for(int k=0;k<4;k++){
            if(squad[k]!=null){
                roster.add(squad[k]);
                squad[k] = null;
            }
        }
        
        for(int k=0;k<4;k++){
            if(bench[k]!=null){
                roster.add(bench[k]);
                bench[k] = null;
            }
        }
        
        Collections.sort(roster, Comparator.comparingInt(Player::getOverallRating));
        Collections.reverse(roster);
        
        ArrayList<Player> playersToRemoveFromRoster = new ArrayList<>();
        
        for (Player player : roster) {
            switch (player.getPosition()) {
                case "PG":
                    if (squad[0] == null) {
                        squad[0] = player; 
                        playersToRemoveFromRoster.add(player);
                    } else if (bench[0] == null) {
                        bench[0] = player; 
                        playersToRemoveFromRoster.add(player);
                    }
                    // Other PGs can stay in the roster
                    break;

                case "SG":
                    if (squad[1] == null) {
                        squad[1] = player; 
                        playersToRemoveFromRoster.add(player);
                    } else if (bench[1] == null) {
                        bench[1] = player; 
                        playersToRemoveFromRoster.add(player);
                    }
                    // Other SGs can stay in the roster
                    break;

                case "SF":
                    if (squad[2] == null) {
                        squad[2] = player; // Assign the highest overall SF to squad[2]
                        playersToRemoveFromRoster.add(player);
                    } else if (bench[2] == null) {
                        bench[2] = player; // Assign the second-highest overall SF to bench[2]
                        playersToRemoveFromRoster.add(player);
                    }
                    // Other SFs can stay in the roster
                    break;

                case "PF":
                    if (squad[3] == null) {
                        squad[3] = player; 
                        playersToRemoveFromRoster.add(player);
                    } else if (bench[3] == null) {
                        bench[3] = player; 
                        playersToRemoveFromRoster.add(player);
                    }
                    // Other PFs can stay in the roster
                    break;

                case "C":
                    if (squad[4] == null) {
                        squad[4] = player; 
                        playersToRemoveFromRoster.add(player);
                    } else if (bench[4] == null) {
                        bench[4] = player; 
                        playersToRemoveFromRoster.add(player);
                    }
                    // Other Cs can stay in the roster
                    break;

                default:
                    // Handle other positions if necessary
            }
        }
        
        for(Player p : playersToRemoveFromRoster){
            if(roster.contains(p)){
                roster.remove(p);
            }
        }
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

    public Player[] getBench() {
        return bench;
    }

    public void setBench(Player[] bench) {
        this.bench = bench;
    }

    public int getMoneyTotal() {
        return moneyTotal;
    }

    public void setMoneyTotal(int moneyTotal) {
        this.moneyTotal = moneyTotal;
    }

    public int getMoneyAvailable() {
        return moneyAvailable;
    }

    public void setMoneyAvailable(int moneyAvailable) {
        this.moneyAvailable = moneyAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Team> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<Team> schedule) {
        this.schedule = schedule;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public ArrayList<Player> getIncomingFreeAgents() {
        return incomingFreeAgents;
    }

    public void setIncomingFreeAgents(ArrayList<Player> incomingFreeAgents) {
        this.incomingFreeAgents = incomingFreeAgents;
    }
    
    
    
}
