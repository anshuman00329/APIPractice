import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class PracticeAPI {

    int id;

    @Test(priority = 1)
    void getUsers(){
        given()
        .when()
                .get("https://reqres.in/api/users?page=2")
        .then()
                .statusCode(200)
                .body("page",equalTo(2))
                .log().all();
    }

    @Test(priority = 2)
    void createUser(){

        HashMap data = new HashMap();
        data.put("Name","Noob");
        data.put("Job","Trainer");

        id = given()
                .contentType("application/json")
                .body(data)
            .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
//            .then()
//                .statusCode(201)
//                .log().all();
    }

    @Test(priority = 3)
    void updateUser(){
        HashMap data = new HashMap();
        data.put("Name","Noobio");
        data.put("Job","Trainer");

        given()
                .contentType("application/json")
                .body(data)
             .when()
                .put("https://reqres.in/api/users/"+id)
             .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 4)
    void deleteUser()   {

        given()
                .contentType("application/json")
                .when()
                .delete("https://reqres.in/api/users/"+id)
                .then()
                .statusCode(204)
                .log().all();
    }

}
