package org.technojays.first.model;

import org.technojays.first.model.Match;
import org.technojays.first.model.Team;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * @author Derelle.Redmond
 * @since 2/9/2015.
 *
 * Hibernate metamodel for teams for criteria building
 */
@StaticMetamodel( Team.class)
public class Team_ {
    public static volatile SingularAttribute<Team, Long> id;
    public static volatile SingularAttribute<Team, Long> teamNum;
    public static volatile SingularAttribute<Team, String> name;
    public static volatile SingularAttribute<Team, String> shortName;
    public static volatile SetAttribute<Team, Match> matches;
}
