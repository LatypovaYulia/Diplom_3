package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class FeedPage {
    public static String FEED_PAGE_URL = "https://stellarburgers.nomoreparties.site/feed";

    private SelenideElement personalAccountOnFeedPageButton = $x(".//p[text()=\"Личный Кабинет\"]");
    private SelenideElement constructorOnFeedPageButton = $x(".//p[text()=\"Конструктор\"]");
    private SelenideElement logoStellarBurgersOnFeedPage = $x(".//div[contains(@class, 'logo')]");

    @Step("Click personal account on feed page")
    public void clickPersonalAccountOnFeedPageButton() {
        personalAccountOnFeedPageButton.click();
    }

    @Step("Click constructor on feed page")
    public void clickConstructorOnFeedPageButton() {
        constructorOnFeedPageButton.click();
    }

    @Step("Click logo Stellar Burgers on feed page")
    public void clickLogoStellarBurgersOnFeedPage() {
        logoStellarBurgersOnFeedPage.click();
    }
}
