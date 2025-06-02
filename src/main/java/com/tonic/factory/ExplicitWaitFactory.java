package com.tonic.factory;
import com.tonic.constants.FrameworkConstants;
import com.tonic.driver.Driver;
import com.tonic.enums.WaitLogic;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class ExplicitWaitFactory {

    private ExplicitWaitFactory() {}

    public static WebElement waitExplicitlyForElement(WaitLogic waitStrategy, By by) {
        System.out.println(Driver.getDriver());
        WebElement element = null;
        if (waitStrategy == WaitLogic.CLICKABLE) {
            element = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitWait()))
                    .until(ExpectedConditions.elementToBeClickable(by));
        }
        else if (waitStrategy == WaitLogic.PRESENCE) {
            element = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitWait()))
                    .until(ExpectedConditions.presenceOfElementLocated(by));
        }
        else if (waitStrategy == WaitLogic.VISIBLE) {
            element = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitWait()))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
        }
        else if (waitStrategy == WaitLogic.NONE) {
            element = Driver.getDriver().findElement(by);
        }
        return element;
    }

    public static WebElement waitExplicitlyForElement(WaitLogic waitStrategy, By by, int timeout) {
        WebElement element = null;
        if (waitStrategy == WaitLogic.CLICKABLE) {
            element = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout))
                    .until(ExpectedConditions.elementToBeClickable(by));
        } else if (waitStrategy == WaitLogic.PRESENCE) {
            element = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout))
                    .until(ExpectedConditions.presenceOfElementLocated(by));
        } else if (waitStrategy == WaitLogic.VISIBLE) {
            element = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
        } else if (waitStrategy == WaitLogic.NONE) {
            element = Driver.getDriver().findElement(by);
        }
        return element;
    }
}
