package pageobject;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    public static String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    private SelenideElement registerOnLoginPageButton = $x(".//a[contains(@class, 'Auth_link') and (text()=\"Зарегистрироваться\")]");
    private SelenideElement loginOnLoginPageButton = $x(".//button[text()=\"Войти\"]");
    private SelenideElement emailFieldInLogin = $x(".//input[@name=\"name\"]");
    private SelenideElement passwordFieldInLogin = $x(".//input[@name=\"Пароль\"]");
    private SelenideElement recoverPasswordButton = $x(".//a[text()=\"Восстановить пароль\"]");
    private SelenideElement personalAccountOnLoginPageButton = $x(".//p[text()=\"Личный Кабинет\"]");
    private SelenideElement constructorOnLoginPageButton = $x(".//p[text()=\"Конструктор\"]");
    private SelenideElement logoStellarBurgersOnLoginPage = $x(".//div[contains(@class, 'logo')]");

    @Step("Click register button")
    public void clickRegisterOnLoginPageButton() {
        registerOnLoginPageButton.click();
    }

    @Step("Click login button")
    public void clickLoginOnLoginPageButton() {
        loginOnLoginPageButton.click();
    }

    @Step("Set email field in login")
    public void setEmailFieldInLogin(String email) {
        emailFieldInLogin.clear();
        emailFieldInLogin.setValue(email);
    }

    @Step("Set password field in login")
    public void setPasswordFieldInLogin(String password) {
        passwordFieldInLogin.clear();
        passwordFieldInLogin.setValue(password);
    }

    @Step("Set password field in login")
    public void fillOutLoginForm(String email, String password) {
        this.setEmailFieldInLogin(email);
        this.setPasswordFieldInLogin(password);
        this.clickLoginOnLoginPageButton();
    }

    @Step("Click recover password button")
    public void clickRecoverPasswordButton() {
        recoverPasswordButton.click();
    }

    @Step("Click personal account button")
    public void clickPersonalAccountOnLoginPageButton() {
        personalAccountOnLoginPageButton.click();
    }

    @Step("Click constructor button")
    public void clickConstructorOnLoginPageButton() {
        constructorOnLoginPageButton.click();
    }

    @Step("Click logo Stellar Burgers on login page")
    public void clickLogoStellarBurgersOnLoginPage() {
        logoStellarBurgersOnLoginPage.click();
    }

    @Step("login button is visible")
    public boolean isLoginButtonVisible() {
        return loginOnLoginPageButton.isDisplayed();
    }
}
