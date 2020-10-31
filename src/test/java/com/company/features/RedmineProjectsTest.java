package com.company.features;

import com.company.config.RedmineConfig;
import com.company.config.RedmineEndpoints;
import com.company.entities.Entity;
import com.company.entities.Project;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class RedmineProjectsTest extends RedmineConfig {

    @Test
    public void testProjectSerialization() {
        //Serialización
        //Cuando un Objeto JAVA ===> Objecto JSON

        Integer randomNumber = (new Random()).nextInt(900000) + 100000;

        Project project = new Project();
        project.setName("RedmineProject" + randomNumber);
        project.setIdentifier("redminepuwafgo9" + randomNumber);
        project.setDescription("Esta es una descripción" +randomNumber);
        project.setInherit_members(false);
        project.setIs_public(true);


        Entity entity = new Entity(project);

        given()
                .body(entity).
        when()
                .post(RedmineEndpoints.REDMINE_PROJECTS_JSON).
        then()
                .statusCode(201);
    }

    @Test
    public void testProjectDeserialization(){

        //Deserialización
        //Cuando un Objeto JSON ===> Objecto Java

        Response response =
                            given()
                                    .pathParam("idProject", 285).
                            when()
                                    .get(RedmineEndpoints.SINGLE_REDMINE_PROJECT_JSON);

        response.then().statusCode(200);

        Entity entity = response.getBody().as(Entity.class);
        Project project = entity.getProject();

        System.out.println(project.toString());
    }

    @Test
    public void testProjectValidateSchemaJSON(){

        given()
                .pathParam("idProject", 285).
        when()
                .get(RedmineEndpoints.SINGLE_REDMINE_PROJECT_JSON).
        then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("project_schema.json"));
    }
}
