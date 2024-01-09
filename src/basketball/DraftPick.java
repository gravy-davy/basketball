
package basketball;


public class DraftPick {
    
    private Team team;
    private Player player;

    public DraftPick(Team team, Player player) {
        this.team = team;
        this.player = player;
    }

    
    
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    
    
}
