package data_objects;

public class Player {

    private String fname;
    private String minit;
    private String lname;
    private int goals;
    private int playerNum;
    private String position;
    private int caps;
    private String team;

    public Player(String fname, String minit, String lname, int goals, int playerNum, String position, int caps, String team) {
        this.fname = fname;
        this.minit = minit;
        this.lname = lname;
        this.goals = goals;
        this.playerNum = playerNum;
        this.position = position;
        this.caps = caps;
        this.team = team;
    }

    public String getFname() {
        return fname;
    }

    public String getMinit() {
        return minit;
    }

    public String getLname() {
        return lname;
    }

    public int getGoals() {
        return goals;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public String getPosition() {
        return position;
    }

    public int getCaps() {
        return caps;
    }

    public String getTeam() {
        return team;
    }
}
