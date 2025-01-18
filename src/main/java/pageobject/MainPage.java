package pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    public static String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    private SelenideElement loginToAccountOnMainPageButton = $x(".//button[text() =\"Войти в аккаунт\"]");
    private SelenideElement personalAccountOnMainPageButton = $x(".//p[text()=\"Личный Кабинет\"]");
    private SelenideElement constructorOnMainPageButton = $x(".//p[text()=\"Конструктор\"]");
    private SelenideElement orderFeedOnMainPageButton = $x(".//p[text()=\"Лента Заказов\"]");
    private SelenideElement logoStellarBurgers = $x(".//div[contains(@class, 'logo')]");
    private SelenideElement bunsButton = $x(".//span[text()=\"Булки\"]");
    private SelenideElement saucesButton = $x(".//span[text()=\"Соусы\"]");
    private SelenideElement fillingsButton = $x(".//span[text()=\"Начинки\"]");

    @Step("Click login to account button")
    public void clickLoginToAccountOnMainPageButton() {
        loginToAccountOnMainPageButton.click();
    }

    @Step("Click personal account button")
    public void clickPersonalAccountOnMainPageButton() {
        personalAccountOnMainPageButton.click();
    }

    @Step("Click constructor button")
    public void clickConstructorOnMainPageButton() {
        constructorOnMainPageButton.click();
    }

    @Step("Click order feed button")
    public void clickOrderFeedOnMainPageButton() {
        orderFeedOnMainPageButton.click();
    }

    @Step("Click logo Stellar Burgers")
    public void clickLogoStellarBurgers() {
        logoStellarBurgers.click();
    }

    @Step("Get buns button")
    public SelenideElement getBunsButton() {
        return bunsButton;
    }

    @Step("Click buns button")
    public void clickBunsButton() {
        bunsButton.click();
    }

    @Step("Get sauces button")
    public SelenideElement getSaucesButton() {
        return saucesButton;
    }

    @Step("Click sauces button")
    public void clickSaucesButton() {
        saucesButton.click();
    }

    @Step("Get fillings button")
    public SelenideElement getFillingsButton() {
        return fillingsButton;
    }

    @Step("Click fillings button")
    public void clickFillingsButton() {
        fillingsButton.click();
    }

}
