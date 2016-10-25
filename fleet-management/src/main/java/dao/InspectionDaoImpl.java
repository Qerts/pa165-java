package dao;

import dao.interfaces.InspectionDao;
import entity.Inspection;
import org.springframework.stereotype.Repository;

/**
 * Created by MBalicky on 23/10/2016.
 */
@Repository
public class InspectionDaoImpl extends JpaDao<Inspection, Long> implements InspectionDao{
}
