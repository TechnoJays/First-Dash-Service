package org.technojays.first.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.technojays.first.exception.DashException;
import org.technojays.first.model.Team;
import org.technojays.first.service.TeamService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author DaPortlyJester
 * @since 1/19/2015
 * <p/>
 * REST endpoint for retrieving team information
 */
@Path("teams")
public class TeamResource extends DashResource {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private TeamService teamService;

    @Inject
    public TeamResource(TeamService teamService) {
        this.teamService = teamService;
    }

    /**
     * Get team by FIRST Dash Id
     *
     * @param teamNumParam Id of the team
     * @return Team associated with given system id number
     * @throws DashException
     */
    @GET
    @Path("/{teamNum}")
    @Produces(MediaType.APPLICATION_JSON)
    public Team getTeam(@PathParam("teamNum") String teamNumParam) throws DashException {
        logger.debug("Getting team by teamNumber {}", teamNumParam);
        Long teamNum = getLongFromParameter(teamNumParam);
        return teamService.getTeamByTeamNumber(teamNum);
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
        if(teams == null || teams.isEmpty()) {
            return null;
        }
        return teams;
    }

    /**
     * Get team by FIRST Dash id number
     *
     * @param idParam Number associated with the team by FIRST
     * @return Team associated with team number
     * @throws DashException
     */
    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Team getTeamByTeamNumber(@PathParam("id") String idParam) throws DashException {
        logger.debug("Getting team by id {}", idParam);
        Long id = getLongFromParameter(idParam);
        return teamService.getTeamById(id);
    }
}
