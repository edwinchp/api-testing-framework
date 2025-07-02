Feature: Validate 500 status codes

  @Test @Test_009 @WireMock
  Scenario: Validate response returns 500 internal server error
    When I fetch data from an endpoint the response should be 500 internal server error

  @Test @Test_010 @WireMock
  Scenario: Validate response returns 502 bad gateway
    When I fetch data from an endpoint the response should be 502 bad gateway

  @Test @Test_011 @WireMock
  Scenario: Validate response returns 503 service unavailable
    When I fetch data from an endpoint the response should be 503 service unavailable

  @Test @Test_011 @WireMock
  Scenario: Validate response returns 505 forbidden
    When I fetch data from an endpoint the response should be 505 http method not supported

  @Test @Test_012 @Spotify
  Scenario: Validate Spotify response OAuth authentication
    When I successfully authenticate then validate the new album releases have the following properties:
      | name                   |
      | release_date           |
      | release_date_precision |
      | total_tracks           |
      | type                   |
      | uri                    |