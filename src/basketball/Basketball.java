
package basketball;


public class Basketball {


    // This is the most recent, tryhard version.
    // NBA style league. Draft, then players either resign with a team or don't based off their personality. Then FA. Then new season. No trades.
    // FA = how desirable the player is. They'll be given a score, regenerated each year. FA goes day by day.
    public static void main(String[] args) {

        
        PlayerCreator pc = new PlayerCreator();
        
        Team t1 = new Team();
        for(int k=0;k<5;k++){
            Player p = pc.createRandomPlayer();
            t1.getSquad().add(p);
        }
        
        Team t2 = new Team();
        for(int k=0;k<5;k++){
            Player p = pc.createRandomPlayer();
            t2.getSquad().add(p);
        }
        
        Game game = new Game(t1,t2);
        game.simGame();
        
        System.out.println("t1 score: " + t1.getGameScore() + " - t2 score: " + t2.getGameScore());
        
        System.out.println("\n\n\n");
        
        for(Player p : t1.getSquad()){
            p.printPlayerStats();
        }
        
        for(Player p : t2.getSquad()){
            p.printPlayerStats();
        }
        
    }
    
}
