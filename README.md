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
## Contribuer

Les contributions sont les bienvenues ! Voici comment vous pouvez aider :

1. Forkez ce dépôt.
2. Créez une branche pour vos modifications :
   ```bash
   git checkout -b feature/ma-modification
## Tests

Pour exécuter les tests unitaires et vérifier la couverture, utilisez Maven :

```bash
mvn clean verify

## Sécurité

La sécurité de ce projet est prise très au sérieux. Si vous découvrez une vulnérabilité dans ce projet, veuillez suivre ces étapes pour nous contacter de manière responsable :

1. Ne signalez pas la vulnérabilité publiquement.
2. Contactez l'équipe de sécurité via l'email suivant : [adresse-email@example.com].
3. Fournissez une description détaillée de la vulnérabilité, y compris les étapes pour la reproduire et toute information supplémentaire qui pourrait nous aider à résoudre le problème rapidement.

Nous nous efforçons de répondre aux rapports de sécurité dans les plus brefs délais et de publier les mises à jour nécessaires pour corriger les problèmes identifiés.

Pour plus de détails, veuillez consulter le fichier `SECURITY.md` dans ce dépôt.

## Licence

Ce projet est sous licence **GNU GPL v3**. Vous pouvez le redistribuer et/ou le modifier selon les termes de la licence GNU General Public License publiée par la Free Software Foundation, soit la version 3 de la licence, soit (à votre choix) une version ultérieure.

Ce programme est distribué dans l'espoir qu'il sera utile, mais **SANS AUCUNE GARANTIE**, sans même la garantie implicite de COMMERCIALISATION ou d'ADÉQUATION À UN BUT PARTICULIER.

Pour plus de détails, consultez le fichier [LICENSE](./LICENSE) dans ce dépôt.

## Auteurs

- **Bembli Wiem** - Étudiante en ingénierie logicielle. Ce projet a été industrialisé à partir d'un prototype étudiant.

N'hésitez pas à consulter les historiques de commit pour plus d'informations sur les contributeurs du projet.



