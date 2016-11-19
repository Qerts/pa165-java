package cz.fi.muni.pa165.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Martin on 19.11.2016.
 */
public class EmployeeDTO {

    private Long id;

    private String name;

    private String surname;

    Set<VehicleCategoryDTO> vehicleCategories = new HashSet<>();

    protected EmployeeDTO() {
    }


    public EmployeeDTO(String name, String surname) {
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
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

}
