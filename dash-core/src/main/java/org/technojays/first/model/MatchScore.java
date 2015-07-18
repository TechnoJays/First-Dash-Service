package org.technojays.first.model;

import javax.persistence.*;

/**
 * @author DaPortlyJester
 * @since 1/19/2015
 *
 * Match Scores represent any of the many types of scoring that can be associated with a
 * match. Examples include autonomous, coopertitition, bonus, foul, etc.
 */
@Entity
@Table(name = "match_score", schema = "first")
public class MatchScore {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

    @Column(name = "type", nullable = false)
    private ScoreType type;

    @Column(name = "score", nullable = false)
    private Long score;

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

    public ScoreType getType() {
        return type;
    }

    public void setType(ScoreType type) {
        this.type = type;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}
