package com.demo.project.utils;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.assertj.core.api.Assertions.assertThat;

public class ElementActions extends Expectations {
    public void fillField(SelenideElement field, String givenValue) {
        field.append(givenValue)
                .shouldNotBe(empty)
                .shouldHave(value(givenValue));
    }

    public void clearField(SelenideElement field) {
        field.clear();
    }

    public void waitUntilPageLoads() {
        assertThat((String)executeJavaScript("return document.readyState")).isEqualTo("complete");
    }

    public void waitUntilElementBecomesActive(SelenideElement element) {
        element.waitUntil(active, 10000);
    }

    public void clickOnClickableElement(SelenideElement element) {
        element.shouldBe(active).click();
    }

    public void waitUntilPageLoadsContent() {
        $x("//div[@id='preloader']").waitUntil(appear.because("There is a small delay for loader to appear"), 2000)
                .waitUntil(disappear, 10000);
    }

    public void waitUntilElementAppears(By pathToElement) {
        $(pathToElement).waitUntil(appears, 10000);
    }

    public void waitUntilElementAppears(SelenideElement element) {
        element.waitUntil(appears, 10000);
    }

}
