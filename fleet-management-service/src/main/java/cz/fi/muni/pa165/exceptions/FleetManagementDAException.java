package cz.fi.muni.pa165.exceptions;

import org.springframework.dao.DataAccessException;

/**
 * Created by Martin Schmidt on 19.11.2016.
 */
public class FleetManagementDAException extends DataAccessException {

    public FleetManagementDAException(String msg) {
        super(msg);
    }

    public FleetManagementDAException(String msg, Throwable cause) {
        super(msg, cause);
    }
}