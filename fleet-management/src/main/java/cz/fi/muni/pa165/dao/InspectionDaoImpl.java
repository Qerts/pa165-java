package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.dao.interfaces.InspectionDao;
import cz.fi.muni.pa165.entity.Inspection;
import org.springframework.stereotype.Repository;

/**
 * @author Michal Balický
 */
@Repository
public class InspectionDaoImpl extends JpaDao<Inspection, Long> implements InspectionDao{
}
