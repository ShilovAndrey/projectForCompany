package tests;

import dataclasses.PlayerInfo;
import integrations.api.ApiRequests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


/**
 * Тесты для тестирования API
 */
public class ApiTests{

    protected static final Logger logger = LoggerFactory.getLogger(ApiTests.class);

    @DisplayName("5 задач по API")
    @ParameterizedTest(name = "{displayName} {arguments}]")
    @MethodSource("helpers.TestRunData#firstApiTest")
    void firstApiTest(int firstExpectedCode, String firstEndPoint, Map<String, String> tokenRequestDataMap,
                      String httpUserName, String authTokenPassword, Map<String, String> userDataMap, String endPointForCreationTask,
                      int creationTaskStatusCode, Map<String, String> logInUserDataMap, int fifthExpectedCode) {
        logger.info("Задача 1. Получить токен гостя и проверить, что statusCode равен '{}'", firstExpectedCode);
        ApiRequests apiRequests = new ApiRequests();
        String token = apiRequests.getGuestToken(firstEndPoint, tokenRequestDataMap, httpUserName, authTokenPassword,
                firstExpectedCode);
        logger.info("Проверить, что полученная переменная {} не пустая", token);
        Assertions.assertNotNull(token, "Ошибка! Токен не получен, значение токена = '" + token + "'");
        logger.info("Задача 1 выполнена. Получен токен '{}'", token);

        logger.info("Задача 2. Зарегистрировать игрока и проверить, что statusCode равен '{}'", creationTaskStatusCode);
        PlayerInfo playerInfo = apiRequests.createPlayer(endPointForCreationTask, userDataMap, token, creationTaskStatusCode);
        logger.info("Проверим, что сервер вернул ожидаемый username");
        Assertions.assertEquals(userDataMap.get("username"), playerInfo.getUsername(), "Ошибка! переданное значение 'username' '" +
                userDataMap.get("username") + "' не эквивалентно возвращенному с сервера '" + playerInfo.getUsername() + "'");
        logger.info("Задача 2 выполнена, игрок создан. Его username '{}' соответствует отправленному", playerInfo.getUsername());

        logger.info("Задача 3. Авторизоваться под созданным игроком, проверить, что ответ содержит токен");
        token = apiRequests.logIn(firstEndPoint, logInUserDataMap, httpUserName, authTokenPassword, firstExpectedCode);
        Assertions.assertNotNull(token, "Ошибка! Токен не получен, значение токена = '" + token + "'");

        logger.info("Задача 4. Запросить данные профиля игрока");
        PlayerInfo playerInfoFromServer = apiRequests.getPlayerProfileData(endPointForCreationTask, token, playerInfo.getId(), firstExpectedCode);
        logger.info("Проверим, что сервер вернул ожидаемый username");
        Assertions.assertEquals(playerInfo.getUsername(), playerInfoFromServer.getUsername(), "Ошибка! username " +
                "должен быть = '" + playerInfo.getUsername() + "', а сервер вернул '" + playerInfoFromServer.getUsername() + "'");

        logger.info("Задача 5. Запросить данные другого игрока, проверить статус код");
        logger.info("В случайном порядке выберем id для запроса, но не больше, чем у нашего игрока");
        int randomId = (int) (Math.random() * playerInfo.getId());
        apiRequests.getPlayerProfileData(endPointForCreationTask, token, randomId, fifthExpectedCode);
    }
}
