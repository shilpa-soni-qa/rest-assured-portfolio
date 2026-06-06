import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostApiTest extends BaseTest{



    @Test
    public void testGetAllPosts() {
        given()
                .when()
                .get("/posts")
                .then()
                .spec(responseSpec200)
                .body("size()", equalTo(100));
    }

    @Test
    public void testGetSinglePost() {
        given()
                .when()
                .get("/posts/1")
                .then()
                .spec(responseSpec200)
                .body("id", equalTo(1))
                .body("title", notNullValue());
    }

    @Test
    public void testCreatePost() {
        String requestBody = "{\n" +
                "  \"title\": \"REST Assured Portfolio\",\n" +
                "  \"body\": \"Created by Shilpa Soni\",\n" +
                "  \"userId\": 1\n" +
                "}";

        given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .spec(responseSpec201)
                .body("title", equalTo("REST Assured Portfolio"))
                .body("id", notNullValue());
    }

    @Test
    public void testUpdatePost() {
        String requestBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"Updated by Shilpa Soni\",\n" +
                "  \"body\": \"Updated post\",\n" +
                "  \"userId\": 1\n" +
                "}";

        given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .put("/posts/1")
                .then()
                .spec(responseSpec200)
                .body("title", equalTo("Updated by Shilpa Soni"));
    }

    @Test
    public void testDeletePost() {
        given()
                .when()
                .delete("/posts/1")
                .then()
                .spec(responseSpec200);
    }
}