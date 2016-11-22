package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.interfaces.InspectionDao;
import cz.fi.muni.pa165.entity.Inspection;
import cz.fi.muni.pa165.service.interfaces.InspectionService;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Jozef Krcho
 */
public class InspectionServiceImpl implements InspectionService {

    @Inject
    private InspectionDao inspectionDao;

    @Override
    public Inspection findById(Long id) {
        return inspectionDao.findById(id);
    }

    @Override
    public List<Inspection> findAll() {
        return inspectionDao.findAll();
    }

    @Override
    public void create(Inspection inspection) {
        inspectionDao.persist(inspection);
    }

    @Override
    public void update(Inspection inspection) {
        inspectionDao.merge(inspection);
    }

    @Override
    public void remove(Inspection inspection) {
        inspectionDao.remove(inspection);
    }
}
