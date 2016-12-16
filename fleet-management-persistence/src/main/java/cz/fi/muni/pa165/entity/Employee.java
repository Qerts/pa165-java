package cz.fi.muni.pa165.entity;


import cz.fi.muni.pa165.enums.Role;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jozef Krcho
 */
@Entity
@Table
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

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
     * @param email        email of the EMPLOYEE
     * @param name         name of the EMPLOYEE
     * @param surname      surname of the EMPLOYEE
     * @param passwordHash hashed password of the EMPLOYEE
     * @param role         role of the EMPLOYEE
     */
    public Employee(String email, String name, String surname, String passwordHash, Role role) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<VehicleCategory> getVehicleCategories() {
        return Collections.unmodifiableSet(vehicleCategories);
    }

    public void addVehicleCategory(VehicleCategory vehicleCategory) {
        vehicleCategories.add(vehicleCategory);
        vehicleCategory.addEmployee(this);
    }

    public void removeVehicleCategory(VehicleCategory vehicleCategory) {
        this.vehicleCategories.remove(vehicleCategory);
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

        return email.equals(employee.getEmail());
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return "EMPLOYEE{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
