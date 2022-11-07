package integrations.api;

import dataclasses.PlayerInfo;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Класс {@code ApiRequests} содержит методы по отправке Rest запросов
 */
public class ApiRequests {

    /**
     * Метод {@code getGuestToken} получает гостевой токен
     *
     * @param endPoint          endPoint запроса
     * @param bodyRequest       тело запроса
     * @param httpUserName      userName аутентификации
     * @param authTokenPassword пароль
     * @param expectedCode      ожидаемый статус-код
     * @return полученный токен
     * @author A.Shilov
     */
    public String getGuestToken(String endPoint, Map<String, String> bodyRequest, String httpUserName,
                                String authTokenPassword, int expectedCode) {
        Specification.installSpec(Specification.requestSpec());
        Response response = given()
                .auth().preemptive().basic(httpUserName, authTokenPassword)
                .body(bodyRequest)
                .when()
                .post(endPoint)
                .then()
                .log().all()
                .statusCode(expectedCode)
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getString("access_token");
    }

    /**
     * Метод {@code createPlayer} создает нового игрока и возвращает его объект с заполненными данными, после того,
     * как распарсит ответ от сервера
     *
     * @param endPoint     endPoint запроса
     * @param userDataMap  данные игрока
     * @param token        токен
     * @param expectedCode ожидаемый статус-код
     * @return объект PlayerInfo
     * @author A.Shilov
     */
    public PlayerInfo createPlayer(String endPoint, Map<String, String> userDataMap, String token, int expectedCode) {
        Specification.installSpec(Specification.requestSpec());
        return given()
                .auth().oauth2(token)
                .body(userDataMap)
                .when()
                .post(endPoint)
                .then()
                .log().all()
                .statusCode(expectedCode)
                .extract().as(PlayerInfo.class);
    }

    /**
     * Метод {@code logIn} авторизуется под игроком, чьи данные переданы в метод
     *
     * @param endPoint          endPoint запроса
     * @param userDataMap       данные для регистрации
     * @param httpUserName      userName аутентификации
     * @param authTokenPassword пароль
     * @param expectedCode      ожидаемый статус-код
     * @author A.Shilov
     */
    public String logIn(String endPoint, Map<String, String> userDataMap, String httpUserName,
                        String authTokenPassword, int expectedCode) {
        Specification.installSpec(Specification.requestSpec());
        Response response = given()
                .auth().preemptive().basic(httpUserName, authTokenPassword)
                .body(userDataMap)
                .when()
                .post(endPoint)
                .then()
                .log().all()
                .statusCode(expectedCode)
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getString("access_token");
    }

    /**
     * Метод {@code getPlayerProfileData} запрашивает информацию об игрока по id, парсит ответ и возвращает объект класс PlayerInfo
     *
     * @param endPoint     endPoint запроса
     * @param token        токен
     * @param playerId     id запрашиваемого игрока
     * @param expectedCode ожидаемый статус-код
     * @return объект профиля игрока
     * @author A.Shilov
     */
    public PlayerInfo getPlayerProfileData(String endPoint, String token, int playerId, int expectedCode) {
        Specification.installSpec(Specification.requestSpec());
        PlayerInfo playerInfoFromServer = given()
                .auth().oauth2(token)
                .when()
                .get(endPoint + "/" + playerId)
                .then()
                .log().all()
                .statusCode(expectedCode)
                .extract().as(PlayerInfo.class);
        return playerInfoFromServer;
    }
}
