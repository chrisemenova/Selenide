import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class SelenideTest {

    String date(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test

    public void deliveryTest() {

        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Чебоксары");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(date(7));
        $("[data-test-id='name'] input").setValue("Вася Сидоров");
        $("[data-test-id='phone'] input").setValue("+79373777777");
        $("[data-test-id='agreement']").click();
        $(".button_theme_alfa-on-white").click();
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча успешно забронирована на " + date(7)));


    }

}
