package com.hightest.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EmailUtils {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    public EmailUtils(WebDriver driver, int timeoutInSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }
    
    /**
     * Vérifie si un email avec un sujet spécifique est présent dans la boîte de réception
     * 
     * @param emailSubject Le sujet de l'email à rechercher
     * @param iframeSelector Le sélecteur pour l'iframe contenant la liste des emails
     * @return true si l'email est trouvé, sinon false
     */
    public boolean isEmailWithSubjectPresent(String emailSubject, By iframeSelector) {
        try {
            // Attendre que l'iframe soit disponible et y accéder
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeSelector));
            
            // Construire un sélecteur XPath qui recherche le sujet spécifique dans les éléments d'email
            By subjectSelector = By.xpath("//*[contains(text(), '" + emailSubject + "')]");
            
            // Attendre que l'élément avec le sujet soit visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(subjectSelector));
            
            // Revenir au contexte principal
            driver.switchTo().defaultContent();
            
            return true;
        } catch (Exception e) {
            // Revenir au contexte principal en cas d'exception
            driver.switchTo().defaultContent();
            return false;
        }
    }
    
    /**
     * Ouvre l'email le plus récent et vérifie son contenu
     * 
     * @param contentText Le texte à rechercher dans le contenu de l'email
     * @param mailContentIframeSelector Le sélecteur pour l'iframe contenant le contenu de l'email
     * @return true si le texte est trouvé dans le contenu de l'email, sinon false
     */
    public boolean verifyEmailContent(String contentText, By mailContentIframeSelector) {
        try {
            // Attendre que l'iframe du contenu de l'email soit disponible et y accéder
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(mailContentIframeSelector));
            
            // Créer un sélecteur pour trouver le texte spécifique dans le contenu de l'email
            By contentSelector = By.xpath("//*[contains(text(), '" + contentText + "')]");
            
            // Attendre que l'élément avec le contenu recherché soit visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(contentSelector));
            
            // Revenir au contexte principal
            driver.switchTo().defaultContent();
            
            return true;
        } catch (Exception e) {
            // Revenir au contexte principal en cas d'exception
            driver.switchTo().defaultContent();
            return false;
        }
    }
    
    /**
     * Rafraîchit la boîte de réception pour vérifier les nouveaux emails
     * 
     * @param refreshButtonSelector Le sélecteur pour le bouton de rafraîchissement
     */
    public void refreshInbox(By refreshButtonSelector) {
        WebElement refreshButton = wait.until(ExpectedConditions.elementToBeClickable(refreshButtonSelector));
        refreshButton.click();
        
        // Ajouter un petit délai pour permettre le rafraîchissement complet
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Attend l'arrivée d'un nouvel email avec le nombre spécifié de tentatives
     * 
     * @param refreshButtonSelector Le sélecteur pour le bouton de rafraîchissement
     * @param emailCountSelector Le sélecteur pour le compteur d'emails
     * @param expectedCount Le nombre attendu d'emails
     * @param maxAttempts Le nombre maximum de tentatives
     * @return true si le nombre attendu d'emails est atteint, sinon false
     */
    public boolean waitForNewEmail(By refreshButtonSelector, By emailCountSelector, int expectedCount, int maxAttempts) {
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            // Rafraîchir la boîte de réception
            refreshInbox(refreshButtonSelector);
            
            try {
                // Vérifier le nombre d'emails
                WebElement countElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailCountSelector));
                String countText = countElement.getText();
                int currentCount = extractNumberFromText(countText);
                
                if (currentCount >= expectedCount) {
                    return true;
                }
            } catch (Exception e) {
                // Continuer à essayer si une exception se produit
            }
            
            // Attendre avant la prochaine tentative
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        return false;
    }
    
    /**
     * Extrait un nombre d'une chaîne de texte
     * Utile pour analyser des compteurs comme "5 messages" pour obtenir le nombre 5
     * 
     * @param text Le texte contenant un nombre
     * @return Le nombre extrait ou 0 s'il n'y a pas de nombre
     */
    private int extractNumberFromText(String text) {
        try {
            return Integer.parseInt(text.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
