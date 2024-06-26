
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
    private ArrayList<Player> freeAgents;
    private ArrayList<PlayoffMatchup> playoffMatchups;
    private ArrayList<Player> draftees;
    
    private ArrayList<Team> freeAgencyOrder;
    
    private Team enemyPlayoffTeam;
    
    public League() {
        year = 2020;
        teams = new ArrayList<>();
        allActivePlayers = new ArrayList<>();
        playoffTeams = new ArrayList<>();
        playoffMatchups = new ArrayList<>();
        freeAgents = new ArrayList<>();
        fillUpLeague();
        fillActivePlayers();
        stage = "Season";
        draftees = new ArrayList<>();
        freeAgencyOrder = new ArrayList<>();
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
    
    public void addFillerPlayersToTeams(){
        NewPlayerCreator npc = new NewPlayerCreator();
        String pos;
        
        for(Team t : teams){
            
            for(int k=0;k<5;k++){
                if(t.getSquad()[k] == null){
                    System.out.println("null player found for " + t.getName() + " - filling in");
                    if(k==0){
                        pos = "PG";
                    }else if(k==1){
                        pos = "SG";
                    }else if(k==2){
                        pos = "SF";
                    }else if(k==3){
                        pos = "PF";
                    }else{
                        pos = "C";
                    }
                    
                    Player p = npc.createFillerPlayer(pos);
                    t.getSquad()[k] = p;
                }
            }
            
            for(int k=0;k<5;k++){
                if(t.getBench()[k] == null){
                    System.out.println("null player found for " + t.getName() + " - filling in!");
                    if(k==0){
                        pos = "PG";
                    }else if(k==1){
                        pos = "SG";
                    }else if(k==2){
                        pos = "SF";
                    }else if(k==3){
                        pos = "PF";
                    }else{
                        pos = "C";
                    }
                    
                    Player p = npc.createFillerPlayer(pos);
                    t.getBench()[k] = p;
                }
            }
            
        }
    }
    
    public void addTeamsToFreeAgencyOrderRandomly() {
        if (teams == null || freeAgencyOrder == null) {
            System.out.println("Teams or Free Agency Order list not initialized.");
            return;
        }

        Collections.shuffle(teams);
        freeAgencyOrder.addAll(teams);
    }
    
    public void sortTeamsByWins(){
        Collections.sort(teams, Comparator.comparingInt(Team::getWins).reversed());
    }
    
    public void sortTeamsByLosses(){
        Collections.sort(teams, Comparator.comparingInt(Team::getWins));
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
        
        int seed = 1;
        for(Team t : playoffTeams){
            System.out.println("SEED " + seed + " - " + t.getName());
            seed++;
        }
    }
    
    public void simPlayoffRound(Team playerTeam){
        // 1st round, 8 teams, 4 matchups
        System.out.println("\n\n\n");
        int playoffTeamsSize = playoffTeams.size();
        
        
        ArrayList<Team> deadPlayoffTeams = new ArrayList<>();
        
        int numMatchups = playoffTeamsSize / 2; // Number of matchups in the first round

        for (Team t : playoffTeams) {
            System.out.println("Team in the playoffs: " + t.getName());
        }

        for (int k = 0; k < numMatchups; k++) {
            int t1Wins = 0;
            int t2Wins = 0;
            Team t1 = playoffTeams.get(k);
            Team t2 = playoffTeams.get(playoffTeamsSize-1-k);

            if(t1.equals(playerTeam)){
                enemyPlayoffTeam = t2;
                continue;
            }
            if(t2.equals(playerTeam)){
                enemyPlayoffTeam = t1;
                continue;
            }

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
    
    public void simPlayoffs(){
        // 1st round, 8 teams, 4 matchups
        System.out.println("\n\n\n");
        int playoffTeamsSize = playoffTeams.size();
        
        
        ArrayList<Team> deadPlayoffTeams = new ArrayList<>();
        
        // Determine the number of rounds based on the size of the playoff teams
        int rounds = (int) (Math.log(playoffTeamsSize) / Math.log(2));
    
        for (int round = rounds; round > 0; round--) {
            for(Team t : playoffTeams){
                System.out.println("Team in the playoffs: " + t.getName());
            }
            
            int matchups = playoffTeamsSize / 2;
            for(int k=0;k<matchups;k++){
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

    public ArrayList<Player> getFreeAgents() {
        return freeAgents;
    }

    public void setFreeAgents(ArrayList<Player> freeAgents) {
        this.freeAgents = freeAgents;
    }

    public ArrayList<Player> getDraftees() {
        return draftees;
    }

    public void setDraftees(ArrayList<Player> draftees) {
        this.draftees = draftees;
    }

    public ArrayList<Team> getFreeAgencyOrder() {
        return freeAgencyOrder;
    }

    public void setFreeAgencyOrder(ArrayList<Team> freeAgencyOrder) {
        this.freeAgencyOrder = freeAgencyOrder;
    }

    public Team getEnemyPlayoffTeam() {
        return enemyPlayoffTeam;
    }

    public void setEnemyPlayoffTeam(Team enemyPlayoffTeam) {
        this.enemyPlayoffTeam = enemyPlayoffTeam;
    }
    
    
    
}
