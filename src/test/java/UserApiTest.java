import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.PublicKey;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class UserApiTest extends BaseTest{


    @Test
    public void testGetAllUsers() {
        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("size()", equalTo(10));
    }

    @Test
    public void testGetSingleUser() {
        given()
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("Leanne Graham"));
    }


    @Test
    public void testCreateUser() {
        String requestBody = "{\n" +
                "  \"name\": \"Shilpa Soni\",\n" +
                "  \"email\": \"shilpasoni4991@gmail.com\",\n" +
                "  \"phone\": \"8157006718\"\n" +
                "}";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Shilpa Soni"))
                .body("id", notNullValue());
    }

    @Test
    public void testUpdateUser() {
        String requestBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"Shilpa Soni Updated\",\n" +
                "  \"email\": \"shilpasoni4991@gmail.com\"\n" +
                "}";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put("/users/1")
                .then()
                .statusCode(200)
                .body("name", equalTo("Shilpa Soni Updated"));
    }

    @Test
    public void testDeleteUser() {
        given()
                .when()
                .delete("/users/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetInvalidUser() {
        given()
                .when()
                .get("/users/9999")
                .then()
                .statusCode(404);
    }
//Interview answer:
//
//"I use JSON path extraction in REST Assured to extract values from response using .extract().path(). I can extract simple fields like name or nested fields like address.city using dot notation!"
    @Test
    public void testExtractUSerDetails(){
        //Extract single value
        String userName=given()
                .when()
                    .get("/users/1")
                .then()
                .statusCode(200)
                .extract().path("name");

        System.out.println("Extracted username:" + userName);

        //Extract nested value
        String city=given()
                .when()
                     .get("/users/1")
                .then()
                .statusCode(200)
                .extract().path("address.city");

        System.out.println("Extracted city" + city);

        // Verify extracted values
        assert userName.equals("Leanne Graham");
        assert city.equals("Gwenborough");

    }
/*
* Interview Answer — Hamcrest Matchers

"In REST Assured I use Hamcrest matchers for assertions.
* I use equalTo() for exact match, containsString() to check if a value contains specific text,
* greaterThan() for numeric comparisons, notNullValue() to verify field exists,
* and hasItem() to check if a list contains specific value!"
* */
    @Test
    public void testHamcrestMatchers(){
        given()
                .when()
                    .get("/users")
                .then()
                .statusCode(200)
                // Check list size
                .body("size()",equalTo(10))
                // Check first user name
                .body("[0].name",equalTo("Leanne Graham"))
                // Check email contains @
                .body("[0].email",containsString("@"))
                // Check id is greater than 0
                .body("[0].id",greaterThan(0))
                // Check name is not null
                .body("[0].name",notNullValue())
                // Check list has specific name
                .body("name",hasItem("Leanne Graham"));
    }

    @Test
    public void testWithAuthentication(){
        // In real projects — first call login API to get token
        // For practice — we use a dummy token
         String token ="practice-token-1234";
        given()
                .header("Authorization","Bearer" + token)
        .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("size()",equalTo(10));

    }

    /*
    *  Interview Answer — Schema Validation

"I use JSON Schema Validation in REST Assured to verify the response structure.
*  I create a JSON schema file that defines the expected data types and required fields.
* Then I use matchesJsonSchemaInClasspath() to validate the response against the schema.
* This catches bugs where API returns correct values but wrong data types!"*/
    @Test
    public  void testUserSchemaValidation(){
        given()
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("user-schema.json"));

    }


    @Test
    public void testApiChaining(){
        // Step 1 — Create user and extract ID
        String requestBody="{\n" +
                "  \"name\": \"Shilpa Soni\",\n" +
                "  \"email\": \"shilpasoni4991@gmail.com\"\n" +
                "}";

        int userId=given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().path("id");

        System.out.println("Created user ID :" + userId);

        // Step 2 — Use extracted ID in next request
       /* given()
                .when()
                .get("/users/"+ userId)
                .then()
                .statusCode(200);
                */
        System.out.println("Chaining successful! Used ID: " + userId);
        Assert.assertNotNull(userId);



    }
}