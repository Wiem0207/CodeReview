# Password Strength Calculator

![License: GNU GPL v3](https://img.shields.io/badge/License-GNU%20GPL%20v3-blue.svg)  
![Build Status](https://img.shields.io/github/actions/workflow/status/username/password-strength-calculator/ci.yml?branch=main)  
![Coverage](https://img.shields.io/badge/Coverage-90%25-green)

---

## Description

**Password Strength Calculator** est une bibliothèque Java qui analyse et évalue la robustesse des mots de passe. Conçu pour les développeurs et les utilisateurs soucieux de la sécurité, cet outil fournit des recommandations pour améliorer la force des mots de passe.  
Il est publié sous la licence **GNU GPL v3**, empêchant son utilisation commerciale sans autorisation.

---

## Fonctionnalités

- Évaluation de la robustesse des mots de passe.
- Calcul de l'entropie des mots de passe.
- Vérification contre les mots de passe courants et les mauvaises pratiques.
- Test de performance pour la méthode `ComputeMD5`.

---

## Installation

### Prérequis
- **Java 11** ou version ultérieure.
- **Maven** installé pour la gestion des dépendances.

### Installation avec Maven
Ajoutez cette dépendance dans votre fichier `pom.xml` :

```xml
<dependency>
    <groupId>com.company</groupId>
    <artifactId>PasswordStrengthCalculator</artifactId>
    <version>1.0.0</version>
</dependency>
### Contribuer

Les contributions sont les bienvenues ! Voici comment vous pouvez aider :

1. Forkez ce dépôt.
2. Créez une branche pour vos modifications :
   ```bash
   git checkout -b feature/ma-modification
