package com.hightest.pages;

import com.hightest.constants.TestConstants;
import com.hightest.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YopmailPage {
    
    private WebDriver driver;
    private WaitUtils waitUtils;
    
    // Locators
    private final By emailInputSelector = By.id("login");
    private final By checkInboxButtonSelector = By.cssSelector("button[title='Vérifier les mails']");
    private final By iframeMailSelector = By.id("ifmail");
    private final By scoreTextSelector = By.xpath("//*[contains(text(), '100%')]");
    
    public YopmailPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, TestConstants.DEFAULT_TIMEOUT);
    }
    
    public void enterEmailAddress(String email) {
        WebElement emailInput = waitUtils.waitForElementToBeVisible(emailInputSelector);
        emailInput.clear();
        emailInput.sendKeys(email);
    }
    
    public void checkInbox() {
        waitUtils.waitForElementToBeClickable(checkInboxButtonSelector).click();
    }
    
    public boolean verifyScore() {
        // Attendre et passer à l'iframe contenant le mail
        waitUtils.waitForFrameAndSwitchToIt(iframeMailSelector);
        
        try {
            // Vérifier la présence du score 100%
            waitUtils.waitForElementToBeVisible(scoreTextSelector);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            // Revenir au contexte principal
            driver.switchTo().defaultContent();
        }
    }
}
