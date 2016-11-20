package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entity.Inspection;

import java.util.List;

/**
 * Created by JozeFe on 11/20/2016.
 * @author Jozef Krcho
 */
public interface InspectionService {
    /**
     * find inspection by id
     * @param id id of inspection
     * @return  inspection
     */
    public Inspection findById(Long id);

    /**
     * find all inspections
     * @return list of inspections
     */
    public List<Inspection> findAll();

    /**
     * save new inspection
     * @param inspection inspection
     */
    public void create(Inspection inspection);

    /**
     * update saved inspection
     * @param inspection inspection
     */
    public void update(Inspection inspection);

    /**
     * remove saved inspection
     * @param inspection inspection
     */
    public void remove(Inspection inspection);
}
