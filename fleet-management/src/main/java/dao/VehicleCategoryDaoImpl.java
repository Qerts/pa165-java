package dao;

import dao.interfaces.VehicleCategoryDao;
import entity.VehicleCategory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Martin on 23.10.2016.
 * @author Martin Schmidt
 */
@Repository
public class VehicleCategoryDaoImpl extends JpaDao<VehicleCategory, Long> implements VehicleCategoryDao {

}
