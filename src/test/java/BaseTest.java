import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    public RequestSpecification requestSpec;
    public ResponseSpecification responseSpec200;
    public ResponseSpecification responseSpec201;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        requestSpec = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        responseSpec200 = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

        responseSpec201 = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();
        }

}
