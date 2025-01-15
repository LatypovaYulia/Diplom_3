import org.example.models.OrderModel;
import org.example.pageobject.MainPage;
import org.junit.Test;
import tests.selenidetest.BaseUITest;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.example.pageobject.MainPage.MAIN_PAGE_URL;

public class UrlTest extends BaseUITest {

    @Test
    public void urlTest(){
        open(MAIN_PAGE_URL, MainPage.class);
        System.out.println("url = " + url());
    }

    @Test
    public void builderTest(){
        OrderModel orderModel = OrderModel.builder()
                .name("Ваня")
                .surname("Петров")
                .phoneNumber("+375 445 12 45")
                .build();

        orderModel.getAddress();
        orderModel.setAddress("JHGjshdf");

        System.out.println(orderModel.toString());
    }
}