import data_objects.*;

import java.util.Scanner;
import java.util.ArrayList;

class ui {

    private static String userString = "What type of user are you (q to quit)?\nPlayer\nCoach\nManager\nLeague";
    private static String noActionPermission = "You don't have permission to do that";
    private static String subMenuUsage = "Please enter a number between 1-7";
    private static Scanner s;
    private static Server server;

    private static int main_menu(boolean viewOnly) {
        System.out.print("What would you like to");
        if (!viewOnly) System.out.print(" modify or");
        System.out.println(" view? (Enter a number 1-7)\n" +
                "\t1. Locations\n" +
                "\t2. Leagues\n" +
                "\t3. Teams\n" +
                "\t4. Players\n" +
                "\t5. Staff\n" +
                "\t6. Matches\n" +
                "\t7. Exit");
        return s.nextInt();
    }

    private static int sub_menu() {
        System.out.println("Would you like to\n" +
                "\t1. Create an entry\n" +
                "\t2. Edit an entry\n" +
                "\t3. Delete an entry\n" +
                "\t4. View the table\n" +
                "\t5. Go back");
        return s.nextInt();
    }

    private static String getInput(){
        String input;
        while(true) {
            input = s.next();
            if (!input.equals("")) {
                return input;
            }
        }
    }

    private static String getInput(String helpMessage){
        String input;
        while(true) {
            System.out.println(helpMessage);
            input = s.next();
            if (!input.equals("")) {
                return input;
            }
        }
    }

    private static String getInputLine(String helpMessage){
        String input;
        while(true) {
            System.out.println(helpMessage);
            input = s.nextLine();
            if (!input.equals("")) {
                return input;
            }
        }
    }

    private static String getInputLine(){
        String input;
        while(true) {
            input = s.nextLine();
            if (!input.equals("")) {
                return input;
            }
        }
    }

    private static String getInput(String helpMessage, String blockInput){
        String input;
        while(true) {
            System.out.println(helpMessage);
            input = s.next();
            if (!input.equals("") && !input.equals(blockInput)) {
                return input;
            }
        }
    }

    private static String getInput(String helpMessage, char[] allowedVals){
        String input;
        while(true) {
            System.out.println(helpMessage);
            input = s.next();
            for(char c : allowedVals){
                if (!input.equals("") && input.contains("" + c)) {
                    return input;
                }
            }
        }
    }

    private static int getInput(String helpMessage, int input){
        while(true) {
            System.out.println(helpMessage);
            if(s.hasNextInt()){
                input = s.nextInt();
                return input;
            }
        }
    }

    private static int getInput(int input){
        while(true) {
            if(s.hasNextInt()){
                input = s.nextInt();
                return input;
            }
        }
    }


    private static void run_player(){
        // view only
        int action;
        boolean loop = true;
        while (loop) {
            int choice = main_menu(true);
            switch( choice ){
                case 1:
                    action = sub_menu();
                    switch( action ) {
                        case 4:
                            ArrayList<data_objects.Location> locations = server.getLocations();
                            for (data_objects.Location loc : locations) {
                                System.out.println(loc.getCity() + ", " + loc.getCountry());
                            }
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println(noActionPermission);
                            break;
                    }
                    break;
                case 2:
                    action = sub_menu();
                    switch( action ) {
                        case 4:
                            ArrayList<data_objects.League> leagues = server.getLeagues();
                            for (data_objects.League l : leagues) {
                                System.out.println(l.getName());
                                System.out.println("\t"+l.getNumTeams());
                                System.out.println("\t"+l.getFirstPlace().getName());
                                System.out.println("\t"+l.getLastMatch());
                            }
                            break;
                        default:
                            System.out.println(noActionPermission);
                            break;
                    }
                    break;
                case 3:
                    action = sub_menu();
                    switch( action ) {
                        case 4:
                            ArrayList<data_objects.Team> teams = server.getTeams();
                            for (data_objects.Team t : teams) {
                                System.out.println(t.getName());
                                System.out.println("\t"+t.getWins()+"/"+t.getLosses()+"/"+t.getTies());
                                System.out.println("\t"+t.getLocation().getCity()+","+t.getLocation().getCountry());
                                System.out.println("\t"+t.getLeague());
                                System.out.println("\tplayers: "+t.getPlayers().size()+" staff: "+t.getStaff().size());
                            }
                            break;
                        default:
                            System.out.println(noActionPermission);
                            break;
                    }
                    break;
                case 4:
                    action = sub_menu();
                    switch( action ) {
                        case 4:
                            ArrayList<data_objects.Player> players = server.getPlayers("Germany"); // TODO: need to pick a team to narrow
                            for (data_objects.Player p : players) {
                                System.out.println(p.getFname() + " " + p.getMinit() + " " + p.getLname());
                                System.out.println("\t" + p.getPosition());
                                System.out.println("\t" + p.getTeam());
                                System.out.println("\t" + p.getCaps());
                                System.out.println("\t" + p.getGoals());
                                System.out.println("\t" + p.getPlayerNum());
                            }
                            break;
                        default:
                            System.out.println(noActionPermission);
                            break;
                    }
                    break;
                case 5:
                    action = sub_menu();
                    switch( action ) {
                        case 4:
                            ArrayList<data_objects.Staff> staffs = server.getStaff("Germany");
                            for (data_objects.Staff s : staffs) {
                                System.out.println(s.getFname() + " " + s.getMinit() + " " + s.getLname());
                                System.out.println("\t" + s.getRole());
                                System.out.println("\t" + s.getTeam());
                            }
                            break;
                        default:
                            System.out.println(noActionPermission);
                            break;
                    }
                    break;
                case 6:
                    action = sub_menu();
                    switch( action ) {
                        case 4:
                            ArrayList<data_objects.Match> matches = server.getMatches();
                            for(data_objects.Match m : matches){
                                System.out.println(m.getDate());
                                System.out.println("\t" + m.getHomeScore() + " - " + m.getAwayScore());
                                System.out.println("\t" + m.getHomeTeam().getName() + " vs. " + m.getAwayTeam().getName());
                            }
                            break;
                        default:
                            System.out.println(noActionPermission);
                            break;
                    }
                    break;
                case 7:
                    loop = false;
                    System.out.println(userString);
                    break;
                default:
                    break;
            }
        }
        // display choice
    }

    private static void run_manager(){
        System.out.println("What team do you manage?");
        ArrayList<Team> teams = server.getTeams();
        for(Team t : teams){
            System.out.println(t.getName());
            System.out.println("\t" + t.getLocation());
            System.out.println("\t" + t.getLeague());
        }
        String teamIManage = getInput();
        Team myTeam = server.getTeam(teamIManage);

        boolean loop = true;
        int action;
        String input;
        // full access
        while (loop) {
            int choice = main_menu(false);
            switch (choice) {
                case 1: // location
                    System.out.println(noActionPermission);
                    break;
                case 2: // league
                    System.out.println(noActionPermission);
                    break;
                case 3: // team
                    action = sub_menu();
                    switch( action ) {
                        case 2:
                            // edit an entry
                            server.modifyTeam(myTeam,myTeam.getName());
                            break;
                        case 4:
                            teams = server.getTeams();
                            for (data_objects.Team t : teams) {
                                System.out.println(t.getName());
                                System.out.println("\t"+t.getWins()+"/"+t.getLosses()+"/"+t.getTies());
                                System.out.println("\t"+t.getLocation().getCity()+","+t.getLocation().getCountry());
                                System.out.println("\t"+t.getLeague());
                                System.out.println("\tplayers: "+t.getPlayers().size()+" staff: "+t.getStaff().size());
                            }
                            break;
                        default:
                            System.out.println(noActionPermission);
                            break;
                    }
                    break;
                case 4: // player
                    action = sub_menu();
                    Player player;
                    String fname,lname,minit,pos;
                    int goals,playerNum,caps;
                    switch( action ) {
                        case 1:
                            // create an entry
                            System.out.println("Enter player details:");
                            fname = getInput("First name: ");
                            lname = getInput("Last name: ");
                            minit = getInput("Middle initial(enter NA if NA): ");
                            goals = getInput("Goals: ",0);
                            playerNum = getInput("Player Number: ",0);
                            pos = getInput("Position(enter initials): ");
                            caps = getInput("Caps: ",0);

                            if(minit.toLowerCase().equals("na")) minit = null;
                            player = new Player(fname,minit,lname,goals,playerNum,pos,caps,myTeam.getName());
                            server.createPlayer(player);
                            break;
                        case 2:
                            // edit an entry
                            System.out.println("Enter player details:");
                            String oldFname = getInput("First name: ");
                            String oldLname = getInput("Last name: ");
                            player = server.getPlayer(myTeam,oldFname,oldLname);
                            if(player == null){
                                System.out.println("Player not found");
                                break;
                            }


                            System.out.println("Enter new details:");
                            fname = getInput("First name: ");
                            lname = getInput("Last name: ");
                            minit = getInput("Middle initial(enter NA if NA): ");
                            goals = getInput("Goals: ",0);
                            playerNum = getInput("Player Number: ",0);
                            pos = getInput("Position(enter initials): ");
                            caps = getInput("Caps: ",0);

                            if(minit.toLowerCase().equals("na")) minit = null;
                            player = new Player(fname,minit,lname,goals,playerNum,pos,caps,myTeam.getName());
                            server.modifyPlayer(player,oldFname,oldLname);
                            break;
                        case 3:
                            // delete an entry
                            String fName;
                            String lName;
                            System.out.println("Which player do you want to delete?  Enter their first name, then their last name.");
                            ArrayList<data_objects.Player> players = server.getPlayers(myTeam.getName()); // TODO: need to pick a team to narrow
                            for (data_objects.Player p : players) {
                                System.out.println(p.getFname() + " " + p.getMinit() + " " + p.getLname());
                            }
                            fName = getInput("First name: ");
                            lName = getInput("Last name: ");
                            server.deletePlayer(fName, lName);
                            break;
                        case 4:
                            players = server.getPlayers(myTeam.getName()); // TODO: need to pick a team to narrow
                            for (data_objects.Player p : players) {
                                System.out.println(p.getFname() + " " + p.getMinit() + " " + p.getLname());
                                System.out.println("\t" + p.getPosition());
                                System.out.println("\t" + p.getTeam());
                                System.out.println("\t" + p.getCaps());
                                System.out.println("\t" + p.getGoals());
                                System.out.println("\t" + p.getPlayerNum());
                            }
                            break;
                        default:
                            System.out.println(noActionPermission);
                            break;
                    }
                    break;
                case 5: // staff
                    Staff staff;
                    String role;
                    action = sub_menu();
                    switch( action ) {
                        case 1:
                            // create an entry
                            System.out.println("Enter staff details:");
                            fname = getInput("First name: ");
                            lname = getInput("Last name: ");
                            minit = getInput("Middle initial(enter NA if NA): ");
                            role = getInput("Role: ");

                            if(minit.toLowerCase().equals("na"))
                                minit = null;
                            staff = new Staff(fname,minit,lname,role,myTeam.getName());
                            server.createStaff(staff);
                            break;
                        case 2:
                            // edit an entry
                            System.out.println("Enter staff details:");
                            String oldFname = getInput("First name: ");
                            String oldLname = getInput("Last name: ");

                            staff = server.getStaff(myTeam,oldFname,oldLname);
                            if(staff == null){
                                System.out.println("Staff member not found");
                                break;
                            }

                            System.out.println("Enter new staff details:");
                            fname = getInput("First name: ");
                            lname = getInput("Last name: ");
                            minit = getInput("Middle initial(enter NA if NA): ");
                            role = getInput("Role: ");

                            if(minit.toLowerCase().equals("na"))
                                minit = null;
                            staff = new Staff(fname,minit,lname,role,myTeam.getName());
                            server.modifyStaff(staff,oldFname,oldLname);

                            String fName;
                            String lName;
                            System.out.println("Which staff do you want to delete?  Enter their first name, then their last name.");
                            ArrayList<data_objects.Staff> staffs = server.getStaff(myTeam.getName()); // TODO: need to pick a team to narrow
                            for (data_objects.Staff s : staffs) {
                                System.out.println(s.getFname() + " " + s.getMinit() + " " + s.getLname());
                            }
                            fName = getInput("First name: ");
                            lName = getInput("Last name: ");
                            server.deleteStaff(fName,lName);
                            break;
                        case 4:
                            staffs = server.getStaff(myTeam.getName());
                            for (data_objects.Staff s : staffs) {
                                System.out.println(s.getFname() + " " + s.getMinit() + " " + s.getLname());
                                System.out.println("\t" + s.getRole());
                                System.out.println("\t" + s.getTeam());
                            }
                            break;
                        default:
                            System.out.println(noActionPermission);
                            break;
                    }
                    break;
                case 6: // match
                    action = sub_menu();
                    // Use the data type and action to preform an operation on the database
                    switch( action ) {
                        case 4:
                            ArrayList<Match> matches = server.getMatches();
                            for(data_objects.Match m : matches){
                                System.out.println(m.getDate());
                                System.out.println("\t" + m.getHomeScore() + " - " + m.getAwayScore());
                                System.out.println("\t" + m.getHomeTeam().getName() + " vs. " + m.getAwayTeam().getName());
                            }
                            break;
                        default:
                            System.out.println(noActionPermission);
                            break;
                    }
                    break;
                case 7:
                    loop = false;
                    System.out.println(userString);
                    break;
                default:
                    System.out.println("Please enter a number 1-7");
                    break;
            }
        }
    }

    private static void run_league(){
        boolean loop = true;
        int action;
        String input;
        // full access
        while (loop) {
            int choice = main_menu(false);
            switch (choice) {
                case 1: // location
                    action = sub_menu();
                    switch( action ) {
                        case 1:
                            // create an entry
                            String country;
                            String city;
                            country = getInput("Please enter the country the team is from");
                            city = getInput("Please enter the city the team is from");
                            Location tempLoc = new Location(-1, country, city);
                            server.createLocation(tempLoc);
                            break;
                        case 2:
                            // edit an entry
                            int id = 0;
                            System.out.println("Which location did you want to modify? Enter the ID num as displayed below");
                            ArrayList<Location> locations = server.getLocations();
                            for(Location loc : locations){
                                System.out.println("\t" + loc.getId() + ", " + loc.getCountry() + ", " + loc.getCity());
                            }
                            id = getInput(id);
                            country = getInput("Please enter the country the team is from");
                            city = getInput("Please enter the city the team is from");
                            tempLoc = new Location(-1, country, city);
                            server.modifyLocation(tempLoc,id);
                            break;
                        case 3:
                            // delete an entry
                            id = 0;
                            System.out.println("Which location did you want to delete? Enter the ID num as displayed below");
                            locations = server.getLocations();
                            for(Location loc : locations){
                                System.out.println("\t" + loc.getId() + ", " + loc.getCountry() + ", " + loc.getCity());
                            }
                            id = getInput(id);
                            server.deleteLocation(id);
                            break;
                        case 4:
                            locations = server.getLocations();
                            for (data_objects.Location loc : locations) {
                                System.out.println(loc.getCity() + ", " + loc.getCountry());
                            }
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println(noActionPermission);
                            break;
                    }
                    break;
                case 2: // league
                    action = sub_menu();
                    char[] ynArray = {'y', 'Y', 'n', 'N'};
                    switch( action ) {
                        case 1:
                            // create an entry
                            String name;
                            String temp;
                            int numTeams = 0;
                            int matchID = 0;
                            data_objects.Team firstPlace;
                            data_objects.Match lastMatch;
                            name = getInputLine("Please enter a League Name");

                            input = getInput("Is there an existing first place team? Y/N", ynArray);
                            if(input.contains("" + 'y') || input.contains("" + 'Y')) {
                                input = getInput("Please enter the team name that's in first place.");
                                firstPlace = server.getTeam(input);
                            }
                            else {
                                firstPlace = null;
                            }
                            numTeams = getInput("How many teams are in the league?", numTeams);
                            input = getInput("Was there an existing last match? Y/N", ynArray);
                            if(input.equals('y') || input.equals('Y')){
                                matchID = getInput("Please enter the last match's ID", matchID);
                                lastMatch = server.getMatch(matchID);
                            }
                            else{
                                lastMatch = null;
                            }
                            League tempLeague = new League(name, numTeams, firstPlace, lastMatch);
                            server.createLeague(tempLeague);
                            break;
                        case 2:
                            // edit an entry
                            System.out.println("What league are you modifying?");
                            ArrayList<League> leagues = server.getLeagues();
                            for(League l : leagues){
                                System.out.println(l.getName());
                            }
                            String oldLeague = getInputLine();
                            name = getInput("Please enter a League Name");
                            input = getInput("Is there an existing first place team? Y/N", ynArray);
                            if(input.equals('y') || input.equals('Y')) {
                                input = getInput("Please enter the team name that's in first place.");
                                firstPlace = server.getTeam(input);
                            }
                            else {
                                firstPlace = null;
                            }
                            numTeams = 0;
                            numTeams = getInput("How many teams are in the league?", numTeams);
                            input = getInput("Was there an existing last match? Y/N", ynArray);
                            if(input.equals('y') || input.equals('Y')){
                                matchID = 0;
                                matchID = getInput("Please enter the last match's ID", matchID);
                                lastMatch = server.getMatch(matchID);
                            }
                            else{
                                lastMatch = null;
                            }
                            tempLeague = new League(name, numTeams, firstPlace, lastMatch);
                            server.modifyLeague(tempLeague,oldLeague);
                            break;
                        case 3:
                            // delete an entry
                            System.out.println("Enter the name of the league you want to delete from the list");
                            leagues = server.getLeagues();
                            for (data_objects.League l : leagues) {
                                System.out.println(l.getName());
                            }
                            name = getInputLine();
                            server.deleteLeague(name);
                            break;
                        case 4:
                            leagues = server.getLeagues();
                            for (data_objects.League l : leagues) {
                                System.out.println(l.getName());
                                System.out.println("\t"+l.getNumTeams());
                                if(l.getFirstPlace() != null)
                                    System.out.println("\t"+l.getFirstPlace().getName());
                                if(l.getLastMatch() != null)
                                    System.out.println("\t"+l.getLastMatch());
                            }
                            break;
                        default:
                            System.out.println(noActionPermission);
                            break;
                    }
                    break;
                case 3: // team
                    action = sub_menu();
                    switch( action ) {
                        case 1:
                            // create an entry
                            String newName;
                            int wins = 0;
                            int losses = 0;
                            int ties = 0;
                            Location location = null;
                            String league = null;
                            ArrayList<Player> players = null;
                            ArrayList<Staff> staff = null;
                            Team team;

                            newName = getInput("Please enter a name for the team.");
                            wins = getInput("Please enter number of wins", wins);
                            losses = getInput("Please enter number of losses", losses);
                            ties = getInput("Please enter number of ties", ties);
                            int locId = -1;

                            System.out.println("Please enter ID of location specified below");
                            ArrayList<Location> locations = server.getLocations();
                            for(Location loc : locations){
                                System.out.println(loc.getId());
                                System.out.println("\t"+loc.getCountry());
                                System.out.println("\t"+loc.getCity());
                            }
                            locId = getInput(locId);
                            location = server.getLocation(locId);


                            System.out.println("Please enter league name as specified below");
                            ArrayList<League> leagues = server.getLeagues();
                            ArrayList<String> leagueNames = new ArrayList<String>();
                            for (data_objects.League l : leagues) {
                                System.out.println(l.getName());
                                leagueNames.add(l.getName());
                            }
                            league = getInputLine();
                            while(!leagueNames.contains(league) && !league.equals("quit")){
                                System.out.println("Enter a viable name please.");
                                league = getInput();
                            }

                            league = "db league";
                            players = null;
                            staff = null;

                            team = new Team(newName, wins, losses, ties, location, league, players, staff);
                            server.createTeam(team);
                            break;
                        case 2:
                            System.out.println("Enter the name of the team you want to modify from the list");
                            ArrayList<Team> teams = server.getTeams();
                            for (Team t : teams) {
                                System.out.println(t.getName());
                            }
                            String name = getInput();

                            newName = getInput("Please enter a name for the team.");
                            wins = 0;
                            wins = getInput("Please enter number of wins", wins);
                            losses = 0;
                            losses = getInput("Please enter number of losses", losses);
                            ties = 0;
                            ties = getInput("Please enter number of ties", ties);
                            locId = -1;

                            System.out.println("Please enter ID of location specified below");
                            locations = server.getLocations();
                            for(Location loc : locations){
                                System.out.println(loc.getId());
                                System.out.println("\t"+loc.getCountry());
                                System.out.println("\t"+loc.getCity());
                            }
                            locId = getInput(locId);
                            location = server.getLocation(locId);

                            league = getInputLine("Please enter league name");
                            players = server.getPlayers(name);
                            staff = server.getStaff(name);

                            // edit an entry
                            server.modifyTeam(null,name);
                            break;
                        case 3:
                            // delete an entry
                            System.out.println("Enter the name of the Team you want to delete from the list");
                            teams = server.getTeams();
                            for (Team t : teams) {
                                System.out.println(t.getName());
                            }
                            name = getInput();
                            server.deleteTeam(name);
                            break;
                        case 4:
                            teams = server.getTeams();
                            for (data_objects.Team t : teams) {
                                System.out.println(t.getName());
                                System.out.println("\t"+t.getWins()+"/"+t.getLosses()+"/"+t.getTies());
                                System.out.println("\t"+t.getLocation().getCity()+","+t.getLocation().getCountry());
                                System.out.println("\t"+t.getLeague());
                                System.out.println("\tplayers: "+t.getPlayers().size()+" staff: "+t.getStaff().size());
                            }
                            break;
                        default:
                            System.out.println(noActionPermission);
                            break;
                    }
                    break;
                case 4: // player
                    action = sub_menu();
                    switch( action ) {
                        case 1:
                            // create an entry
                            Player player = null;

                            server.createPlayer(player);
                            break;
                        case 2:
                            // edit an entry
                            server.modifyPlayer(null,null,null);
                            break;
                        case 3:
                            // delete an entry
                            String fName;
                            String lName;
                            System.out.println("Which player do you want to delete?  Enter their first name, then their last name.");
                            ArrayList<data_objects.Player> players = server.getPlayers(); // TODO: need to pick a team to narrow
                            for (data_objects.Player p : players) {
                                System.out.println(p.getFname() + " " + p.getMinit() + " " + p.getLname());
                            }
                            fName = getInput("First name: ");
                            lName = getInput("Last name: ");
                            server.deletePlayer(fName, lName);
                            break;
                        case 4:
                            players = server.getPlayers("Germany"); // TODO: need to pick a team to narrow
                            for (data_objects.Player p : players) {
                                System.out.println(p.getFname() + " " + p.getMinit() + " " + p.getLname());
                                System.out.println("\t" + p.getPosition());
                                System.out.println("\t" + p.getTeam());
                                System.out.println("\t" + p.getCaps());
                                System.out.println("\t" + p.getGoals());
                                System.out.println("\t" + p.getPlayerNum());
                            }
                            break;
                        default:
                            System.out.println(noActionPermission);
                            break;
                    }
                    break;
                case 5: // staff
                    action = sub_menu();
                    switch( action ) {
                        case 1:
                            // create an entry
                            Staff staff = null;

                            server.createStaff(staff);
                            break;
                        case 2:
                            // edit an entry
                            server.modifyStaff(null,null,null);
                            break;
                        case 3:
                            // delete an entry
                            String fName;
                            String lName;
                            System.out.println("Which staff do you want to delete?  Enter their first name, then their last name.");
                            ArrayList<data_objects.Staff> staffs = server.getStaff(); // TODO: need to pick a team to narrow
                            for (data_objects.Staff s : staffs) {
                                System.out.println(s.getFname() + " " + s.getMinit() + " " + s.getLname());
                            }
                            fName = getInput("First name: ");
                            lName = getInput("Last name: ");
                            server.deleteStaff(fName,lName);
                            break;
                        case 4:
                            staffs = server.getStaff("Germany");
                            for (data_objects.Staff s : staffs) {
                                System.out.println(s.getFname() + " " + s.getMinit() + " " + s.getLname());
                                System.out.println("\t" + s.getRole());
                                System.out.println("\t" + s.getTeam());
                            }
                            break;
                        default:
                            System.out.println(noActionPermission);
                            break;
                    }
                    break;
                case 6: // match
                    action = sub_menu();
                    // Use the data type and action to preform an operation on the database
                    switch( action ) {
                        case 1:
                            // create an entry
                            Match match = null;
                            int homeScore = 0;
                            int awayScore = 0;
                            String homeName = getInput("Please enter the home team for the match");
                            Team homeTeam = server.getTeam(homeName);
                            homeScore = getInput("Please enter the home score for the match", homeScore);
                            String awayName = getInput("Please enter the away team for the match");
                            Team awayTeam = server.getTeam(awayName);
                            awayScore = getInput("Please enter the away score for the match", awayScore);
                            match = new Match(-1, null, homeScore, awayScore, homeTeam, awayTeam);
                            server.createMatch(match);
                            break;
                        case 2:
                            // edit an entry
                            System.out.println("What match do you want to modify?  Enter the as shown ID for the match you want to modify.");
                            ArrayList<data_objects.Match> matches = server.getMatches();
                            for(data_objects.Match m : matches){
                                System.out.println(m.getId());
                                System.out.println(m.getDate());
                                System.out.println("\t" + m.getHomeScore() + " - " + m.getAwayScore());
                                System.out.println("\t" + m.getHomeTeam().getName() + " vs. " + m.getAwayTeam().getName());
                            }
                            int id = -1;
                            id = getInput(id);
                            homeName = getInput("Please enter the home team for the match");
                            homeTeam = server.getTeam(homeName);
                            homeScore = 0;
                            homeScore = getInput("Please enter the home score for the match", homeScore);
                            awayName = getInput("Please enter the away team for the match");
                            awayTeam = server.getTeam(awayName);
                            awayScore = 0;
                            awayScore = getInput("Please enter the away score for the match", awayScore);
                            match = new Match(-1, null, homeScore, awayScore, homeTeam, awayTeam);
                            server.modifyMatch(match,id);

                            break;
                        case 3:
                            // delete an entry
                            System.out.println("What match do you want to delete?  Enter the as shown ID for the match you want to delete.");
                            matches = server.getMatches();
                            for(data_objects.Match m : matches){
                                System.out.println(m.getId());
                                System.out.println(m.getDate());
                                System.out.println("\t" + m.getHomeScore() + " - " + m.getAwayScore());
                                System.out.println("\t" + m.getHomeTeam().getName() + " vs. " + m.getAwayTeam().getName());
                            }
                            id = -1;
                            id = getInput(id);
                            server.deleteMatch(id);
                            break;
                        case 4:
                            matches = server.getMatches();
                            for(data_objects.Match m : matches){
                                System.out.println(m.getDate());
                                System.out.println("\t" + m.getHomeScore() + " - " + m.getAwayScore());
                                System.out.println("\t" + m.getHomeTeam().getName() + " vs. " + m.getAwayTeam().getName());
                            }
                            break;
                        default:
                            System.out.println(noActionPermission);
                            break;
                    }
                    break;
                case 7:
                    loop = false;
                    System.out.println(userString);
                    break;
                default:
                    System.out.println("Please enter a number 1-7");
                    break;
            }
        }
    }

    public static void main(String args[]) {
        System.out.println("Welcome to the Soccer Database.  " + userString);
        s = new Scanner(System.in);
        boolean loop = true;

        while (true) {
            String opt = s.next();
            int action;
            if (opt.equals("Player") || opt.equals("Coach") || opt.equals("player") || opt.equals("coach")) {
                run_player();
            }
            else if (opt.equals("Manager") || opt.equals("manager")) {
                run_manager();
            }
            else if (opt.equals("League") || opt.equals("league")) {
                run_league();
            }
            else if(opt.equals("Exit") || opt.equals("exit") || opt.equals("Quit") || opt.equals("quit") || opt.equals("q")){
                return;
            }
            else {
                System.out.println("Please enter a valid user type.");
            }
        }
    }
}