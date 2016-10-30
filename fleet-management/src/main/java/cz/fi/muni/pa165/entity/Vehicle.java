package cz.fi.muni.pa165.entity;

import javax.persistence.*;
import java.time.Year;

/**
 * @author Martin Schmidt
 */
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Vehicle registration plate number (SPZ).
     */
    @Column(nullable=false, unique=true)
    private String vrp;

    /**
     * Car producer and model.
     */
    @Column(nullable=false)
    private String type;

    @Column(nullable=false)
    private Year productionYear;

    @Column(nullable=false)
    private String engineType;

    @Column(nullable=false, unique=true)
    private String vin;

    /**
     * Number od driven kilometres when car was added to the catalogue.
     */
    @Column(nullable=false)
    private Long initialKilometrage;

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

    public Year getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Year productionYear) {
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

    public Long getInitialKilometrage() {
        return initialKilometrage;
    }

    public void setInitialKilometrage(Long initialKilometrage) {
        this.initialKilometrage = initialKilometrage;
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
                ", initialKilometrage=" + initialKilometrage +
                '}';
    }
}

