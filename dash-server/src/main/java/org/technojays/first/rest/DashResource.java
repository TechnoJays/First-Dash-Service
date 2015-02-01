package org.technojays.first.rest;

import com.google.common.base.Strings;
import org.technojays.first.exception.DashException;
import org.technojays.first.exception.ServiceError;

/**
 * @author derelle.redmond
 * @since 1/24/15.
 */
public class DashResource {

    protected Long getLongFromParameter(String longParam) throws DashException {
        if(Strings.isNullOrEmpty(longParam)) {
            throw new DashException(ServiceError.EMPTY_ID);
        }

        try {
            return Long.valueOf(longParam);
        } catch (NumberFormatException e) {
            throw new DashException(ServiceError.BAD_NUMBER_FORMAT);
        }
    }
}
