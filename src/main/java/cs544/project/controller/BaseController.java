/**
 * Ahmed Hamdy
 */
package cs544.project.controller;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Ahmed Hamdy
 *
 */
@RestController
@RequestMapping("/")
@ApiResponses(value = { //
		@ApiResponse(code = 400, message = "Something went wrong"), //
		@ApiResponse(code = 403, message = "Access denied"), //
		@ApiResponse(code = 404, message = "The user doesn't exist"), //
		@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
public class BaseController {
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> 
        handleConstraintViolation(ConstraintViolationException ex,
                                  WebRequest request) {

        return new ResponseEntity<>("Error 400: \n" + ex.getMessage(),
                                    HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> 
        handleIntegrityConstraintViolation(DataIntegrityViolationException ex,
                                           WebRequest request) {

        return new ResponseEntity<>("Error 400: \n" + ex.getMessage() 
                                        + "\n" + ex.getRootCause(),
                                    HttpStatus.BAD_REQUEST);
    }

}
