package org.technojays.first.rest;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.technojays.first.exception.DashException;
import org.technojays.first.model.Team;
import org.technojays.first.service.TeamService;

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

    private TeamService teamService;

    @Inject
    public TeamResource(TeamService teamService){
        this.teamService = teamService;
    }

    /**
     * Get team by FIRST Dash Id
     *
     * @param idParam Id of the team
     * @return Team associated with given system id number
     * @throws DashException
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Team getTeam(@PathParam("id") String idParam) throws DashException {
        logger.debug("Getting team by id {}", idParam);
        Long id = getLongFromParameter(idParam);
        Team team = teamService.getTeamById(id);
        return team;
    }

    /**
     * Get all teams
     *
     * @return All known teams
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Team> getTeams() {
        logger.debug("Getting full list of teams");
        List<Team> teams = teamService.getTeams();
        return teams;
    }

    /**
     * Get team by team number
     *
     * @param teamNumber Number associated with the team by FIRST
     * @return Team associated with team number
     * @throws DashException
     */
    @GET
    @Path("/number/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public Team getTeamByTeamNumber(@PathParam("number") String teamNumber) throws DashException {
        logger.debug("Getting team by team number {}", teamNumber);
        Long teamNum = getLongFromParameter(teamNumber);
        Team team = teamService.getTeamByTeamNumber(teamNum);
        return team;
    }
}
