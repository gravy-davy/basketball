
package basketball;

import java.util.ArrayList;


// Loop thru this list like it's a roster and show it on it's own screen in the player details panel
public class YearlyStats {
    
    private int year;
    
    private Team team;
    private int overallRating;
    private int age;
    private ArrayList<Award> awards; // MVP, All-League PG/SG/SF/PF/C (by player value), DBA Champion
    private double fieldGoalPercentage;
    private double threePointPercentage;
    private double averagePointsPerGame;
    private double averageAssistsPerGame;
    private double averageReboundsPerGame;
    private double averageOffensiveReboundsPerGame;
    private double averageDefensiveReboundsPerGame;
    
    public String generateStatline(){
        String stats = String.format("%d - %.20s - PPG: %.2f - APG: %.2f - RPG: %.2f - ORPG: %.2f - DRPG: %.2f - FG%%: %.2f - 3FG%%: %.2f",
            year, team.getName(), averagePointsPerGame, averageAssistsPerGame, averageReboundsPerGame,
            averageOffensiveReboundsPerGame, averageDefensiveReboundsPerGame, fieldGoalPercentage, threePointPercentage);
        return stats;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getFieldGoalPercentage() {
        return fieldGoalPercentage;
    }

    public void setFieldGoalPercentage(double fieldGoalPercentage) {
        this.fieldGoalPercentage = fieldGoalPercentage;
    }

    public double getThreePointPercentage() {
        return threePointPercentage;
    }

    public void setThreePointPercentage(double threePointPercentage) {
        this.threePointPercentage = threePointPercentage;
    }

    public double getAveragePointsPerGame() {
        return averagePointsPerGame;
    }

    public void setAveragePointsPerGame(double averagePointsPerGame) {
        this.averagePointsPerGame = averagePointsPerGame;
    }

    public double getAverageAssistsPerGame() {
        return averageAssistsPerGame;
    }

    public void setAverageAssistsPerGame(double averageAssistsPerGame) {
        this.averageAssistsPerGame = averageAssistsPerGame;
    }

    public double getAverageReboundsPerGame() {
        return averageReboundsPerGame;
    }

    public void setAverageReboundsPerGame(double averageReboundsPerGame) {
        this.averageReboundsPerGame = averageReboundsPerGame;
    }

    public double getAverageOffensiveReboundsPerGame() {
        return averageOffensiveReboundsPerGame;
    }

    public void setAverageOffensiveReboundsPerGame(double averageOffensiveReboundsPerGame) {
        this.averageOffensiveReboundsPerGame = averageOffensiveReboundsPerGame;
    }

    public double getAverageDefensiveReboundsPerGame() {
        return averageDefensiveReboundsPerGame;
    }

    public void setAverageDefensiveReboundsPerGame(double averageDefensiveReboundsPerGame) {
        this.averageDefensiveReboundsPerGame = averageDefensiveReboundsPerGame;
    }

    public int getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(int overallRating) {
        this.overallRating = overallRating;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public ArrayList<Award> getAwards() {
        return awards;
    }

    public void setAwards(ArrayList<Award> awards) {
        this.awards = awards;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    
    
}
