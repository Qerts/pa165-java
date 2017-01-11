package cz.fi.muni.pa165.dto;

import java.util.Date;

/**
 * @author Martin Schmidt
 */
public class InspectionDTO {

    private Long id;

    private Date performedOn;

    private InspectionIntervalDTO inspectionInterval;

    public InspectionDTO() {
    }

    public InspectionDTO(Date performedAt) {
        this.performedOn = performedAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {this.id = id;}

    public Date getPerformedOn() {
        return this.performedOn;
    }

    public void setPerformedOn(Date performedOn) {
        this.performedOn = performedOn;
    }

    public InspectionIntervalDTO getInspectionInterval() {
        return inspectionInterval;
    }

    public void setInspectionInterval(InspectionIntervalDTO inspectionInterval) {
        this.inspectionInterval = inspectionInterval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || (!(o instanceof InspectionDTO))) return false;

        InspectionDTO that = (InspectionDTO) o;

        return performedOn != null ? performedOn.equals(that.getPerformedOn()) : that.getPerformedOn() == null;

    }

    @Override
    public int hashCode() {
        return performedOn != null ? performedOn.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Inspection{" +
                "id=" + id +
                ", performedOn=" + performedOn +
                '}';
    }

}
