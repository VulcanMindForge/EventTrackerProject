# EventTrackerProject

# Hi 👋, I'm Jacob Stuart
### Programming student with Skill Distillery

- I’m currently working on [TabletopTasks REST](https://github.com/VulcanMindForge/EventTrackerProject)

- I’m currently learning **Full-Stack Developement**

- All of my projects are available at [https://github.com/VulcanMindForge](https://github.com/VulcanMindForge)

# Overview
This application is set up to track characters, players, and game systems for a tabletop roleplaying games. It can also be used to track and schedule game sessions to avoid conflicts.

# How to use this project
Access the site and respond to the prompts to search the database.

# Technologies Used
- Java 
- MySQL
- Spring
- JPA
- Hibernate
- Rest API

# Current Progress
- Created basic project
- Implemented JPA project
- Created SQL database with 5 tables(player, game, meeting, adventurer, and meeting_player)
- Implemented Entities and verified mapping from SQL.
- Implemented relationships(OneToOne, ManyToMany, etc)
- Implemented Spring Boot project and finished set up
- Implemented Services, Controllers, and Repositories
- Implemented CRUD operations to be access through REST API
- Tested via postman and verified all Rest Endpoint working

# Next Steps
- Add search query functionality

# Lessons to research
- HTTP status codes

# Rest Table
| Http Verb | URI                                             | Request Body                      | Response Body                            | Status Codes |
|-----------|-------------------------------------------------|-----------------------------------|------------------------------------------|--------------|
| Get       | 'players/{playerId}/adventurers'                |                                   | List of all Adventurers by Player Id     | 200          |
| Post      | 'players/{playerId}/adventurers'                | JSON of new Adventurer to add     | JSON of created Adventurer               | 201,400      |
| Put       | 'players/{playerId}/adventurers'                | JSON of new version of Adventurer | JSON of latest version of Adventurer     | 200,404,400  |
| Delete    | 'players/{playerId}/adventurers/{adventurerId}' |                                   |                                          | 204,404,400  |
| Get       | 'games'                                         |                                   | List of all Game Systems                 | 200          |
| Get       | 'games/{id}'                                    |                                   | JSON of specific Game System based on id | 200,404      |
| Get       | 'meetings'                                      |                                   | List of all Meetings                     | 200          |
| Get       | 'meetings/{id}'                                 |                                   | JSON of specific Meeting based on id     | 200,404      |
| Post      | 'meetings'                                      | JSON of new meeting to add        | JSON of added Meeting                    | 201,400      |
| Put       | 'meetings/{meetingId}'                          | JSON of new version of Meeting    | JSON of latest version of Meeting        | 200,404,400  |
| Delete    | 'meetings/{meetingId}'                          |                                   |                                          | 204,404,400  |
| Get       | 'players'                                       |                                   | List of all Players                      | 200          |
| Get       | 'players{id}'                                   |                                   | JSON of specific Player by id            | 200,404      |
| Post      | 'players'                                       | JSON of new Player to add         | JSON of added player                     | 201,400      |
| Put       | 'players/{playerId}                             | JSON of new version of Player     | JSON of latest version of player         | 200,404,400  |
| DELETE    | 'players/{playerId}                             |                                   |                                          | 204,404,400  |
