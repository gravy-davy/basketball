
package basketball;

import java.util.ArrayList;
import java.util.Random;


public class PlayerCreator {
    
    public Player createRandomPlayer(){
        Random r = new Random();
        Player p = new Player();
        
        // Create and return a player
        generateRatings(p);
        generateModifiers(p);
        generateTendencies(p);
        createEmptyStats(p);
        p.setDevelopment(getRandomRarity());
        genPositionBasedOnStats(p);
        
        generateMaxQuartersPlayable(p);
        p.setQuarters(new ArrayList<>());
        
        p.setLoyalty(r.nextInt(100));
        p.setGreed(r.nextInt(100));
        p.setLastYearRecordImportance(r.nextInt(100));
        
        genAge(p); // should make it more common for 20-29 year olds
        
        return p;
        // Missing: name, salary/contract (rounded to 100k)
    }
    
    private void genAge(Player p){
        Random r = new Random();
        p.setAge(r.nextInt(35 - 18 + 1) + 18);
    }
    
    private void genPositionBasedOnStats(Player p){
        // given a player's stats, give them a relevant position
        // PG/SG either OR PF/C random 0 or 1. Else sf.
        
        int guardWeight = 0;
        int forwardWeight = 0;
        int centerWeight = 0;
        
        
        // guards
        guardWeight+= p.getCloseSkill();
        guardWeight+= p.getMidShotSkill();
        guardWeight+= p.getThreeShotSkill();
        guardWeight+= p.getPassingSkill();
        guardWeight+= p.getDriveSkill();
        
        guardWeight+= p.getStrengthSkill() / 3;
        guardWeight+= p.getSpeedSkill();
        guardWeight+= p.getDribblingSkill();
        
        guardWeight+= p.getPerimeterDefSkill();
        guardWeight+= p.getInteriorDefSkill() / 3;
        guardWeight+= p.getDefRebounding() / 3;
        guardWeight+= p.getOffRebounding() / 3;
        
        
        // forwards
        forwardWeight+= p.getCloseSkill();
        forwardWeight+= p.getMidShotSkill();
        forwardWeight+= p.getThreeShotSkill() / 2;
        forwardWeight+= p.getPassingSkill() / 2;
        forwardWeight+= p.getDriveSkill();
        
        forwardWeight+= p.getStrengthSkill();
        forwardWeight+= p.getSpeedSkill() / 2;
        forwardWeight+= p.getDribblingSkill() / 2;
        
        forwardWeight+= p.getPerimeterDefSkill();
        forwardWeight+= p.getInteriorDefSkill();
        forwardWeight+= p.getDefRebounding() / 2;
        forwardWeight+= p.getOffRebounding() / 2;
        
        
        // centers
        centerWeight+= p.getCloseSkill();
        centerWeight+= p.getMidShotSkill() / 3;
        centerWeight+= p.getThreeShotSkill();
        centerWeight+= p.getPassingSkill();
        centerWeight+= p.getDriveSkill();
        
        centerWeight+= p.getStrengthSkill();
        centerWeight+= p.getSpeedSkill() / 3;
        centerWeight+= p.getDribblingSkill() / 3;
        
        centerWeight+= p.getPerimeterDefSkill() / 3;
        centerWeight+= p.getInteriorDefSkill();
        centerWeight+= p.getDefRebounding();
        centerWeight+= p.getOffRebounding();
        
        String[] guards = {"PG", "SG"};
        String[] forwards = {"SF", "PF"};
        
        Random r = new Random();
        if(guardWeight>=forwardWeight && guardWeight>=centerWeight){
            p.setPosition(guards[r.nextInt(2)]);
        }else if(forwardWeight>guardWeight && forwardWeight>centerWeight){
            p.setPosition(forwards[r.nextInt(2)]);
        }else{
            p.setPosition("C");
        }
    }

    private void generateRatings(Player p){
        p.setCloseSkill(generateRating(getRandomRarity()));
        p.setMidShotSkill(generateRating(getRandomRarity()));
        p.setThreeShotSkill(generateRating(getRandomRarity()));
        p.setPassingSkill(generateRating(getRandomRarity()));
        p.setDriveSkill(generateRating(getRandomRarity()));
        p.setDribblingSkill(generateRating(getRandomRarity()));
        p.setStrengthSkill(generateRating(getRandomRarity()));
        p.setSpeedSkill(generateRating(getRandomRarity()));
        
        
        p.setPerimeterDefSkill(generateRating(getRandomRarity()));
        p.setInteriorDefSkill(generateRating(getRandomRarity()));
        p.setStealSkill(generateRating(getRandomRarity()));
        p.setInterceptionSkill(generateRating(getRandomRarity()));
        p.setBlockingSkill(generateRating(getRandomRarity()));
        p.setDefRebounding(generateRating(getRandomRarity()));
        p.setOffRebounding(generateRating(getRandomRarity()));
    }
    
    private void generateMaxQuartersPlayable(Player p){
        Random r = new Random();
        p.setMaxQuartersPlayable(r.nextInt(3 - 2 + 1) + 2);
    }
    
    private void createEmptyStats(Player p){
        p.setFgMade(0);
        p.setFgAttempted(0);
        p.setAssists(0);
        p.setPoints(0);
        p.setThreePointersMade(0);
        p.setThreePointersAttempted(0);
        p.setRebounds(0);
        p.setOffRebounds(0);
        p.setDefRebounds(0);
        p.setSteals(0);
        p.setBlocks(0);
        
        p.setTotalRebounds(0);
        p.setTotalOffRebounds(0);
        p.setTotalDefRebounds(0);
        p.setTotalFgMade(0);
        p.setTotalFgAttempted(0);
        p.setTotalThreePointersMade(0);
        p.setTotalThreePointersAttempted(0);
        p.setTotalPoints(0);
        p.setTotalAssists(0);
        p.setTotalGamesPlayed(0);
        p.setTotalBlocks(0);
        p.setTotalSteals(0);
        
    }
    
    private void generateModifiers(Player p){
        Random r = new Random();
        p.setCatchAndShootModifier(r.nextInt(10) + 1);
        p.setDriveShootModifier(r.nextInt(10) + 1);
        p.setDrivePassModifier(r.nextInt(10) + 1);
        
        int negative = r.nextInt(2);
        if(negative==1){
            p.setCatchAndShootModifier(p.getCatchAndShootModifier()*-1);
        }
        
        negative = r.nextInt(2);
        if(negative==1){
            p.setDriveShootModifier(p.getDriveShootModifier()*-1);
        }
        
        negative = r.nextInt(2);
        if(negative==1){
            p.setDrivePassModifier(p.getDrivePassModifier()*-1);
        }
        
    }
    
    public void generateTendencies(Player p){
        Random rando = new Random();
        p.setDriveTendy(rando.nextInt(p.getDriveSkill()) + rando.nextInt(50));
        p.setDribbleDriveTendy(rando.nextInt(p.getDribblingSkill()) + rando.nextInt(50));
        p.setSpeedDriveTendy(rando.nextInt(p.getSpeedSkill()) + rando.nextInt(50));
        p.setPowerDriveTendy(rando.nextInt(p.getStrengthSkill()) + rando.nextInt(50));
        
        p.setDrivePassTendy(rando.nextInt(p.getPassingSkill()) + rando.nextInt(75));
        p.setPassTendy(rando.nextInt(p.getPassingSkill()) + rando.nextInt(75));
        
        // these 2 will use avg of shooting
        p.setDriveShootTendy(rando.nextInt(findShootingAvg(p)) + rando.nextInt(65));
        p.setCatchAndShootTendy(rando.nextInt(findShootingAvg(p)) + rando.nextInt(55));
        
        p.setShootCloseTendy(rando.nextInt(p.getCloseSkill()) + rando.nextInt(50));
        p.setShootMidTendy(rando.nextInt(p.getMidShotSkill()) + rando.nextInt(50));
        p.setShootThreeTendy(rando.nextInt(p.getThreeShotSkill()) + rando.nextInt(50));
        
        p.setInitTendy(rando.nextInt(p.getDriveSkill()) + rando.nextInt(50));
        p.setOffReboundTendy(rando.nextInt(p.getOffRebounding()) + rando.nextInt(50));
        p.setDefReboundTendy(rando.nextInt(p.getDefRebounding()) + rando.nextInt(50));
        
    }
    
    private int findShootingAvg(Player p){
        return (p.getCloseSkill() + p.getMidShotSkill() + p.getThreeShotSkill()) / 3;
    }
      
    private int getValueWithinRange(int min, int max){
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }
    
    private int getRandomRarity(){
        Random r = new Random();
        int seed = r.nextInt(100);
        
        if(seed<40){
            return 1;
        }else if(seed<80){
            return 2;
        }else if(seed<94){
            return 3;
        }else if(seed<97){
            return 4;
        }else{
            return 5;
        }
    }
    
    private int generateRating(int raritySeed){
        Random r = new Random();
        switch (raritySeed) {
            case 1:
                return r.nextInt(40 - 25 + 1) + 25;
            case 2:
                return r.nextInt(50 - 35 + 1) + 35;
            case 3:
                return r.nextInt(50 - 35 + 1) + 35;
            case 4:
                return r.nextInt(85 - 55 + 1) + 55;
            default:
                return r.nextInt(99 - 70 + 1) + 70;
        }
    }
    
    // FOR TESTING
    public Player generatePlayerByPosition(String position) {
        Player player;
        do {
            player = createRandomPlayer();
            System.out.println("desired pos = " + position + " - this guy's pos = " + player.getPosition());
        } while (!player.getPosition().equalsIgnoreCase(position));

        return player;
    }
    
}
