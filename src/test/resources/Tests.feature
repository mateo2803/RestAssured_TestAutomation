Feature: Employee Endpoint
  Background: Testing Dummy API example Web Page

  @getAll
  Scenario: /employee should return all the employees
    Given I perform a GET request to the employee endpoint
    Then I verify that it returns status code 200
    And I verify that the body does not have size 0

  @getEmployee
  Scenario: /employee/{id} should return an employee
    Given an id I GET an employee: "23"
    Then I verify that it returns status code 200
    And I verify that I obtain the employee 23

  @createEmployee
  Scenario: /create should create a new Employee
    Given the data, I should perform a POST:
      | Cangrejo | 3500 | 20 |
    Then I verify that it returns status code 200
    And I verify that the body does not have size 0
    Then I verify the body response with the same data
      | Cangrejo | 3500 | 20 |

  @updateEmployee
  Scenario: /put should update an Employee`s information
    Given and id "21" I should update the Employee`s inf with the following data
      | Cangrejo | 71391 | 20 |
    Then I verify that it returns status code 200
    Then I verify the body response with the same data
      | Cangrejo | 71391 | 20 |

  @deleteEmployee
  Scenario: /delete should delete an Employee`s information
    Given an id "21", I should delete the Employee`1s information
    Then I verify that it returns status code 200
    And I verify that the employee was deleted by their id: "21"
