import api.UserApi;
import com.codeborne.selenide.WebDriverConditions;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.UserData;
import model.UserGeneration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.ForgotPasswordPage;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegisterPage;

import static com.codeborne.selenide.Selenide.webdriver;

public class LoginTest extends BaseTest {
    MainPage mainPage;
    LoginPage loginPage;
    RegisterPage registerPage;
    ForgotPasswordPage forgotPasswordPage;
    UserData userData;
    protected UserApi userApi;
    protected String token;

    @Before
    public void setUp() {
        mainPage = new MainPage();
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        forgotPasswordPage = new ForgotPasswordPage();
        userApi = new UserApi();

        userData = UserGeneration.getRandomUser("Anna", "password", "Anna");
        ValidatableResponse response = userApi.createUser(userData);
        response.log().all();
        token = response.extract().path("accessToken");
    }

    @After
    public void cleanUp() {

        if (token != null) {
            userApi.deleteUser(token);
        }
    }

    @Test
    @DisplayName("Log in by clicking the \"Login to account\" button")
    @Description("Log in by clicking the \"Login to account\" button on the main page")
    public void clickLoginToAccountButtonTest() {
        mainPage.clickLoginToAccountOnMainPageButton();
        loginPage.fillOutLoginForm(userData.getEmail(), userData.getPassword());
        webdriver().shouldHave(WebDriverConditions.url(MainPage.MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("Log in by clicking the \"Personal account\" button")
    @Description("Log in by clicking the \"Personal account\" button on the main page")
    public void clickPersonalAccountButtonTest() {
        mainPage.clickPersonalAccountOnMainPageButton();
        loginPage.fillOutLoginForm(userData.getEmail(), userData.getPassword());
        webdriver().shouldHave(WebDriverConditions.url(MainPage.MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("Log in by clicking the \"Login\" button")
    @Description("Log in by clicking the \"Login\" button on the register page")
    public void clickLoginButtonOnRegisterPageTest() {
        mainPage.clickLoginToAccountOnMainPageButton();
        loginPage.clickRegisterOnLoginPageButton();
        registerPage.clickLoginButtonOnRegisterPage();

        loginPage.fillOutLoginForm(userData.getEmail(), userData.getPassword());
        webdriver().shouldHave(WebDriverConditions.url(MainPage.MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("Log in by clicking the \"Login\" button on forgot-password page")
    @Description("Log in by clicking the \"Login\" button on forgot-password page")
    public void clickLoginButtonOnForgotPasswordPageTest() {
        mainPage.clickLoginToAccountOnMainPageButton();
        loginPage.clickRecoverPasswordButton();
        forgotPasswordPage.clickLoginOnForgotPasswordPageButton();
        loginPage.fillOutLoginForm(userData.getEmail(), userData.getPassword());
        webdriver().shouldHave(WebDriverConditions.url(MainPage.MAIN_PAGE_URL));
    }
}
