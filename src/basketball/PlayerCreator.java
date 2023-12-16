
package basketball;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class PlayerCreator {

    private static ArrayList<String> firstNames;
    private static ArrayList<String> lastNames;
    
    public PlayerCreator() {
        loadNames();
    }
    
    public void loadNames() {
        firstNames = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("assets/first_names.txt"))){
            String line;
            while ((line = reader.readLine()) != null) {
                firstNames.add(line.trim());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        
        lastNames = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("assets/last_names.txt"))){
            String line;
            while ((line = reader.readLine()) != null) {
                lastNames.add(line.trim());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    
    public Player createRandomPlayer(){
        Random r = new Random();
        Player p = new Player();
        
        // Create and return a player
        genName(p);
        generateRatings(p);
        generateModifiers(p);
        createEmptyStats(p);
        p.setDevelopment(getRandomRarity());
        genPositionBasedOnStats(p);
        
        inflateRatings(p);
        generateTendencies(p);
        
        generateMaxQuartersPlayable(p);
        p.setQuarters(new ArrayList<>());
        
        p.setFREE_AGENCY_loyalty(r.nextInt(100)+1);
        p.setFREE_AGENCY_playForWinner(r.nextInt(100)+1);
        
        genAge(p); // should make it more common for 20-29 year olds
        
        p.regenOverallRating();
        // leagues in the year right here
        generateYearsInTheLeague(p);
        // contract generation here
        p.regenContract();
        
        return p;
    }
    
    private void generateYearsInTheLeague(Player p){
        Random r = new Random();
        int years;
        if(p.getAge() == 18){
            years = 0;
        }else{
            int startingAge;
            do{
                startingAge = getValueWithinRange(18, 24);
            }while(startingAge>p.getAge());
            years = p.getAge() - startingAge;
        }
        p.setYearsInTheLeague(years);
    }
    
    public int generateGuardOverall(Player p){
        int overall = 0;
        
        overall+= p.getCloseSkill();
        overall+= p.getMidShotSkill();
        overall+= p.getThreeShotSkill();
        overall+= p.getPassingSkill();
        overall+= p.getDriveSkill();
        
        overall+= p.getSpeedSkill();
        overall+= p.getDribblingSkill();
        
        overall+= p.getPerimeterDefSkill();
        
        overall = overall / 8;
        
        return overall;
    }
    
    public int generateCenterOverall(Player p){
        int overall = 0;
        
        overall+= p.getCloseSkill();
        overall+= p.getMidShotSkill();
        overall+= p.getDriveSkill();
        
        overall+= p.getStrengthSkill();
        
        overall+= p.getInteriorDefSkill();
        overall+= p.getDefRebounding();
        overall+= p.getOffRebounding();
        
        overall = overall / 7;
        
        return overall;
    }
    
    public int generateForwardOverall(Player p){
        int overall = 0;
        
        overall+= p.getCloseSkill();
        overall+= p.getMidShotSkill();
        overall+= p.getThreeShotSkill();
        overall+= p.getPassingSkill();
        overall+= p.getDriveSkill();
        
        overall+= p.getStrengthSkill();
        overall+= p.getSpeedSkill();
        overall+= p.getDribblingSkill();
        
        overall+= p.getPerimeterDefSkill();
        overall+= p.getInteriorDefSkill();
        overall+= p.getDefRebounding();
        overall+= p.getOffRebounding();
        
        overall = overall / 12;
        
        return overall;
    }
    
    private void genName(Player p){
        Random r = new Random();
        String name = "";
        name = name + firstNames.get(r.nextInt(firstNames.size()));
        name = name + " " + lastNames.get(r.nextInt(lastNames.size()));
        
        p.setName(name);
    }
    
    private void genAge(Player p){
        Random r = new Random();
        p.setAge(r.nextInt(35 - 18 + 1) + 18);
    }
    
    // actually just calculate an overall for each position ***************************************************************
    private void genPositionBasedOnStats(Player p){
        // given a player's stats, give them a relevant position
        // PG/SG either OR PF/C random 0 or 1. Else sf.
        
        int guardOverall = generateGuardOverall(p);
        int forwardOverall = generateForwardOverall(p);
        int centerOverall = generateCenterOverall(p);
        
        
        
        String[] guards = {"PG", "SG"};
        String[] forwards = {"SF", "PF"};
        
        Random r = new Random();
        if(guardOverall>=forwardOverall && guardOverall>=centerOverall){
            p.setPosition(guards[r.nextInt(2)]);
            p.setOverallRating(guardOverall);
        }else if(forwardOverall>guardOverall && forwardOverall>centerOverall){
            p.setPosition(forwards[r.nextInt(2)]);
            p.setOverallRating(forwardOverall);
        }else{
            p.setPosition("C");
            p.setOverallRating(centerOverall);
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
        p.setDriveTendy(rando.nextInt(p.getDriveSkill()) + rando.nextInt(50) + 1);
        p.setDribbleDriveTendy(rando.nextInt(p.getDribblingSkill()) + rando.nextInt(50) + 1);
        p.setSpeedDriveTendy(rando.nextInt(p.getSpeedSkill()) + rando.nextInt(50) + 1);
        p.setPowerDriveTendy(rando.nextInt(p.getStrengthSkill()) + rando.nextInt(50) + 1);
        
        p.setDrivePassTendy(rando.nextInt(p.getPassingSkill()) + rando.nextInt(75) + 1);
        p.setPassTendy(rando.nextInt(p.getPassingSkill()) + rando.nextInt(75) + 1);
        
        // these 2 will use avg of shooting
        p.setDriveShootTendy(rando.nextInt(findShootingAvg(p)) + rando.nextInt(65) + 1);
        p.setCatchAndShootTendy(rando.nextInt(findShootingAvg(p)) + rando.nextInt(55) + 1);
        
        p.setShootCloseTendy(rando.nextInt(p.getCloseSkill()) + rando.nextInt(50) + 1);
        p.setShootMidTendy(rando.nextInt(p.getMidShotSkill()) + rando.nextInt(50) + 1);
        p.setShootThreeTendy(rando.nextInt(p.getThreeShotSkill()) + rando.nextInt(50) + 1);
        
        p.setInitTendy(rando.nextInt(p.getDriveSkill()) + rando.nextInt(50) + 1);
        p.setOffReboundTendy(rando.nextInt(p.getOffRebounding()) + rando.nextInt(50) + 1);
        p.setDefReboundTendy(rando.nextInt(p.getDefRebounding()) + rando.nextInt(50) + 1);
        
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
    
    public Player generatePlayerByPosition(String position) {
        Player player;
        do {
            player = createRandomPlayer();
        } while (!player.getPosition().equalsIgnoreCase(position));

        return player;
    }
    
    public void inflateRatings(Player p){
        Random r = new Random();
        int rarity = getRandomRarity();
        
        int min; int max;
        
        if(rarity==1){
            min = 1;
            max = 7;
        }else if(rarity==2){
            min = 5;
            max = 10;
        }else if(rarity==3){
            min = 10;
            max = 15;
        }else if(rarity==4){
            min = 15;
            max = 30;
        }else{
            min = 25;
            max = 40;
        }
        
        if(p.getPosition().equalsIgnoreCase("PG") || p.getPosition().equalsIgnoreCase("SG")){
            p.setCloseSkill(p.getCloseSkill()+ getValueWithinRange(min, max));
            p.setMidShotSkill(p.getMidShotSkill()+ getValueWithinRange(min, max));
            p.setThreeShotSkill(p.getThreeShotSkill()+ getValueWithinRange(min, max));
            p.setPassingSkill(p.getPassingSkill() + getValueWithinRange(min, max));
            p.setDriveSkill(p.getDriveSkill()+ getValueWithinRange(min, max));
            
            p.setSpeedSkill(p.getSpeedSkill()+ getValueWithinRange(min, max));
            p.setDribblingSkill(p.getDribblingSkill()+ getValueWithinRange(min, max));
            
            p.setPerimeterDefSkill(p.getPerimeterDefSkill()+ getValueWithinRange(min, max));
        }else if(p.getPosition().equalsIgnoreCase("SF") || p.getPosition().equalsIgnoreCase("PF")){
            p.setCloseSkill(p.getCloseSkill()+ getValueWithinRange(min, max));
            p.setMidShotSkill(p.getMidShotSkill()+ getValueWithinRange(min, max));
            p.setThreeShotSkill(p.getThreeShotSkill()+ getValueWithinRange(min, max));
            p.setPassingSkill(p.getPassingSkill() + getValueWithinRange(min, max));
            p.setDriveSkill(p.getDriveSkill()+ getValueWithinRange(min, max));
            
            p.setStrengthSkill(p.getStrengthSkill()+ getValueWithinRange(min, max));
            p.setSpeedSkill(p.getSpeedSkill()+ getValueWithinRange(min, max));
            p.setDribblingSkill(p.getDribblingSkill()+ getValueWithinRange(min, max));
            
            p.setPerimeterDefSkill(p.getPerimeterDefSkill()+ getValueWithinRange(min, max));
            p.setInteriorDefSkill(p.getInteriorDefSkill()+ getValueWithinRange(min, max));
            p.setOffRebounding(p.getOffRebounding()+ getValueWithinRange(min, max));
            p.setDefRebounding(p.getDefRebounding()+ getValueWithinRange(min, max));
        }else{
            p.setCloseSkill(p.getCloseSkill()+ getValueWithinRange(min, max));
            p.setMidShotSkill(p.getMidShotSkill()+ getValueWithinRange(min, max));
            p.setDriveSkill(p.getDriveSkill()+ getValueWithinRange(min, max));
            
            p.setStrengthSkill(p.getStrengthSkill()+ getValueWithinRange(min, max));
            
            p.setInteriorDefSkill(p.getInteriorDefSkill()+ getValueWithinRange(min, max));
            p.setOffRebounding(p.getOffRebounding()+ getValueWithinRange(min, max));
            p.setDefRebounding(p.getDefRebounding()+ getValueWithinRange(min, max));
        }
    }
    
}
