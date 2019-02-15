package data_objects;

import java.util.ArrayList;

public class Team {
    private String name;
    private int wins;
    private int losses;
    private int ties;
    private Location location;
    private String league;
    private ArrayList<Player> players;
    private ArrayList<Staff> staff;

    public Team(String name, int wins, int losses, int ties, Location loc, String league, ArrayList<Player> players, ArrayList<Staff> staff) {
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
        this.location = loc;
        this.league = league;
        this.players = players;
        this.staff = staff;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getTies() {
        return ties;
    }

    public Location getLocation() {
        return location;
    }

    public String getLeague() {
        return league;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Staff> getStaff() {
        return staff;
    }
}
