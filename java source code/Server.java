import data_objects.*;

import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Server {


    private static Connection createConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false";
        String user = "root";
        String password = "password";
        return DriverManager.getConnection(url, user, password);
    }

    private static void executeQuery(sqlQuery query){
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = createConnection();
            st = con.createStatement();
            rs = query.execute(st);
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (Exception e) { /* ignored */ }
            try {
                st.close();
            } catch (Exception e) { /* ignored */ }
            try {
                con.close();
            } catch (Exception e) { /* ignored */ }
        }
    }

    public static ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try {
                    ResultSet rs = st.executeQuery("SELECT * FROM player");
                    while (rs.next()) {
                        Player p = new Player(rs.getString(1).trim(), rs.getString(2).trim(), rs.getString(3).trim(),
                                rs.getInt(4), rs.getInt(5), rs.getString(6).trim(), rs.getInt(7), rs.getString(8).trim());
                        players.add(p);
                    }
                    return rs;
                }catch (SQLException e){
                    e.printStackTrace();
                    return null;
                }
            }
        });
        return players;
    }

    public static Player getPlayer(Team team,String fname, String lname) {
        Player players[] = {null};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try {
                    String query = String.format("SELECT * FROM player WHERE fname='%s' and lname='%s' and team_Name='%s'",fname,lname,team.getName().trim());
                    ResultSet rs = st.executeQuery(query);
                    if(rs.next()) {
                        Player p = new Player(rs.getString(1).trim(), rs.getString(2).trim(), rs.getString(3).trim(),
                                rs.getInt(4), rs.getInt(5), rs.getString(6).trim(), rs.getInt(7), rs.getString(8).trim());
                        players[0] = p;
                    }
                    return rs;
                }catch (SQLException e){
                    e.printStackTrace();
                    return null;
                }
            }
        });
        return players[0];
    }

    public static ArrayList<Player> getPlayers(String team) {
        ArrayList<Player> players = new ArrayList<>();
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try {
                    ResultSet rs = st.executeQuery("SELECT * FROM player WHERE player.team_Name='"+team+"'");
                    while (rs.next()) {
                        Player p = new Player(rs.getString(1).trim(), rs.getString(2).trim(), rs.getString(3).trim(),
                                rs.getInt(4), rs.getInt(5), rs.getString(6).trim(), rs.getInt(7), rs.getString(8).trim());
                        players.add(p);
                    }
                    return rs;
                }catch (SQLException e){
                    e.printStackTrace();
                    return null;
                }
            }
        });
        return players;
    }

    public static ArrayList<Staff> getStaff() {
        ArrayList<Staff> staff = new ArrayList<>();
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try {
                    ResultSet rs = st.executeQuery("SELECT * FROM staff");
                    while (rs.next()) {
                        Staff s = new Staff(rs.getString(1).trim(), rs.getString(2).trim(), rs.getString(3).trim(),
                                rs.getString(4).trim(), rs.getString(5).trim());
                        staff.add(s);
                    }
                    return rs;
                }catch (SQLException e){
                    e.printStackTrace();
                    return null;
                }
            }
        });
        return staff;
    }

    public static Staff getStaff(Team team,String fname, String lname) {
        Staff staff[] = {null};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try {
                    String query = String.format("SELECT * FROM staff WHERE fname='%s' and lname='%s' and team_Name='%s'",fname,lname,team.getName().trim());
                    ResultSet rs = st.executeQuery(query);
                    if(rs.next()) {
                        Staff s = new Staff(rs.getString(1).trim(), rs.getString(2).trim(), rs.getString(3).trim(),
                                rs.getString(4).trim(), rs.getString(5).trim());
                        staff[0] = s;
                    }
                    return rs;
                }catch (SQLException e){
                    e.printStackTrace();
                    return null;
                }
            }
        });
        return staff[0];
    }

    public static ArrayList<Staff> getStaff(String team) {
        ArrayList<Staff> staff = new ArrayList<>();
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try {
                    ResultSet rs = st.executeQuery("SELECT * FROM staff WHERE staff.team_Name='"+team+"' UNION SELECT * FROM staff WHERE team_Name='"+team+"'");
                    while (rs.next()) {
                        Staff s = new Staff(rs.getString(1).trim(), rs.getString(2).trim(), rs.getString(3).trim(),
                                rs.getString(4).trim(), rs.getString(5).trim());
                        staff.add(s);
                    }
                    return rs;
                }catch (SQLException e){
                    e.printStackTrace();
                    return null;
                }
            }
        });
        return staff;
    }

    public static Location getLocation(int id){
        final Location[] loc = {null};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    ResultSet rs = st.executeQuery("SELECT * FROM location WHERE loc_id="+id);
                    if(rs.next())
                        loc[0] = new Location(rs.getInt(3),rs.getString(1).trim(),rs.getString(2).trim());
                    return rs;
                }catch (SQLException e){
                    e.printStackTrace();
                    return null;
                }
            }
        });
        return loc[0];
    }

    public static ArrayList<Location> getLocations(){
        ArrayList<Location> locations = new ArrayList<>();
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    ResultSet rs = st.executeQuery("SELECT * FROM location");
                    while(rs.next())
                        locations.add(new Location(rs.getInt(3),rs.getString(1).trim(),rs.getString(2).trim()));
                    return rs;
                }catch (SQLException e){
                    e.printStackTrace();
                    return null;
                }
            }
        });
        return locations;
    }

    public static ArrayList<Team> getTeams(){
        ArrayList<Team> teams = new ArrayList<>();
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    ResultSet rs = st.executeQuery("SELECT * FROM team");
                    while(rs.next()){
                        String teamName = rs.getString(1).trim();
                        ArrayList<Player> players = getPlayers(teamName);
                        ArrayList<Staff> staff = getStaff(teamName);
                        Team t = new Team(teamName,rs.getInt(2),rs.getInt(3),rs.getInt(4),
                                getLocation(rs.getInt(5)),rs.getString(6).trim(),players,staff);
                        teams.add(t);
                    }
                    return rs;
                }catch (SQLException e){
                    e.printStackTrace();
                    return null;
                }
            }
        });
        return teams;
    }

    public static Team getTeam(String teamName){
        Team teams[] = {null};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    ResultSet rs = st.executeQuery("SELECT * FROM team WHERE Name='"+teamName+"'");
                    while(rs.next()){
                        String teamName = rs.getString(1).trim();
                        ArrayList<Player> players = getPlayers(teamName);
                        ArrayList<Staff> staff = getStaff(teamName);
                        Team t = new Team(teamName,rs.getInt(2),rs.getInt(3),rs.getInt(4),
                                getLocation(rs.getInt(5)),rs.getString(6).trim(),players,staff);
                        teams[0] = t;
                    }
                    return rs;
                }catch (SQLException e){
                    e.printStackTrace();
                    return null;
                }
            }
        });
        return teams[0];
    }

    // TODO: clean database entries of /r and /n
    public static ArrayList<Match> getMatches(){
        ArrayList<Match> matches = new ArrayList<>();
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    ResultSet rs = st.executeQuery("SELECT idmatch,hscore,ascore,date," +
                            "plays_home.team_Name as 'home_team',plays_away.team_Name as 'away_team' FROM `match`\n" +
                            "INNER JOIN plays_away on `match`.idmatch = plays_away.match_idmatch " +
                            "INNER JOIN plays_home on `match`.idmatch = plays_home.match_idmatch");
                    while(rs.next()){
                        String homeTeam = rs.getString(5).trim(); // removes \r or \n at end
                        String awayTeam = rs.getString(6).trim();
                        Date date = rs.getDate(4);
                        int hScore = rs.getInt(2);
                        int aScore = rs.getInt(3);
                        int id = rs.getInt(1);

                        Team home = getTeam(homeTeam);
                        Team away = getTeam(awayTeam);
                        matches.add(new Match(id,date,hScore,aScore,home,away));
                    }
                    return rs;
                }catch(SQLException e){
                    e.printStackTrace();
                    return null;
                }
            }
        });
        return matches;
    }

    public static Match getMatch(int id){
        Match matches[] = {null};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    ResultSet rs = st.executeQuery("SELECT idmatch,hscore,ascore,date," +
                            "plays_home.team_Name AS 'home_team',plays_away.team_Name AS 'away_team' FROM `match`\n" +
                            "INNER JOIN plays_away ON `match`.idmatch = plays_away.match_idmatch " +
                            "INNER JOIN plays_home ON `match`.idmatch = plays_home.match_idmatch " +
                            "WHERE idmatch="+id);
                    while(rs.next()){
                        String homeTeam = rs.getString(5).trim(); // removes \r or \n at end
                        String awayTeam = rs.getString(6).trim();
                        Date date = rs.getDate(4);
                        int hScore = rs.getInt(2);
                        int aScore = rs.getInt(3);
                        int id = rs.getInt(1);

                        Team home = getTeam(homeTeam);
                        Team away = getTeam(awayTeam);
                        matches[0] = new Match(id,date,hScore,aScore,home,away);
                    }
                    return rs;
                }catch(SQLException e){
                    e.printStackTrace();
                    return null;
                }
            }
        });
        return matches[0];
    }

    public static ArrayList<League> getLeagues(){
        ArrayList<League> league = new ArrayList<>();
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    ResultSet rs = st.executeQuery("select * from league");
                    while(rs.next()){
                        String leagueName = rs.getString(1).trim();
                        int numTeam = rs.getInt(2);
                        String teamName = rs.getString(3);
                        if(!(teamName == null) && !teamName.isEmpty())
                            teamName = teamName.trim();
                        int lastMatchID = rs.getInt(4);
                        Team firstPlace = getTeam(teamName);
                        Match lastMatch = getMatch(lastMatchID);

                        league.add(new League(leagueName,numTeam,firstPlace,lastMatch));
                    }
                    return rs;
                }catch (SQLException e){
                    e.printStackTrace();
                    return null;
                }
            }
        });
        return  league;
    }

    public static League getLeague(String leagueName){
        ArrayList<League> league = new ArrayList<>();
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    ResultSet rs = st.executeQuery("SELECT * from league WHERE name='"+leagueName+"'");
                    while(rs.next()){
                        String leagueName = rs.getString(1).trim();
                        int numTeam = rs.getInt(2);
                        String teamName = rs.getString(3).trim();
                        int lastMatchID = rs.getInt(4);

                        Team firstPlace = getTeam(teamName);
                        Match lastMatch = getMatch(lastMatchID);

                        league.add(new League(leagueName,numTeam,firstPlace,lastMatch));
                    }
                    return rs;
                }catch (SQLException e){
                    e.printStackTrace();
                    return null;
                }
            }
        });
        return league.get(0);
    }

    public static int createLeague(League league){
        int affected[] = {0};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    String first = league.getFirstPlace() != null ? league.getFirstPlace().getName() : null;
                    int lastMatch = league.getLastMatch() != null ? league.getLastMatch().getId() : -1;

                    if(lastMatch != -1) {
                        if(first != null) {
                            String query = String.format("INSERT INTO league(name, NumTeams, `1st_place`, match_idmatch) VALUES ('%s',%d,'%s',%d)"
                                    , league.getName(), league.getNumTeams(), first, lastMatch);
                            affected[0] = st.executeUpdate(query);
                        }else{
                            String query = String.format("INSERT INTO league(name, NumTeams,match_idmatch) VALUES ('%s',%d,%d)"
                                    , league.getName(), league.getNumTeams(),lastMatch);
                            affected[0] = st.executeUpdate(query);
                        }
                    }else{
                        if(first != null) {
                            String query = String.format("INSERT INTO league(name, NumTeams, `1st_place`) VALUES ('%s',%d,'%s')"
                                    , league.getName(), league.getNumTeams(), first);
                            affected[0] = st.executeUpdate(query);
                        }else{
                            String query = String.format("INSERT INTO league(name, NumTeams) VALUES ('%s',%d)"
                                    , league.getName(), league.getNumTeams());
                            affected[0] = st.executeUpdate(query);
                        }
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        });

        return affected[0];
    }

    public static int createPlayer(Player p){
        int affected[] = {0};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    if(p.getMinit() != null){
                        String query = String.format("INSERT INTO player(fname, minit, lname, goals, player_num, position, caps, team_Name) VALUES('%s','%s','%s','%d','%d','%s','%d','%s')",p.getFname(),p.getMinit(),p.getLname(),p.getGoals(),p.getPlayerNum(),p.getPosition(),p.getCaps(),p.getTeam());
                        affected[0] = st.executeUpdate(query);
                    }else if(p.getMinit() == null){
                        String query = String.format("INSERT INTO player(fname, minit, lname, goals, player_num, position, caps, team_Name) VALUES('%s','','%s','%d','%d','%s','%d','%s')",p.getFname(),p.getLname(),p.getGoals(),p.getPlayerNum(),p.getPosition(),p.getCaps(),p.getTeam());
                        affected[0] = st.executeUpdate(query);
                    }

                }catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        });

        return affected[0];
    }

    public static int createTeam(Team t){
        int affected[] = {0};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    String query = String.format("INSERT INTO team(Name, Wins, Losses, Ties, location_loc_id, league_name) VALUES('%s','%d','%d','%d','%d','%s')",t.getName(),t.getWins(),t.getLosses(),t.getTies(),t.getLocation().getId(),t.getLeague());
                    affected[0] = st.executeUpdate(query);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        });

        return affected[0];
    }

    public static int createStaff(Staff s){
        int affected[] = {0};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    if(s.getMinit() != null){
                        String query = String.format("INSERT INTO staff(fname, minit, lname, role, team_Name) VALUES('%s','%s','%s','%s','%s')", s.getFname(),s.getMinit(),s.getLname(),s.getRole(),s.getTeam());
                        affected[0] = st.executeUpdate(query);
                    }else if(s.getMinit() == null){
                        String query = String.format("INSERT INTO staff(fname, minit, lname, role, team_Name) VALUES('%s','','%s','%s','%s')", s.getFname(),s.getLname(),s.getRole(),s.getTeam());
                        affected[0] = st.executeUpdate(query);
                    }

                }catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        });

        return affected[0];
    }

    public static int createMatch(Match m){
        int affected[] = {0};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String date = dateFormat.format(m.getDate());

                    String query = String.format("INSERT INTO mydb.match(hscore,ascore,date) VALUES('%d','%d','%s')", m.getHomeScore(),m.getAwayScore(),date);
                    affected[0] += st.executeUpdate(query);

                    ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()");
                    if(rs.next()){
                        int id = rs.getInt(1);
                        query = String.format("INSERT INTO plays_home(match_idmatch, team_name) VALUES('%d','%s')",id, m.getHomeTeam().getName());
                        affected[0] = affected[0] + st.executeUpdate(query);
                        query = String.format("INSERT INTO plays_away(match_idmatch, team_Name) VALUES('%d','%s')", id,m.getAwayTeam().getName());
                        affected[0] = affected[0] + st.executeUpdate(query);
                    }else{
                        throw new SQLException(); // Probably a better exception should be used, but oh well
                    }

                }catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        });

        return affected[0];
    }

    public static int createLocation(Location l){
        int affected[] = {0};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    String query = String.format("INSERT INTO location(Country, City) VALUES('%s','%s')",l.getCountry(),l.getCity());
                    affected[0] = st.executeUpdate(query);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        });

        return affected[0];
    }

    public static int deleteLeague(String name){
        int affected[] = {0};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    affected[0] = st.executeUpdate("DELETE from league where name = '"+name.trim()+"'");
                }catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        });

        return affected[0];
    }

    public static int deletePlayer(String fname, String lname){
        int affected[] = {0};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    affected[0] = st.executeUpdate("DELETE from player where fname='"+fname.trim()+"' and lname='"+lname.trim()+"'");
                }catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        });

        return affected[0];
    }

    public static int deleteTeam(String name){
        int affected[] = {0};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    affected[0] = st.executeUpdate("DELETE from team where Name = '"+name.trim()+"'");
                }catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        });

        return affected[0];
    }

    public static int deleteStaff(String fname, String lname){
        int affected[] = {0};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    affected[0] = st.executeUpdate("DELETE from staff where fname='"+fname.trim()+"' and lname='"+lname.trim()+"'");
                }catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        });

        return affected[0];
    }

    public static int deleteMatch(int id){
        int affected[] = {0};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    affected[0] = st.executeUpdate("DELETE from `match` where idmatch="+id);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        });

        return affected[0];
    }

    public static int deleteLocation(int id){
        int affected[] = {0};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    affected[0] = st.executeUpdate("DELETE from location where loc_id="+id);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        });

        return affected[0];
    }

    public static int modifyLeague(League league,String oldName){
        int affected[] = {0};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    String first = league.getFirstPlace()!= null ? league.getFirstPlace().getName().trim() : null;
                    String name = league.getName();
                    int numTeams = league.getNumTeams();
                    int lastMatch = league.getLastMatch() != null ? league.getLastMatch().getId() : -1;

                    affected[0] = st.executeUpdate("UPDATE league SET name='"+name+"',NumTeams="+numTeams+" WHERE name='"+oldName+"'");

                    // optional last match
                    if(lastMatch != -1){
                        affected[0] = st.executeUpdate("UPDATE league SET match_idmatch="+lastMatch+" where name='"+name+"'");
                    }else{
                        affected[0] = st.executeUpdate("UPDATE league SET match_idmatch=NULL where name='"+name+"'");
                    }
                    // optional first place
                    if(first != null) {
                        affected[0] = st.executeUpdate("UPDATE league SET `1st_place`='" + first + "'" + " where name='" + name + "'");
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        });

        return affected[0];
    }

    public static int modifyPlayer(Player player,String oldFName,String oldLName){
        int affected[] = {0};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    String fname = player.getFname();
                    String minit = player.getMinit();
                    String lname = player.getLname();
                    int goals = player.getGoals();
                    int pnum = player.getPlayerNum();
                    String position = player.getPosition();
                    int caps = player.getCaps();
                    String team = player.getTeam();

                    String query = String.format("UPDATE player SET fname='%s',lname='%s',goals=%d,player_num=%d,position='%s',caps=%d,team_Name='%s' " +
                                    "WHERE fname='%s' AND lname='%s'"
                            ,fname,lname,goals,pnum,position,caps,team,oldFName,oldLName);
                    affected[0] = st.executeUpdate(query);
                    // Optional middle initial
                    if(minit != null){
                        affected[0] = st.executeUpdate("UPDATE player SET minit='"+minit+"' where fname='"+fname+"' AND lname='"+lname+"'");
                    }else{
                        affected[0] = st.executeUpdate("UPDATE player SET minit='' where fname='"+fname+"' AND lname='"+lname+"'");
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        });

        return affected[0];
    }

    public static int modifyTeam(Team team,String oldName){
        int affected[] = {0};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    String name = team.getName();
                    int wins = team.getWins();
                    int losses = team.getLosses();
                    int ties = team.getTies();
                    int loc = team.getLocation().getId();
                    String league = team.getLeague();

                    String query= String.format("UPDATE team SET Name='%s',Wins=%d,Losses=%d,Ties=%d,location_loc_id=%d,league_name='%s'" +
                            "WHERE Name='%s'",name,wins,losses,ties,loc,league,oldName);
                    affected[0] = st.executeUpdate(query);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        });

        return affected[0];
    }

    public static int modifyStaff(Staff staff, String oldFName, String oldLName){
        int affected[] = {0};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    String fname = staff.getFname();
                    String minit = staff.getMinit();
                    String lname = staff.getLname();
                    String role = staff.getRole();
                    String team = staff.getTeam();

                    String query = String.format("UPDATE staff SET fname='%s',lname='%s',role='%s',team_Name='%s' " +
                                    "WHERE fname='%s' AND lname='%s'"
                            ,fname,lname,role,team,oldFName,oldLName);
                    affected[0] = st.executeUpdate(query);

                    // Optional middle initial
                    if(minit != null){
                        affected[0] = st.executeUpdate("UPDATE staff SET minit='"+minit+"' where fname='"+fname+"' AND lname='"+lname+"'");
                    }else{
                        affected[0] = st.executeUpdate("UPDATE staff SET minit='' where fname='"+fname+"' AND lname='"+lname+"'");
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        });

        return affected[0];
    }

    public static int modifyMatch(Match m, int id){
        int affected[] = {0};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String date = dateFormat.format(m.getDate());

                    String query = String.format("UPDATE `match` SET hscore=%d,ascore=%d,date='%s' WHERE idmatch=%d",m.getHomeScore(),m.getAwayScore(),date,id);
                    affected[0] = st.executeUpdate(query);

                    affected[0] += st.executeUpdate("UPDATE plays_home SET team_Name='"+m.getHomeTeam().getName().trim()+"' where match_idmatch="+id);
                    affected[0] += st.executeUpdate("UPDATE plays_away SET team_Name='"+m.getAwayTeam().getName().trim()+"' where match_idmatch="+id);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        });

        return affected[0];
    }

    public static int modifyLocation(Location l,int id){
        int affected[] = {0};
        executeQuery(new sqlQuery() {
            @Override
            public ResultSet execute(Statement st) {
                try{
                    String query = String.format("UPDATE location SET Country='%s',City='%s' WHERE loc_id=%d",l.getCountry(),l.getCity(),id);
                    affected[0] = st.executeUpdate(query);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                return null;
            }
        });

        return affected[0];
    }
    /*
    public static void main(String args[]) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1988);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        java.util.Date dateRepresentation = cal.getTime();

        Team home = getTeam("Brazil");
        Team away = getTeam("Germany");
        int b = createMatch(new Match(1,dateRepresentation, 1, 7, home, away));
        System.out.println(b);
    }*/
    private interface sqlQuery {
        ResultSet execute(Statement st);
    }
}