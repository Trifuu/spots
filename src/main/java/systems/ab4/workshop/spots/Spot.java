package systems.ab4.workshop.spots;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;
import java.util.zip.DataFormatException;

/**
 * Created by mariu on 6/22/2017.
 */
@Entity
@Table
public class Spot {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @NotNull
    private Location location;

    @Column
    @NotNull
    private Date startSeason;

    @Column
    @NotNull
    private Date endSeason;

    @Column
    @NotNull
    private Double cost;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Set<Activity> activities;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Spot(String name, Location location, Date startSeason, Date endSeason, Double cost, Set<Activity> activities) {
        this.name = name;
        this.location = location;
        this.startSeason = startSeason;
        this.endSeason = endSeason;
        this.cost = cost;
        this.activities = activities;
    }

    public Spot() {
    }
}