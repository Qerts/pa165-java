package cz.fi.muni.pa165.dto;

import java.util.Date;

/**
 * @author Martin Schmidt
 */
public class JourneyDTO {

    private Long id;

    private Float distance;

    private Date borrowedAt;

    private Date returnedAt;

    private VehicleDTO vehicle;

    private EmployeeDTO employee;

    public JourneyDTO() {
    }

    public JourneyDTO(Date borrowedAt, VehicleDTO vehicle, EmployeeDTO employee) {
        this.borrowedAt = borrowedAt;
        this.vehicle = vehicle;
        this.employee = employee;
    }


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
        this.returnedAt = returnedAt;
    }

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || (!(o instanceof JourneyDTO))) return false;

        JourneyDTO journey = (JourneyDTO) o;

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
