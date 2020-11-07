Feature:  REST - Redmine Rest Testing - Create project
  As a user..
  I want to ...


  @ApiRest
  Scenario: Create an project - JSON
    Given System is ready to send request
    When System sends a request to create project service from redmine
      | name            | RedmineProject                  |
      | identifier      | redmineproject                  |
      | description     | Redmine Project for api testing |
      | inherit_members | false                           |
      | is_public       | true                            |
    Then The response status should be 201


  @ApiRest
  Scenario Outline: Create an project JSON from example
    Given System is ready to send request
    When System sends a request to create project service from redmine table:
      | name   | identifier   | description   | inherit_members   | is_public   |
      | <name> | <identifier> | <description> | <inherit_members> | <is_public> |
    Then The response status should be 201

    Examples: Happy Paths
      | name            | identifier      | description           | inherit_members | is_public |
      | RedmineProject1 | redmineproject1 | Redmine Project test1 | false           | true      |
      | RedmineProject2 | redmineproject2 | Redmine Project test2 | false           | false     |
      | RedmineProject3 | redmineproject2 | Redmine Project test3 | false           | true      |


  @ApiRest
  Scenario: Get project by id - JSON
    Given System is ready to send request
    When System sends a request to get projects by id service from redmine
      | id | 285 |
    Then The response status should be 200
    And The schema is "project_schema.json"
    And System should responds with response data of project
      | id   | 285                               |
      | name | Redmine Project Sw Testing Dragon |
