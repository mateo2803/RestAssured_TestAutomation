Feature: Employees endpoint
  Background: Final project

  @getAll
  Scenario: /employees should return all the employees
    Given I perform a GET to the employees endpoint
    Then I verify status code 200 is returned
    And I verify that the body does not have size 0


  @getEmployee
  Scenario: /employee/{id} should return an employee
    Given an id I GET an employee: "23"
    Then I verify status code 200 is returned
    And I verify that I obtain the employee 23


  @createEmployee
  Scenario: /create should create a new Employee
    Given the data, I should perform a POST:
      | Santiago | 5300 | 26 |
    Then I verify status code 200 is returned
    And I verify that the body does not have size 0
    Then I verify the body response with the same data
      | Santiago | 5300 | 26 |


  @updateEmployee
  Scenario: /put should update an Employee`s information
    Given and id "21" I should update the Employee`s inf with the following data
     | Lucas | 123456 | 20 |
    Then I verify status code 200 is returned
    Then I verify the body response with the same data
      | Lucas | 123456 | 20 |

    @deleteEmployee
    Scenario: /delete should delete an Employee`s information
      Given an id "21", I should delete the Employee`1s information 
      Then I verify status code 200 is returned
      And I verify that the employee was deleted by their id: "21"
