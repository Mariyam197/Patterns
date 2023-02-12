package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryTest {

    @BeforeEach
    void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
    }

    @Test
    void shouldSuccessfulPlanAndReplanMeeting() {
        int daysToAddForFirstMeeting = 3;
        String firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        int daysToAddForSecondMeeting = 7;
        String secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $("[data-test-id=city] input").setValue(DataGenerator.generateCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(firstMeetingDate);
        $("[data-test-id=name] input").setValue(DataGenerator.generateName());
        $("[data-test-id=phone] input").setValue(DataGenerator.generatePhone());
        $("[data-test-id=agreement]").click();
        $(byText("Запланировать")).click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(Condition.exactText("Встреча успешно запланирована на " + firstMeetingDate))
                .shouldBe(Condition.visible);
        $("[data-test-id='success-notification'] button").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=date] input").setValue(secondMeetingDate);
        $(byText("Запланировать")).click();
        $(byText("Необходимо подтверждение")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='replan-notification'] .notification__content")
                .shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"))
                .shouldBe(Condition.visible);
        $("[data-test-id='replan-notification'] button").click();
        $("[data-test-id='success-notification'] .notification__content")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactText("Встреча успешно запланирована на " + secondMeetingDate));
    }
}
