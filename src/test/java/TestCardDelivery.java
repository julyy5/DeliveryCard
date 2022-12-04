import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCardDelivery {
    @Test
    void test() {
        LocalDate date = LocalDate.now().plusDays(3);
        String dateText = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999");
        $x("//input[@placeholder=\"Город\"]").setValue("Санкт-Петербург");
        $x("//input[@placeholder=\"Дата встречи\"]").setValue(dateText);
        $("[name=\"name\"]").setValue("Анна-Мария Петрова-Сидорова");
        $("[name=\"phone\"]").setValue("+71234567890");
        $("[data-test-id=\"agreement\"]").click();
        $(".button").click();
        $("[data-test-id=\"notification\"]").should(visible, Duration.ofSeconds(15));
        String text = $("[class=\"notification__content\"]").getText();
        assertEquals("Встреча успешно забронирована на " + dateText, text);
    }

}
