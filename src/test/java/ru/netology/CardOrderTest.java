package ru.netology;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.remote.tracing.EventAttribute.setValue;

class CardOrderTest {

    private String getPossibleData(int daysToAdd) {
        LocalDate currentDate = LocalDate.now();
        LocalDate futureData = currentDate.plusDays(daysToAdd);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String possibleDate = futureData.format(formatter);
        return possibleDate;
    }

    @Test
    void shouldCardOrder() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Казань");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(getPossibleData(4));
        $("[data-test-id='name'] input").setValue("Петров-Водкин Сергей");
        $("[data-test-id='phone'] input").setValue("+79161111111");
        $(".checkbox__box").click();
        $(".button").click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText(getPossibleData(4))).shouldBe(visible, Duration.ofSeconds(15));
    }
}
