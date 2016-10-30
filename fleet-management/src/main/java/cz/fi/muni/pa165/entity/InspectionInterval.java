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

}
