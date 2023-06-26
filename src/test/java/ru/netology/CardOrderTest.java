package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

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
        $("[data-test-id='date'] input").doubleClick().sendKeys("BackSpace");
        $("[data-test-id='date'] input").setValue(getPossibleData(3));
        $("[data-test-id='name'] input").setValue("Петров-Водкин Сергей");
        $("[data-test-id='phone'] input").setValue("+79161111111");
        $(".checkbox__box").click();
        $(".button").click();
        WebElement title = $("[data-test-id=notification] .notification__title").shouldBe(visible, Duration.ofSeconds(15));
        Assertions.assertEquals("Успешно!", title.getText());
        WebElement content = $("[data-test-id=notification] .notification__content");
        Assertions.assertEquals("Встреча успешно забронирована на " + getPossibleData(3), content.getText());


    }

    ;

}
