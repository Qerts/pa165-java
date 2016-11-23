package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.interfaces.Dao;
import cz.fi.muni.pa165.dao.interfaces.InspectionDao;
import cz.fi.muni.pa165.entity.Inspection;
import cz.fi.muni.pa165.service.interfaces.InspectionService;

import javax.inject.Inject;

/**
 * @author Jozef Krcho
 */
public class InspectionServiceImpl extends JpaService<Inspection, Long> implements InspectionService {

    @Inject
    private InspectionDao inspectionDao;

    @Override
    protected Dao<Inspection, Long> getDao() {
        return inspectionDao;
    }
}
