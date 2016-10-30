package cz.fi.muni.pa165.entity;

import javax.persistence.*;

/**
 * Created by MBalicky on 23/10/2016.
 * @author Michal Balický
 */

@Entity
public class InspectionInterval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int interval;

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
        this.interval = interval;
    }

    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public int getInterval(){
        return this.interval;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setInterval(int interval){
        this.interval = interval;
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
