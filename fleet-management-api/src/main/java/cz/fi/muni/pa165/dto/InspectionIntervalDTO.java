package cz.fi.muni.pa165.dto;


/**
 * @author Martin Schmidt
 */
public class InspectionIntervalDTO {

    private Long id;

    private String name;

    private int days;

    private VehicleDTO vehicle;

    protected InspectionIntervalDTO() {
    }

    public InspectionIntervalDTO(String name, int interval) {
        this.name = name;
        this.days = interval;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getDays() {
        return this.days;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDays(int days) {
        this.days = days;
    }


    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || (!(o instanceof InspectionIntervalDTO))) return false;

        InspectionIntervalDTO that = (InspectionIntervalDTO) o;

        return name != null ? name.equals(that.getName()) : that.getName() == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "InspectionInterval{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", days=" + days +
                '}';
    }

}
