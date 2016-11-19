package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.entity.Employee;
import org.springframework.stereotype.Service;
import cz.fi.muni.pa165.entity.Journey;

import java.util.Date;
import java.util.List;

/**
 * Created by Martin on 19.11.2016.
 */
@Service
public interface JourneyService {
    public Journey findById(Long id);
    public List<Journey> findAll();
    public void create(Journey j);
    public void update(Journey j);
    public void remove(Journey j);
    public List<Journey> getAllJourneys(Date from, Date to, Employee employee);

}
