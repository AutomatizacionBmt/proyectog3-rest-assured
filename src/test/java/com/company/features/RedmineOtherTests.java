package com.company.features;

import com.company.config.RedmineConfig;
import com.company.config.RedmineEndpoints;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class RedmineOtherTests extends RedmineConfig {

    @Test
    public void getIssueStartDate(){
    //"start_date": "2020-10-17",

        given().
                pathParam("idIssue", 992).
        when()
                .get(RedmineEndpoints.SINGLE_REDMINE_ISSUE_JSON).
        then()
                .statusCode(200)
                .body("issue.start_date",equalTo("2020-10-17"))
                .body("issue.subject", equalTo("Issue Modificado por José Humberto"));
    }



}
