package com.cnarios.challenges.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage
{
    protected WebDriver driver;
    protected Logger logger;

    private Duration timeout = Duration.ofSeconds(10);

    public BasePage(WebDriver driver, Logger logger)
    {
        this.driver = driver;
        this.logger = logger;
    }

    protected void loadPage(String url)
    {
        driver.get(url);
    }

    protected void clickElement(By locator)
    {
        waitForVisibilityOf(locator, timeout);
        searchElement(locator).click();
    }

    protected WebElement searchElement(By locator)
    {
        return driver.findElement(locator);
    }

    protected List<WebElement> searchElements(By locator)
    {
        return driver.findElements(locator);
    }

    protected void waitFor(ExpectedCondition<WebElement> condition, Duration timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(condition);
    }

    protected void waitForVisibilityOf(By locator, Duration timeoutInSeconds)
    {
        try
        {
            waitFor(ExpectedConditions.visibilityOfElementLocated(locator), timeoutInSeconds);
        }
        catch (StaleElementReferenceException e)
        {
            logger.info(e.getMessage());
        }
    }

    protected boolean isElementVisible(By locator)
    {
        return driver.findElement(locator).isEnabled();
    }
}
