package demo.project.tests;

import com.codeborne.selenide.Configuration;
import com.demo.project.config.PropertyFileReader;
import com.demo.project.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class loginTest {
    private LoginPage loginPage = new LoginPage();
    private String adminLogin, adminPassword;

    @BeforeAll

    public void setup() {
        adminLogin = PropertyFileReader.getProperty("setup.properties", "adminLogin");
        adminPassword = PropertyFileReader.getProperty("setup.properties", "adminPassword");
        Configuration.browser = System.getProperty("selenide.browser", "firefox");
        Configuration.remote = System.getenv("ZALENIUM_HUB");

        Map.of("firefox", WebDriverManager.firefoxdriver(),
                "chrome", WebDriverManager.chromedriver(),
                "edge", WebDriverManager.edgedriver(),
                "phantomjs", WebDriverManager.phantomjs())
                .get(Configuration.browser)
                .setup();

        Configuration.timeout = 15000;
    }

    @Test
    public void testLoginAsAdmin() {
        open(loginPage.getLoginPageUrl());
        loginPage.fillLoginField(adminLogin)
                .fillPasswordField(adminPassword)
                .login();
    }

}
