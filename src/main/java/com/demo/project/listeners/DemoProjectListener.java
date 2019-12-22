package com.demo.project.listeners;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class DemoProjectListener {


    private DesiredCapabilities firefoxCapabilities() {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
        cap.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
        return cap;
    }



    public void onStart() {
        Configuration.browser = System.getProperty("selenide.browser", "firefox");
        Configuration.remote = System.getenv("ZALENIUM_HUB");

        Map.of("firefox", WebDriverManager.firefoxdriver(),
                "chrome", WebDriverManager.chromedriver(),
                "edge", WebDriverManager.edgedriver(),
                "phantomjs", WebDriverManager.phantomjs())
                .get(Configuration.browser)
                .setup();

        Configuration.browserCapabilities = Map.of("firefox", firefoxCapabilities())
                .get(Configuration.browser);
        Configuration.timeout = 15000;

    }




}
