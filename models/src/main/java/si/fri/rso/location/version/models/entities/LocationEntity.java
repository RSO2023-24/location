package si.fri.rso.location.version.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "image_metadata")
@NamedQueries(value =
        {
                @NamedQuery(name = "LocationEntity.getAll",
                        query = "SELECT im FROM LocationEntity im")
        })
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "toilets")
    private boolean toilets;

    @Column(name = "food")
    private boolean food;

    @Column(name = "sleep")
    private boolean sleep;

    @Column(name = "longtitude")
    private Double longtitude;

    @Column(name = "latitude")
    private Double latitude;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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