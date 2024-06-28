
package basketball;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;


public class Player implements Comparable<Player>{
    
    // basics
    private String name;
    private int age;
    private String position;
    private int development; // 1-5
    private Contract contract;
    private int yearsInTheLeague;
    private int value; // based on age/attributes aka overall/development
    private int performanceValue; // based on actual game results
    private int freeAgentValue; // value + performanceValue
    private ImageIcon portrait;
    
    // for FA decisions
    private int FREE_AGENCY_loyalty;
    private int FREE_AGENCY_playForWinner;
    
    // what quarters they'll play in
    private ArrayList<Integer> quarters; // 1,2,4 for quarters played
    private int maxQuartersPlayable; // how many can be up there
  
    
    // basic tendies: drive (iso), pass. catchAndShoot is when receiving the ball.
    private int driveTendy; // so its drive -> type of drive (like speed/power/dribble) -> use normal tendies like shot/pass/etc after the drive
    private int dribbleDriveTendy;
    private int speedDriveTendy;
    private int powerDriveTendy;
    private int driveShootTendy; // uses normal shoot tendies
    private int drivePassTendy;
    
    private int passTendy;
    
    private int catchAndShootTendy; // tendency to catch the ball and put up a shot immediately. or just do another tendy for the player.
    
    // used for catchAndShoot, and driveShoot
    private int shootCloseTendy;
    private int shootMidTendy;
    private int shootThreeTendy;
    
    private int initTendy; // chance for player to do something
    private int offReboundTendy;
    private int defReboundTendy;
    
    // skills - 20 needed to make shots, 30 for 3s maybe.
    
    private int closeSkill;
    private int midShotSkill;
    private int threeShotSkill;
    private int passingSkill;   // adds shot bonus potentially + prevent steals
    private int driveSkill;     // uses dribbling or speed or power too
    
    private int strengthSkill; // used in conjunction with drive skill for power drives
    private int speedSkill; // used in conjunction with drive skill for speed drives
    private int dribblingSkill; // prevent ball from being stolen + used in conjunction with drive skill for dribble drives
    
    private int perimeterDefSkill;
    private int interiorDefSkill;
    private int stealSkill;
    private int interceptionSkill; // has to be way higher than a bad pass
    private int blockingSkill; // cool way to keep track of how well a player is doing on def. nullifies having to use interiorDefSkill
    private int defRebounding;
    private int offRebounding;
    
    private int overallRating;
    
    private int catchAndShootModifier; // reduction/addition to shot after catching the ball. -10 to +10
    private int driveShootModifier; // reduction/addition to shot after driving the ball. -10 to +10
    private int drivePassModifier;
    
    // these are for in-game stats. need to reset them after every game.
    private int fgMade;
    private int fgAttempted;
    private int threePointersMade;
    private int threePointersAttempted;
    private int defRebounds;
    private int offRebounds;
    private int rebounds;
    private int points;
    private int assists;
    private int steals;
    private int blocks;
    
    // these are for career stats.
    private int totalDefRebounds;
    private int totalOffRebounds;
    private int totalRebounds;
    private int totalFgMade;
    private int totalFgAttempted;
    private int totalThreePointersMade;
    private int totalThreePointersAttempted;
    private int totalPoints;
    private int totalSteals;
    private int totalBlocks;
    private int totalAssists;
    private int totalGamesPlayed;
    
    private boolean hasTrainedThisYear;
    
    private ArrayList<YearlyStats> yearlyStats;
    private ArrayList<Award> awards;
    private boolean isChampionThisYear;
    
    private String role;
    private boolean isRetiring; // only gets flicked to true when they are actually retiring

    public Player() {
        hasTrainedThisYear = false;
        yearlyStats = new ArrayList<>();
        isRetiring = false;
        awards = new ArrayList<>();
        isChampionThisYear = false;
    }
    
    public void regenInitTendy(){
        initTendy = overallRating * 2 + 1;
    }
    
    
    public void printPlayerStats() {
        System.out.print("POSITION: " + position + " - ");
        System.out.print("Field Goals Made: " + fgMade + " - ");
        System.out.print("Field Goals Attempted: " + fgAttempted + " - ");
        System.out.print("Three-Pointers Made: " + threePointersMade + " - ");
        System.out.print("Three-Pointers Attempted: " + threePointersAttempted + " - ");
        System.out.print("Defensive Rebounds: " + defRebounds + " - ");
        System.out.print("Offensive Rebounds: " + offRebounds + " - ");
        System.out.print("Total Rebounds: " + rebounds + " - ");
        System.out.print("Points: " + points + " - ");
        System.out.println("Assists: " + assists);
    }

    public void updateTotalStats() {
        // Increment total stats with in-game stats
        totalDefRebounds += defRebounds;
        totalOffRebounds += offRebounds;
        totalRebounds += rebounds;
        totalFgMade += fgMade;
        totalFgAttempted += fgAttempted;
        totalThreePointersMade += threePointersMade;
        totalThreePointersAttempted += threePointersAttempted;
        totalPoints += points;
        totalAssists += assists;
        totalGamesPlayed++;
    }
    
    public void resetGameStats() {
        fgMade = 0;
        fgAttempted = 0;
        threePointersMade = 0;
        threePointersAttempted = 0;
        defRebounds = 0;
        offRebounds = 0;
        rebounds = 0;
        points = 0;
        assists = 0;
    }
    
    public void resetTotalStats() {
        totalFgMade = 0;
        totalFgAttempted = 0;
        totalThreePointersMade = 0;
        totalThreePointersAttempted = 0;
        totalDefRebounds = 0;
        totalOffRebounds = 0;
        totalRebounds = 0;
        totalPoints = 0;
        totalAssists = 0;
        totalGamesPlayed = 0;
    }
    
    public void regenFreeAgencyValue(){
        regenPlayerPerformanceValue();
        regenPlayerValue();
        freeAgentValue = value + performanceValue;
    }
    
    // calculates it for the year. should impact free agency.
    public void regenPlayerPerformanceValue(){
        // ppg, apg, rpg, fg, 3fg make a player's value. their def values too possibly, based on position for perimeter vs interior.
        double fieldGoalPercentage = calculatePercentage(totalFgMade, totalFgAttempted);
        double threePointPercentage = calculatePercentage(totalThreePointersMade, totalThreePointersAttempted);
        double averagePointsPerGame = calculateAverage(totalPoints, totalGamesPlayed);
        double averageAssistsPerGame = calculateAverage(totalAssists, totalGamesPlayed);
        double averageReboundsPerGame = calculateAverage(totalRebounds, totalGamesPlayed);
        
        int val = 0;
        
        val+= averagePointsPerGame + averageAssistsPerGame + averageReboundsPerGame;
        
        
        if(fieldGoalPercentage>=50){
            val = val * 2;
        }else if(fieldGoalPercentage<30){
            val = val / 2;
        }
        
        if(threePointPercentage>=35){
            val = val * 2;
        }
        
        performanceValue = val;
    }
    
    public void addOntoYearlyStats(int year, Team t){
        double fieldGoalPercentage = calculatePercentage(totalFgMade, totalFgAttempted);
        double threePointPercentage = calculatePercentage(totalThreePointersMade, totalThreePointersAttempted);
        double averagePointsPerGame = calculateAverage(totalPoints, totalGamesPlayed);
        double averageAssistsPerGame = calculateAverage(totalAssists, totalGamesPlayed);
        double averageReboundsPerGame = calculateAverage(totalRebounds, totalGamesPlayed);
        double averageDefReboundsPerGame = calculateAverage(totalDefRebounds, totalGamesPlayed);
        double averageOffReboundsPerGame = calculateAverage(totalOffRebounds, totalGamesPlayed);
        // eventually add a role here as another available stat: Starter or Sub
        
        YearlyStats ys = new YearlyStats();
        
        // populate ys right below this line here
        ys.setYear(year);
        ys.setOverallRating(overallRating);
        ys.setTeam(t);
        ys.setAge(age);
        ys.setRole(role);
        
        ys.setFieldGoalPercentage(fieldGoalPercentage);
        ys.setThreePointPercentage(threePointPercentage);
        ys.setAveragePointsPerGame(averagePointsPerGame);
        ys.setAverageAssistsPerGame(averageAssistsPerGame);
        ys.setAverageReboundsPerGame(averageReboundsPerGame);
        ys.setAverageOffensiveReboundsPerGame(averageOffReboundsPerGame);
        ys.setAverageDefensiveReboundsPerGame(averageDefReboundsPerGame);
        
        if(isChampionThisYear){
            ys.setChampion(true);
            isChampionThisYear = false;
            System.out.println(name + " is now a champion via the " + t.getName());
        }
        
        yearlyStats.add(ys);
    }
    
    /**
     * This method will regenerate a player's value, and is used to sign free agents.
     */
    public void regenPlayerValue(){
        int pValue = 0;
        
        pValue+= overallRating;
        
        if(age<30){
            if(development==5){
                pValue*=4;
            }else if(development==4){
                pValue*=3;
            }else if(development==3){
                pValue*=2;
            }else if(development==2){
                pValue*=1;
            }else{
                pValue/=2;
            }
        }
        
        
        // reduce for age
        
        if(age>=32){
            pValue/=2;
        }else if(age<=25){
            pValue*=2;
        }
        
        value = pValue;
    }
    
    /**
     * 
     * @param t the team making the contract offer
     * @param typeOfOffer either "Resigning" OR "Free agency"
     * @param isPlayerTeam if it's false, then the team has their own decision based off playerValue
     * @return "Signed" or "Declined"
     */
    /**
    public String getSigningDecision(Team t, String typeOfOffer, boolean isPlayerTeam){
        int signingScore = 0;
        Random r = new Random();
        
        if(FREE_AGENCY_playForWinner<50){
            // nothing
        }else if(FREE_AGENCY_playForWinner<60){
            if(t.getWins()>=30){
                signingScore+= r.nextInt(getValueWithinRange(10,25));
            }else{
                signingScore-= r.nextInt(10);
            }
        }else if(FREE_AGENCY_playForWinner<75){
            if(t.getWins()>=40){
                signingScore+= r.nextInt(getValueWithinRange(15,30));
            }else{
                signingScore-= r.nextInt(15);
            }
        }else if(FREE_AGENCY_playForWinner<80){
            if(t.getWins()>=45){
                signingScore+= r.nextInt(getValueWithinRange(25,50));
            }else{
                signingScore-= r.nextInt(25);
            }
        }else if(FREE_AGENCY_playForWinner<100){
            if(t.getWins()>=50){
                signingScore+= getValueWithinRange(50,75);
            }else{
                signingScore-= r.nextInt(35);
            }
        }
        
        if(typeOfOffer.equalsIgnoreCase("Resigning")){
            // uses loyalty + winner
            if(FREE_AGENCY_loyalty<50){
                signingScore-= r.nextInt(10);
            }else if(FREE_AGENCY_loyalty<60){
                signingScore+= r.nextInt(10);
            }else if(FREE_AGENCY_loyalty<70){
                signingScore+= r.nextInt(20);
            }else if(FREE_AGENCY_loyalty<80){
                signingScore+= r.nextInt(30);
            }else if(FREE_AGENCY_loyalty<90){
                signingScore+= r.nextInt(40);
            }else{
                signingScore+= r.nextInt(50);
            }
            signingScore = signingScore / 2;
        }
        
        // So if it's an AI team, they have to factor in playerValue into the decision
        if(!isPlayerTeam){
            if(value<50){
                signingScore-= r.nextInt(50 - 20 + 1) + 20;
            }else if(value<80){
                signingScore-= r.nextInt(25 - 10 + 1) + 10;
            }else if(value<125){
                signingScore+= r.nextInt(25 - 10 + 1) + 10;
            }else if(value<175){
                signingScore+= r.nextInt(35 - 20 + 1) + 20;
            }else{
                signingScore+= r.nextInt(50 - 20 + 1) + 20;
            }
        }
        
        signingScore+= r.nextInt(40);
        signingScore+= 20;
        System.out.println("signing score = " + signingScore);
        if(signingScore>=25){
            return "Signed";
        }
        return "Declined";
    }
    **/
    
    /**
     * Basic signing decision, 75% chance to sign with a team.
     * @return 
     */
    public boolean getSigningDecision(){
        Random r = new Random();
        int seed = r.nextInt(100);
        if(seed<75){
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @return true for yes, false for now
     */
    public boolean getRetiringDecision(){
        Random r = new Random();
        int seed = r.nextInt(100);
        
        if(age>=45){
            return true;
        }else if(age>=40){
            if(seed<95){
                return true;
            }
        }else if(age>=37){
            if(seed<85){
                return true;
            }
        }else if(age>=35){
            if(seed<60){
                return true;
            }
        }else if(age>=33){
            if(seed<25){
                return true;
            }
        }
        return false;
    }
    
    public void regenContract(){
        Contract c = new Contract();
        if(yearsInTheLeague==0){
            c.setLength(3);
            c.setSalary(2);
        }else{
            Random r = new Random();
            c.setLength(r.nextInt(4) + 1);
            

            if(overallRating<50){
                c.setSalary(r.nextInt(5));
            }else if(overallRating<60){
                c.setSalary(r.nextInt(8));
            }else if(overallRating<70){
                c.setSalary(getValueWithinRange(5, 15));
            }else if(overallRating<75){
                c.setSalary(getValueWithinRange(10, 20));
            }else if(overallRating<80){
                c.setSalary(getValueWithinRange(15, 25));
            }else if(overallRating<85){
                c.setSalary(getValueWithinRange(20, 35));
            }else if(overallRating<90){
                c.setSalary(getValueWithinRange(25, 40));
            }else if(overallRating<95){
                c.setSalary(getValueWithinRange(30, 50));
            }else{
                c.setSalary(getValueWithinRange(45, 70));
            }
            c.setSalary(c.getSalary()+1);
        }
        setContract(c);
    }
    
    private int getValueWithinRange(int min, int max){
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }
    
    // based on their position. this is called yearly before the offseason.
    public void regenOverallRating(){
        NewPlayerCreator pc = new NewPlayerCreator();
        if(position.equalsIgnoreCase("PG") || position.equalsIgnoreCase("SG")){
            this.setOverallRating(pc.generateGuardOverall(this));
        }else if(position.equalsIgnoreCase("PF") || position.equalsIgnoreCase("C")){
            this.setOverallRating(pc.generateCenterAndPowerForwardOverall(this));
        }else{
            this.setOverallRating(pc.generateSmallForwardOverall(this));
        }
        regenInitTendy();
    }
    
    public String generateStatline() {
        // Calculate derived stats
        double fieldGoalPercentage = calculatePercentage(totalFgMade, totalFgAttempted);
        double threePointPercentage = calculatePercentage(totalThreePointersMade, totalThreePointersAttempted);
        double averagePointsPerGame = calculateAverage(totalPoints, totalGamesPlayed);
        double averageAssistsPerGame = calculateAverage(totalAssists, totalGamesPlayed);
        double averageReboundsPerGame = calculateAverage(totalRebounds, totalGamesPlayed);
        double averageDefReboundsPerGame = calculateAverage(totalDefRebounds, totalGamesPlayed);
        double averageOffReboundsPerGame = calculateAverage(totalOffRebounds, totalGamesPlayed);

        // Build the statline string
        String stats = String.format("PPG: %.2f - APG: %.2f - RPG: %.2f - ORPG: %.2f - DRPG: %.2f - FG%%: %.2f - 3FG%%: %.2f",
            averagePointsPerGame, averageAssistsPerGame, averageReboundsPerGame,
            averageOffReboundsPerGame, averageDefReboundsPerGame, fieldGoalPercentage, threePointPercentage);
        return stats;
    }
    
    public String generateBasicInfo(){
        String text = String.format("%s - AGE: %d - POS: %s - OVR: %d - DEV: %d - CON: %d m per year / %d years left",
            name, age, position, overallRating, development, contract.getSalary(), contract.getLength());
        return text;
    }
    
    // Helper method to calculate percentage
    private double calculatePercentage(int numerator, int denominator) {
        if (denominator == 0) {
            return 0.0; // Prevent division by zero
        }
        return ((double) numerator / denominator) * 100;
    }

    // Helper method to calculate average
    private double calculateAverage(int total, int count) {
        if (count == 0) {
            return 0.0; // Prevent division by zero
        }
        return ((double) total / count);
    }
    
    @Override
    public int compareTo(Player other) {
        return Integer.compare(this.overallRating, other.overallRating);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getDevelopment() {
        return development;
    }

    public void setDevelopment(int development) {
        this.development = development;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public int getFREE_AGENCY_loyalty() {
        return FREE_AGENCY_loyalty;
    }

    public void setFREE_AGENCY_loyalty(int FREE_AGENCY_loyalty) {
        this.FREE_AGENCY_loyalty = FREE_AGENCY_loyalty;
    }

    public int getFREE_AGENCY_playForWinner() {
        return FREE_AGENCY_playForWinner;
    }

    public void setFREE_AGENCY_playForWinner(int FREE_AGENCY_playForWinner) {
        this.FREE_AGENCY_playForWinner = FREE_AGENCY_playForWinner;
    }

    public ArrayList<Integer> getQuarters() {
        return quarters;
    }

    public void setQuarters(ArrayList<Integer> quarters) {
        this.quarters = quarters;
    }

    public int getMaxQuartersPlayable() {
        return maxQuartersPlayable;
    }

    public void setMaxQuartersPlayable(int maxQuartersPlayable) {
        this.maxQuartersPlayable = maxQuartersPlayable;
    }

    public int getDriveTendy() {
        return driveTendy;
    }

    public void setDriveTendy(int driveTendy) {
        this.driveTendy = driveTendy;
    }

    public int getDribbleDriveTendy() {
        return dribbleDriveTendy;
    }

    public void setDribbleDriveTendy(int dribbleDriveTendy) {
        this.dribbleDriveTendy = dribbleDriveTendy;
    }

    public int getSpeedDriveTendy() {
        return speedDriveTendy;
    }

    public void setSpeedDriveTendy(int speedDriveTendy) {
        this.speedDriveTendy = speedDriveTendy;
    }

    public int getPowerDriveTendy() {
        return powerDriveTendy;
    }

    public void setPowerDriveTendy(int powerDriveTendy) {
        this.powerDriveTendy = powerDriveTendy;
    }

    public int getDriveShootTendy() {
        return driveShootTendy;
    }

    public void setDriveShootTendy(int driveShootTendy) {
        this.driveShootTendy = driveShootTendy;
    }

    public int getDrivePassTendy() {
        return drivePassTendy;
    }

    public void setDrivePassTendy(int drivePassTendy) {
        this.drivePassTendy = drivePassTendy;
    }

    public int getPassTendy() {
        return passTendy;
    }

    public void setPassTendy(int passTendy) {
        this.passTendy = passTendy;
    }

    public int getCatchAndShootTendy() {
        return catchAndShootTendy;
    }

    public void setCatchAndShootTendy(int catchAndShootTendy) {
        this.catchAndShootTendy = catchAndShootTendy;
    }

    public int getShootCloseTendy() {
        return shootCloseTendy;
    }

    public void setShootCloseTendy(int shootCloseTendy) {
        this.shootCloseTendy = shootCloseTendy;
    }

    public int getShootMidTendy() {
        return shootMidTendy;
    }

    public void setShootMidTendy(int shootMidTendy) {
        this.shootMidTendy = shootMidTendy;
    }

    public int getShootThreeTendy() {
        return shootThreeTendy;
    }

    public void setShootThreeTendy(int shootThreeTendy) {
        this.shootThreeTendy = shootThreeTendy;
    }

    public int getCloseSkill() {
        return closeSkill;
    }

    public void setCloseSkill(int closeSkill) {
        this.closeSkill = closeSkill;
    }

    public int getMidShotSkill() {
        return midShotSkill;
    }

    public void setMidShotSkill(int midShotSkill) {
        this.midShotSkill = midShotSkill;
    }

    public int getThreeShotSkill() {
        return threeShotSkill;
    }

    public void setThreeShotSkill(int threeShotSkill) {
        this.threeShotSkill = threeShotSkill;
    }

    public int getPassingSkill() {
        return passingSkill;
    }

    public void setPassingSkill(int passingSkill) {
        this.passingSkill = passingSkill;
    }

    public int getDriveSkill() {
        return driveSkill;
    }

    public void setDriveSkill(int driveSkill) {
        this.driveSkill = driveSkill;
    }

    public int getCatchAndShootModifier() {
        return catchAndShootModifier;
    }

    public void setCatchAndShootModifier(int catchAndShootModifier) {
        this.catchAndShootModifier = catchAndShootModifier;
    }

    public int getDriveShootModifier() {
        return driveShootModifier;
    }

    public void setDriveShootModifier(int driveShootModifier) {
        this.driveShootModifier = driveShootModifier;
    }

    public int getStrengthSkill() {
        return strengthSkill;
    }

    public void setStrengthSkill(int strengthSkill) {
        this.strengthSkill = strengthSkill;
    }

    public int getSpeedSkill() {
        return speedSkill;
    }

    public void setSpeedSkill(int speedSkill) {
        this.speedSkill = speedSkill;
    }

    public int getDribblingSkill() {
        return dribblingSkill;
    }

    public void setDribblingSkill(int dribblingSkill) {
        this.dribblingSkill = dribblingSkill;
    }

    public int getPerimeterDefSkill() {
        return perimeterDefSkill;
    }

    public void setPerimeterDefSkill(int perimeterDefSkill) {
        this.perimeterDefSkill = perimeterDefSkill;
    }

    public int getInteriorDefSkill() {
        return interiorDefSkill;
    }

    public void setInteriorDefSkill(int interiorDefSkill) {
        this.interiorDefSkill = interiorDefSkill;
    }

    public int getStealSkill() {
        return stealSkill;
    }

    public void setStealSkill(int stealSkill) {
        this.stealSkill = stealSkill;
    }

    public int getInterceptionSkill() {
        return interceptionSkill;
    }

    public void setInterceptionSkill(int interceptionSkill) {
        this.interceptionSkill = interceptionSkill;
    }

    public int getBlockingSkill() {
        return blockingSkill;
    }

    public void setBlockingSkill(int blockingSkill) {
        this.blockingSkill = blockingSkill;
    }

    public int getDefRebounding() {
        return defRebounding;
    }

    public void setDefRebounding(int defRebounding) {
        this.defRebounding = defRebounding;
    }

    public int getOffRebounding() {
        return offRebounding;
    }

    public void setOffRebounding(int offRebounding) {
        this.offRebounding = offRebounding;
    }

    public int getFgMade() {
        return fgMade;
    }

    public void setFgMade(int fgMade) {
        this.fgMade = fgMade;
    }

    public int getFgAttempted() {
        return fgAttempted;
    }

    public void setFgAttempted(int fgAttempted) {
        this.fgAttempted = fgAttempted;
    }

    public int getThreePointersMade() {
        return threePointersMade;
    }

    public void setThreePointersMade(int threePointersMade) {
        this.threePointersMade = threePointersMade;
    }

    public int getThreePointersAttempted() {
        return threePointersAttempted;
    }

    public void setThreePointersAttempted(int threePointersAttempted) {
        this.threePointersAttempted = threePointersAttempted;
    }

    public int getRebounds() {
        return rebounds;
    }

    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getTotalRebounds() {
        return totalRebounds;
    }

    public void setTotalRebounds(int totalRebounds) {
        this.totalRebounds = totalRebounds;
    }

    public int getTotalFgMade() {
        return totalFgMade;
    }

    public void setTotalFgMade(int totalFgMade) {
        this.totalFgMade = totalFgMade;
    }

    public int getTotalFgAttempted() {
        return totalFgAttempted;
    }

    public void setTotalFgAttempted(int totalFgAttempted) {
        this.totalFgAttempted = totalFgAttempted;
    }

    public int getTotalThreePointersMade() {
        return totalThreePointersMade;
    }

    public void setTotalThreePointersMade(int totalThreePointersMade) {
        this.totalThreePointersMade = totalThreePointersMade;
    }

    public int getTotalThreePointersAttempted() {
        return totalThreePointersAttempted;
    }

    public void setTotalThreePointersAttempted(int totalThreePointersAttempted) {
        this.totalThreePointersAttempted = totalThreePointersAttempted;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getTotalSteals() {
        return totalSteals;
    }

    public void setTotalSteals(int totalSteals) {
        this.totalSteals = totalSteals;
    }

    public int getTotalBlocks() {
        return totalBlocks;
    }

    public void setTotalBlocks(int totalBlocks) {
        this.totalBlocks = totalBlocks;
    }

    public int getTotalAssists() {
        return totalAssists;
    }

    public void setTotalAssists(int totalAssists) {
        this.totalAssists = totalAssists;
    }

    public int getTotalGamesPlayed() {
        return totalGamesPlayed;
    }

    public void setTotalGamesPlayed(int totalGamesPlayed) {
        this.totalGamesPlayed = totalGamesPlayed;
    }

    public int getDrivePassModifier() {
        return drivePassModifier;
    }

    public void setDrivePassModifier(int drivePassModifier) {
        this.drivePassModifier = drivePassModifier;
    }

    public int getInitTendy() {
        return initTendy;
    }

    public void setInitTendy(int initTendy) {
        this.initTendy = initTendy;
    }

    public int getOffReboundTendy() {
        return offReboundTendy;
    }

    public void setOffReboundTendy(int offReboundTendy) {
        this.offReboundTendy = offReboundTendy;
    }

    public int getDefReboundTendy() {
        return defReboundTendy;
    }

    public void setDefReboundTendy(int defReboundTendy) {
        this.defReboundTendy = defReboundTendy;
    }

    public int getDefRebounds() {
        return defRebounds;
    }

    public void setDefRebounds(int defRebounds) {
        this.defRebounds = defRebounds;
    }

    public int getOffRebounds() {
        return offRebounds;
    }

    public void setOffRebounds(int offRebounds) {
        this.offRebounds = offRebounds;
    }

    public int getTotalDefRebounds() {
        return totalDefRebounds;
    }

    public void setTotalDefRebounds(int totalDefRebounds) {
        this.totalDefRebounds = totalDefRebounds;
    }

    public int getTotalOffRebounds() {
        return totalOffRebounds;
    }

    public void setTotalOffRebounds(int totalOffRebounds) {
        this.totalOffRebounds = totalOffRebounds;
    }

    public int getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(int overallRating) {
        this.overallRating = overallRating;
    }

    public int getYearsInTheLeague() {
        return yearsInTheLeague;
    }

    public void setYearsInTheLeague(int yearsInTheLeague) {
        this.yearsInTheLeague = yearsInTheLeague;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPerformanceValue() {
        return performanceValue;
    }

    public void setPerformanceValue(int performanceValue) {
        this.performanceValue = performanceValue;
    }

    public int getFreeAgentValue() {
        return freeAgentValue;
    }

    public void setFreeAgentValue(int freeAgentValue) {
        this.freeAgentValue = freeAgentValue;
    }

    public ImageIcon getPortrait() {
        return portrait;
    }

    public void setPortrait(ImageIcon portrait) {
        this.portrait = portrait;
    }

    public boolean hasTrainedThisYear() {
        return hasTrainedThisYear;
    }

    public void setHasTrainedThisYear(boolean hasTrainedThisYear) {
        this.hasTrainedThisYear = hasTrainedThisYear;
    }

    public ArrayList<YearlyStats> getYearlyStats() {
        return yearlyStats;
    }

    public void setYearlyStats(ArrayList<YearlyStats> yearlyStats) {
        this.yearlyStats = yearlyStats;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isRetiring() {
        return isRetiring;
    }

    public void setIsRetiring(boolean isRetiring) {
        this.isRetiring = isRetiring;
    }

    public ArrayList<Award> getAwards() {
        return awards;
    }

    public void setAwards(ArrayList<Award> awards) {
        this.awards = awards;
    }

    public boolean isIsChampionThisYear() {
        return isChampionThisYear;
    }

    public void setIsChampionThisYear(boolean isChampionThisYear) {
        this.isChampionThisYear = isChampionThisYear;
    }
    
    
    
}
