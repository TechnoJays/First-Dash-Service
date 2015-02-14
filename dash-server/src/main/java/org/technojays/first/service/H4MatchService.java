package org.technojays.first.service;

import com.google.inject.Inject;
import org.technojays.first.dao.EventDAO;
import org.technojays.first.dao.MatchDAO;
import org.technojays.first.dao.TeamDAO;
import org.technojays.first.model.Event;
import org.technojays.first.model.Match;
import org.technojays.first.model.MatchType;
import org.technojays.first.model.Team;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Derelle.Redmond
 * @since 2/9/2015
 *
 * Match Service for Hibernate 4
 */
public class H4MatchService implements MatchService {

    @Inject
    MatchDAO matchDAO;

    @Inject
    EventDAO eventDAO;

    @Inject
    TeamDAO teamDAO;

    @Override
    public Match getById(Long id) {
        return matchDAO.find(id);
    }

    @Override
    public List<Match> getByMatchNumber(Long matchNum) {
        return matchDAO.getByMatchNumber(matchNum);
    }

    @Override
    public Match getByMatchNumberAndEvent(Long matchNum, Long eventId) {
        return matchDAO.getByMatchNumberAndEvent(matchNum,eventId);
    }

    @Override
    public List<Match> getByEventId(Long eventId) {
        Event event = eventDAO.find(eventId);
        return matchDAO.getForEvent(event);
    }

    @Override
    public List<Match> getAfterStartTime(ZonedDateTime start) {
        return matchDAO.getAfterDate(start);
    }

    @Override
    public List<Match> getByTypeAndEvent(MatchType type, Long eventId) {
        Event event = eventDAO.find(eventId);
        return matchDAO.getByEventAndType(event, type);
    }

    @Override
    public List<Match> getByTeamAndEvent(Long teamId, Long eventId) {
//        Event event = eventDAO.find(eventId);
//        Team team = teamDAO.find(teamId);
//        return matchDAO.getByTeamAndEvent(team, event);
        return null;
    }

    @Override
    public Match saveMatch(Match match) {
        matchDAO.save(match);
        return match;
    }
}
