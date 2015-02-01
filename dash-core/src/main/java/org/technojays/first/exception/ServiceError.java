package org.technojays.first.exception;

/**
 * @author derelle.redmond
 * @since 1/24/15.
 */
public enum ServiceError {
    UNKNOWN("Service Exception"),
    EMPTY_ID("Null or Empty Id"),
    BAD_NUMBER_FORMAT("Bad number format");

    private String message;

    ServiceError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
