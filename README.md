### fines_saveurs_fo
  # [fines_saveurs_bo](https://github.com/Truong-Terence/fines_saveurs_bo)

## BDD credentials :
- Copier le fichier src/main/resources/appToComplete.properties.
- Renommer appToComplete.properties et copier le contrnu dans application.properties.
- Remplir app.properties avec vos propres credentials.

## Front Office (Utilisateurs)  => (Spring Boot, MySQL)
- s'inscrire
- se connecter
- se déconnecter
- accéder au détail de son profil
- visualiser les produits
- rechercher un produit
- passer une commande via un tunnel de paiement
- voir ses commandes passées
- éditer les infos de son compte (photo, infos perso etc….)

- contacter un administrateur via un formulaire :
- Le choix de l'administrateur à contacter sera fera via une API Rest qui renvoie tous les administrateurs. (API Rest => Côté Back Office). La récupération des informations sera fera en Javascript dans le HTML du    FrontOffice
