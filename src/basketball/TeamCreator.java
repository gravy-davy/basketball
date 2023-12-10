
package basketball;


public class TeamCreator {
    
    public Team generateRandomTeam(){
        Team t = new Team();
        
        PlayerCreator pc = new PlayerCreator();
        t.getRoster().add(pc.generatePlayerByPosition("PG"));
        t.getRoster().add(pc.generatePlayerByPosition("PG"));
        
        t.getRoster().add(pc.generatePlayerByPosition("SG"));
        t.getRoster().add(pc.generatePlayerByPosition("SG"));
        
        t.getRoster().add(pc.generatePlayerByPosition("SF"));
        t.getRoster().add(pc.generatePlayerByPosition("SF"));
        
        t.getRoster().add(pc.generatePlayerByPosition("PF"));
        t.getRoster().add(pc.generatePlayerByPosition("PF"));
        
        t.getRoster().add(pc.generatePlayerByPosition("C"));
        t.getRoster().add(pc.generatePlayerByPosition("C"));
        
        return t;
    }
    
}
