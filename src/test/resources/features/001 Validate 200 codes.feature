Feature: Get User

  @Test
  Scenario: Valid user ID returns 200
    When I fetch user with id "7895353" the response should be 200