package com.hightest.tests;

import com.hightest.config.BrowserConfig;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    
    protected WebDriver driver;
    private BrowserConfig browserConfig;
    
    @BeforeMethod
    public void setUp() {
        browserConfig = new BrowserConfig();
        driver = browserConfig.initializeDriver();
    }
    
    @AfterMethod
    public void tearDown() {
        browserConfig.closeDriver();
    }
}
