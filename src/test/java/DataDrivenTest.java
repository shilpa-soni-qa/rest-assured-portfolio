import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DataDrivenTest extends BaseTest{
    @DataProvider(name = "postData")
    public Object[][] getPostData() throws IOException{
        return ExcelReader.getTestData(
                System.getProperty("user.dir") + "/src/test/resources/test-data.xlsx"
        );
    }
    @Test(dataProvider = "postData")
    public void testCreatePostWithExcelData(int userId,String title,String body){
        String requestBody = "{\n" +
                "  \"userId\": " + userId + ",\n" +
                "  \"title\": \"" + title + "\",\n" +
                "  \"body\": \"" + body + "\"\n" +
                "}";
        System.out.println("Testing with title:"+ title);

        given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo(title))
                .body("userId", equalTo(userId));


    }
}
