package cz.fi.muni.pa165.dto;

import java.util.Date;

/**
 * @author Martin Schmidt
 */
public class InspectionDTO {

    private Long id;

    private Date performedAt;

    private InspectionIntervalDTO inspectionInterval;

    public InspectionDTO(){}

    public InspectionDTO(Date performedAt){
        this.performedAt = performedAt;
    }

    public Long getId(){
        return this.id;
    }

    public Date getPerformedAt(){
        return this.performedAt;
    }

    public void setPerformedAt(Date performedAt){
        this.performedAt = performedAt;
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

        return performedAt != null ? performedAt.equals(that.getPerformedAt()) : that.getPerformedAt() == null;

    }

    @Override
    public int hashCode() {
        return performedAt != null ? performedAt.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Inspection{" +
                "id=" + id +
                ", performedAt=" + performedAt +
                '}';
    }

}
