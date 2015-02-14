# First-Dash-Service
FIRST Robotics Competition Dash Service



## Building FIRST Dash

### Requirements
- Java 1.8
- Tomcat 8
- PostgreSQL 9+

### Libraries
- Jersey
- Google Guice / Guice Persist
- Jackson (JSON)
- Hibernate 4
- Jukito Testing Framework

### Setup
- Build Postgres Database - [TODO: Include Vagarant Build To Support Dash Development]
  - Database: first_dash, configured in H4 properties]
  - Schema: first
    - Must be created for database
  - User: configured in H4 properties file
- Properties Files
  - dash.h4.config.file property
    - /etc/dash/h4.properties
  - dash.config.file property
    - /etc/dash/application.properties
  - Properties can be configured through system environment properties. Property file variables should be empty


