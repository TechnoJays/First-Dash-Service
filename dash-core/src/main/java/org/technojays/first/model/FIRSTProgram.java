package org.technojays.first.model;

/**
 * @author Derelle.Redmond
 * @since 2/9/2015.
 *
 * FIRST Programs enumeration. Contains information about FIRST current
 * programs
 */
public enum FIRSTProgram {
    JUNIOR_FLL("Junior FIRST Lego League", "K-3"),
    FLL("FIRST Lego League", "4-8"),
    FTC("FIRST Tech Challenge", "7-12"),
    FRC("FIRST Robotics Competition", "9-12");

    private String programName;
    private String grades;

    FIRSTProgram(String programName, String grades) {
        this.programName = programName;
        this.grades = grades;
    }

    public String getProgramName() {
        return programName;
    }

    public String getGrades() {
        return grades;
    }
}
