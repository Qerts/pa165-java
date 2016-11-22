package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.interfaces.InspectionIntervalDao;
import cz.fi.muni.pa165.entity.InspectionInterval;
import cz.fi.muni.pa165.service.interfaces.InspectionIntervalService;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Jozef Krcho
 */
public class InspectionIntervalServiceImpl implements InspectionIntervalService {

    @Inject
    private InspectionIntervalDao inspectionIntervalDao;

    @Override
    public InspectionInterval findById(Long id) {
        return inspectionIntervalDao.findById(id);
    }

    @Override
    public List<InspectionInterval> findAll() {
        return inspectionIntervalDao.findAll();
    }

    @Override
    public void create(InspectionInterval inspectionInterval) {
        inspectionIntervalDao.persist(inspectionInterval);
    }

    @Override
    public void update(InspectionInterval inspectionInterval) {
        inspectionIntervalDao.merge(inspectionInterval);
    }

    @Override
    public void remove(InspectionInterval inspectionInterval) {
        inspectionIntervalDao.remove(inspectionInterval);
    }
}
