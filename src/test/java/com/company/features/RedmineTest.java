package com.company.features;

import com.company.config.RedmineConfig;
import com.company.config.RedmineEndpoints;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RedmineTest extends RedmineConfig {


    @Test
    public void createNewIssueByJSON(){

        String issueBody = "{\n" +
                "  \"issue\": {\n" +
                "    \"project_id\": 1,\n" +
                "    \"subject\": \"Issue JH2 creado desde RestAssured G3\",\n" +
                "    \"priority_id\": 4\n" +
                "  }\n" +
                "}";

        given()
                .body(issueBody).
        when()
                .post(RedmineEndpoints.REDMINE_ISSUES_JSON).
        then()
                .statusCode(201);
    }

    @Test
    public void getSingleIssueJSON(){

        given()
                .pathParam("idIssue",1020).
        when()
                .get(RedmineEndpoints.SINGLE_REDMINE_ISSUE_JSON).
        then()
                .statusCode(200);
    }

    @Test
    public void updateIssueJSON(){

        String issueBody = "{\n" +
                "  \"issue\": {\n" +
                "    \"subject\": \"Issue Modificado por José Humberto\",\n" +
                "    \"priority_id\" : 5,\n" +
                "    \"notes\": \"The subject was changed\" \n" +
                "  }\n" +
                "}";

        given()
                .body(issueBody)
                .pathParam("idIssue", 1020).
        when().
                put(RedmineEndpoints.SINGLE_REDMINE_ISSUE_JSON).
        then()
                .statusCode(204);

    }

    @Test
    public void deleteIssue(){

        given().
        when()
                .delete("issues/1022.json").
        then()
                .statusCode(204);
    }

    @Test
    public void getSingleIssueXML(){

        given()
                .contentType("application/xml")
                .pathParam("idIssue", 1019).
        when()
                .get(RedmineEndpoints.SINGLE_REDMINE_ISSUE_XML).
        then()
                .statusCode(200);

    }



}
