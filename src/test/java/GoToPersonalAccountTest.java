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
import pageobject.*;

import static com.codeborne.selenide.Selenide.webdriver;

public class GoToPersonalAccountTest extends BaseTest {
    MainPage mainPage;
    LoginPage loginPage;
    FeedPage feedPage;
    UserData userData;
    protected UserApi userApi;
    protected String token;

    @Before
    public void setUp() {
        mainPage = new MainPage();
        loginPage = new LoginPage();
        feedPage = new FeedPage();
        userApi = new UserApi();

        userData = UserGeneration.getRandomUser("Roman", "password", "Roman");
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
    @DisplayName("Go to Personal Account on Main Page before authorization")
    @Description("Go to Personal Account on Main Page before authorization")
    public void clickPersonalAccountButtonOnMainPageTest() {
        mainPage.clickPersonalAccountOnMainPageButton();
        webdriver().shouldHave(WebDriverConditions.url(LoginPage.LOGIN_PAGE_URL));
    }

    @Test
    @DisplayName("Go to Personal Account on Main Page after authorization")
    @Description("Go to Personal Account on Main Page after authorization")
    public void clickPersonalAccountButtonOnMainPageAfterAuthorizationTest() {
        mainPage.clickLoginToAccountOnMainPageButton();
        loginPage.fillOutLoginForm(userData.getEmail(), userData.getPassword());
        mainPage.clickPersonalAccountOnMainPageButton();
        webdriver().shouldHave(WebDriverConditions.url(ProfilePage.PROFILE_PAGE_URL));
    }

    @Test
    @DisplayName("Go to Personal Account on Login Page before authorization")
    @Description("Go to Personal Account on Login Page before authorization")
    public void clickPersonalAccountButtonOnLoginPageTest() {
        mainPage.clickLoginToAccountOnMainPageButton();
        loginPage.clickPersonalAccountOnLoginPageButton();
        webdriver().shouldHave(WebDriverConditions.url(LoginPage.LOGIN_PAGE_URL));
    }

    @Test
    @DisplayName("Go to Personal Account on Feed Page after authorization")
    @Description("Go to Personal Account on Feed Page after authorization")
    public void clickPersonalAccountButtonOnFeedPageTest() {
        mainPage.clickOrderFeedOnMainPageButton();
        feedPage.clickPersonalAccountOnFeedPageButton();
        loginPage.fillOutLoginForm(userData.getEmail(), userData.getPassword());
        mainPage.clickPersonalAccountOnMainPageButton();
        webdriver().shouldHave(WebDriverConditions.url(ProfilePage.PROFILE_PAGE_URL));
    }
}
