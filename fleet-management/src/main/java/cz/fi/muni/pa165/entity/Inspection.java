package cz.fi.muni.pa165.entity;

import javax.persistence.*;
import java.util.Date;

/**
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
     * Initialize new Inspection object. For serialization uses.
     */
    public Inspection(){}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        else return false;
    }

    @Override
    public int hashCode() {
        return getPerformedAt().hashCode();
    }

    @Override
    public String toString() {
        return "Inspection{" +
                "id=" + id +
                ", date='" + this.getPerformedAt().toString() + '\'' +
                '}';
    }
}
