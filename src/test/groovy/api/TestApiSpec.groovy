package api


import groovy.json.JsonSlurper
import model.AuthResponse
import model.PostResponse
import spock.lang.Shared
import spock.lang.Specification
import steps.Steps

class TestApiSpec extends Specification {

    @Shared
    def jsonSlurper = new JsonSlurper()

    def "authenticate in postman-echo"() {

        given:
        def responseLogin = Steps.loginPostmanEcho(username, password, header)

        when:
        def authenticated = jsonSlurper.parseText(responseLogin) as AuthResponse

        then:
        assert authenticated.getAuthenticated().equals(ExpectedValue)

        where:
        username   | password   | header                           | ExpectedValue
        "username" | "password" | "Basic cG9zdG1hbjpwYXNzd29yZA==" | true
    }

    def "get post response"() {

        given:
        def responsePost = Steps.postToPostmanEcho(text)

        when:
        def postObject = jsonSlurper.parseText(responsePost) as PostResponse

        then:
        assert postObject.getData().equals(expectedValue)

        where:
        text               | expectedValue
        "Hello from Spock" | "Hello from Spock"
    }
}
