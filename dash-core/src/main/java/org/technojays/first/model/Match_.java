package org.technojays.first.model;

import org.technojays.first.model.*;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import java.time.ZonedDateTime;

/**
 * @author Derelle.Redmond
 * @since 2/9/2015
 *
 * Hibernate metamodel for matches for criteria building
 */
public class Match_ {
    public static volatile SingularAttribute<Match, Long> id;
    public static volatile SingularAttribute<Match, Long> matchNum;
    public static volatile SingularAttribute<Match, Game> game;
    public static volatile SingularAttribute<Match, Event> event;
    public static volatile SingularAttribute<Match, ZonedDateTime> start;
    public static volatile SingularAttribute<Match, MatchType> type;
    public static volatile SetAttribute<Match, Ally> allies;
    public static volatile SetAttribute<Match, MatchScore> scores;
}
