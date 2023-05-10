Feature: List encounter work items

  Scenario: Default call works
    Given a new request
    When the request is sent
    Then the response should be successful
