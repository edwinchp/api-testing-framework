Feature: Get User

  @Test
  Scenario: Valid user ID returns 200
    When I fetch user with id 1
    Then the response status should be 200