package com.cnairos.challenges.utlities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest extends DataProviders
{
    protected WebDriver driver;
    protected Logger logger;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void methodSetup(@Optional("CHROME") String browser, ITestContext testContext)
    {
        logger = LogManager.getLogger(testContext.getCurrentXmlTest().getName());

        DriverFactory factory = new DriverFactory(browser, logger);
        driver = factory.createWebDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void methodTeardown()
    {
        driver.quit();
    }
}
