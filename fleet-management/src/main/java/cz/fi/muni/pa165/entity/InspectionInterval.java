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

}
