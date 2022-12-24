import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestCardDelivery {

    public String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    private final String Date = generateDate(5, "dd.MM.yyyy");

    @Test
    void testFormOfDeliveryCard() {
        open("http://localhost:9999");
        $x("//input[@placeholder=\"Город\"]").setValue("Санкт-Петербург");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder=\"Дата встречи\"]").setValue(Date);
        $("[name=\"name\"]").setValue("Анна-Мария Петрова-Сидорова");
        $("[name=\"phone\"]").setValue("+71234567890");
        $("[data-test-id=\"agreement\"]").click();
        $(".button").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + Date), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

}
