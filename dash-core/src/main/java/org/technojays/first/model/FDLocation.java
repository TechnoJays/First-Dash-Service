package org.technojays.first.model;

import javax.persistence.*;

/**
 * @author DaPortlyJester
 * @since 6/5/2015
 *
 * Entity to maintain location information around an Event instance. The placeId field maps to Google Maps API
 * place ID
 */

@Entity
@Table(name = "locations", schema = "first")
public class FDLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String placeId;

    @Column(name = "lng")
    private Float longitude;

    @Column(name = "lat")
    private Float latitude;

}
