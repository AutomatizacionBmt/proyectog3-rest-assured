package com.company.features;

import com.company.config.RedmineConfig;
import com.company.config.RedmineEndpoints;
import com.company.entities.Entity;
import com.company.entities.Project;
import org.junit.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;

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
}
