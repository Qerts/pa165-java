package cz.fi.muni.pa165.entity;

import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Richard Trebichavský
 */
@Entity
public class Journey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float distance;

    @Column(nullable = false)
    private Date borrowedAt;

    private Date returnedAt;

    @OneToOne(cascade = {CascadeType.ALL})
    private Vehicle vehicle;

    @OneToOne(cascade = {CascadeType.ALL})
    private Employee employee;

    /**
     * All persistent classes must have a default constructor (which can be non-public)
     * so that Hibernate can instantiate them using Constructor.newInstance().
     */
    protected Journey() {
    }

    /**
     * Creates entity in initial phase where vehicle is borrowed.
     *
     * @param borrowedAt Date, when vehicle was borrowed.
     * @param vehicle    Vehicle which was borrowed.
     * @param employee   EMPLOYEE who borrowed a vehicle.
     */
    public Journey(Date borrowedAt, Vehicle vehicle, Employee employee) {
        this.borrowedAt = borrowedAt;
        this.vehicle = vehicle;
        this.employee = employee;
    }

    /**
     * Updated entity to final phase where vehicle is returned.
     *
     * @param returnedAt Date, when vehicle was returned.
     * @param distance   Distance driven with this vehicle during the journey in kilometres.
     */
    public void returnVehicle(Date returnedAt, Float distance) {
        this.returnedAt = returnedAt;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Date getBorrowedAt() {
        return borrowedAt;
    }

    public void setBorrowedAt(Date borrowedAt) {
        this.borrowedAt = borrowedAt;
    }

    public Date getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(Date returnedAt) {
        Assert.notNull(this.distance);
        this.returnedAt = returnedAt;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || (!(o instanceof Journey))) return false;

        Journey journey = (Journey) o;

        if (!borrowedAt.equals(journey.getBorrowedAt())) return false;
        if (!vehicle.equals(journey.getVehicle())) return false;
        return employee.equals(journey.getEmployee());

    }

    @Override
    public int hashCode() {
        int result = borrowedAt.hashCode();
        result = 31 * result + vehicle.hashCode();
        result = 31 * result + employee.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "id=" + id +
                ", distance=" + distance +
                ", borrowedAt=" + borrowedAt +
                ", returnedAt=" + returnedAt +
                ", vehicle=" + vehicle +
                ", employee=" + employee +
                '}';
    }
}
