package com.hightest.pages;

import com.hightest.constants.TestConstants;
import com.hightest.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    
    private WebDriver driver;
    private WaitUtils waitUtils;
    
    // Locators
    private final By toolboxMenuSelector = By.cssSelector("li[id='menu-item-33']");
    private final By toolboxLinkSelector = By.cssSelector("a[title='Toolbox']");
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, TestConstants.DEFAULT_TIMEOUT);
    }
    
    public void navigateToHomePage() {
        driver.get(TestConstants.BASE_URL);
    }
    
public ToolboxPage clickOnToolboxMenu() {
    // Essayer d'abord avec le sélecteur de l'élément li
    try {
        WebElement toolboxMenu = waitUtils.waitForElementToBeClickable(toolboxMenuSelector);
        
        // Faire défiler l'élément dans la vue
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", toolboxMenu);
        
        toolboxMenu.click();
    } catch (Exception e) {
        // Si cela échoue, essayer avec le sélecteur du lien direct
        WebElement toolboxLink = waitUtils.waitForElementToBeClickable(toolboxLinkSelector);
        
        // Faire défiler l'élément dans la vue
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", toolboxLink);
        
        toolboxLink.click();
    }
    
    return new ToolboxPage(driver);
}
}
