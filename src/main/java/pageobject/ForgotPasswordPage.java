package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ForgotPasswordPage {
    private SelenideElement loginOnForgotPasswordPageButton = $x(".//a[text()=\"Войти\"]");

    @Step("Click login button on forgot password page")
    public void clickLoginOnForgotPasswordPageButton() {
        loginOnForgotPasswordPageButton.click();
    }
}
