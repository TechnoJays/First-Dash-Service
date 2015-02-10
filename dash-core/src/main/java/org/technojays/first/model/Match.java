package org.technojays.first.model;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * @author DaPortlyJester
 * @since 1/19/2015
 *
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Game game;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Event event;

    @Column(name = "start_time")
    private ZonedDateTime start;

    @Column(name = "type")
    private MatchType type;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "alliances",
            joinColumns = {@JoinColumn(name = "match_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "id")}
    )
    private Set<Team> teams;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Set<MatchScore> scores;

}
