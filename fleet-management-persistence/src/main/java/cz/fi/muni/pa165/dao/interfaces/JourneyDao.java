package cz.fi.muni.pa165.dao.interfaces;

import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.entity.Journey;

import java.util.List;

/**
 * @author Richard Trebichavský + Martin Schmidt
 */
public interface JourneyDao extends Dao<Journey, Long> {

   /**
    * find all journeys for given employee
    * @param e employee
    * @return list of journeys
    */
   List<Journey> findByEmployee(Employee e);

}
