package com.company.cucumber.stepdefs;

import com.company.config.RedmineEndpoints;
import com.company.entities.Entity;
import com.company.entities.Project;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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

}
