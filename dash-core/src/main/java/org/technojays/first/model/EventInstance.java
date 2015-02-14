package org.technojays.first.model;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * @author DaPortlyJester
 * @since 6/2/2015
 *
 * Entity representing a particular instance of an event. This describes which Game the event was tied to,
 * its location and date
 */
@Entity
@Table(name = "event_instance", schema = "first")
public class EventInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(name = "start_time")
    private ZonedDateTime startDate;

    @Column(name = "end_time")
    private ZonedDateTime endDate;

    @OneToOne
    @JoinColumn(name = "location_id")
    private FDLocation location;
}
