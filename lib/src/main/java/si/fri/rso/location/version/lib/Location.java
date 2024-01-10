package si.fri.rso.location.version.lib;



public class Location {

    private Integer id;
    private String name;
    private String description;
    private boolean toilets;
    private boolean food;
    private boolean sleep;
    private Double longtitude;
    private Double latitude;

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public boolean gettoilets() {
        return toilets;
    }

    public void settoilets(boolean toilets) {
        this.toilets = toilets;
    }

    public boolean getfood() {
        return food;
    }

    public void setfood(boolean food) {
        this.food = food;
    }

    public boolean getsleep() {
        return sleep;
    }

    public void setsleep(boolean sleep) {
        this.sleep = sleep;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
