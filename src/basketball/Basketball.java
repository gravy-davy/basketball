
package basketball;


public class Basketball {


    // This is the most recent, tryhard version.
    // NBA style league. Draft, then players either resign with a team or don't based off their personality. Then FA. Then new season. No trades.
    // FA = how desirable the player is. They'll be given a score, regenerated each year. FA goes day by day.
    public static void main(String[] args) {

        
        PlayerCreator pc = new PlayerCreator();
        
        Team t1 = new Team();
        Player t1PG = pc.generatePlayerByPosition("PG");
        Player t1SG = pc.generatePlayerByPosition("SG");
        Player t1SF = pc.generatePlayerByPosition("SF");
        Player t1PF = pc.generatePlayerByPosition("PF");
        Player t1C = pc.generatePlayerByPosition("C");
        
        t1.getSquad().add(t1PG);
        t1.getSquad().add(t1SG);
        t1.getSquad().add(t1SF);
        t1.getSquad().add(t1PF);
        t1.getSquad().add(t1C);
        
        Team t2 = new Team();
        Player t2PG = pc.generatePlayerByPosition("PG");
        Player t2SG = pc.generatePlayerByPosition("SG");
        Player t2SF = pc.generatePlayerByPosition("SF");
        Player t2PF = pc.generatePlayerByPosition("PF");
        Player t2C = pc.generatePlayerByPosition("C");
        
        t2.getSquad().add(t2PG);
        t2.getSquad().add(t2SG);
        t2.getSquad().add(t2SF);
        t2.getSquad().add(t2PF);
        t2.getSquad().add(t2C);
        
        Game game = new Game(t1,t2);
        game.simGame();
        
        System.out.println("t1 score: " + t1.getGameScore() + " - t2 score: " + t2.getGameScore());
        
        System.out.println("\n\n\n");
        
        for(Player p : t1.getSquad()){
            p.printPlayerStats();
        }
        
        System.out.println("\n\n\n");
        
        for(Player p : t2.getSquad()){
            p.printPlayerStats();
        }
        
    }
    
}
