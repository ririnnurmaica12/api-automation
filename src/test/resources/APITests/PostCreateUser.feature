Feature: Post create user

  Scenario Outline: try to create user
    When setting "<URL>" created user
    Then set body create user "<name>" and "<job>" should be "<result>"
    Then <statusCode> create user

    Examples:
      |URL                   |statusCode   |name| job  |result    |
      |https://reqres.in/api |201          |ica |QA    |Success   |
      |https://reqres.in/api |201          |ica |QA    |Success   |
      |https://reqres.in/api |201          |123 |12    |Success   |
      |https://reqres.in/api |201          |    |QA    |Success   |
      |https://reqres.in/api |201          |ica |      |Success   |
      |https://reqres.in/api |201          |    |      |Success   |
      |https://reqres.in/api |201          |!!! |##    |Success   |
      |https://reqres.in     |404          |ica |QA    |Failed    |