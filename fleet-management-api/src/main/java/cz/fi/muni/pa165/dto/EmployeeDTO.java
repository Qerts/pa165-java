package cz.fi.muni.pa165.dto;

import cz.fi.muni.pa165.enums.Role;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Martin on 19.11.2016.
 */
public class EmployeeDTO {

    private Long id;

    private String email;

    private String name;

    private String surname;

    private String passwordHash;

    private Role role;

    Set<VehicleCategoryDTO> vehicleCategories = new HashSet<>();

    protected EmployeeDTO() {
    }

    public EmployeeDTO(String email, String name, String surname, String passwordHash, Role role) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<VehicleCategoryDTO> getVehicleCategories() {
        return vehicleCategories;
    }

    public void setVehicleCategories(Set<VehicleCategoryDTO> vehicleCategories) {
        this.vehicleCategories = vehicleCategories;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || (!(o instanceof EmployeeDTO))) return false;

        EmployeeDTO employee = (EmployeeDTO) o;

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
        return "EMPLOYEE{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

}
