package model;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class UserGeneration {
    @Step("Generate random user")
    public static UserData getRandomUser(String emailParam, String passwordParam,
                                         String nameParam) {
        String email = emailParam + RandomStringUtils.randomAlphabetic(4) + "@gmail.com";
        String password = passwordParam + RandomStringUtils.randomAlphabetic(4);
        String name = nameParam + RandomStringUtils.randomAlphabetic(4);

        return new UserData(email, password, name);
    }
}
