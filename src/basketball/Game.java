
package basketball;

import java.util.Random;

// TO ADD: catch and shoot, drive pass, modifiers and we're done :)
public class Game {
    
    private Team team1;
    private Team team2;
    private boolean isWatchingGame;

    public Game(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        isWatchingGame = false;
    }
    
    // just add subs aka change the team's squad after each quarter, ez :)
    public void simGame(){
        
        Random r = new Random();
        int secondsPassed = 0;
        int quarter = 1;
        
        
        team1.setGameScore(0);
        team2.setGameScore(0);
        
        Team offTeam = team1;
        Team defTeam = team2;
        
        
        
        // jump ball
        int pos = r.nextInt(2);
        pos+= 1;
        
        
        Player prevPasser = null;
        Boolean wasGoodPass = false;
        Boolean isReboundScenario = false;
        
        // we have drive shoot now we need catch and shoot
        do{
            
            
            if(pos==1){
                offTeam = team1;
                defTeam = team2;
            }else{
                offTeam = team2;
                defTeam = team1;
            }
            
            Player playIniter;
            do{
                playIniter = getRandomPlayer(offTeam);
            }while(playIniter.equals(prevPasser));
             
            
            boolean exists = containsObject(offTeam.getSquad(), prevPasser);
            if(null != prevPasser && exists && isCatchingAndShooting(playIniter)){
                // pickTheStartingPlayFtCatchAndShoot BUT this one uses catch and shoot
                // or use isCatchingAndShooting()
                // if so, then they will take a shot right here. need a great pass modifier.
                // if it goes in, great
                // if not, rebound scenario. but basically might have to put the code below into an else block.
                
                int indexOfPlayIniter = getIndexOfPlayIniter(offTeam, playIniter);
                Player defender = defTeam.getSquad()[indexOfPlayIniter];
                
                String shotType = pickTheTypeOfShot(playIniter);
                int shotResult = 0;
                
                if(shotType.equalsIgnoreCase("Close range")){
                    // interior defender now
                    defender = getInteriorDefender(defTeam);
                    shotResult = getShotResult(playIniter, defender, shotType);
                }else{
                    shotResult = getShotResult(playIniter, defender, shotType);
                }

                shotResult+= playIniter.getCatchAndShootModifier();
                if(wasGoodPass){
                    shotResult+= 10;
                }
                
                if(shotType.equalsIgnoreCase("Three point range")){
                    // compare to random 100 to make it way better. formerly was 30.
                    int shotSeed = r.nextInt(100);
                    if(shotResult>shotSeed){
                        offTeam.setGameScore(offTeam.getGameScore()+3);
                        playIniter.setThreePointersMade(playIniter.getThreePointersMade()+1);
                        playIniter.setThreePointersAttempted(playIniter.getThreePointersAttempted()+1);
                        playIniter.setFgMade(playIniter.getFgMade()+1);
                        playIniter.setFgAttempted(playIniter.getFgAttempted()+1);
                        playIniter.setPoints(playIniter.getPoints()+3);
                        prevPasser.setAssists(prevPasser.getAssists()+1);
                        isReboundScenario = false;
                    }else{
                        playIniter.setThreePointersAttempted(playIniter.getThreePointersAttempted()+1);
                        playIniter.setFgAttempted(playIniter.getFgAttempted()+1);
                        // REBOUND ************
                        isReboundScenario = true;
                    }
                }else{
                    int shotSeed = r.nextInt(90);
                    // shot seed was formerly 20
                    if(shotResult>shotSeed){
                        offTeam.setGameScore(offTeam.getGameScore()+2);
                        playIniter.setFgMade(playIniter.getFgMade()+1);
                        playIniter.setFgAttempted(playIniter.getFgAttempted()+1);
                        playIniter.setPoints(playIniter.getPoints()+2);
                        prevPasser.setAssists(prevPasser.getAssists()+1);
                        // switch pos
                        isReboundScenario = false;
                    }else{
                        playIniter.setFgAttempted(playIniter.getFgAttempted()+1);
                        // REBOUND ************
                        isReboundScenario = true;
                    }
                }
            }else{
                String startingPlay = pickTheStartingPlay(playIniter);
            
                if(startingPlay.equalsIgnoreCase("Pass")){
                    prevPasser = playIniter;
                    // standing hockey pass, doesn't use drive modifier
                    int passSeed = r.nextInt(100);
                    if(r.nextInt(playIniter.getPassingSkill())>=passSeed){
                        wasGoodPass = true; // only if this is true does prevpasser get an assist
                    }
                    continue;
                }

                String drivingPlay = pickTheDrivingPlay(playIniter);
                int indexOfPlayIniter = getIndexOfPlayIniter(offTeam, playIniter);
                Player defender = defTeam.getSquad()[indexOfPlayIniter];

                // added to the shot result
                int driveResult = getDriveResult(playIniter, defender, drivingPlay);

                // drive shoot or drive pass
                String driveFinish = pickTheDriveFinish(playIniter);


                int shotResult = 0;

                if(driveFinish.equalsIgnoreCase("Drive shoot")){
                    String shotType = pickTheTypeOfShot(playIniter);
                    if(shotType.equalsIgnoreCase("Close range")){
                        // interior defender now
                        defender = getInteriorDefender(defTeam);
                        shotResult = getShotResult(playIniter, defender, shotType);
                    }else{
                        shotResult = getShotResult(playIniter, defender, shotType);
                    }

                    shotResult+= driveResult;
                    shotResult+= playIniter.getDriveShootModifier();

                    if(shotType.equalsIgnoreCase("Three point range")){
                        int shotSeed = r.nextInt(100);
                        if(shotResult>shotSeed){
                            offTeam.setGameScore(offTeam.getGameScore()+3);
                            playIniter.setThreePointersMade(playIniter.getThreePointersMade()+1);
                            playIniter.setThreePointersAttempted(playIniter.getThreePointersAttempted()+1);
                            playIniter.setFgMade(playIniter.getFgMade()+1);
                            playIniter.setFgAttempted(playIniter.getFgAttempted()+1);
                            playIniter.setPoints(playIniter.getPoints()+3);
                            isReboundScenario = false;
                        }else{
                            playIniter.setThreePointersAttempted(playIniter.getThreePointersAttempted()+1);
                            playIniter.setFgAttempted(playIniter.getFgAttempted()+1);
                            // REBOUND ************
                            isReboundScenario = true;
                        }
                    }else{
                        int shotSeed = r.nextInt(90);
                        if(shotResult>shotSeed){
                            offTeam.setGameScore(offTeam.getGameScore()+2);
                            playIniter.setFgMade(playIniter.getFgMade()+1);
                            playIniter.setFgAttempted(playIniter.getFgAttempted()+1);
                            playIniter.setPoints(playIniter.getPoints()+2);
                            // switch pos
                            isReboundScenario = false;
                        }else{
                            playIniter.setFgAttempted(playIniter.getFgAttempted()+1);
                            // REBOUND ************
                            isReboundScenario = true;
                        }
                    }

                }else{
                    // pass to someone else and make it a good pass if it passes a threshold
                    // this will use a continue
                    prevPasser = playIniter;
                    int passScore = r.nextInt(playIniter.getPassingSkill());
                    passScore += driveResult;
                    
                    int passSeed = r.nextInt(100);
                    if(passScore>=passSeed){
                        wasGoodPass = true;
                    }else{
                        wasGoodPass = false;
                    }
                    continue;
                }
            }
            
            
            
            
            if(isReboundScenario){
                Player offReber = getOffRebounder(offTeam);
                Player defReber = getDefRebounder(defTeam);
                
                int offRebChance = r.nextInt(offReber.getOffRebounding()) - 35;
                int defRebChance = r.nextInt(defReber.getDefRebounding());
                
                if(defRebChance>=offRebChance){
                    // flip pos
                    if(pos==1){
                        pos = 2;
                    }else{
                        pos = 1;
                    }
                    defReber.setRebounds(defReber.getRebounds()+1);
                    defReber.setDefRebounds(defReber.getDefRebounds()+1);
                    prevPasser = null;
                    wasGoodPass = false;
                }else{
                    offReber.setRebounds(offReber.getRebounds()+1);
                    offReber.setOffRebounds(offReber.getOffRebounds()+1);
                }
            }else{
                if(pos==1){
                    pos = 2;
                }else{
                    pos = 1;
                }
                prevPasser = null;
                wasGoodPass = false;
            }
            secondsPassed += r.nextInt(24 - 12 + 1) + 12;
            
            // get the random player 
            // pick their starting play
            // if pass, send the ball to someone else aka continue the method
            // if driving, pick their drive. maybe only drive passes can be assists.
            // do the drive, see how good it is. off skill vs def skill determines success and therein the finale.
            // do the drive finale. the chance of success is impacted by the drive result value. just have a method that calculates driveResult.
            
            
            if(secondsPassed >= 720){
                quarter++;
                secondsPassed = 0;
            }
            
        }while(quarter<5);
        
        
        if(!isWatchingGame){
            for(Player p : team1.getSquad()){
                p.updateTotalStats();
                p.resetGameStats();
            }

            for(Player p : team2.getSquad()){
                p.updateTotalStats();
                p.resetGameStats();
            }

            for(Player p : team1.getBench()){
                p.updateTotalStats();
                p.resetGameStats();
            }

            for(Player p : team2.getBench()){
                p.updateTotalStats();
                p.resetGameStats();
            }
        }
        
        
    }
    
    private Player getOffRebounder(Team team){
        Random r = new Random();
        int maxIndex = 0;
        int maxTendy = 0;
        int currIndex = 0;
        
        for(Player p : team.getSquad()){
            int tendy = r.nextInt(p.getOffReboundTendy());
            if(tendy>maxTendy){
                maxIndex = currIndex;
                maxTendy = tendy;
            }
            currIndex++;
        }
        
        return team.getSquad()[maxIndex];
    }
    
    private Player getDefRebounder(Team team){
        Random r = new Random();
        int maxIndex = 0;
        int maxTendy = 0;
        int currIndex = 0;
        
        for(Player p : team.getSquad()){
            int tendy = r.nextInt(p.getDefReboundTendy());
            if(tendy>maxTendy){
                maxIndex = currIndex;
                maxTendy = tendy;
            }
            currIndex++;
        }
        
        return team.getSquad()[maxIndex];
    }
    
    private Player getInteriorDefender(Team t){
        Random r = new Random();
        int seed = r.nextInt(100);
        if(seed>=70){
            return t.getSquad()[4];
        }else if(seed>=40){
            return t.getSquad()[3];
        }else if(seed>=25){
            return t.getSquad()[2];
        }else if(seed>10){
            return t.getSquad()[1];
        }else{
            return t.getSquad()[0];
        }
    }
    
    private int getIndexOfPlayIniter(Team offTeam, Player playIniter){
        
        int index = 0;
        for(Player p : offTeam.getSquad()){
            if(p.equals(playIniter)){
                return index;
            }
            index++;
        }
        
        return 2;
    }
    
    private int getShotResult(Player shooter, Player defender, String typeOfShot){
        Random r = new Random();
        
        int shotScore = 0;
        int defScore = 0;
        
        if(typeOfShot.equalsIgnoreCase("Close range")){
            shotScore += r.nextInt(shooter.getCloseSkill());
            
            defScore += r.nextInt(defender.getInteriorDefSkill());
        }else if(typeOfShot.equalsIgnoreCase("Mid range")){
            shotScore += r.nextInt(shooter.getMidShotSkill());
            
            defScore += r.nextInt(defender.getPerimeterDefSkill());
        }else{
            shotScore += r.nextInt(shooter.getThreeShotSkill());
            
            defScore += r.nextInt(defender.getPerimeterDefSkill());
        }
        
        int diff = shotScore - (defScore/8);
        return diff;
    }
    
    // dribble drive, speed drive, power drive. result should be a number. negative = def win. positive = off hard win.
    // returns a number that adds to the probability of the shot or pass
    private int getDriveResult(Player driver, Player defender, String typeOfDrive){
        Random r = new Random();
        
        int driveScore = 0;
        int defScore = 0;
        int diff = 0;
        
        if(typeOfDrive.equalsIgnoreCase("Dribble Drive")){
            driveScore = r.nextInt(driver.getDriveSkill()) + r.nextInt(driver.getDribblingSkill());
            driveScore = driveScore / 2;
            
            defScore = r.nextInt(defender.getPerimeterDefSkill());
        }else if(typeOfDrive.equalsIgnoreCase("Speed Drive")){
            driveScore = r.nextInt(driver.getDriveSkill()) + r.nextInt(driver.getSpeedSkill());
            driveScore = driveScore / 2;
            
            defScore = r.nextInt(defender.getPerimeterDefSkill()) + r.nextInt(defender.getSpeedSkill());
            driveScore = defScore / 2;
        }else{
            driveScore = r.nextInt(driver.getDriveSkill()) + r.nextInt(driver.getStrengthSkill());
            driveScore = driveScore / 2;
            
            defScore = r.nextInt(defender.getInteriorDefSkill()) + r.nextInt(defender.getStrengthSkill());
            driveScore = defScore / 2;
        }
        
        diff = driveScore - defScore;
        
        return diff / 5;
    }
    
    private Player getRandomPlayer(Team team){
        // player init tendency???
        Random r = new Random();
        int maxIndex = 0;
        int maxTendy = 0;
        int currIndex = 0;
        
        for(Player p : team.getSquad()){
            int tendy = r.nextInt(p.getInitTendy());
            if(tendy>maxTendy){
                maxIndex = currIndex;
                maxTendy = tendy;
            }
            currIndex++;
        }
        
        return team.getSquad()[maxIndex];
    }
    
    // gen up to their tendies to see what they're doing.
    private String pickTheStartingPlay(Player p){
        Random r = new Random();
        
        int passTendy = r.nextInt(p.getPassTendy());
        int driveTendy = r.nextInt(p.getDriveTendy());
        
        if(passTendy>=driveTendy){
            return "Pass";
        }else{
            return "Drive";
        }
    }
    
    private String pickTheDrivingPlay(Player p){
        Random r = new Random();
        
        int dribbleDrive = r.nextInt(p.getDribbleDriveTendy());
        int speedDrive = r.nextInt(p.getSpeedDriveTendy());
        int powerDrive = r.nextInt(p.getPowerDriveTendy());

        if (dribbleDrive >= speedDrive && dribbleDrive >= powerDrive){
            return "Dribble Drive";
        }else if(speedDrive > dribbleDrive && speedDrive > powerDrive){
            return "Speed Drive";
        }else{
            return "Power Drive";
        }
    }
    
    private String pickTheDriveFinish(Player p){
        Random r = new Random();
        
        int driveShoot = r.nextInt(p.getDriveShootTendy());
        int drivePass = r.nextInt(p.getDrivePassTendy());
        
        if(driveShoot>=drivePass){
            return "Drive shoot";
        }else{
            return "Drive pass";
        }
    }
    
    private String pickTheTypeOfShot(Player p){
        Random r = new Random();
        
        int shootCloseTendy = r.nextInt(p.getShootCloseTendy());
        int shootMidTendy = r.nextInt(p.getShootMidTendy());
        int shootThreeTendy = r.nextInt(p.getShootThreeTendy());
        
        if (shootCloseTendy >= shootMidTendy && shootCloseTendy >= shootThreeTendy){
            return "Close Range";
        }else if (shootMidTendy > shootCloseTendy && shootMidTendy > shootThreeTendy){
            return "Mid Range";
        }else{
            return "Three Point Range";
        }
    }
    
    private boolean isCatchingAndShooting(Player p){
        Random r = new Random();
        int seed = r.nextInt(p.getCatchAndShootTendy());
        if(seed>=25){
            return true;
        }
        return false;
    }
    
    private <T> boolean containsObject(T[] array, T target) {
        for (T element : array) {
            // Use equals for object comparison
            if (element != null && element.equals(target)) {
                return true;
            }
        }
        return false;
    }

    public boolean isWatchingGame() {
        return isWatchingGame;
    }

    public void setIsWatchingGame(boolean isWatchingGame) {
        this.isWatchingGame = isWatchingGame;
    }
    
    
    
}
