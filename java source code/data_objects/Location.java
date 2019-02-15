package data_objects;

public class Location {
    private int id;
    private String country;
    private String city;

    public Location(int id, String country, String city) {
        this.id = id;
        this.country = country;
        this.city = city;
    }

    public int getId(){
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}
