import com.codeborne.selenide.Configuration;
import org.junit.Before;

import java.io.IOException;

import static browser.Browser.initDriver;
import static com.codeborne.selenide.Selenide.open;
import static pageobject.MainPage.MAIN_PAGE_URL;

public class BaseTest {

    @Before
    public void startUp() throws IOException {
        initDriver();
        Configuration.timeout = 10000;
        open(MAIN_PAGE_URL);
    }
}
