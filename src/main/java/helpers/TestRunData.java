package helpers;

import helpers.utils.CustomUtilities;
import org.junit.jupiter.params.provider.Arguments;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Класс {@code TestRunData} содержит тестовые данные
 */
public class TestRunData {

    public static Stream<Arguments> firstApiTest() {

        int firstExpectedCode = 200;
        String firstEndPoint = "/v2/oauth2/token";
        Map<String, String> tokenRequestDataMap = new HashMap<String, String>() {
            {
                put("grant_type", "client_credentials");
                put("guest", "default");
            }
        };
        String httpUserName = "front_2d6b0a8391742f5d789d7d915755e09e";
        String authTokenPassword = "c29tZWNsaWVudDphbmRpdHNzZWNyZXQ=";

        String userName = CustomUtilities.randomizer(14);
        Map<String, String> userDataMap = new HashMap<String, String>() {
            {
                put("username", userName);
                put("password_change", "amFuZWRvZTEyMw==");
                put("password_repeat", "amFuZWRvZTEyMw==");
                put("email", userName + "@example.com");
                put("name", "Adam");
                put("surname", "Jensen");
                put("Currency_code", "EUR");
            }
        };
        String endPointForCreationTask = "/v2/players";
        int creationTaskStatusCode = 201;

        Map<String, String> logInUserDataMap = new HashMap<String, String>() {
            {
                put("grant_type", "password");
                put("username", userName);
                put("password", userDataMap.get("password_change"));
            }
        };
        int fifthExpectedCode = 404;
        return Stream.of(
                Arguments.of(firstExpectedCode, firstEndPoint, tokenRequestDataMap, httpUserName, authTokenPassword,
                        userDataMap, endPointForCreationTask, creationTaskStatusCode, logInUserDataMap, fifthExpectedCode)
        );
    }
}
