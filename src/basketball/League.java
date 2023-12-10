
package basketball;

import java.util.ArrayList;


public class League {
    
    private int year;
    private ArrayList<Team> teams; // player team goes in here. if playerTeam = this then show player playoffs screen

    public League() {
        year = 2020;
        teams = new ArrayList<>();
    }

    // fill up the league here with the teamcreator class
    public void fillUpLeague(){
        
    }
    
    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }
    
    
}
