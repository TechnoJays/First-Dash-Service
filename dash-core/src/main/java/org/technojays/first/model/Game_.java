package org.technojays.first.model;

import org.technojays.first.model.FIRSTProgram;
import org.technojays.first.model.Game;

import javax.persistence.metamodel.SingularAttribute;
import java.time.Year;

/**
 * @author Derelle.Redmond
 * @since 2/9/2015
 *
 * Hibernate metamodel for Game for criteria building
 */
public class Game_ {
    public static volatile SingularAttribute<Game, Long> id;
    public static volatile SingularAttribute<Game, String> name;
    public static volatile SingularAttribute<Game, Year> year;
    public static volatile SingularAttribute<Game, FIRSTProgram> program;
}
