package stepDefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.EmployeeEndpoints;
import entities.Employee;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import utils.Request;

import javax.xml.crypto.Data;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;

public class EmployeesSteps {
    Response response;

    @Given("I perform a GET to the employees endpoint")
    public void getEmployees(){
        response = Request.get(EmployeeEndpoints.GET_EMPLOYEES);
    }

    @And("I verify status code {int} is returned")
    public void verifyStatusCode(int statusCode){
        response.then().assertThat().statusCode(statusCode);
    }

    @And("I verify that the body does not have size {int}")
    public void verifyResponseSize(int size){
        response.then().assertThat().body("size()", not(size));
    }

    @Given("an id I GET an employee: {string}")
    public void getEmployee(String id){
        response = Request.getWithId(EmployeeEndpoints.GET_EMPLOYEE,id);
    }

    @And("I verify that I obtain the employee {int}")
    public void verifyEmployee(Integer id){
        response.then().assertThat().body("data.id", Matchers.equalTo(id));
    }

    @Given("the data, I should perform a POST:")
    public void createEmployee(DataTable employeeInfo) throws JsonProcessingException{
        List<String> data = employeeInfo.transpose().asList(String.class);

        Employee employee = new Employee();
        employee.setName((String)data.get(0));
        employee.setSalary((String)data.get(1));
        employee.setAge((String)data.get(2));

        ObjectMapper mapper = new ObjectMapper();

        String payload = mapper.writeValueAsString(employee);
        this.response = Request.post(EmployeeEndpoints.POST_EMPLOYEE, payload);
    }

    @Then("I verify the body response with the same data")
    public void verifyEmployeeResponseData(DataTable employeeInfo){
        List<String> data = employeeInfo.transpose().asList(String.class);

        response.then().assertThat().body("data.name", Matchers.equalTo(data.get(0)));
        response.then().assertThat().body("data.salary", Matchers.equalTo(data.get(1)));
        response.then().assertThat().body("data.age", Matchers.equalTo(data.get(2)));
    }

    @Given("and id {string} I should update the Employee`s inf with the following data")
    public void updateEmployee(String id, DataTable employeeInfo) throws JsonProcessingException{
        List<String> data = employeeInfo.transpose().asList(String.class);

        Employee employee = new Employee();
        employee.setName((String)data.get(0));
        employee.setSalary((String)data.get(1));
        employee.setAge((String)data.get(2));

        ObjectMapper mapper = new ObjectMapper();

        String payload = mapper.writeValueAsString(employee);

        this.response = Request.put(EmployeeEndpoints.PUT_EMPLOYEE,id,payload);
    }

    @Given("an id {string}, I should delete the Employee`1s information")
    public void deleteEmployee(String id){
        this.response = Request.delete(EmployeeEndpoints.DELETE_EMPLOYEE, id);
    }

    @And("I verify that the employee was deleted by their id: {string}")
    public void verifyDelete(String id){
        response.then().assertThat().body("message", Matchers.equalTo("Successfully! Record has been deleted"));
        response.then().assertThat().body("data", Matchers.equalTo(id));
    }

}
