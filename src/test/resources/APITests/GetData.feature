Feature: Get List User API

  Scenario Outline: Validation API
    When set "<URL>" list user
    And set "<method>" get response API a list of users
    Then <statusCode> get list user
    Then response get API <page> accessed, data <per_page>, <total> all user, <total_pages> should be "<result>"

    Examples:
      |URL                   |method        |statusCode   |result    |page|per_page|total|total_pages|
      |https://reqres.in/api |/users?page=2 |200          |Success   |2   |6       |12   |2          |
      |https://reqres.in/api |/u            |404          |Failed    |2   |6       |12   |2          |
      |https://reqres.in/api |              |404          |Failed    |2   |6       |12   |2          |
      |https://reqres.in     |/users?page=2 |404          |Failed    |2   |6       |12   |2          |
