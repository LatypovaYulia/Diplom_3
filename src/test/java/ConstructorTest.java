import api.UserApi;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.UserData;
import model.UserGeneration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.*;

public class ConstructorTest extends BaseTest {
    MainPage mainPage;
    LoginPage loginPage;
    UserData userData;
    protected UserApi userApi;
    protected String token;
    SelenideElement tab;

    @Before
    public void setUp() {
        userApi = new UserApi();
        mainPage = new MainPage();
        loginPage = new LoginPage();
        userData = UserGeneration.getRandomUser("Alla", "password", "Alla");
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
    @DisplayName("Transition to Buns tab is worked before authorization")
    @Description("Check that transition to Buns tab is worked before authorization")
    public void bunsTabIsActiveBeforeAuthorizationTest() {
        mainPage.clickSaucesButton();
        mainPage.clickBunsButton();
        mainPage.getBunsButton().parent().shouldHave(Condition.match("класс содержит type_current",
                element -> element.getAttribute("class").contains("type_current")));
    }

    @Test
    @DisplayName("Transition to Buns tab is worked after authorization")
    @Description("Check that transition to Buns tab is worked after authorization")
    public void bunsTabIsActiveAfterAuthorizationTest() {
        mainPage.clickLoginToAccountOnMainPageButton();
        loginPage.fillOutLoginForm(userData.getEmail(), userData.getPassword());
        mainPage.clickSaucesButton();
        mainPage.clickBunsButton();
        mainPage.getBunsButton().parent().shouldHave(Condition.match("класс содержит type_current",
                element -> element.getAttribute("class").contains("type_current")));
    }

    @Test
    @DisplayName("Transition to Sauces tab is worked before authorization")
    @Description("Check that transition to Sauces tab is worked before authorization")
    public void saucesTabIsActiveBeforeAuthorizationTest() {
        mainPage.clickSaucesButton();
        mainPage.getSaucesButton().parent().shouldHave(Condition.match("класс содержит type_current",
                element -> element.getAttribute("class").contains("type_current")));
    }

    @Test
    @DisplayName("Transition to Sauces tab is worked after authorization")
    @Description("Check that transition to Sauces tab is worked after authorization")
    public void saucesTabIsActiveAfterAuthorizationTest() {
        mainPage.clickLoginToAccountOnMainPageButton();
        loginPage.fillOutLoginForm(userData.getEmail(), userData.getPassword());
        mainPage.clickSaucesButton();
        mainPage.getSaucesButton().parent().shouldHave(Condition.match("класс содержит type_current",
                element -> element.getAttribute("class").contains("type_current")));
    }

    @Test
    @DisplayName("Transition to Fillings tab is worked before authorization")
    @Description("Check that transition to Fillings tab is worked before authorization")
    public void fillingsTabIsActiveBeforeAuthorizationTest() {
        mainPage.clickFillingsButton();
        mainPage.getFillingsButton().parent().shouldHave(Condition.match("класс содержит type_current",
                element -> element.getAttribute("class").contains("type_current")));
    }

    @Test
    @DisplayName("Transition to Fillings tab is worked after authorization")
    @Description("Check that transition to Fillings tab is worked after authorization")
    public void fillingsTabIsActiveAfterAuthorizationTest() {
        mainPage.clickLoginToAccountOnMainPageButton();
        loginPage.fillOutLoginForm(userData.getEmail(), userData.getPassword());
        mainPage.clickFillingsButton();
        mainPage.getFillingsButton().parent().shouldHave(Condition.match("класс содержит type_current",
                element -> element.getAttribute("class").contains("type_current")));
    }
}

