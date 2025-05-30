Feature: Validate 400 status codes

  @Test @Test_005
  Scenario: Validate response returns 400 bad request
    When I send an incorrect request the response should be 400 bad request

  @Test @Test_006
  Scenario: Validate response OAuth authentication
    When I enter Google credentials the Spotify API allows login access