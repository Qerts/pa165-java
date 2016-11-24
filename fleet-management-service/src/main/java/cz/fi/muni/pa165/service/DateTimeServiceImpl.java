package cz.fi.muni.pa165.service;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Richard Trebichavský
 */
@Service
public class DateTimeServiceImpl implements DateTimeService {

    public Date getCurrentDate() {
        return new Date();
    }
}
