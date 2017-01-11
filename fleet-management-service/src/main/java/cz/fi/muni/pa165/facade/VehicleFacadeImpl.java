package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.VehicleCategoryDTO;
import cz.fi.muni.pa165.dto.VehicleCreateDTO;
import cz.fi.muni.pa165.dto.VehicleDTO;
import cz.fi.muni.pa165.entity.Vehicle;
import cz.fi.muni.pa165.service.interfaces.BeanMappingService;
import cz.fi.muni.pa165.service.interfaces.VehicleCategoryService;
import cz.fi.muni.pa165.service.interfaces.VehicleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collection;

/**
 * Created by Michal Balicky on 24/11/2016.
 */
@Service
@Transactional
public class VehicleFacadeImpl implements VehicleFacade {

    @Inject
    private VehicleService vehicleService;

    @Inject
    private VehicleCategoryService vehicleCategoryService;

    @Inject
    private BeanMappingService bms;

    @Override
    public Collection<VehicleDTO> getAllVehicles() {
        return bms.mapTo(vehicleService.findAll(), VehicleDTO.class);
    }

    @Override
    public Collection<VehicleDTO> getAllActiveVehicles() {
        return bms.mapTo(vehicleService.findAllActiveVehicles(), VehicleDTO.class);
    }

    @Override
    public Collection<VehicleCategoryDTO> getAllVehicleCategories() {
        return bms.mapTo(vehicleCategoryService.findAll(), VehicleCategoryDTO.class);
    }

    @Override
    public Collection<VehicleDTO> getObsoleteVehicles() {
        return bms.mapTo(vehicleService.getAllObsoleteVehicles(), VehicleDTO.class);
    }

    @Override
    public Collection<VehicleDTO> findVehiclesToBeBorrowedByUser(Long employeeId) {
        return bms.mapTo(vehicleService.findVehiclesToBeBorrowedByUser(employeeId), VehicleDTO.class);
    }

    @Override
    public double getTotalKilometrage(long vehicleId) {
        return vehicleService.getTotalKilometrage(vehicleId);
    }

    @Override
    public VehicleDTO findVehicleById(Long vehicleId) {
        return bms.mapTo(vehicleService.findById(vehicleId), VehicleDTO.class);
    }

    @Override
    public Long addNewVehicle(VehicleCreateDTO vehicle) {
        vehicle.setActive(true);
        Vehicle mappedVehicle = bms.mapTo(vehicle, Vehicle.class);
        mappedVehicle.setVehicleCategory(vehicleCategoryService.findById(vehicle.getVehicleCategoryId()));
        vehicleService.create(mappedVehicle);
        return mappedVehicle.getId();
    }

    @Override
    public void updateVehicle(VehicleDTO vehicle) {
        vehicleService.update(bms.mapTo(vehicle, Vehicle.class));
    }

    @Override
    public void disableVehicle(Long vehicleId) {
        vehicleService.disable(vehicleId);
    }
}
