package com.company.features;

import com.company.config.RedmineConfig;
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
                .post("issues.json").
        then()
                .statusCode(201);
    }

}
