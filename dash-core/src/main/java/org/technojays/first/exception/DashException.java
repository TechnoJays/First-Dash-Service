package org.technojays.first.exception;

/**
 * @author derelle.redmond
 * @since 1/24/15.
 */
public class DashException extends Exception {

    public DashException(ServiceError error) {
        super(error.getMessage());
    }
}
