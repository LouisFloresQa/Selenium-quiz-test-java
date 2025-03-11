package com.hightest.tests;

import com.hightest.constants.TestConstants;
import com.hightest.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class QuizTest extends BaseTest {
    
    @Test
    public void testQuizEmailVerification() {
        // Initialiser la page d'accueil
        HomePage homePage = new HomePage(driver);
        
        // Naviguer vers la page d'accueil
        homePage.navigateToHomePage();
        
        // Cliquer sur Toolbox
        ToolboxPage toolboxPage = homePage.clickOnToolboxMenu();

                
        // Attendre simplement un moment avant de cliquer sur le lien du quiz (par exemple 2 secondes)
        try {
            Thread.sleep(4000); // Attendre pendant 4000 millisecondes (4 secondes)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Cliquer sur le lien du quiz
        QuizPage quizPage = toolboxPage.clickOnQuizLink();
        
        // Compléter le quiz en sélectionnant toutes les bonnes réponses
        quizPage.completeQuiz();
        
        // Soumettre le quiz
        ResultPage resultPage = quizPage.submitQuiz();
        
        // Entrer l'adresse e-mail
        resultPage.enterEmail(TestConstants.EMAIL_ADDRESS);
        
        // Soumettre le formulaire
        resultPage.submitEmail();
        
        // Naviguer vers Yopmail
        YopmailPage yopmailPage = resultPage.navigateToYopmail();
        
        // Saisir l'adresse Yopmail
        yopmailPage.enterEmailAddress(TestConstants.EMAIL_ADDRESS);
        
        // Vérifier la boîte de réception
        yopmailPage.checkInbox();
        
        // Vérifier que le mail reçu indique 100% de bonnes réponses
        boolean scoreVerification = yopmailPage.verifyScore();
        
        // Assertion pour vérifier que le score est bien de 100%
        Assert.assertTrue(scoreVerification, "Le score de 100% n'a pas été trouvé dans l'e-mail");
    }
}
