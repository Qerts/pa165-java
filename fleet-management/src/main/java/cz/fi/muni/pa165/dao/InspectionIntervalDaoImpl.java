package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.dao.interfaces.InspectionIntervalDao;
import cz.fi.muni.pa165.entity.InspectionInterval;
import org.springframework.stereotype.Repository;

/**
 * Created by MBalicky on 23/10/2016.
 */
@Repository
public class InspectionIntervalDaoImpl extends JpaDao<InspectionInterval, Long> implements InspectionIntervalDao{
}
