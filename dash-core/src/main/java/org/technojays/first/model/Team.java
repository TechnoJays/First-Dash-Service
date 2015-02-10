package org.technojays.first.model;

import javax.persistence.*;
import java.util.Set;

/**
 * @author DaPortlyJester
 * @since 1/17/2015
 * <p/>
 * Entity to represent team information.
 */

@Entity
@Table(name = "teams", schema = "first")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "team_num", unique = true, nullable = false)
    private Long teamNum;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "short_name")
    private String shortName;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "alliances",
//            joinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "match_id", referencedColumnName = "id")})
//    private Set<Match> matches;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(Long teamNum) {
        this.teamNum = teamNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

//    public Set<Match> getMatches() {
//        return matches;
//    }
//
//    public void setMatches(Set<Match> matches) {
//        this.matches = matches;
//    }
}
