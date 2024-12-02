package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class GpbiTests extends TestBase {

    @Tag("Smoke")
    @Tag("Regression")
    @DisplayName("Раздел Брокерский счет: Проверка отображения премиум тарифа")
    @Test
    void checkTariffsBAPremiumTest() {
        step("Открыть главную страницу", () -> {
            open("");
        });
        step("Перейти в раздел Брокерский Счет", () -> {
            $("div.header__secondary-menu").$(byText("Брокерский счет")).click();
        });
        step("Найти карточку с описанием Премиум тарифа", () -> {
            $("div.tariffs").shouldHave(text("Тарифы брокерского обслуживания"));
            $("ul.tariffs__list.swiper-wrapper").shouldHave(text("Премиум"));
        });
    }

    @Tag("Smoke")
    @Tag("Regression")
    @DisplayName("Инестидеи: Проверка отображения инвестидей при переходе с главной страницы")
    @Test
    void checkInvestIdeasTest() {
        step("Открыть главную страницу", () -> {
            open("");
        });
        step("Проверить карусель инвестидей на главной странице", () -> {
            $(".invest-ideas").shouldHave(text("Инвестиционные идеи"));
        });
        step("Нажать кнопку \"Больше инвестидей\"", () -> {
            $(".invest-ideas__bottom").$(byText("Больше инвестидей")).click();
        });
        step("Проверить, что Инвестидеи открылись и отображаются", () -> {
            $("#invest_items").shouldBe(not(empty));
        });
    }

    @Tag("Smoke")
    @Tag("Regression")
    @DisplayName("ЛК: Проверка страницы входа в ЛК")
    @Test
    void checkLoginPageTest() {
        step("Открыть главную страницу", () -> {
            open("");
        });
        step("Нажать кнопку \"Войти\"", () -> {
            $(".header__top-main").$(".btn_desktop").click();
        });
        step("Проверить страницу входа", () -> {
            $(".sc-kFCsca.bAmhBT").shouldHave(text("Вход или регистрация"));
            $("#input").sibling(0).shouldHave(text("Номер телефона"));
            $(".sc-iBdmCd.jxxOCC").shouldHave(text("Продолжить")).shouldBe(disabled);
        });
    }

    @Tag("Smoke")
    @Tag("Regression")
    @DisplayName("ЛК: Кнопка логина доступна если валидный номер введен на странице входа")
    @Test
    void checkLoginButtonEnabledIfNumberEnteredTest() {
        step("Открыть главную страницу", () -> {
            open("");
        });
        step("Нажать кнопку \"Войти\"", () -> {
            $(".header__top-main").$(".btn_desktop").click();
        });
        step("Ввести валидный номер телефона", () -> {
            $("#input").setValue(" 995 308 66 48");
        });
        step("Проверить что кнопка \"Продолжить\" доступна", () -> {
            $(".sc-iBdmCd.jxxOCC").shouldBe(enabled);
        });
    }

    @Tag("Regression")
    @DisplayName("ЛК: Кнопка логина недоступна если введен некорректный номер")
    @Test
    void checkLoginButtonDisabledIfIncorrectNumberEnteredTest() {
        step("Открыть главную страницу", () -> {
            open("");
        });
        step("Нажать кнопку \"Войти\"", () -> {
            $(".header__top-main").$(".btn_desktop").click();
        });
        step("Ввести валидный номер телефона", () -> {
            $("#input").setValue(" 99533");
        });
        step("Проверить что кнопка \"Продолжить\" недоступна", () -> {
            $(".sc-iBdmCd.jxxOCC").shouldBe(disabled);
        });
    }
}
