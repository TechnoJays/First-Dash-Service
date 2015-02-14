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

    /**
     * Get match by id
     *
     * @param id System id of the match
     * @return Match with given id, null if match does not exist
     */
    public Match getById(Long id);

    /**
     * Get matches by FIRST match number
     *
     * @param matchNum Match number to find by
     * @return List of matches that have this match Number
     */
    public List<Match> getByMatchNumber(Long matchNum);

    /**
     * Get match by number and event
     *
     * @param matchNum Match number to find by
     * @param eventId System id of the event associated with the match
     * @return Match with given match number and event, null if the match does not exist
     */
    public Match getByMatchNumberAndEvent(Long matchNum, Long eventId);

    /**
     * Get list of matches by event
     *
     * @param eventId System id of the event to find by
     * @return List of matches associated with the event
     */
    public List<Match> getByEventId(Long eventId);

    /**
     * Get list of matches starting after the give date
     *
     * @param start Date all matches start after
     * @return List of matches that start after the given date
     */
    public List<Match> getAfterStartTime(ZonedDateTime start);

    /**
     * Get list of matches by event and match type
     *
     * @param type Type of match
     * @param eventId System id for the event
     * @return List of matches with the given type for the event
     */
    public List<Match> getByTypeAndEvent(MatchType type, Long eventId);

    /**
     * Get list of matches by event and team
     *
     * @param teamId System id of team
     * @param eventId System id of event
     * @return List of matches for the team and event
     */
    public List<Match> getByTeamAndEvent(Long teamId, Long eventId);

    /**
     * Persist the match data
     *
     * @param match Match information to persist
     * @return Saved match information
     */
    public Match saveMatch(Match match);

}
