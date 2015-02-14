# FIRST Dash REST Structure

## Top Level Entities
*Alliance*<br />
*Award*<br />
*Event*<br />
*Game*<br />
*Program*<br />
*Sponsors* <br />
*Team*<br />
*User / Member*<br />

### Alliance Hierarchy
* Alliances collection
* Alliance entity
  * Teams collection
  * Team entity

### Awards Hierarchy
* Awards collection
  * League collection
  * Game collection
    * Event collection
* Awards Entity
  * Person Entity

### Event Hierarchy
* Events collection
  * Game collection
* Event entity
  * Competition collection
    * Matches collection
    * Volunteers collection
    * Locations collection

### Game / Season Hierarchy
* Games collection (Years)
* Game entity
  * Events collection
  * Event entity
    * Matches collection
    * Match
  * Awards collection
    * Event collection

### Program Hierarchy
* Programs collection
* Program entity
  * Games collection
  * Events collection
  * Awards collection

### Sponsors Hierarchy
* Sponsors collection
* Sponsor entity
  * Teams collection
  * Events collection

### Team Hierarchy
* Team collection
* Team entity
  * Awards collection
    * Game collection (Awards for given game)
      * Event Collection
    * Event Collection
  * Events collection
  * Event entity
    * Matches collection
    * Match entity
  * Members collection
    * Member entity
    
### User Hierarchy
* TBD