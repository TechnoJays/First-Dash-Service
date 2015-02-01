package org.technojays.first.model;

import javax.persistence.*;

/**
 * @author DaPortlyJester
 * @since 1/19/2015
 */
@Table(name = "match_score")
public class MatchScore {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = true)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Match match;

    @Column(name = "type", nullable = false)
    private ScoreType type;

    @Column(name = "score", nullable = false)
    private Long score;
}
