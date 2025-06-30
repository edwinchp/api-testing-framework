Feature: Validate 400 status codes

  @Test @Test_005 @GoRest
  Scenario: Validate response returns 400 bad request
    When I send an incorrect request the response should be 400 bad request

  @Test @Test_006 @Spotify
    Scenario: Validate response returns 401 unauthorized
      When I send an incorrect Bearer token the response should return 401 unauthorized

  @Test @Test_007 @Spotify
  Scenario: Validate response returns 403 forbidden
    When I send request the response should return 403 forbidden

  @Test @Test_008 @Spotify
  Scenario: Validate response returns 404 not found
    When I send request the response should return 404 not found

  @Test @Test_009 @Spotify
  Scenario: Validate Spotify response OAuth authentication
    When I successfully authenticate then validate the new album releases have the following properties:
      | name                   |
      | release_date           |
      | release_date_precision |
      | total_tracks           |
      | type                   |
      | uri                    |