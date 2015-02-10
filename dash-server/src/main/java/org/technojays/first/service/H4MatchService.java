package org.technojays.first.service;

import com.google.inject.Inject;
import org.technojays.first.dao.MatchDAO;
import org.technojays.first.model.Match;
import org.technojays.first.model.MatchType;

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

    @Override
    public Match getMatchById(Long id) {
        return null;
    }

    @Override
    public Match getMatchByMatchNumber(Long matchNum) {
        return null;
    }

    @Override
    public List<Match> getMatchesByEventId(Long eventId) {
        return null;
    }

    @Override
    public List<Match> getMatchesAfterTime(ZonedDateTime start) {
        return null;
    }

    @Override
    public List<Match> getMatchesByTypeAndEvent(MatchType type, Long eventId) {
        return null;
    }

    @Override
    public List<Match> getMatchesByTeamAndEvent(Long teamId, Long eventId) {
        return null;
    }

    @Override
    public Match saveMatch(Match match) {
        return null;
    }
}
