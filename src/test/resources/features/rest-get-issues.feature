Feature: REST - Redmine Rest Testing  - Get Issues
  As a user...
  I want to...

  @ApiRest
  Scenario: Get issues by list - JSON
    Given System is ready to send request
    When System sends a request to list issues service from redmine json
    Then The response status should be 200