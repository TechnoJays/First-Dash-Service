# First Dash Entity Models
Description of the entity models for first dash.

All entities below are expected to be maintained in first_dash schema.
All types should be implemented by hstore key-value pairs

## Entities
*Alliance Type*<br />
*Alliances*<br />
*Allies*<br />
*Attribute Types*<br />
*Awards*<br />
*Awardees*<br />
*Competitions*<br />
*Events*<br />
*Games*<br />
*Locations*<br />
*Matches*<br />
*MatchScores*<br />
*Programs*<br />
*Sponsors*<br />
*Teams*<br />
*Team Attributes*<br />
*Users / Members*<br />
*Volunteers*<br />

### Alliance Type
Type descriptor for an alliance (red, blue, etc)
#### Fields
- id: system id for this alliance type
    - integer
    - primary key
- name: name of alliance type
    - varchar
- short_name: short name for alliance type
    - varchar

### Alliances
Relationship between a match and a group of allies
#### Fields
- id: system id for this alliance
    - integer
    - primary key
- type: type (color) of alliance
    - varchar
    - foreign key ( alliance type short name? )
- match_id: system id for the associated match
    - integer
    - foreign key (match) TODO
- ally_ref: ally->alliance reference for all teams that were a part of this alliance (sha-1 from team numbers)
    - varchar
    - indexed  TODO

### Allies
Relationship between a set of teams for an alliance
#### Fields
- id: system id for this ally
    - integer
    - primary key
- team_id: system id for associated team
    - integer
    - foreign key
- ally_ref: ally->alliance reference for matching teams
    - varchar
    - indexed
    
### Attribute types
Type of attributes in system
#### Fields
- id: system id for this attribute type
    - integer
    - primary key
- name: name for this attribute type
    - varchar
- short_name: short name for this attribute type
    - varchar
    - unique

### Awards
Awards presented at an event or competition to a team or person
#### Fields
- id: system id for this award
    - integer
    - primary key
- type_id: type of award
    - integer
    - foreign key
- event_id: system id for associated event
    - integer
    - foreign key
- competition_id: system id for associated competition
    - integer
    - foreign key
- team_id: system id for associated team
    - integer
    - foreign key
- awardee_id: system id for assocated awardee
    - integer
    - foreign key
    
### Awardees
Person / People who received an award
HStore key-value
- id: system id for awardee
    - integer
    - primary key
- first_name:
    - varchar
- last_name:
    - varchar

### Award Types
Types of awards for events and competitions
#### Fields
- id: system id for this award type
- info: key-value award info store


### Competitions
A competition describes a Location, Game, and is held at an Event
#### Fields
- id: system id for this event
    - integer
    - primary key
- name: display name
    - text
- type: type of competition (district, district championship, regional, championship)
- event_id: event system id
    - integer
    - foreign key
- location_id: location system id
    - integer
    - foreign key
- game_id: game system id
    - integer
    - foreign key
- start_time: start time of this competition
    - timestamp
- end_time: end time of this competition
    - timestamp

### Competition Types
Types of competitions
#### Fields
- id: system id for this event
    - integer
    - primary key
- info: info about this competition type
    - hstore

### Events
An event describes the timeframe for competitions and celebrations that are managed together
#### Fields
- id: system id for this event
    - integer
    - primary key
- name: display name
    - text
- short_name:
    - varchar
    - unique
- start_date: start of the event
- end_date: end of the event

### Games
A game describes a specific set of rules and competitions for a program and its teams. It has a period of time 
associated with it.
HStore Key-Value
#### Fields
- id: system id for this game
    - integer
    - primary key
- name: display name
    - text
- short_name: symbolic / abbreviated name
    - varchar
    - unique
- program_id: first program system id. the program associated with this game.
    - integer
    - foreign key
- s_start_date: season start date for this game.
    - timestamp 
- s_end_date: season end date for this game.
    - timestamp
- year: year associated with this game
    - year

### Locations
Coordinates for a location
#### Fields
- id: system id for a location
    - integer
    - primary key
- long: longitutde
    - float
- lat: latitude
    - float
- place_id: Google place id

### Matches
Describe a match where teams competed against each other
#### Fields
- id: system id for this match
    - integer
    - primary key
- match_number: match number for the competition
    - integer
    - indexed (with competition id)
- competition_id: competition system id
    - integer
    - indexed (with match_number)
- start_time: expected match start time
    - timestamp
- end_time: expected match end time
    - timestamp

### Match Scores
Scores associated with a match
#### Fields
- id: system id for this match
    - integer
    - primary key
- match_id: system id of the associated match
    - integer
    - foreign key
- alliance_id: system id of the associated alliance
    - integer
    - foreign key
- score_type_id: system id of the score type
    - integer
    - foreign key
- value: score value
    - varchar
    
### Score Types
Types of scores associated with matches
#### Fields
- id: system id for this score type
    - integer
    - primary key
- name: name of this score type
    - varchar
- short_name: short name for this score type
    - varchar

### Programs
A program is a sub-organization of FIRST that describes the type of games that will be played and the
types of participants.
#### Fields
- id: system id for this program
    - integer
    - primary key
- name: display name
    - text
- short_name: symbolic / abbreviated name. used in program searches.
    - varchar
    - unique
- membership: the student participant body for this program. (e.g. 1-5 grades, k-12, high school, etc)
    - varchar

### Sponsors
Sponsors for teams, events, groups, programs, etc
#### Fields
- id: system id for this sponsor
    - integer
    - primary key
- name: sponsor name
    - varchar
- short_name: short sponsor name
    - varchar
- website: sponsor website

### Teams
A team associated with a specific FIRST program
#### Fields
- id: system id for this Team
    - integer
    - primary key
- program_id: system id for the associated program
    - integer
    - indexed
- number: number for this team
    - integer
    - indexed
- name: name for this team
    - varchar
- short_name: short name for this team
    - varchar

### Team Attributes
Attributes associated with a FIRST team
(Thinking this should be implemented as an hstore key value combination of team system id with value of all attributes)
#### Fields
- id: system id for this attribute
    - integer
    - primary key
- team_id: system id for associated team
    - integer
    - foreign key
- attribute_type_id: type of this attribute
    - integer
    - foreign key
- value: value for this attribute
    - varchar

### Team Scores
Scores associated with a FIRST team during a match at a competition
#### Fields
- id: system id for this score
    - integer
    - primary key
- team_id: system id for the associated team
    - integer
    - foreign key
- match_id: system id for the associated match
    - integer
    - foreign key
- score_type_id: system id for the associated score type
    - integer
    - foreign key
- value: score value
    - varchar

### Team Sponsors
Sponsors associated with a FIRST team for a given game (season)
#### Fields
- id: system id for this team sponsor
    - integer
    - primary key
- team_id: system id for the associated
    - integer
    - foreign key
- sponsor_id: system id for the associated sponsor
    - integer
    - foreign key
- game_id: system id for the associated game
    - integer
    - foreign key

### Users / Members
TBD

### Volunteers
TBD