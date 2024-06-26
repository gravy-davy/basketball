
package basketball;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;


public class NewPlayerCreator {

    private static ArrayList<String> firstNames;
    private static ArrayList<String> lastNames;
    
    public NewPlayerCreator() {
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
        
        // the difference here is we're going to make position specific ratings + tendencies. so make a random position first.
        generatePosition(p);
        generateRatings(p);
        generateModifiers(p);
        createEmptyStats(p);
        p.setDevelopment(getDevelopment());
        
        // inflateRatings(p);
        p.regenOverallRating();
        generateTendencies(p);
        
        p.setFREE_AGENCY_loyalty(r.nextInt(100)+1);
        p.setFREE_AGENCY_playForWinner(r.nextInt(100)+1);
        
        genAge(p); // should make it more common for 20-29 year olds
        
        p.regenOverallRating();
        // leagues in the year right here
        generateYearsInTheLeague(p);
        // contract generation here
        p.regenContract();
        p.regenPlayerValue();
        
        generatePortrait(p);
        return p;
    }
    
    public Player createFillerPlayer(String pos){
        Random r = new Random();
        Player p = new Player();
        
        // Create and return a player
        genName(p);
        
        p.setPosition(pos);
        do{
            generateRatings(p);
            p.regenOverallRating();
        }while(p.getOverallRating()>55);
        
        p.regenOverallRating();
        generateModifiers(p);
        createEmptyStats(p);
        p.setDevelopment(getDevelopment());
        generateTendencies(p);
        
        p.setFREE_AGENCY_loyalty(r.nextInt(100)+1);
        p.setFREE_AGENCY_playForWinner(r.nextInt(100)+1);
        
        genAge(p); // should make it more common for 20-29 year olds
        
        
        // leagues in the year right here
        p.setYearsInTheLeague(0);
        // contract generation here
        p.regenContract();
        p.regenPlayerValue();
        
        generatePortrait(p);
        
        return p;
    }
    
    private void generatePortrait(Player p){
        
        String directoryPath = "faces/";

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        
        if (files == null || files.length == 0) {
            System.out.println("The directory is empty or does not exist.");
            return;
        }
        
        Random rando = new Random();
        String randomFile = files[rando.nextInt(files.length)].getPath();
        
        // System.out.println("random file = " + randomFile);
        
        p.setPortrait(new ImageIcon(randomFile));
        
    }
    
    private void generateRatings(Player p){
        int playerSeed = getRandomRarity();
        
        int primaryMin;
        int primaryMax;
        
        int secondaryMin = 10;
        int secondaryMax = 40;
        
        if(playerSeed == 1){
            primaryMin = 40;
            primaryMax = 70;
        }else if(playerSeed == 2){
            primaryMin = 50;
            primaryMax = 75;
        }else if(playerSeed == 3){
            primaryMin = 55;
            primaryMax = 85;
        }else if(playerSeed == 4){
            primaryMin = 65;
            primaryMax = 90;
        }else if(playerSeed == 5){
            primaryMin = 70;
            primaryMax = 95;
        }else{
            primaryMin = 80;
            primaryMax = 99;
        }
        
        if(p.getPosition().equalsIgnoreCase("PG") || p.getPosition().equalsIgnoreCase("SG")){
            p.setCloseSkill(generateRating(primaryMin, primaryMax));
            p.setMidShotSkill(generateRating(primaryMin, primaryMax));
            p.setThreeShotSkill(generateRating(primaryMin, primaryMax));
            p.setPassingSkill(generateRating(primaryMin, primaryMax));
            
            p.setDriveSkill(generateRating(primaryMin, primaryMax));
            p.setDribblingSkill(generateRating(primaryMin, primaryMax));
            p.setStrengthSkill(generateRating(secondaryMin, secondaryMax));
            p.setSpeedSkill(generateRating(primaryMin, primaryMax));

            p.setPerimeterDefSkill(generateRating(primaryMin, primaryMax));
            p.setInteriorDefSkill(generateRating(secondaryMin, secondaryMax));
            p.setDefRebounding(generateRating(secondaryMin, secondaryMax));
            p.setOffRebounding(generateRating(secondaryMin, secondaryMax));
        }else if(p.getPosition().equalsIgnoreCase("PF") || p.getPosition().equalsIgnoreCase("C")){
            
            p.setCloseSkill(generateRating(primaryMin, primaryMax));
            p.setMidShotSkill(generateRating(primaryMin, primaryMax));
            p.setThreeShotSkill(generateRating(secondaryMin, secondaryMax));
            p.setPassingSkill(generateRating(secondaryMin, secondaryMax));
            
            p.setDriveSkill(generateRating(primaryMin, primaryMax));
            p.setDribblingSkill(generateRating(secondaryMin, secondaryMax));
            p.setStrengthSkill(generateRating(primaryMin, primaryMax));
            p.setSpeedSkill(generateRating(secondaryMin, secondaryMax));

            p.setPerimeterDefSkill(generateRating(secondaryMin, secondaryMax));
            p.setInteriorDefSkill(generateRating(primaryMin, primaryMax));
            p.setDefRebounding(generateRating(primaryMin, primaryMax));
            p.setOffRebounding(generateRating(primaryMin, primaryMax));
            
            int rangeSeed = getRandomRarity(); // 1-6
            secondaryMin = 15;
            secondaryMax = 15*rangeSeed;
            p.setThreeShotSkill(generateRating(secondaryMin, secondaryMax));
        }else{ // SF WELL ROUNDED PLAYER
            primaryMin = primaryMin - 10;
            primaryMax = primaryMax - 10;
            p.setCloseSkill(generateRating(primaryMin, primaryMax));
            p.setMidShotSkill(generateRating(primaryMin, primaryMax));
            p.setThreeShotSkill(generateRating(primaryMin, primaryMax));
            p.setPassingSkill(generateRating(primaryMin, primaryMax));
            
            p.setDriveSkill(generateRating(primaryMin, primaryMax));
            p.setDribblingSkill(generateRating(primaryMin, primaryMax));
            p.setStrengthSkill(generateRating(primaryMin, primaryMax));
            p.setSpeedSkill(generateRating(primaryMin, primaryMax));

            p.setPerimeterDefSkill(generateRating(primaryMin, primaryMax));
            p.setInteriorDefSkill(generateRating(primaryMin, primaryMax));
            p.setDefRebounding(generateRating(primaryMin, primaryMax));
            p.setOffRebounding(generateRating(primaryMin, primaryMax));
        }
    }
    
    private int getRandomRarity(){
        Random r = new Random();
        int seed = r.nextInt(100);

        if(seed < 30){ // scrub
            return 1;
        }else if(seed < 60){ // bench
            return 2;
        }else if(seed < 80){ // average
            return 3;
        }else if(seed < 92){ // good
            return 4;
        }else if(seed < 97){ // great
            return 5;
        }else{ // legendary
            return 6;
        }
    }
    
    private int getDevelopment(){
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
    
    public int generateCenterAndPowerForwardOverall(Player p){
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
    
    public int generateSmallForwardOverall(Player p){
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
    
    public void generateTendencies(Player p){
        Random rando = new Random();
        
        if(p.getPosition().equalsIgnoreCase("PG") || p.getPosition().equalsIgnoreCase("SG")){
            
            p.setDriveTendy(rando.nextInt(p.getDriveSkill()) + rando.nextInt(50) + 1);
            p.setDribbleDriveTendy(rando.nextInt(p.getDribblingSkill()) + rando.nextInt(50) + 1);
            p.setSpeedDriveTendy(rando.nextInt(p.getSpeedSkill()) + rando.nextInt(50) + 1);
            p.setPowerDriveTendy(rando.nextInt(p.getStrengthSkill()) + rando.nextInt(10) + 1);

            p.setDrivePassTendy(rando.nextInt(p.getPassingSkill()) + getValueWithinRange(25,100) + 1);
            p.setPassTendy(rando.nextInt(p.getPassingSkill()) + getValueWithinRange(50,100) + 1);

            // these 2 will use avg of shooting
            p.setDriveShootTendy(rando.nextInt(findShootingAvg(p)) + rando.nextInt(65) + 1);
            p.setCatchAndShootTendy(rando.nextInt(findShootingAvg(p)) + rando.nextInt(60) + 1);

            p.setShootCloseTendy(rando.nextInt(p.getCloseSkill()) + rando.nextInt(50) + 1);
            p.setShootMidTendy(rando.nextInt(p.getMidShotSkill()) + rando.nextInt(50) + 1);
            p.setShootThreeTendy(rando.nextInt(p.getThreeShotSkill()) + rando.nextInt(50) + 1);

            p.setInitTendy(p.getOverallRating()*2 + 1);
            p.setOffReboundTendy(rando.nextInt(p.getOffRebounding()) + rando.nextInt(10) + 1);
            p.setDefReboundTendy(rando.nextInt(p.getDefRebounding()) + rando.nextInt(10) + 1);
        }else if(p.getPosition().equalsIgnoreCase("PF") || p.getPosition().equalsIgnoreCase("C")){
            
            p.setDriveTendy(rando.nextInt(p.getDriveSkill()) + rando.nextInt(50) + 1);
            p.setDribbleDriveTendy(rando.nextInt(p.getDribblingSkill()) + rando.nextInt(10) + 1);
            p.setSpeedDriveTendy(rando.nextInt(p.getSpeedSkill()) + rando.nextInt(10) + 1);
            p.setPowerDriveTendy(rando.nextInt(p.getStrengthSkill()) + rando.nextInt(50) + 1);

            p.setDrivePassTendy(rando.nextInt(p.getPassingSkill()) + getValueWithinRange(25,60) + 1);
            p.setPassTendy(rando.nextInt(p.getPassingSkill()) + getValueWithinRange(50,75) + 1);

            // these 2 will use avg of shooting
            p.setDriveShootTendy(rando.nextInt(findShootingAvg(p)) + rando.nextInt(65) + 1);
            p.setCatchAndShootTendy(rando.nextInt(findShootingAvg(p)) + rando.nextInt(60) + 1);

            p.setShootCloseTendy(rando.nextInt(p.getCloseSkill()) + rando.nextInt(50) + 1);
            p.setShootMidTendy(rando.nextInt(p.getMidShotSkill()) + rando.nextInt(50) + 1);
            p.setShootThreeTendy(rando.nextInt(p.getThreeShotSkill()) + rando.nextInt(p.getThreeShotSkill()) + 1);

            p.setInitTendy(p.getOverallRating()*2 + 1);
            p.setOffReboundTendy(rando.nextInt(p.getOffRebounding()) + rando.nextInt(50) + 1);
            p.setDefReboundTendy(rando.nextInt(p.getDefRebounding()) + rando.nextInt(50) + 1);
        }else{ // sf
            
            p.setDriveTendy(rando.nextInt(p.getDriveSkill()) + rando.nextInt(50) + 1);
            p.setDribbleDriveTendy(rando.nextInt(p.getDribblingSkill()) + rando.nextInt(50) + 1);
            p.setSpeedDriveTendy(rando.nextInt(p.getSpeedSkill()) + rando.nextInt(50) + 1);
            p.setPowerDriveTendy(rando.nextInt(p.getStrengthSkill()) + rando.nextInt(50) + 1);

            p.setDrivePassTendy(rando.nextInt(p.getPassingSkill()) + getValueWithinRange(25,75) + 1);
            p.setPassTendy(rando.nextInt(p.getPassingSkill()) + getValueWithinRange(50,75) + 1);

            // these 2 will use avg of shooting
            p.setDriveShootTendy(rando.nextInt(findShootingAvg(p)) + rando.nextInt(65) + 1);
            p.setCatchAndShootTendy(rando.nextInt(findShootingAvg(p)) + rando.nextInt(60) + 1);

            p.setShootCloseTendy(rando.nextInt(p.getCloseSkill()) + rando.nextInt(50) + 1);
            p.setShootMidTendy(rando.nextInt(p.getMidShotSkill()) + rando.nextInt(50) + 1);
            p.setShootThreeTendy(rando.nextInt(p.getThreeShotSkill()) + rando.nextInt(50) + 1);

            p.setInitTendy(p.getOverallRating()*2 + 1);
            p.setOffReboundTendy(rando.nextInt(p.getOffRebounding()) + rando.nextInt(25) + 1);
            p.setDefReboundTendy(rando.nextInt(p.getDefRebounding()) + rando.nextInt(25) + 1);
        }
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
    
    private int findShootingAvg(Player p){
        return (p.getCloseSkill() + p.getMidShotSkill() + p.getThreeShotSkill()) / 3;
    }
      
    private int getValueWithinRange(int min, int max){
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }
    
    private int generateRating(int min, int max){
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }
    
    public Player generatePlayerByPosition(String position) {
        Player player;
        do {
            player = createRandomPlayer();
        } while (!player.getPosition().equalsIgnoreCase(position));

        return player;
    }
    
    private void generatePosition(Player p){
        String[] positions = {"PG", "SG", "SF", "PF", "C"};
        
        Random r = new Random();
        p.setPosition(positions[r.nextInt(5)]);
    }
    
    public void inflateRatings(Player p){
        Random r = new Random();
        int rarity = getDevelopment();
        
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
        }else if(p.getPosition().equalsIgnoreCase("SF")){
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

