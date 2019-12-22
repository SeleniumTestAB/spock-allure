package demo.project.tests

import com.codeborne.selenide.Configuration
import com.demo.project.config.PropertyFileReader
import com.demo.project.pages.LoginPage
import io.github.bonigarcia.wdm.WebDriverManager
import spock.lang.Shared
import spock.lang.Specification

import static com.codeborne.selenide.Selenide.open

class LoginSpec extends Specification {
    @Shared
    def loginPage = new LoginPage()
    @Shared
    def adminLogin, adminPassword

    def setupSpec() {
        adminLogin = PropertyFileReader.getProperty("setup.properties", "adminLogin")
        adminPassword = PropertyFileReader.getProperty("setup.properties", "adminPassword")
        Configuration.browser = System.getProperty("selenide.browser", "firefox")
        Configuration.remote = System.getenv("ZALENIUM_HUB")
        WebDriverManager.firefoxdriver().setup()
        Map.of("firefox", WebDriverManager.firefoxdriver(),
                "chrome", WebDriverManager.chromedriver(),
                "edge", WebDriverManager.edgedriver(),
                "phantomjs", WebDriverManager.phantomjs())
                .get(Configuration.browser)
                .setup()


        Configuration.timeout = 15000
    }

     def  'test Login as Admin'() {
        given:
        open(loginPage.getLoginPageUrl())
        when:
        loginPage.fillLoginField(adminLogin)
        .fillPasswordField(adminPassword)
        .login()
        then:
            loginPage.getLoginPageUrl()

    }



}
