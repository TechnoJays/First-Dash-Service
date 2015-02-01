package org.technojays.first.rest;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.technojays.first.exception.DashException;
import org.technojays.first.model.Team;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DaPortlyJester
 * @since 1/19/2015
 * <p/>
 * REST endpoint for retrieving team information
 */
@Path("team")
public class TeamResource extends DashResource{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Team getTeam(@PathParam("id") String idParam) throws DashException {
        logger.debug("Getting team by id");
        Long id = getLongFromParameter(idParam);
        Team team = new Team();
        team.setId(id);
        return team;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Team> getTeams() {
        logger.debug("Getting full list of teams");
        List<Team> teams = new ArrayList<Team>();
        teams.add(new Team());
        teams.add(new Team());
        return teams;
    }

    @GET
    @Path("/number/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public Team getTeamByTeamNumber(@PathParam("number") String teamNumber) throws DashException {
        logger.debug("Getting team by team number");
        Long teamNum = getLongFromParameter(teamNumber);
        Team team = new Team()                   ;
        team.setTeamNum(teamNum);
        return team;
    }
}
