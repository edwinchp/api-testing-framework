Feature: Validate 200 status codes

  @Test @Test_001 @GoRest
  Scenario: Validate response returns 200 ok
    When I fetch a valid user id the response should be 200 ok

  @Test @Test_002 @GoRest
  Scenario: Validate response returns 201 created
    When I create a new user the response should be 201 created

  @Test @Test_003 @MockServer
  Scenario: Validate response returns 202 accepted
    When I create a new user the response should be 202 accepted

  @Test @Test_004 @GoRest @Post @Auth
  Scenario: Validate response returns 204 no content
    When I create a new user the response should be 204 no content