package cz.fi.muni.pa165.entity;

import javax.persistence.*;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Martin Schmidt
 */
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_active")
    private Boolean active;

    /**
     * Vehicle registration plate number (SPZ).
     */
    @Column(nullable = false, unique = true)
    private String vrp;

    /**
     * Car producer and model.
     */
    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Year productionYear;

    @Column(nullable = false)
    private String engineType;

    @Column(nullable = false, unique = true)
    private String vin;

    /**
     * Number od driven kilometres when car was added to the catalogue.
     */
    @Column(nullable = false)
    private Long initialKilometrage;

    @ManyToOne
    private VehicleCategory vehicleCategory;

    @OneToMany(mappedBy = "vehicle")
    private Set<Journey> journeys = new HashSet<>();

    @OneToMany(mappedBy = "vehicle")
    private Set<InspectionInterval> inspectionIntervals = new HashSet<>();

    /**
     * All persistent classes must have a default constructor (which can be non-public)
     * so that Hibernate can instantiate them using Constructor.newInstance().
     */
    protected Vehicle() {
    }

    /**
     * @param vrp                Vehicle VRP.
     * @param type               Mane of the manufacturer name.
     * @param productionYear     Year when vehicle was manufactured.
     * @param engineType         Type of the engine.
     * @param vin                Vehicle VIN.
     * @param initialKilometrage The driven distance when vehicle was added to the catalogue. In kilometres.
     */
    public Vehicle(String vrp, String type, Year productionYear, String engineType, String vin, Long initialKilometrage) {
        this.vrp = vrp;
        this.type = type;
        this.productionYear = productionYear;
        this.engineType = engineType;
        this.vin = vin;
        this.initialKilometrage = initialKilometrage;
    }

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

    public Set<Journey> getJourneys() {
        return journeys;
    }

    public void setJourneys(Set<Journey> journeys) {
        this.journeys = journeys;
    }

    public Set<InspectionInterval> getInspectionIntervals() {
        return inspectionIntervals;
    }

    public void setInspectionIntervals(Set<InspectionInterval> inspectionIntervals) {
        this.inspectionIntervals = inspectionIntervals;
    }

    public boolean getActive() {return this.active;}
    public void setActive(Boolean active) {this.active = active;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || (!(o instanceof Vehicle))) return false;

        Vehicle vehicle = (Vehicle) o;

        return vin.equals(vehicle.getVin());

    }

    @Override
    public int hashCode() {
        return vin.hashCode();
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", vrp='" + vrp + '\'' +
                ", type='" + type + '\'' +
                ", productionYear=" + productionYear +
                ", engineType='" + engineType + '\'' +
                ", vin='" + vin + '\'' +
                ", initialKilometrage=" + initialKilometrage +
                ", vehicleCategory=" + vehicleCategory +
                '}';
    }
}

