package in.kpmg.hrms.payroll.exceptions;

import javax.persistence.PersistenceException;

public class NotFoundException extends PersistenceException {

    public NotFoundException() {
        super("404 Not Found!");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
