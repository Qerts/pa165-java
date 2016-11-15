package cz.fi.muni.pa165.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jozef Krcho
 */
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"name", "surname"})
})
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String surname;


    @ManyToMany
    Set<VehicleCategory> vehicleCategories = new HashSet<>();

    @OneToMany(mappedBy = "employee")
    private Set<Journey> journeys = new HashSet<>();

    /**
     * All persistent classes must have a default constructor (which can be non-public)
     * so that Hibernate can instantiate them using Constructor.newInstance().
     */
    protected Employee() {
    }

    /**
     * @param name    name of the Employee
     * @param surname surname of the Employee
     */
    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<VehicleCategory> getVehicleCategories() {
        return vehicleCategories;
    }

    public void setVehicleCategories(Set<VehicleCategory> vehicleCategories) {
        this.vehicleCategories = vehicleCategories;
    }

    public Set<Journey> getJourneys() {
        return journeys;
    }

    public void setJourneys(Set<Journey> journeys) {
        this.journeys = journeys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || (!(o instanceof Employee))) return false;

        Employee employee = (Employee) o;

        if (!name.equals(employee.name)) return false;
        return surname.equals(employee.surname);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + surname.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
