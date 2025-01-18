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
import pageobject.FeedPage;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.ProfilePage;

import static com.codeborne.selenide.Selenide.webdriver;

public class LogoStellarBurgersTest extends BaseTest {

    MainPage mainPage;
    LoginPage loginPage;
    ProfilePage profilePage;
    FeedPage feedPage;
    UserData userData;
    protected UserApi userApi;
    protected String token;

    @Before
    public void setUp() {
        mainPage = new MainPage();
        loginPage = new LoginPage();
        profilePage = new ProfilePage();
        feedPage = new FeedPage();
        userApi = new UserApi();

        userData = UserGeneration.getRandomUser("Olga", "password", "Olga");
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
    @DisplayName("Click logo Stellar Burgers on Main Page")
    @Description("Click logo Stellar Burgers on Main Page before authorization")
    public void clickLogoStellarBurgersOnMainPageTest() {
        mainPage.clickLogoStellarBurgers();
        webdriver().shouldHave(WebDriverConditions.url(MainPage.MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("Click logo Stellar Burgers on Login Page")
    @Description("Click logo Stellar Burgers on Login Page before authorization")
    public void clickLogoStellarBurgersOnLoginPageTest() {
        mainPage.clickLoginToAccountOnMainPageButton();
        loginPage.clickLogoStellarBurgersOnLoginPage();
        webdriver().shouldHave(WebDriverConditions.url(MainPage.MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("Click logo Stellar Burgers on Main Page")
    @Description("Click logo Stellar Burgers on Main Page after authorization")
    public void clickLogoStellarBurgersOnMainPageAfterAuthorizationTest() {
        mainPage.clickLoginToAccountOnMainPageButton();
        loginPage.fillOutLoginForm(userData.getEmail(), userData.getPassword());
        mainPage.clickLogoStellarBurgers();
        webdriver().shouldHave(WebDriverConditions.url(MainPage.MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("Click logo Stellar Burgers on Feed Page")
    @Description("Click logo Stellar Burgers on Feed Page after authorization")
    public void clickLogoStellarBurgersOnFeedPageTest() {
        mainPage.clickLoginToAccountOnMainPageButton();
        loginPage.fillOutLoginForm(userData.getEmail(), userData.getPassword());
        mainPage.clickOrderFeedOnMainPageButton();
        feedPage.clickLogoStellarBurgersOnFeedPage();
        webdriver().shouldHave(WebDriverConditions.url(MainPage.MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("Click logo Stellar Burgers on Profile Page")
    @Description("Click logo Stellar Burgers on Profile Page after authorization")
    public void clickLogoStellarBurgersOnProfilePageTest() {
        mainPage.clickLoginToAccountOnMainPageButton();
        loginPage.fillOutLoginForm(userData.getEmail(), userData.getPassword());
        mainPage.clickPersonalAccountOnMainPageButton();
        profilePage.clickLogoStellarBurgersOnProfilePage();
        webdriver().shouldHave(WebDriverConditions.url(MainPage.MAIN_PAGE_URL));
    }
}
