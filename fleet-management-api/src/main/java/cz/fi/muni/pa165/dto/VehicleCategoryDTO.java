package cz.fi.muni.pa165.dto;


/**
 * Created by Martin on 19.11.2016.
 */
public class VehicleCategoryDTO {

    private Long id;

    private String name;

    protected VehicleCategoryDTO() {
    }


    public VehicleCategoryDTO(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || (!(o instanceof VehicleCategoryDTO))) return false;

        VehicleCategoryDTO that = (VehicleCategoryDTO) o;

        return name.equals(that.getName());

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "VehicleCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
