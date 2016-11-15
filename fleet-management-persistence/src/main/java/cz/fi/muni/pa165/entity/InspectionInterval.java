package cz.fi.muni.pa165.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Michal Balický
 */

@Entity
public class InspectionInterval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Number of days, after which Inspection must be repeated.
     */
    @Column(nullable = false)
    private int days;

    @ManyToOne
    private Vehicle vehicle;

    @OneToMany(mappedBy = "inspectionInterval")
    private Set<Inspection> inspections = new HashSet<>();



    /**
     * All persistent classes must have a default constructor (which can be non-public)
     * so that Hibernate can instantiate them using Constructor.newInstance().
     */
    protected InspectionInterval(){
    }

    /**
     * Initializes new InspectionInterval object
     * @param name      name of the inspection interval, i.e. "oil change"
     * @param interval  days between inspections
     */
    public InspectionInterval(String name, int interval) {
        this.name = name;
        this.days = interval;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getDays() {
        return this.days;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDays(int days) {
        this.days = days;
    }


    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Set<Inspection> getInspections() {
        return inspections;
    }

    public void setInspections(Set<Inspection> inspections) {
        this.inspections = inspections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || (!(o instanceof InspectionInterval))) return false;

        InspectionInterval that = (InspectionInterval) o;

        return name != null ? name.equals(that.getName()) : that.getName() == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "InspectionInterval{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", days=" + days +
                '}';
    }
}
