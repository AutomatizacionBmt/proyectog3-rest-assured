package com.company.features;

import com.company.config.RedmineConfig;
import com.company.config.RedmineEndpoints;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

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

    @Test
    public void getFirstIssueSubject(){



                given().
                when()
                        .get(RedmineEndpoints.REDMINE_ISSUES_JSON).
                then()
                        .statusCode(200)
                        .body("issues[0].subject",equalTo("Issue creado por JH desde Postman"));


    }

    @Test
    public void getAllIssueData(){

        Response response =
                            given().
                            when()
                                    .get("issues/2233.json").
                            then()
                                    .statusCode(200)
                                    .extract().response();

        String jsonResponseAsString = response.asString();

        System.out.println("El response como string es: " +jsonResponseAsString);

    }

    @Test
    public void extractHeaders(){

        Response response =
                            given().
                            when()
                                    .get("issues/2233.json").
                            then()
                                    .statusCode(200)
                                    .extract().response();


        Headers headers = response.getHeaders();

        String contentType =response.getHeader("Content-Type");

        System.out.println("El Header Content-Type es: " + contentType);

    }

    @Test
    public void extractAllIssueSubjects(){

        Response response =
                            given().
                            when()
                                    .get(RedmineEndpoints.REDMINE_ISSUES_JSON).
                            then()
                                    .extract().response();


        List<String> subjects = response.path("issues.subject");

        for (String subject : subjects){
            System.out.println(subject);
        }
    }
}
