package cz.fi.muni.pa165.rest.exceptions;

/**
 * @author Richard Trebichavský
 */
//The @ResponseStatus annotation is not needed if we use GlobalExceptionController
//@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason="The resource already exists")
public class ResourceAlreadyExistingException extends RuntimeException {
}
