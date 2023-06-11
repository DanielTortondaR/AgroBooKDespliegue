/**
 * 
 */
package com.dtr.agroBook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Daniel Tortonda Ruiz
 *
 */
public class ExceptionModel extends ResponseStatusException {

    
    private static final long serialVersionUID = -5384668747595768849L;

    public ExceptionModel(HttpStatus status, String reason) {
        super(status, reason);

    }

}
