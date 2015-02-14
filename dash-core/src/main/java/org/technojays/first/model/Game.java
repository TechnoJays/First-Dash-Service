package org.technojays.first.model;

import javax.persistence.*;
import java.time.Year;

/**
 * @author DaPortlyJester
 * @since 1/17/2015
 *
 * Entity representing a FIRST "Game". A "Game" defines the basic attributes
 * of the challenge for each year.
 */
@Entity
@Table(name = "games", schema = "first")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private Year year;

    @Column(name = "program")
    private FIRSTProgram program;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public FIRSTProgram getProgram() {
        return program;
    }

    public void setProgram(FIRSTProgram program) {
        this.program = program;
    }
}
