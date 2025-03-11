package com.hightest.pages;

import com.hightest.constants.TestConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

public class ToolboxPage {
    
    private WebDriver driver;
    
    // Locators
    private final By quizLinkSelector = By.cssSelector("a[href='https://hightest.nc/ressources/test-istqb-agile.php']");
    
    public ToolboxPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public QuizPage clickOnQuizLink() {
        // Créer une instance de WebDriverWait avec un délai d'attente de 45 secondes
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
        
        // Faire défiler la page à la moitié de la salle (en utilisant JavaScript)
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight / 2);");
        
        // Attendre que l'élément soit visible après le défilement
        WebElement quizLink = wait.until(ExpectedConditions.visibilityOfElementLocated(quizLinkSelector));

        // Supprimer l'attribut target de l'élément pour que le clique n'ouvre pas une nouvelle fenetre 
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('target');", quizLink);
        
        // Attendre que l'élément soit cliquable
        wait.until(ExpectedConditions.elementToBeClickable(quizLink));
        
        // Cliquer sur l'élément
        quizLink.click();
        
        return new QuizPage(driver);
    }
}