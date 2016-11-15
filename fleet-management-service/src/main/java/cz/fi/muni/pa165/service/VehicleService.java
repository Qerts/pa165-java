package cz.fi.muni.pa165.service;

import org.springframework.stereotype.Service;

import cz.fi.muni.pa165.entity.Vehicle;

import java.util.List;

/**
 * Created by Martin on 13.11.2016.
 */
@Service
public interface VehicleService {
    public Vehicle findById(Long id);
    public List<Vehicle> findAll();
    public void persist(Vehicle v);
    public void merge(Vehicle v);
    public void remove(Vehicle v);
    public void removeById(Long id);

}
