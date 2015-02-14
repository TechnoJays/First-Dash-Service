package org.technojays.first.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.technojays.first.exception.DashException;
import org.technojays.first.model.Match;
import org.technojays.first.service.MatchService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author DaPortlyJester
 * @since 1/19/2015
 * Rest endpoint for retrieving match information
 */
@Path("matches")
public class MatchResource extends DashResource {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private MatchService matchService;

    @Inject
    public MatchResource(MatchService matchService) {
        this.matchService = matchService;
    }

    /**
     * Get match by FIRST Dash Id
     *
     * @param idParam System id of match
     * @return Match associated with system id number
     */
    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Match getById(@PathParam("id") String idParam) throws DashException {
        logger.debug("Getting match by id: {}", idParam);
        Long id = getLongFromParameter(idParam);
        return matchService.getById(id);
    }

    /**
     * Get match by match number
     *
     * @param matchNumberParam FIRST event match number
     * @param eventIdParam event Id Param
     * @return Match associated with match number
     */
    @GET
    @Path("/{matchNumber}/event/{eventId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Match getByMatchNumberAndEvent(@PathParam("matchNumber") String matchNumberParam,
                                          @PathParam("eventId") String eventIdParam) throws DashException {
        logger.debug("Getting match by match number: {}, and event id {}", matchNumberParam, eventIdParam);
        Long matchNum = getLongFromParameter(matchNumberParam);
        Long eventId = getLongFromParameter(eventIdParam);
        return matchService.getByMatchNumberAndEvent(matchNum, eventId);
    }
}
