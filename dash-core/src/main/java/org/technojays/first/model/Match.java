package org.technojays.first.model;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * @author DaPortlyJester
 * @since 1/19/2015
 * <p/>
 * Entity representing a match at a competition or event
 */
@Entity
@Table(name = "matches", schema = "first")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "match_number")
    private Long matchNum;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "start_time")
    private ZonedDateTime start;

    @Column(name = "type")
    private MatchType type;

/*    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", referencedColumnName = "id")
    private Set<Ally> allies;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Set<MatchScore> scores;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMatchNum() {
        return matchNum;
    }

    public void setMatchNum(Long matchNum) {
        this.matchNum = matchNum;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public MatchType getType() {
        return type;
    }

    public void setType(MatchType type) {
        this.type = type;
    }

/*    public Set<Ally> getAllies() {
        return allies;
    }

    public void setAllies(Set<Ally> allies) {
        this.allies = allies;
    }

    public Set<MatchScore> getScores() {
        return scores;
    }

    public void setScores(Set<MatchScore> scores) {
        this.scores = scores;
    }*/
}
