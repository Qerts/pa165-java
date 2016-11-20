package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entity.InspectionInterval;

import java.util.List;

/**
 * Created by JozeFe on 11/20/2016.
 * @author Jozef Krcho
 */
public interface InspectionIntervalService {
    /**
     * find inspectionInterval by id
     * @param id id of inspectionInterval
     * @return  inspectionInterval
     */
    public InspectionInterval findById(Long id);

    /**
     * find all inspectionIntervals
     * @return list of inspectionIntervals
     */
    public List<InspectionInterval> findAll();

    /**
     * save new inspectionInterval
     * @param inspectionInterval inspectionInterval
     */
    public void create(InspectionInterval inspectionInterval);

    /**
     * update saved inspectionInterval
     * @param inspectionInterval inspectionInterval
     */
    public void update(InspectionInterval inspectionInterval);

    /**
     * remove saved inspectionInterval
     * @param inspectionInterval inspectionInterval
     */
    public void remove(InspectionInterval inspectionInterval);
}
