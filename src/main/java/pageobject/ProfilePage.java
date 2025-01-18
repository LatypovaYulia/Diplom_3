package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {
    public static String PROFILE_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";
    private SelenideElement constructorOnProfilePageButton = $x(".//p[text()=\"Конструктор\"]");
    private SelenideElement logoStellarBurgersOnProfilePage = $x(".//div[contains(@class, 'logo')]");
    private SelenideElement logOutOnProfilePageButton = $x(".//button[contains(@class, 'Account_button') and text()=\"Выход\"]");

    @Step("Click constructor on profile page")
    public void clickConstructorOnProfilePageButton() {
        constructorOnProfilePageButton.click();
    }

    @Step("Click logo Stellar Burgers on profile page")
    public void clickLogoStellarBurgersOnProfilePage() {
        logoStellarBurgersOnProfilePage.click();
    }

    @Step("Click log out button on profile page")
    public void clickLogOutOnProfilePageButton() {
        logOutOnProfilePageButton.click();
    }
}
