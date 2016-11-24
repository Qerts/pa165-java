package cz.fi.muni.pa165.exceptions;

import org.springframework.dao.DataAccessException;

/**
 * @author Martin Schmidt
 */
public class FleetManagementDAException extends DataAccessException {

    public FleetManagementDAException(String msg) {
        super(msg);
    }

    public FleetManagementDAException(String msg, Throwable cause) {
        super(msg, cause);
    }
}