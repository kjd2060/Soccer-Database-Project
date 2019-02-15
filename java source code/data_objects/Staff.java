package data_objects;

public class Staff {

    private String fname;
    private String minit;
    private String lname;
    private String role;
    private String team;

    public Staff(String fname, String minit, String lname, String role, String team) {
        this.fname = fname;
        this.minit = minit;
        this.lname = lname;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public String getTeam() {
        return team;
    }
}
