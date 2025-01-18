import api.UserApi;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import jdk.jfr.Description;
import model.UserData;
import model.UserGeneration;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegisterPage;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static com.codeborne.selenide.WebDriverConditions.url;

public class RegistrationTest extends BaseTest {
    MainPage mainPage;
    LoginPage loginPage;
    RegisterPage registerPage;
    protected UserApi userApi;
    protected String token;

    @Before
    public void setUp() {
        mainPage = new MainPage();
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        userApi = new UserApi();

        mainPage.clickLoginToAccountOnMainPageButton();
        loginPage.clickRegisterOnLoginPageButton();
    }

    @After
    public void cleanUp() {
        if (token != null) {
            userApi.deleteUser(token);
        }
    }

    @Test
    @DisplayName("Register user")
    @Description("Check that user can be registered when all fields are filled in")
    public void registerUserSuccessfulResultTest() {
        UserData userData = UserGeneration.getRandomUser("Yulia", "password", "Yulia");
        SoftAssertions softly = new SoftAssertions();

        registerPage.setNameField(userData.getName());
        registerPage.setEmailField(userData.getEmail());
        registerPage.setPasswordField(userData.getPassword());
        registerPage.clickRegisterButtonOnRegisterPage();

        softly.assertThat(url())
                .as("Текущий URL должен соответствовать странице логина")
                .isEqualTo(LoginPage.LOGIN_PAGE_URL);

        loginPage.setEmailFieldInLogin(userData.getEmail());
        loginPage.setPasswordFieldInLogin(userData.getPassword());
        loginPage.clickLoginOnLoginPageButton();

        webdriver().shouldHave(url(MainPage.MAIN_PAGE_URL));

        softly.assertThat(url())
                .as("Текущий URL должен соответствовать главной странице")
                .isEqualTo(MainPage.MAIN_PAGE_URL);
        softly.assertAll();

        ValidatableResponse loginResponse = userApi.loginUser(userData);
        token = loginResponse.extract().path("accessToken");
    }

    @Test
    @DisplayName("Register user with incorrect password")
    @Description("Check that user cannot be registered with incorrect password ans return error")
    public void registerUserWithIncorrectPasswordTest() {
        UserData userData = UserGeneration.getRandomUser("Olga", "password", "Olga");
        SoftAssertions softly = new SoftAssertions();

        registerPage.setNameField(userData.getName());
        registerPage.setEmailField(userData.getEmail());
        registerPage.setPasswordField("1234");
        registerPage.clickRegisterButtonOnRegisterPage();

        softly.assertThat(registerPage.setPasswordFieldErrorGetText())
                .as("Неверное сообщение об ошибке для поля Password")
                .isEqualTo("Некорректный пароль");

        softly.assertAll();
    }
}
