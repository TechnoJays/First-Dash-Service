package org.technojays.first.model;

/**
 * @author derelle.redmond
 * @since 1/24/15.
 */
public enum EventType {
    CHAMPIONSHIP("Championship"),
    REGIONAL("Regional"),
    DISTRICT_CHAMPIONSHIP("District Championship"),
    DISTRICT_EVENT("District Event");


    private String name;

    EventType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
