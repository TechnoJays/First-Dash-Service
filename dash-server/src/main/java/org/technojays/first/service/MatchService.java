package org.technojays.first.service;

import org.technojays.first.model.Match;
import org.technojays.first.model.MatchType;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Derelle.Redmond
 * @since 2/9/2015.
 * <p/>
 * Service interfave to retrieve match information
 */
public interface MatchService {

    public Match getMatchById(Long id);

    public Match getMatchByMatchNumber(Long matchNum);

    public List<Match> getMatchesByEventId(Long eventId);

    public List<Match> getMatchesAfterTime(ZonedDateTime start);

    public List<Match> getMatchesByTypeAndEvent(MatchType type, Long eventId);

    public List<Match> getMatchesByTeamAndEvent(Long teamId, Long eventId);

    public Match saveMatch(Match match);

}
