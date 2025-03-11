package com.hightest.pages;

import com.hightest.constants.TestConstants;
import com.hightest.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResultPage {
    
    private WebDriver driver;
    private WaitUtils waitUtils;
    
    // Locators
    private final By emailInputSelector = By.id("email");
    private final By submitButtonSelector = By.cssSelector("input#submitMail[name='submitMail'][type='submit'][value='OK']");
    
    public ResultPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, TestConstants.DEFAULT_TIMEOUT);
    }
    
    public void enterEmail(String email) {
        WebElement emailInput = waitUtils.waitForElementToBeVisible(emailInputSelector);
        emailInput.clear();
        emailInput.sendKeys(email);
    }
    
    public void submitEmail() {
        WebElement submitButton = waitUtils.waitForElementToBeClickable(submitButtonSelector);
        submitButton.click();
    }
    
    public YopmailPage navigateToYopmail() {
        driver.get(TestConstants.YOPMAIL_URL);
        return new YopmailPage(driver);
    }
}
