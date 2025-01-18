import api.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.UserData;
import model.UserGeneration;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.FeedPage;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.ProfilePage;

import static com.codeborne.selenide.WebDriverRunner.url;

public class LogOutOfAccountTest extends BaseTest {
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

        userData = UserGeneration.getRandomUser("Mike", "password", "Mike");
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
    @DisplayName("Log out of account from Profile Page")
    @Description("Log out of account from Profile Page after authorization")
    public void logOutOfAccountFromProfilePageTest() {
        mainPage.clickLoginToAccountOnMainPageButton();
        loginPage.fillOutLoginForm(userData.getEmail(), userData.getPassword());
        mainPage.clickPersonalAccountOnMainPageButton();
        profilePage.clickLogOutOnProfilePageButton();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(loginPage.isLoginButtonVisible())
                .as("Кнопка 'Войти' должна быть видима")
                .isTrue();

        softly.assertThat(url())
                .as("Текущий URL должен соответствовать странице логина")
                .isEqualTo(LoginPage.LOGIN_PAGE_URL);

        softly.assertAll();
    }
}
