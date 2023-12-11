
package basketball;

import java.util.ArrayList;


public class League {
    
    private int year;
    private ArrayList<Team> teams; // player team goes in here. if playerTeam = this then show player playoffs screen
    private String stage; // Season, Playoffs, Resigning, Draft, Free agency, Training, Goto new season

    public League() {
        year = 2020;
        teams = new ArrayList<>();
        fillUpLeague();
        stage = "Season";
    }

    // fill up the league here with the teamcreator class
    private void fillUpLeague(){
        TeamCreator tc = new TeamCreator();
        for(int k=0;k<29;k++){
            Team t = tc.generateRandomTeam();
            t.autoSortLineups();
            teams.add(t);
        }
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

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
    
    
    
}
