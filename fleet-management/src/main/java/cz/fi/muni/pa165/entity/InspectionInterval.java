package cz.fi.muni.pa165.entity;

import javax.persistence.*;

/**
 * @author Michal Balický
 */

@Entity
public class InspectionInterval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    /**
     * Number of days, after which Inspection must be repeated.
     */
    @Column(nullable = false)
    private int days;

    /**
     * Initialize new Inspection object. For serialization uses.
     */
    public InspectionInterval(){}

    /**
     * Initializes new InspectionInterval object
     * @param name      name of the inspection interval, i.e. "oil change"
     * @param interval  days between inspections
     */
    public InspectionInterval(String name, int interval){
        this.name = name;
        this.days = interval;
    }

    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public int getDays(){
        return this.days;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDays(int days){
        this.days = days;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;

        InspectionInterval inspectionInterval = (InspectionInterval)o;

        if(inspectionInterval.getName().equalsIgnoreCase(this.getName()) && inspectionInterval.getInterval() == this.getInterval()) return true;

        else return false;
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

    @Override
    public String toString() {
        return "Inspection{" +
                "id=" + id +
                ", name='" + this.getName() + '\'' +
                ", interval='" + this.getInterval() + '\'' +
                '}';
    }

}
