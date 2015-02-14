package org.technojays.first.dao;

import org.technojays.first.model.*;

import java.time.Year;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author DaPortlyJester
 * @since 5/4/2015
 */
public abstract class DAOTest {

    protected long testTeamId;
    protected long testMatchId;
    protected long testEventId;
    protected long testGameId;

    public long TEST_TEAM_NUM = 10l;
    public long TEST_ID = 10l;
    public long TEST_MATCH_NUM = 10l;


    public Match buildTestMatch(MatchDAO matchDAO, boolean save) {
        Match match = new Match();
        match.setMatchNum(TEST_MATCH_NUM);

        Game game = buildTestGame();
        match.setGame(game);

        Event event = buildTestEvent();
        match.setEvent(event);

        match.setStart(ZonedDateTime.now());
        match.setType(MatchType.QUALIFYING);

/*        Ally ally = buildTestAlly(match, buildTestTeam(TEST_TEAM_NUM));
        Set<Ally> allies = new HashSet<>();
        allies.add(ally);
        match.setAllies(allies);

        MatchScore matchScore = buildTestMatchScore(match);
        Set<MatchScore> scores = new HashSet<>();
        scores.add(matchScore);
        match.setScores(scores);*/
        if(save) { matchDAO.save(match); }
        return match;
    }

    public Game buildTestGame(){
        Game game = new Game();
        game.setName("Test Game");
        game.setProgram(FIRSTProgram.FRC);
        game.setYear(Year.now());
        return game;
    }

    public Event buildTestEvent() {
        Event event = new Event();
        event.setEventCode("TSTEVENT");
        event.setType(EventType.DISTRICT_EVENT);
        event.setStartDate(ZonedDateTime.now());
        event.setEndDate(event.getStartDate().plusDays(10l));
        event.setLocation("123 ABC Street");
        event.setName("Test Event");
        event.setVenue("District Test Venue");
        return event;
    }

    public Ally buildTestAlly(Match match) {
        return buildTestAlly(match, new Team());
    }

    public Ally buildTestAlly(Team team) {
        return buildTestAlly(new Match(), team);
    }

    public Ally buildTestAlly(Match match, Team team) {
        Ally ally = new Ally();
        ally.setMatch(match);
        ally.setMatchAllianceNumber(1l);
        ally.setTeam(team);
        return ally;
    }

    public Ally buildTestAlly() {
        return buildTestAlly(new Match(), buildTestTeam(TEST_TEAM_NUM));
    }

    public Team buildTestTeam(long teamNum) {
        Team team = new Team();
        team.setName("Test Team " + teamNum);
        team.setShortName("TT " + teamNum);
        team.setTeamNum(teamNum);
        return team;
    }

    public Team buildTestTeam(long teamNum, String teamName) {
        Team team = new Team();
        team.setName(teamName + teamNum);
        team.setShortName("TT " + teamNum);
        team.setTeamNum(teamNum);
        return team;
    }

    public Team buildTestTeam(long teamNum, String teamName, String shortName) {
        Team team = new Team();
        team.setName(teamName + teamNum);
        team.setShortName("TT " + teamNum);
        team.setTeamNum(teamNum);
        return team;
    }

    public MatchScore buildTestMatchScore(Match match){
        MatchScore matchScore = new MatchScore();
        matchScore.setMatch(match);
        matchScore.setScore(7894l);
        matchScore.setType(ScoreType.TELEOP);
        return matchScore;
    }

}
