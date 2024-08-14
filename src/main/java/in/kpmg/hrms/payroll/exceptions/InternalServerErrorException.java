package in.kpmg.hrms.payroll.exceptions;

import javax.persistence.PersistenceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternalServerErrorException extends PersistenceException {

    public InternalServerErrorException()
    {
        super();
    }

    public InternalServerErrorException(String message)
    {
        super("Unable to fetch data at a moment! Please try again later.");
        log.info("INTERNAL SERVER ERROR: {}", message);
    }
}
