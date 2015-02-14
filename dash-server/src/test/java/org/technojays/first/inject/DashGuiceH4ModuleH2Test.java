package org.technojays.first.inject;

import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.technojays.first.dao.TeamDAO;
import org.technojays.first.model.Team;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Derelle.Redmond
 * @since 2/7/2015
 *
 * Tests the Dash Guice Hibernate Module Module
 */
@RunWith(JukitoRunner.class)
public class DashGuiceH4ModuleH2Test {

    @Test
    @UseModules({ConfigurationInjection.class, DashGuiceH4Module.class})
    public void testDB(TeamDAO teamDAO) {
        Team team = new Team();
        // If we set the id before persisting, the entity will be found
        // to be detached
        //team.setId(1L);
        team.setName("TestName");
        team.setTeamNum(1L);
        team.setShortName("STN");
        teamDAO.save(team);

        Team retrievedTeam = teamDAO.find(team.getId());
        assertEquals(team.getId(), retrievedTeam.getId());
        assertEquals(team.getName(), retrievedTeam.getName());
        assertEquals(team.getShortName(), retrievedTeam.getShortName());

        retrievedTeam.setName("updatedName");
        teamDAO.update(team);

        Team updatedTeam = teamDAO.find(retrievedTeam.getId());
        assertEquals(retrievedTeam.getId(), updatedTeam.getId());
        assertEquals(retrievedTeam.getName(), updatedTeam.getName());
        assertEquals(retrievedTeam.getShortName(), updatedTeam.getShortName());

        List<Team> teams = teamDAO.findAll();
        Iterator itr = teams.iterator();
        Team teamFromList = (Team)itr.next();
        assertEquals(updatedTeam.getId(), teamFromList.getId());
        assertEquals(updatedTeam.getName(), teamFromList.getName());
        assertEquals(updatedTeam.getShortName(), teamFromList.getShortName());
    }
}
