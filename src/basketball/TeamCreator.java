
package basketball;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class TeamCreator {
    
    private static ArrayList<String> teamNames;

    public TeamCreator() {
        loadNames();
    }

    public static void loadNames() {
        teamNames = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("assets/team_names.txt"))){
            String line;
            while ((line = reader.readLine()) != null) {
                teamNames.add(line.trim());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public Team generateRandomTeam(){
        Team t = new Team();
        
        NewPlayerCreator pc = new NewPlayerCreator();
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
        
        // name and money now once players have contracts
        Random r = new Random();
        t.setName(teamNames.get(r.nextInt(teamNames.size())));
        
        int sum = 0;
        for(Player p : t.getRoster()){
            sum+= p.getContract().getSalary();
        }
        
        t.setMoneyTotal(sum + r.nextInt(50));
        t.setMoneyAvailable(t.getMoneyTotal() - sum);
        
        t.setName(teamNames.get(r.nextInt(teamNames.size())));
        
        return t;
    }
    
}
