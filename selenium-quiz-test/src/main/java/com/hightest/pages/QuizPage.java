package com.hightest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


public class QuizPage {
    
    private WebDriver driver;
    
    // Locators
    private final By questionContainerSelector = By.cssSelector("body div#main_content form[method='post']");
    private final By correctAnswerSelector = By.cssSelector("input.correct-answer");
    private final By submitButtonSelector = By.cssSelector("#submit");
    
    public QuizPage(WebDriver driver) {
        this.driver = driver;
    }
    
public void completeQuiz() {
    // Créer une instance de WebDriverWait avec un délai d'attente de 45 secondes
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
    
    // Attendre que le formulaire du quiz soit présent
    WebElement questionContainer = wait.until(ExpectedConditions.presenceOfElementLocated(questionContainerSelector));
    
    // Liste des XPath des bonnes réponses
    String[] correctAnswerXpaths = {
        "//*[@id='main_content']/form/div[1]/input[1]",
        "//*[@id='main_content']/form/div[2]/input[1]",
        "//*[@id='main_content']/form/div[3]/input[1]",
        "//*[@id='main_content']/form/div[4]/input[1]",
        "//*[@id='main_content']/form/div[5]/input[1]",
        "//*[@id='main_content']/form/div[6]/input[1]",
        "//*[@id='main_content']/form/div[7]/input[1]",
        "//*[@id='main_content']/form/div[8]/input[1]",
        "//*[@id='main_content']/form/div[9]/input[1]",
        "//*[@id='main_content']/form/div[10]/input[1]",
        "//*[@id='main_content']/form/div[11]/input[1]",
        "//*[@id='main_content']/form/div[12]/input[1]",
        "//*[@id='main_content']/form/div[13]/input[1]",
        "//*[@id='main_content']/form/div[14]/input[1]",
        "//*[@id='main_content']/form/div[15]/input[1]",
        "//*[@id='main_content']/form/div[16]/input[1]",
        "//*[@id='main_content']/form/div[17]/input[1]",
        "//*[@id='main_content']/form/div[18]/input[1]",
        "//*[@id='main_content']/form/div[19]/input[1]",
        "//*[@id='main_content']/form/div[20]/input[1]",
    };
    
    // Parcourir la liste des XPath et cliquer sur chaque bonne réponse
    for (String xpath : correctAnswerXpaths) {
        WebElement answer = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        
        // Scroller vers l'élément si nécessaire (optionnel)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", answer);
        
        // Cliquer sur l'élément
        answer.click();

        // Attendre 5 secondes après le clic
        try {
            Thread.sleep(3000); // Pause de 3 secondes
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaurer l'état d'interruption
        }
    }
}
    
    public ResultPage submitQuiz() {
        // Créer une instance de WebDriverWait avec un délai d'attente de 45 secondes
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
        
        // Attendre que le bouton de soumission soit cliquable
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(submitButtonSelector));
        
        // Faire défiler jusqu'au bouton de soumission si nécessaire
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        
        // Cliquer sur le bouton de soumission
        submitButton.click();
        
        return new ResultPage(driver);
    }
}