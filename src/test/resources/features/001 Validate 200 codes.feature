Feature: Validate 200 status codes

  @Test
  Scenario: Validate response returns 200 ok
    When I fetch a valid user id the response should be 200 ok

  @Test @Post @Auth
  Scenario: Validate response returns 201 created
    When I create a new user the response should be 201 created

  @Test @Post @Auth
  Scenario: Validate response returns 202 accepted
    When I create a new user the response should be 202 accepted