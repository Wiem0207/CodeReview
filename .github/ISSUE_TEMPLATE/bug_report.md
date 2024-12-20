
## 🐛 Bug Report

### Description du bug

La page de connexion affiche une erreur lorsqu'un utilisateur valide entre son identifiant et son mot de passe.

---

### Étapes pour reproduire le bug

1. Allez à la page d'accueil : `http://localhost:3000`.
2. Cliquez sur "Se connecter".
3. Saisissez les informations suivantes :
   - Identifiant : `user@example.com`
   - Mot de passe : `password123`
4. Cliquez sur le bouton "Connexion".
5. Une erreur s'affiche : `TypeError: Cannot read property 'token' of undefined`.

---

### Comportement attendu

L'utilisateur devrait être redirigé vers la page d'accueil après une connexion réussie.

---

### Capture d'écran

![Erreur de connexion](https://example.com/screenshots/login-error.png)

---

### Environnement

- **Système d'exploitation** : Windows 10
- **Navigateur** : Google Chrome, version 118
- **Version du projet** : v1.0.0
- **Autres détails** : Le bug semble survenir uniquement sur les comptes créés récemment.

---

### Informations supplémentaires

Le problème semble être lié à la gestion des réponses de l'API. Une vérification des données retournées par l'API dans la fonction `loginUser` pourrait être utile. Voici le message d'erreur complet :

```
TypeError: Cannot read property 'token' of undefined
    at loginUser (Auth.js:42)
    at handleSubmit (LoginPage.js:26)