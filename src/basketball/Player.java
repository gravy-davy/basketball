
package basketball;

import java.util.ArrayList;


public class Player {
    
    // basics
    private String name;
    private int age;
    private String position;
    private int development; // 1-5
    private Contract contract;
    private int yearsInTheLeague;
    
    // for FA decisions
    private int loyalty;
    private int greed;
    private int lastYearRecordImportance;
    
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

    // based on their position. this is called yearly before the offseason.
    public void regenOverallRating(){
        PlayerCreator pc = new PlayerCreator();
        if(position.equalsIgnoreCase("PG") || position.equalsIgnoreCase("SG")){
            this.setOverallRating(pc.generateGuardOverall(this));
        }else if(position.equalsIgnoreCase("SF") || position.equalsIgnoreCase("PF")){
            this.setOverallRating(pc.generateForwardOverall(this));
        }else{
            this.setOverallRating(pc.generateCenterOverall(this));
        }
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
    
    public int getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(int loyalty) {
        this.loyalty = loyalty;
    }

    public int getGreed() {
        return greed;
    }

    public void setGreed(int greed) {
        this.greed = greed;
    }

    public int getLastYearRecordImportance() {
        return lastYearRecordImportance;
    }

    public void setLastYearRecordImportance(int lastYearRecordImportance) {
        this.lastYearRecordImportance = lastYearRecordImportance;
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
    
    
    
}
