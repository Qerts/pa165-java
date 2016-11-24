package cz.fi.muni.pa165.dto;


import java.time.Year;


/**
 * @author Martin Schmidt
 */
public class VehicleDTO {

    private Long id;

    private String vrp;

    private String type;

    private Year productionYear;

    private String engineType;

    private String vin;

    private Long initialKilometrage;

    private VehicleCategoryDTO vehicleCategory;


    protected VehicleDTO() {
    }


    public VehicleDTO(String vrp, String type, Year productionYear, String engineType, String vin, Long initialKilometrage) {
        this.vrp = vrp;
        this.type = type;
        this.productionYear = productionYear;
        this.engineType = engineType;
        this.vin = vin;
        this.initialKilometrage = initialKilometrage;
    }

    public VehicleCategoryDTO getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(VehicleCategoryDTO vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
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
