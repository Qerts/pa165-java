package cz.fi.muni.pa165.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Jozef Krcho
 */
public class VehicleCreateDTO {

    @NotNull
    @Size(min = 7, max = 7)
    private String vrp;

    @NotNull
    @Size(min = 3, max = 100)
    private String type;

    @NotNull
    @Min(0)
    private int productionYear;

    @NotNull
    @Size(min = 3, max = 100)
    private String engineType;

    @NotNull
    @Size(min = 17, max = 17)
    private String vin;

    @NotNull
    @Min(0)
    private Long initialKilometrage;

    @NotNull
    private Long vehicleCategoryId;

    //added manually
    private Boolean active;


    public VehicleCreateDTO() {
    }

    public VehicleCreateDTO(String vrp, String type, int productionYear, String engineType, String vin, Long initialKilometrage, Boolean active) {
        this.vrp = vrp;
        this.type = type;
        this.productionYear = productionYear;
        this.engineType = engineType;
        this.vin = vin;
        this.initialKilometrage = initialKilometrage;
        this.active = active;
    }

    public Long getVehicleCategoryId() {
        return vehicleCategoryId;
    }

    public void setVehicleCategoryId(Long vehicleCategoryId) {
        this.vehicleCategoryId = vehicleCategoryId;
    }

    public String getVrp() {
        return vrp;
    }

    public void setVrp(String vrp) {
        this.vrp = vrp.toUpperCase();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
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

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || (!(o instanceof VehicleDTO))) return false;

        VehicleDTO vehicle = (VehicleDTO) o;

        return vin.equals(vehicle.getVin());

    }

    @Override
    public int hashCode() {
        return vin.hashCode();
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                ", vrp='" + vrp + '\'' +
                ", type='" + type + '\'' +
                ", productionYear=" + productionYear +
                ", engineType='" + engineType + '\'' +
                ", vin='" + vin + '\'' +
                ", initialKilometrage=" + initialKilometrage +
                ", active=" + this.active +
                '}';
    }
}
