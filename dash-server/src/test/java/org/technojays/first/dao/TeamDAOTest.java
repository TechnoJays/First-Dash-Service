package org.technojays.first.dao;

import com.google.inject.persist.PersistService;
import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.technojays.first.inject.DashGuiceH4ServletModule;
import org.technojays.first.inject.PersistenceInit;
import org.technojays.first.model.Team;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(JukitoRunner.class)
// ConfigurationInjection.class,
public class TeamDAOTest extends DAOTest {

    /**
     * @Before
     * @UseModules({DashGuiceH4ServletModule.class}) public void setup(PersistService persistService){
     * PersistenceInit persistenceInit = new PersistenceInit(persistService);
     * }*
     */

    @Test
    @UseModules({DashGuiceH4ServletModule.class})
    public void testGetByTeamNumber(PersistenceInit persistenceInit, TeamDAO teamDAO) throws Exception {
        long testTeamNum = 50L;
        Team team = teamDAO.getByTeamNumber(testTeamNum);
        assertNull(team);
        Team testTeam = buildTestTeam(testTeamNum);
        teamDAO.save(testTeam);
        team = teamDAO.getByTeamNumber(testTeamNum);
        assertNotNull(team);
        assertEquals(testTeam, team);
        teamDAO.remove(team.getId());
    }

    @Test
    @UseModules({DashGuiceH4ServletModule.class})
    public void testGetByTeamName(PersistenceInit persistenceInit, TeamDAO teamDAO) throws Exception {
        long testTeamNum = 100l;
        String testTeamName = "TestName";
        Team testTeam = buildTestTeam(testTeamNum, testTeamName);
        List<Team> teams = teamDAO.getByTeamName(testTeamName);
        assertTrue(teams.isEmpty());
        teamDAO.save(testTeam);
        teams = teamDAO.getByTeamName(testTeam.getName());
        assertFalse(teams.isEmpty());
        for (Team t : teams) {
            assertEquals(testTeam, teams.get(0));
            teamDAO.remove(t);
        }
    }

    @Test
    @UseModules({DashGuiceH4ServletModule.class})
    public void testGetByShortName(PersistenceInit persistenceInit, TeamDAO teamDAO) throws Exception {
        long testTeamNum = 25l;
        String testTeamName = "TestName";
        String testShortName = "Test Short Name";
        Team testTeam = buildTestTeam(testTeamNum, testTeamName, testShortName);
        List<Team> teams = teamDAO.getByShortName(testTeam.getShortName());
        assertTrue(teams.isEmpty());
        teamDAO.save(testTeam);
        teams = teamDAO.getByShortName(testTeam.getShortName());
        assertFalse(teams.isEmpty());
        for (Team t : teams) {
            assertEquals(testTeam, teams.get(0));
            teamDAO.remove(t);
        }
    }

}