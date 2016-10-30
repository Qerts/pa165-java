package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.dao.interfaces.InspectionIntervalDao;
import cz.fi.muni.pa165.entity.InspectionInterval;
import org.springframework.stereotype.Repository;

/**
 * @author Michal Balický
 */
@Repository
public class InspectionIntervalDaoImpl extends JpaDao<InspectionInterval, Long> implements InspectionIntervalDao{
}
