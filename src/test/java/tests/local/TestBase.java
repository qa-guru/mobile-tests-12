package tests.local;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.Attach.sessionId;

import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import drivers.BrowserstackMobileDriver;
import drivers.LocalMobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;

public class TestBase {
    //    @BeforeAll
//    public static void setup() {
//        Configuration.browser = LocalMobileDriver.class.getName();
//        Configuration.browserSize = null;
//    }
    @BeforeAll
    public static void setup() throws Exception{
        String host = System.getProperty("env");
        host = "real";
        if (host.equals("browserstack")) {
            Configuration.browser = BrowserstackMobileDriver.class.getName();
        } else if (host.equals("local")) {
            Configuration.browser = LocalMobileDriver.class.getName();
        } else if (host.equals("real")) {
            Configuration.browser = LocalMobileDriver.class.getName();

        }
        Configuration.browserSize = null;
    }

    @BeforeEach
    public void startDriver() {
        addListener("AllureSelenide", new AllureSelenide());

        open();
    }

    @AfterEach
    public void afterEach() {
        if (System.getProperty("env").equals("browserstack")) {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
            String sessionId = sessionId();
            closeWebDriver();
            Attach.video(sessionId);
        } else {
            closeWebDriver();
        }
    }
}
