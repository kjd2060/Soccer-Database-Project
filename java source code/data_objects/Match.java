package data_objects;

import java.util.Date;

public class Match {
    private int id;
    private Date date;
    private int homeScore;
    private int awayScore;
    private Team homeTeam;
    private Team awayTeam;

    public Match(int id,Date date, int homeScore, int awayScore, Team homeTeam, Team awayTeam) {
        this.id = id;
        this.date = date;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public int getId(){
        return id;
    }

    public Date getDate() {
        return date;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }
}
