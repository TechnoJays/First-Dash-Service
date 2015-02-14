package org.technojays.first.dao;

import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.technojays.first.inject.DashGuiceH4ServletModule;
import org.technojays.first.inject.PersistenceInit;
import org.technojays.first.model.Event;
import org.technojays.first.model.Match;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(JukitoRunner.class)
public class MatchDAOTest extends DAOTest {

    public static final long MATCH_NUM = 100l;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    @UseModules({DashGuiceH4ServletModule.class})
    public void testGetByMatchNumber(PersistenceInit persistenceInit, MatchDAO matchDAO) throws Exception {
        Match testMatch = buildTestMatch(matchDAO, false);
        testMatch.setMatchNum(MATCH_NUM);
        List<Match> matches = matchDAO.getByMatchNumber(MATCH_NUM);
        assertNotNull(matches);
        assertTrue(matches.isEmpty());
        matchDAO.save(testMatch);
        matches = matchDAO.getByMatchNumber(MATCH_NUM);
        for (Match m : matches) {
            assertEquals(m, testMatch);
            matchDAO.remove(m);
        }
    }

    @Test
    @UseModules({DashGuiceH4ServletModule.class})
    public void testGetByMatchNumberAndEvent(PersistenceInit persistenceInit, MatchDAO matchDAO) throws Exception {
        Match testMatch = buildTestMatch(matchDAO, false);
        testMatch.setMatchNum(MATCH_NUM);
        Match match = matchDAO.getByMatchNumberAndEvent(MATCH_NUM, testMatch.getEvent().getId());
        assertNull(match);
        matchDAO.save(testMatch);
        match = matchDAO.getByMatchNumberAndEvent(MATCH_NUM, testMatch.getEvent().getId());
        assertEquals(match, testMatch);
        matchDAO.remove(match);
    }

    @Test
    @UseModules({DashGuiceH4ServletModule.class})
    public void testGetAfterDate(PersistenceInit persistenceInit, MatchDAO matchDAO) throws Exception {
        Match testMatch = buildTestMatch(matchDAO, false);
        List<Match> matches = matchDAO.getAfterDate(testMatch.getStart().minusHours(1));
        assertNotNull(matches);
        assertTrue(matches.isEmpty());
        matchDAO.save(testMatch);
        matches = matchDAO.getAfterDate(testMatch.getStart().minusHours(1));
        assertNotNull(matches);
        assertFalse(matches.isEmpty());
        assertTrue(matches.contains(testMatch) && matches.size() == 1);
        matches.forEach(match -> matchDAO.remove(match));
    }

    @Test
    @UseModules({DashGuiceH4ServletModule.class})
    public void testGetBetweenDates(PersistenceInit persistenceInit, MatchDAO matchDAO) throws Exception {
        Match testMatch = buildTestMatch(matchDAO, false);
        List<Match> matches = matchDAO.getBetweenDates(testMatch.getStart().minusHours(1),
                testMatch.getStart().plusHours(1));
        assertNotNull(matches);
        assertTrue(matches.isEmpty());
        matchDAO.save(testMatch);
        matches = matchDAO.getBetweenDates(testMatch.getStart().minusHours(1), testMatch.getStart().plusHours(1));
        assertNotNull(matches);
        assertFalse(matches.isEmpty());
        assertTrue(matches.contains(testMatch) && matches.size() == 1);

        // Parallel stream offers no performance improvement iterating over linked lists
        // Actually can cause performance loss
        matches.parallelStream().forEach(match -> matchDAO.remove(match));
    }

    @Test
    @UseModules({DashGuiceH4ServletModule.class})
    public void testGetForEvent(PersistenceInit persistenceInit, MatchDAO matchDAO, EventDAO eventDAO)
            throws Exception {
        Match testMatch = buildTestMatch(matchDAO, false);
        testMatch.setMatchNum(MATCH_NUM);
        Event event = testMatch.getEvent();
        eventDAO.save(event);
        testMatch.setEvent(event);

        List<Match> matches = matchDAO.getForEvent(event);
        assertNotNull(matches);
        assertTrue(matches.isEmpty());

        matchDAO.save(testMatch);
        matches = matchDAO.getForEvent(event);
        assertNotNull(matches);
        assertTrue(matches.contains(testMatch) && matches.size() == 1);

        eventDAO.remove(event);
        matches.forEach(match -> matchDAO.remove(match));
    }

    @Test
    @UseModules({DashGuiceH4ServletModule.class})
    public void transientEventIllegalState(PersistenceInit persistenceInit, MatchDAO matchDAO) throws Exception {
        Match testMatch = buildTestMatch(matchDAO, false);
        testMatch.setMatchNum(MATCH_NUM);
        Event event = testMatch.getEvent();
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("save the transient instance before flushing: org.technojays.first.model.Event");
        List<Match> matches = matchDAO.getForEvent(event);
    }

    @Test
    @UseModules({DashGuiceH4ServletModule.class})
    public void testGetByType() throws Exception {

    }

    @Test
    @UseModules({DashGuiceH4ServletModule.class})
    public void testGetByEventAndType() throws Exception {

    }

    @Test
    @UseModules({DashGuiceH4ServletModule.class})
    public void testGetByTeamAndEvent() throws Exception {

    }


}