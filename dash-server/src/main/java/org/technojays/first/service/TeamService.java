package org.technojays.first.service;

import org.technojays.first.model.Team;

import java.util.List;

/**
 * Created by DaPortlyJester
 *
 * @since 1/17/2015.
 * <p/>
 * Service interface to retrieve Team information
 */
public interface TeamService {

    Team getTeamById(Long id);

    Team getTeamByTeamNumber(Long teamNumber);

    List<Team> getTeams();

    Team saveTeam(Team team);

}
