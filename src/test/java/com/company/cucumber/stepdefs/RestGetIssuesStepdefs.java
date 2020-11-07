package com.company.cucumber.stepdefs;

import com.company.config.RedmineEndpoints;
import com.company.entities.Entity;
import com.company.entities.Project;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class RestGetIssuesStepdefs {

    private RestCommonStepdefs commonSteps;

    public RestGetIssuesStepdefs(RestCommonStepdefs commonSteps){
        this.commonSteps = commonSteps;
    }


    @When("System sends a request to list issues service from redmine json")
    public void systemSendsARequestToListIssuesServiceFromRedmineJson() {
        commonSteps.response = commonSteps.request.when()
                    .get(RedmineEndpoints.REDMINE_ISSUES_JSON);
    }

    @When("System send a request to get issues service by id from redmine")
    public void systemSendARequestToGetIssuesServiceByIdFromRedmine(Map<String, String> issue) {

        commonSteps.response = commonSteps.request.
                pathParam("idIssue", issue.get("id")).
        when()
                .get(RedmineEndpoints.SINGLE_REDMINE_ISSUE_JSON);

    }

    @And("System should responds with response data")
    public void systemShouldRespondsWithResponseData(Map<String, String> expectedData) {

        JsonPath actualData = new JsonPath(commonSteps.response.getBody().asString());

        Assert.assertEquals("El id no es el correcto",Integer.parseInt(expectedData.get("id")),actualData.getInt("issue.id"));
        Assert.assertEquals("El subject no es correcto",expectedData.get("subject"),actualData.getString("issue.subject"));
        Assert.assertEquals("La descripcion no es correcta",expectedData.get("description"),actualData.getString("issue.description"));
        Assert.assertEquals("La fecha no es correcta",expectedData.get("start_date"), actualData.getString("issue.start_date"));

    }

}