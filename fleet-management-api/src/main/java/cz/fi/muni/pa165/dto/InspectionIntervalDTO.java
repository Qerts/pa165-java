package cz.fi.muni.pa165.dto;


import java.util.Date;

/**
 * @author Martin Schmidt
 */
public class InspectionIntervalDTO {

    private Long id;

    private String name;

    private int days;

    private VehicleDTO vehicle;

    private Date lastInspectionWasPerformedOn;

    private Date nextInspectionShouldBePerformedUntil;

    private int nextInspectionShouldBePerformedInDays;

    public InspectionIntervalDTO() {
    }

    public InspectionIntervalDTO(String name, int interval) {
        this.name = name;
        this.days = interval;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {this.id = id;}

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

    public void setLastInspectionWasPerformedOn(Date lastInspectionWasPerformedOn) {
        this.lastInspectionWasPerformedOn = lastInspectionWasPerformedOn;
    }

    public Date getLastInspectionWasPerformedOn() {
        return lastInspectionWasPerformedOn;
    }

    public Date getNextInspectionShouldBePerformedUntil() {
        return nextInspectionShouldBePerformedUntil;
    }

    public void setNextInspectionShouldBePerformedUntil(Date nextInspectionShouldBePerformedUntil) {
        this.nextInspectionShouldBePerformedUntil = nextInspectionShouldBePerformedUntil;
    }

    public int getNextInspectionShouldBePerformedInDays() {
        return nextInspectionShouldBePerformedInDays;
    }

    public void setNextInspectionShouldBePerformedInDays(int nextInspectionShouldBePerformedInDays) {
        this.nextInspectionShouldBePerformedInDays = nextInspectionShouldBePerformedInDays;
    }
}
