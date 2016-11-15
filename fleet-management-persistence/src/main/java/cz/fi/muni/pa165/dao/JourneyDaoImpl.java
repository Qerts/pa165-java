package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.dao.interfaces.JourneyDao;
import cz.fi.muni.pa165.entity.Journey;
import org.springframework.stereotype.Repository;

/**
 * @author Richard Trebichavský
 */
@Repository
public class JourneyDaoImpl extends JpaDao<Journey, Long> implements JourneyDao {
}
