
package basketball;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class League {
    
    private int year;
    private ArrayList<Team> teams; // player team goes in here. if playerTeam = this then show player playoffs screen
    private ArrayList<Team> playoffTeams;
    private String stage; // Season, Playoffs, Resigning, Draft, Free agency, Training, Goto new season
    
    private ArrayList<Player> allActivePlayers; // includes all players on a team
    private ArrayList<PlayoffMatchup> playoffMatchups;
    
    public League() {
        year = 2020;
        teams = new ArrayList<>();
        allActivePlayers = new ArrayList<>();
        playoffTeams = new ArrayList<>();
        playoffMatchups = new ArrayList<>();
        fillUpLeague();
        fillActivePlayers();
        stage = "Season";
    }
    
    /**
     * This method should only be called after all the lineups have been set. Called again after the player makes changes to their own lineup.
     * Active players is used to sort the stats of players by different categories.
     */
    private void fillActivePlayers(){
        allActivePlayers.clear();
        for(Team t : teams){
            for(int k=0;k<5;k++){
                allActivePlayers.add(t.getSquad()[k]);
                allActivePlayers.add(t.getBench()[k]);
            }
        }
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
    
    public void sortTeamsByWins(){
        Collections.sort(teams, Comparator.comparingInt(Team::getWins).reversed());
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
    }
    
    public void simulateSeason() {
        int numTeams = teams.size();
        int numRounds = numTeams - 1;

        for (int round = 0; round < numRounds; round++) {
            for (int teamIndex = 0; teamIndex < numTeams / 2; teamIndex++) {
                Team t1 = teams.get(teamIndex);
                Team t2 = teams.get(numTeams - 1 - teamIndex);

                // Simulate the game between team1 and team2
                
                for (int gameNumber = 0; gameNumber < 2; gameNumber++) {
                    Game game = new Game(t1,t2);
                    game.simGame();
                    if(t1.getGameScore()>=t2.getGameScore()){
                        t1.setWins(t1.getWins()+1);
                        t2.setLosses(t2.getLosses()+1);
                    }else{
                        t2.setWins(t2.getWins()+1);
                        t1.setLosses(t1.getLosses()+1);
                    }
                }
                
            }

            // Rotate the teams in the list for the next round
            teams.add(1, teams.remove(teams.size() - 1));
        }
    }
    
    public void createPlayoffTeamsList(){
        playoffTeams.clear();
        sortTeamsByWins();
        for(int k=0;k<8;k++){
            playoffTeams.add(teams.get(k));
        }
        
        for(Team t : playoffTeams){
            System.out.println("Team in the playoffs: " + t.getName());
        }
    }
    
    public void simPlayoffs(){
        // 1st round, 8 teams, 4 matchups
        System.out.println("\n\n\n");
        int playoffTeamsSize = playoffTeams.size();
        playoffMatchups.clear();
        
        ArrayList<Team> deadPlayoffTeams = new ArrayList<>();
        
        for(int i=4;i>0;i/=2){
            for(Team t : playoffTeams){
                System.out.println("Team in the playoffs: " + t.getName());
            }
            for(int k=0;k<i;k++){
                int t1Wins = 0;
                int t2Wins = 0;
                Team t1 = playoffTeams.get(k);
                Team t2 = playoffTeams.get(playoffTeamsSize-1-k);

                System.out.println("\n\n" + (k+1) + " round matchup between: " + t1.getName() + " and " + t2.getName());

                do{
                    Game game = new Game(t1,t2);
                    game.simGame();

                    if(t1.getGameScore()>=t2.getGameScore()){
                        t1Wins++;
                    }else{
                        t2Wins++;
                    }
                }while(t1Wins<4 && t2Wins<4);
                
                System.out.println("Series score\n" + t1.getName() + " " + t1Wins + " - " + t2.getName() + " " + t2Wins);
                if(t1Wins>=4){
                    System.out.println(t1.getName() + " has won and is advancing!");
                    deadPlayoffTeams.add(t2);
                }else{
                    System.out.println(t2.getName() + " has won and is advancing!");
                    deadPlayoffTeams.add(t1);
                }
                
                PlayoffMatchup pm = new PlayoffMatchup(t1,t2,t1Wins,t2Wins);
                playoffMatchups.add(pm);
            }
            for(Team t : deadPlayoffTeams){
                if(playoffTeams.contains(t)){
                    playoffTeams.remove(t);
                }
            }
            playoffTeamsSize = playoffTeams.size();
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

    public ArrayList<Team> getPlayoffTeams() {
        return playoffTeams;
    }

    public void setPlayoffTeams(ArrayList<Team> playoffTeams) {
        this.playoffTeams = playoffTeams;
    }

    public ArrayList<Player> getAllActivePlayers() {
        return allActivePlayers;
    }

    public void setAllActivePlayers(ArrayList<Player> allActivePlayers) {
        this.allActivePlayers = allActivePlayers;
    }

    public ArrayList<PlayoffMatchup> getPlayoffMatchups() {
        return playoffMatchups;
    }

    public void setPlayoffMatchups(ArrayList<PlayoffMatchup> playoffMatchups) {
        this.playoffMatchups = playoffMatchups;
    }
    
    
    
}
