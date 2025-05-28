Feature: Validate 400 status codes

  @Test @Test_005
  Scenario: Validate response returns 400 bad request
    When I send an incorrect request the response should be 400 bad request