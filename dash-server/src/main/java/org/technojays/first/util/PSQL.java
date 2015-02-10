package org.technojays.first.util;

/**
 * @author Derelle.Redmond
 * @since 2/7/2015.
 */
public class PSQL {

    public static final String SELECT_TEAMS = "SELECT t FROM Team t";
    public static String SELECT_TEAM_BY_ID = "SELECT t FROM Team t where t.id=:id";
    public static String SELECT_TEAM_BY_TEAM_NUMBER = "SELECT t FROM Team t where t.teamNumber=:teamNumber";
}
