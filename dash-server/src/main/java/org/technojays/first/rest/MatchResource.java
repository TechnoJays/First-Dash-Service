package org.technojays.first.rest;

import org.technojays.first.model.Match;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author DaPortlyJester
 * @since 1/19/2015
 */
@Path("match")
public class MatchResource extends DashResource {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Match getMatch(@PathParam("id") String id) {
        return new Match();
    }

    @GET
    @Path("/number/{matchNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Match getMatchByMatchNumber(@PathParam("matchNumber") String matchNumber) {
        return new Match();
    }
}
