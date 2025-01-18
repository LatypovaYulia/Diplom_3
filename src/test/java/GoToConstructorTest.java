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

public class GoToСonstructorTest extends BaseTest {
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

        userData = UserGeneration.getRandomUser("Mary", "password", "Mary");
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
    @DisplayName("Go to Сonstructor from Main Page")
    @Description("Go to Сonstructor from Main Page before authorization")
    public void clickСonstructorButtonOnMainPageTest() {
        mainPage.clickConstructorOnMainPageButton();
        webdriver().shouldHave(WebDriverConditions.url(MainPage.MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("Go to Сonstructor from Login Page")
    @Description("Go to Сonstructor from Login Page before authorization")
    public void clickСonstructorButtonOnLoginPageTest() {
        mainPage.clickLoginToAccountOnMainPageButton();
        loginPage.clickConstructorOnLoginPageButton();
        webdriver().shouldHave(WebDriverConditions.url(MainPage.MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("Go to Сonstructor from Login Page")
    @Description("Go to Сonstructor from Login Page after authorization")
    public void clickСonstructorButtonOnLoginAfterAuthorizationPageTest() {
        mainPage.clickLoginToAccountOnMainPageButton();
        loginPage.fillOutLoginForm(userData.getEmail(), userData.getPassword());
        mainPage.clickConstructorOnMainPageButton();
        webdriver().shouldHave(WebDriverConditions.url(MainPage.MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("Go to Сonstructor from Profile Page")
    @Description("Go to Сonstructor from Profile Page after authorization")
    public void clickСonstructorButtonOnProfilePageTest() {
        mainPage.clickLoginToAccountOnMainPageButton();
        loginPage.fillOutLoginForm(userData.getEmail(), userData.getPassword());
        mainPage.clickPersonalAccountOnMainPageButton();
        profilePage.clickConstructorOnProfilePageButton();
        webdriver().shouldHave(WebDriverConditions.url(MainPage.MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("Go to Сonstructor from Feed Page")
    @Description("Go to Сonstructor from Feed Page after authorization")
    public void clickСonstructorButtonOnFeedPageTest() {
        mainPage.clickLoginToAccountOnMainPageButton();
        loginPage.fillOutLoginForm(userData.getEmail(), userData.getPassword());
        mainPage.clickOrderFeedOnMainPageButton();
        feedPage.clickConstructorOnFeedPageButton();
        webdriver().shouldHave(WebDriverConditions.url(MainPage.MAIN_PAGE_URL));
    }
}
