package org.technojays.first.model;

import org.technojays.first.model.Event;
import org.technojays.first.model.EventType;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.ZonedDateTime;

/**
 * @author derelle.redmond
 * @since 2/14/15.
 */
@StaticMetamodel(Event.class)
public class Event_ {
    public static volatile SingularAttribute<Event, Long> id;
    public static volatile SingularAttribute<Event, String> name;
    public static volatile SingularAttribute<Event, EventType> type;
    public static volatile SingularAttribute<Event, String> venue;
    public static volatile SingularAttribute<Event, String> location;
    public static volatile SingularAttribute<Event, ZonedDateTime> startDate;
    public static volatile SingularAttribute<Event, ZonedDateTime> endDate;
    public static volatile SingularAttribute<Event, String> eventCode;
}
