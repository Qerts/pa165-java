package cz.fi.muni.pa165.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by MBalicky on 23/10/2016.
 * @author Michal Balický
 */

@Entity
public class Inspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date performedAt;

    /**
     * Initializes new Inspection object.
     * @param performedAt date when Inspection was performed
     */
    public Inspection(Date performedAt){
        this.performedAt = performedAt;
    }

    public Long getId(){
        return this.id;
    }

    public Date getPerformedAt(){
        return this.performedAt;
    }

    public void setPerformedAt(Date performedAt){
        this.performedAt = performedAt;
    }
}
