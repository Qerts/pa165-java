package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.interfaces.Dao;
import cz.fi.muni.pa165.dao.interfaces.InspectionIntervalDao;
import cz.fi.muni.pa165.entity.InspectionInterval;
import cz.fi.muni.pa165.service.interfaces.InspectionIntervalService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Jozef Krcho
 */
@Service
public class InspectionIntervalServiceImpl extends JpaService<InspectionInterval, Long> implements InspectionIntervalService {

    @Inject
    private InspectionIntervalDao inspectionIntervalDao;

    @Override
    protected Dao<InspectionInterval, Long> getDao() {
        return inspectionIntervalDao;
    }
}
