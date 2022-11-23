//file:noinspection GroovyAssignabilityCheck
package steps

import static io.restassured.RestAssured.given

class Steps {
    static def BASE_URI = "https://postman-echo.com"

    static def loginPostmanEcho(username, password, authorizationHeader) {
        return given()
                .urlEncodingEnabled(false)
                .baseUri(BASE_URI)
                .basePath("/basic-auth")
                .header("Authorization", authorizationHeader)
                .queryParam("Username", username)
                .queryParam("Password", password)
                .get()
                .then()
                .statusCode(200)
                .extract().body().jsonPath().prettify()
    }

    static def postToPostmanEcho(text) {
        return given()
                .urlEncodingEnabled(false)
                .baseUri(BASE_URI)
                .basePath("/post")
                .body(text)
                .post()
                .then()
                .statusCode(200)
                .extract().body().jsonPath().prettify()
    }

}
