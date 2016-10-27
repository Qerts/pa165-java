package cz.fi.muni.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Martin on 23.10.2016.
 * @author Martin Schmidt
 */
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Vehicle registration plate number (SPZ)
     */
    @NotNull
    @Column(nullable=false, unique=true)
    private String vrp;

    /**
     * car producer and model
     */
    @NotNull
    @Column(nullable=false)
    private String type;

    @NotNull
    @Column(nullable=false)
    private String productionYear;

    @NotNull
    @Column(nullable=false)
    private String engineType;

    @NotNull
    @Column(nullable=false, unique=true)
    private String vin;

    @NotNull
    @Column(nullable=false)
    private Long initialDrivenDistance;

    @ManyToOne
    private VehicleCategory vehicleCategory;

    public VehicleCategory getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(VehicleCategory vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
        if (vehicleCategory != null) {
            vehicleCategory.addVehicle(this);
        }
    }

    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVrp() {
        return vrp;
    }

    public void setVrp(String vrp) {
        this.vrp = vrp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Long getInitialDrivenDistance() {
        return initialDrivenDistance;
    }

    public void setInitialDrivenDistance(Long initialDrivenDistance) {
        this.initialDrivenDistance = initialDrivenDistance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Vehicle)) return false;

        Vehicle vehicle = (Vehicle) o;

        if (!getVrp().equals(vehicle.getVrp())) return false;
        return getVin().equals(vehicle.getVin());

    }

    @Override
    public int hashCode() {
        int result = getVrp().hashCode();
        result = 31 * result + getVin().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", vrp='" + vrp + '\'' +
                ", type='" + type + '\'' +
                ", productionYear='" + productionYear + '\'' +
                ", engineType='" + engineType + '\'' +
                ", vin='" + vin + '\'' +
                ", initialDrivenDistance=" + initialDrivenDistance +
                '}';
    }
}

