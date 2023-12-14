
package basketball;


public class PlayoffMatchup {
    
    private Team t1;
    private Team t2;
    
    private int t1Wins;
    private int t2Wins;

    public PlayoffMatchup(Team t1, Team t2, int t1Wins, int t2Wins) {
        this.t1 = t1;
        this.t2 = t2;
        this.t1Wins = t1Wins;
        this.t2Wins = t2Wins;
        System.out.println("t1 wins = " + t1Wins + " - t2 wins = " + t2Wins);
    }

    public Team getT1() {
        return t1;
    }

    public void setT1(Team t1) {
        this.t1 = t1;
    }

    public Team getT2() {
        return t2;
    }

    public void setT2(Team t2) {
        this.t2 = t2;
    }

    public int getT1Wins() {
        return t1Wins;
    }

    public void setT1Wins(int t1Wins) {
        this.t1Wins = t1Wins;
    }

    public int getT2Wins() {
        return t2Wins;
    }

    public void setT2Wins(int t2Wins) {
        this.t2Wins = t2Wins;
    }
    
    
    
}
