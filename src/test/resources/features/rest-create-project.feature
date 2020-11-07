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
