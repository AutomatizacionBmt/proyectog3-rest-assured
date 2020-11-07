package com.company.cucumber.stepdefs;

import com.company.config.RedmineEndpoints;
import com.company.entities.Entity;
import com.company.entities.Project;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.Map;
import java.util.Random;


public class RestProjectsStepdefs {

    private RestCommonStepdefs commonSteps;

    public RestProjectsStepdefs(RestCommonStepdefs commonSteps){
        this.commonSteps = commonSteps;
    }

    @When("System sends a request to create project service from redmine")
    public void systemSendsARequestToCreateProjectServiceFromRedmine(Map<String, String> projectData) {

        Integer randomNumber = (new Random()).nextInt(900000) + 100000;

        Project project = new Project();
        project.setName(projectData.get("name") + randomNumber);
        project.setIdentifier(projectData.get("identifier") + randomNumber);
        project.setDescription(projectData.get("description") +randomNumber);
        project.setInherit_members(Boolean.parseBoolean(projectData.get("inherit_members")));
        project.setIs_public(Boolean.parseBoolean(projectData.get("is_public")));

        Entity entity = new Entity(project);


        commonSteps.response = commonSteps.request
                .body(entity)
                .when()
                .post(RedmineEndpoints.REDMINE_PROJECTS_JSON);

    }

    @When("System sends a request to get projects by id service from redmine")
    public void systemSendsARequestToGetProjectsByIdServiceFromRedmine(Map<String, String> issue) {

        commonSteps.response = commonSteps.request.
                pathParam("idProject", issue.get("id")).
                when()
                    .get(RedmineEndpoints.SINGLE_REDMINE_PROJECT_JSON);

    }

    @And("System should responds with response data of project")
    public void systemShouldRespondsWithResponseDataOfProject(Map<String, String> expectedData) {

        JsonPath actualData = new JsonPath(commonSteps.response.getBody().asString());

        Assert.assertEquals("El id no es el correcto", Integer.parseInt(expectedData.get("id")),
                actualData.getInt("project.id"));

        Assert.assertEquals("El nombre no es correcto", expectedData.get("name"),
                actualData.getString("project.name"));
    }

    @When("System sends a request to create project service from redmine table:")
    public void systemSendsARequestToCreateProjectServiceFromRedmineTable(DataTable dataTable) {

        Integer randomNumber = (new Random()).nextInt(900000) + 100000;

        Project project = new Project();
        project.setName(dataTable.row(1).get(0) + randomNumber);
        project.setIdentifier(dataTable.row(1).get(1) + randomNumber);
        project.setDescription(dataTable.row(1).get(2) +randomNumber);
        project.setInherit_members(Boolean.parseBoolean(dataTable.row(1).get(3)));
        project.setIs_public(Boolean.parseBoolean(dataTable.row(1).get(4)));

        Entity entity = new Entity(project);

        commonSteps.response = commonSteps.request
                .body(entity)
                .when()
                .post(RedmineEndpoints.REDMINE_PROJECTS_JSON);

    }
}
