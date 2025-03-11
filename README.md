# Test Automatisé du Quiz Hightest

Ce projet est un test automatisé avec Selenium WebDriver en Java pour vérifier qu'un quiz correctement rempli sur le site Hightest.nc génère un e-mail indiquant 100% de bonnes réponses.

## Prérequis

- Java JDK 11 ou supérieur
- Maven
- Connexion Internet

## Structure du Projet

Le projet suit le design pattern Page Object Model (POM) pour une meilleure maintenabilité et réutilisabilité du code. Voici les principaux éléments :

- **config** : Configuration du navigateur
- **pages** : Objets représentant chaque page du parcours utilisateur
- **utils** : Utilitaires pour les attentes explicites
- **constants** : Constantes utilisées dans les tests
- **tests** : Classes de test TestNG

## Exécution des Tests

Pour exécuter les tests, utilisez la commande suivante :

```bash
mvn clean test
```

## Scénario de Test

Le test automatisé suit le scénario suivant :

1. Se rendre sur https://hightest.nc/
2. Cliquer sur "Toolbox"
3. Cliquer sur le lien vers le quiz ISTQB Agile
4. Réaliser le quiz en cliquant sur les bonnes réponses
5. Valider le quiz en cliquant sur le bouton "Terminé !"
6. Entrer l'adresse e-mail Yopmail (hightestflores@yopmail.com)
7. Valider le formulaire en cliquant sur le bouton "OK"
8. Se rendre sur Yopmail
9. Saisir l'adresse Yopmail
10. Consulter les mails
11. Vérifier que le mail reçu indique bien 100% de bonnes réponses

## Notes Techniques

- Le projet utilise WebDriverManager pour gérer automatiquement les drivers de navigateur
- TestNG est utilisé comme framework de test
- Les attentes explicites sont utilisées pour améliorer la stabilité des tests
- Le projet est configuré pour fonctionner avec Google Chrome
# Selenium-quiz-test-java
