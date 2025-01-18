package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class RegisterPage {

    private SelenideElement nameField = $x(".//label[contains(text(), 'Имя')]//following-sibling::input");
    private SelenideElement emailField = $x(".//label[contains(text(), 'Email')]//following-sibling::input");
    private SelenideElement passwordField = $x(".//label[contains(text(), 'Пароль')]//following-sibling::input");
    private SelenideElement passwordFieldError = $x(".//p[contains(@class, 'input__error') and text()=\"Некорректный пароль\"]");
    private SelenideElement registerOnRegisterPageButton = $x(".//button[text() = \"Зарегистрироваться\"]");
    private SelenideElement loginOnRegisterPageButton = $x(".//a[text()=\"Войти\"]");

    @Step("Set name field")
    public void setNameField(String name) {
        nameField.setValue(name);
    }

    @Step("Set email field")
    public void setEmailField(String email) {
        emailField.setValue(email);
    }

    @Step("Set password field")
    public void setPasswordField(String password) {
        passwordField.setValue(password);
    }

    @Step("Set password field with incorrect password")
    public String setPasswordFieldErrorGetText() {
        return passwordFieldError.getText();
    }

    @Step("Click register button ")
    public void clickRegisterButtonOnRegisterPage() {
        registerOnRegisterPageButton.click();
    }

    @Step("Click login button on register page")
    public void clickLoginButtonOnRegisterPage() {
        loginOnRegisterPageButton.click();
    }
}
