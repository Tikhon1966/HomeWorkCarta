package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CardOrderTest {
    @Test
    void shouldCardOrder() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").setValue("25.06.23");
        $("[data-test-id='name'] input").setValue("Петров-Водкин Сергей");
        $("[data-test-id='phone'] input").setValue("+79161111111");
        $("[data-test-id='agreement']").click();
        $("button").click();

        $(withText("Успешно!")).shouldBe(Condition.hidden, Duration.ofSeconds(15));


    }

    ;

}
