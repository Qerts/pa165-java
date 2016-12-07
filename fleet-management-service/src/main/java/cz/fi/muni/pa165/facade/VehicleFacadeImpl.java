package cz.fi.muni.pa165.facade;

import cz.fi.muni.pa165.dto.VehicleDTO;
import cz.fi.muni.pa165.entity.Vehicle;
import cz.fi.muni.pa165.service.interfaces.BeanMappingService;
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
    private BeanMappingService beanMappingService;

    @Override
    public Collection<VehicleDTO> getAllVehicles() {
        return beanMappingService.mapTo(vehicleService.findAll(), VehicleDTO.class);
    }

    @Override
    public Collection<VehicleDTO> getAllActiveVehicles() {
        return beanMappingService.mapTo(vehicleService.findActiveVehicles(), VehicleDTO.class);
    }
    
    @Override
    public Collection<VehicleDTO> findVehiclesAvailable(Long employeeId) {
        return this.beanMappingService.mapTo(this.vehicleService.findVehiclesAvailable(employeeId), VehicleDTO.class);
    }

    @Override
    public double getTotalKilometrage(long vehicleId) {
        return this.vehicleService.getTotalKilometrage(vehicleId);
    }

    @Override
    public VehicleDTO findVehicleById(Long vehicleId) {
        return this.beanMappingService.mapTo(this.vehicleService.findById(vehicleId), VehicleDTO.class);
    }

    @Override
    public Long addNewVehicle(VehicleDTO vehicle) {
        Vehicle mappedVehicle = this.beanMappingService.mapTo(vehicle, Vehicle.class);
        this.vehicleService.create(mappedVehicle);
        return mappedVehicle.getId();
    }

    @Override
    public void updateVehicle(VehicleDTO vehicle) {
        this.vehicleService.update(this.beanMappingService.mapTo(vehicle, Vehicle.class));
    }

    @Override
    public void disableVehicle(Long vehicleId) {
        this.vehicleService.disable(vehicleId);
    }
}
