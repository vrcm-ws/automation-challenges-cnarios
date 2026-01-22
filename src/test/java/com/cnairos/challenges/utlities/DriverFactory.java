package com.cnairos.challenges.utlities;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory
{
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private String browser;
    private Logger logger;

    public DriverFactory(String browser, Logger logger)
    {
        this.browser = browser.toLowerCase();
        this.logger = logger;
    }

    public WebDriver createWebDriver()
    {
        String message = "Using" + browser.toUpperCase();

        switch(browser)
        {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver.set(new FirefoxDriver(firefoxOptions));
                break;
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                driver.set(new ChromeDriver(chromeOptions));
                break;
            default:
                message = browser.toUpperCase() + " is not a valid option, using CHROME";
                driver.set(new ChromeDriver());
                break;
        }

        logger.info(message);

        return driver.get();
    }
}
