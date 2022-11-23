package tests.local;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("android")
public class SearchTests extends TestBase {
    @Test
    void searchTest() {
        back();
        step("Type search", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(CollectionCondition.sizeGreaterThan(0)));
    }

    @Test
    void searchWithByTextLocatorTest() {
        back();
//        switchTo().alert().accept();
        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(CollectionCondition.sizeGreaterThan(0)));
    }
    @Test
    void onBoardingTest() {
        step("Проверка первого экрана", () -> {
            checkTextAndPicture("The Free Encyclopedia\n" +
                    "…in over 300 languages");
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });

        step("Проверка второго экрана", () -> {
            checkTextAndPicture("New ways to explore");
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });

        step("Проверка третьего экрана", () -> {
            checkTextAndPicture("Reading lists with sync");
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });

        step("Проверка четвертого экрана", () -> {
            checkTextAndPicture("Send anonymous data");
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_done_button")).click();
        });

        step("Проверка что онбордин завершен", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/main_toolbar"))
                    .shouldBe(Condition.visible);
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container"))
                    .shouldBe(Condition.visible);
            $(AppiumBy.id("org.wikipedia.alpha:id/main_nav_tab_container"))
                    .shouldBe(Condition.visible);
        });
    }

    private void checkTextAndPicture(String text) {
        $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                .shouldHave(Condition.text(text));
        $(AppiumBy.id("org.wikipedia.alpha:id/imageViewCentered"))
                .shouldBe(Condition.visible);
    }
}