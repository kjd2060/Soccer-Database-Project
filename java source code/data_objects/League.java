package data_objects;

public class League {
    private String name;
    private int numTeams;
    private Team firstPlace;
    private Match lastMatch;

    public League(String name, int numTeams, Team firstPlace, Match lastMatch) {
        this.name = name;
        this.numTeams = numTeams;
        this.firstPlace = firstPlace;
        this.lastMatch = lastMatch;
    }

    public String getName() {
        return name;
    }

    public int getNumTeams() {
        return numTeams;
    }

    public Team getFirstPlace() {
        return firstPlace;
    }

    public Match getLastMatch() {
        return lastMatch;
    }
}
