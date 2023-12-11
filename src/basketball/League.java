
package basketball;

import java.util.ArrayList;
import java.util.List;


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
            Team t;
            do{
                t = tc.generateRandomTeam();
                t.autoSortLineups();
            }while(doesTeamNameExistInTheLeague(t));
            teams.add(t);
        }
    }
    
    private boolean doesTeamNameExistInTheLeague(Team t){
        for(Team teamInLeague : teams){
            if(teamInLeague.getName().equalsIgnoreCase(t.getName())){
                return true;
            }
        }
        return false;
    }
    
    public void generateSchedule() {
        int numTeams = teams.size();
        int numRounds = numTeams - 1;

        for (int round = 0; round < numRounds; round++) {
            for (int teamIndex = 0; teamIndex < numTeams / 2; teamIndex++) {
                Team team1 = teams.get(teamIndex);
                Team team2 = teams.get(numTeams - 1 - teamIndex);

                // Update the schedule for both teams
                team1.getSchedule().add(team2);
                team2.getSchedule().add(team1);
            }

            // Rotate the teams in the list for the next round
            teams.add(1, teams.remove(teams.size() - 1));
        }

        // Display the schedule
        for (Team team : teams) {
            System.out.println("Schedule for " + team.getName() + ":");
            List<Team> teamSchedule = team.getSchedule();
            for (Team opponent : teamSchedule) {
                System.out.println(opponent.getName());
            }
            System.out.println();
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
