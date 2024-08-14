package in.kpmg.hrms.payroll.exceptions;

import javax.persistence.PersistenceException;

public class BadRequestException extends PersistenceException {

    public BadRequestException()
    {
        super("Bad Request!");
    }

    public BadRequestException(String message)
    {
        super(message);
    }
}
