package org.technojays.first.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author DaPortlyJester
 * @since 1/19/2015
 *
 * Entity for correlating match, team and ally information for a given match
 */
/*, indexes = {@Index(columnList = "id")}*/
@Entity
@Table(name = "allies", schema = "first")
public class Ally implements Serializable {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    Long id;

    @Id
    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @Id
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "m_alliance_number", nullable = false)
    private Long matchAllianceNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getMatchAllianceNumber() {
        return matchAllianceNumber;
    }

    public void setMatchAllianceNumber(Long matchAllianceNumber) {
        this.matchAllianceNumber = matchAllianceNumber;
    }
}
