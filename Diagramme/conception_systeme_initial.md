---

#### "Conception du système réalisé par MAZA Amine, LEDOUX Slayde, ZORGUI Jeremy, TOURE Goulou, MESSAOUDI Naim"

---

## 1- Diagrammes 
### 1.1 Diagrammes cas d'utilisation 

<a href="https://zupimages.net/viewer.php?id=20/15/q0ar.png"><img src="https://zupimages.net/up/20/15/q0ar.png" alt="" /></a>

##### *Figure 1  - Diagramme de Cas d'utilisation* 

Au début, nous avons un utilisateur lambda qui sera le joueur. Une fois que cette personne accède au site internet du jeu, il faut qu’il puisse être identifié. Pour cela, il doit saisir un pseudo, une adresse mail. 

Une fois cette première étape, cet utilisateur doit pouvoir rejoindre une partie ou créer une partie. Les règles du jeu imposent aux joueurs une limite de 50 joueurs par partie.
Après la création de la partie, elle peut démarrer que s’il y a au minimum 2 joueurs dans la partie et que les placements des ranges soient distribués. Cela fait donc par la relation « include », puisque la distribution ne peut se faire sans que la partie ne soit créé.
Ensuite, viens la question de se déplacer. Ainsi un joueur se déplace sur la map à l’aide des touches de son claviers, lorsqu’il y a un obstacle sur la carte ou un adversaire, c’est à ce moment où l’utilisateur doit utiliser le laser. 
Pour que la partie soit terminé, le joueur doit tuer des adversaires jusqu’au dernier d’où la relation « include ». 

---

### 1.2 Problèmes 

Est-ce que les joueurs intéragiront en temps réel, ou au tour par tour et pourquoi ?

Comment sera stocké l'état (toutes les données permettant de représenter le jeu) ?

Comment seront stockées les informations confidentielles des joueurs (emails, etc.)

Comment gérer plusieurs parties en même temps ?

Comment les joueurs s'authentifieront et sauvegardent leur progression ?

Comment gérer une charge imprévue ? (100x plus de joueurs que prévu par ex)

Comment recupérer la sauvegarde s'il y a un bug sur le serveur ? 

Comment verifier si le déplacement est possible ? 

Comment verifier si le joueur peut utiliser son laser ? 

---

### 1.3 Spécification des besoins

#### 1.3.1 S’identifier /créer

Toutes les données de l'utilisateur seront sur une base de donnée embarquée sur notre site web.
Vérifier si l’utilisateur est déjà présent sur la base de données. Si l’utilisateur a déjà été identifié auparavant, il peut passer à l’étape suivant. Sinon, l’utilisateur doit s’inscrire avant de commencer à jouer en entrant son pseudo et son adresse mail.

#### 1.3.2 Initialiser Partie 

Vérifie si une partie est ouverte et s’il dispose suffisamment de place pour pouvoir la rejoindre sinon il doit créer par lui-même une partie qui sera limitée à 50 joueurs. 
Au moment de la création d’une partie, il faudra l’a nommé et elle sera active pendant 2 minutes. La partie se lancera à partir du moment où il y aura 2 joueurs sur la partie.
Il est important de vérifier aussi le nombre de parties ouvertes pour ne pas submerger le serveur, il faudra limiter le nombre de création de partie (par exemple 10 parties) afin de gérer une charge imprévue. Sinon, le système dirigera l’utilisateur vers la création d’une nouvelle partie ou de rejoindre

#### 1.3.3 Commencer à jouer

Au départ, tous les joueurs disposent d’un affichage de son statut (mort ou vivant) et les placements des joueurs sont définis aléatoirement dans la map. Puis, le joueur peut se déplacer dans la map. Le système interpelle l’utilisateur qu’il y a un obstacle et lui demande s’il veut utiliser le laser pour pouvoir se déplacer dans la map. Si ce déplacement est invalide (Hors de la map, un obstacle gêne le passage), nous demandons à l'utilisateur de sélectionner à nouveau une nouvelle case pour rejouer. Sinon, si le choix de case est valide, il est joué.
 En l’apparition d’adversaire, l’utilisateur a le choix de se déplacer et d’utiliser son laser, nous vérifions, si celui-ci est bien à une portée suffisante pour pouvoir neutraliser son adversaire. Sinon, le système bloquera le laser, l’utilisateur devra se déplacer pour l’atteindre.

#### 1.3.4 Sauvegarde

Lors du lancement de la partie, la sauvegarde se fait automatiquement grâce au système de sauvegarde. Celles-ci sont récupérables à tout moment grâce au système de sauvegarde.
S'il n'y a aucun gagnant, le système indique au joueur qu'il est possible de sauvegarder le jeu par extinction.


#### 1.3.5 Vérifier Déplacement / coup du laser

Cette fonctionnalité importante permettra de déterminer si l’utilisation du laser demandé par le joueur est valide. Il faut vérifier que la case d'arrivée est valide (Soit vide, soit occupée par un range adverse). Puis, il faut aussi vérifier qu'aucun obstacle ne gêne le déplacement. 

---

## 2 Diagrammes de séquence système

### 2.1 Initialiser Partie / Identifier 

<a href="https://zupimages.net/viewer.php?id=20/15/ltkg.png"><img src="https://zupimages.net/up/20/15/ltkg.png" alt="" /></a>

##### *Figure 2  - Diagramme de séquence Système (Initialiser Partie / Identifier)* Figure 1 

Nous démarrons le jeu, le système nous demande de nous identifier, si l’utilisateur est enregistré (retourne true ou false). Si True, nous choisissons qu’il puisse accéder à la création de la partie ou rejoindre une partie. Si c’est false, il devra s’inscrire. 
Le système demande à l’utilisateur de choisir entre rejoindre, charger la partie avec le Système de Sauvegarde. S'il n'y a pas de partie disponible, nous demandons d'effectuer la création d'une nouvelle partie, le timer se lance.
 
### 2.2 Commencer à jouer

<a href="https://zupimages.net/viewer.php?id=20/15/ecgo.png"><img src="https://zupimages.net/up/20/15/ecgo.png" alt="" /></a>

##### *Figure 3  - Diagramme de séquence Système (Commencer à jouer)* 
 
Une fois que le joueur a cliqué sur le lancement de la partie, cela va déclencher le timer de la partie puis il va par la suite distribuer les maps et le placement des joueurs. Les spécifications « Vérifier coup » et « déplacer Range » sont dans une boucle avec une condition « coup ne soit pas validé ».

Le jeu débutera quand les joueurs seront tous présents sur la map et se jouera en temps réel, ainsi les seules restrictions seront faites par le système, il y aura la vérification continuelle du système pour se deplacer et pour utiliser le lazer.

### 2.3 Distribuer les placements des ranges et la map

<a href="https://zupimages.net/viewer.php?id=20/15/b49d.png"><img src="https://zupimages.net/up/20/15/b49d.png" alt="" /></a>

##### *Figure 4  - Diagramme de séquence Système (DistribuerPlacementsMap)* 

Une fois que le système a mis en place la map, avant de placer les ranges le système va vérifier que les cases sont vides afin de pouvoir y placer un Range et des obstacles.


### 2.4 Sauvegarder 

<a href="https://zupimages.net/viewer.php?id=20/15/jwy1.png"><img src="https://zupimages.net/up/20/15/jwy1.png" alt="" /></a>

##### *Figure 5  - Diagramme de séquence Système (Sauvegarder)* 
 
Lorsque nous démarrons une partie, il système va lancer le système de sauvegarde, celle-ci sera la première sauvegarde et la sauvegarde se fera tout le long du jeu automatiquement.
Le joueur à la possibilité de récupérer sa sauvegarde, par exemple en cas de bug.


### 2.5 Vérifier Déplacement / coup du laser

<a href="https://zupimages.net/viewer.php?id=20/15/n9w4.png"><img src="https://zupimages.net/up/20/15/n9w4.png" alt="" /></a>

##### *Figure 6  - Diagramme de séquence Système (VerifierDeplacementCoupDeplacement)*  
 
<a href="https://zupimages.net/viewer.php?id=20/15/3s15.png"><img src="https://zupimages.net/up/20/15/3s15.png" alt="" /></a> 

##### *Figure 7  - Diagramme de séquence Système (VerifierDeplacementCoupduLaser)* 

Le système va vérifier si le déplacement est possible, en cas d’obstacle ou d’adversaire, le joueur va devoir utiliser son laser et donc faire appel au système.

En l’apparition d’adversaire, l’utilisateur a le choix de se déplacer et d’utiliser son laser, nous vérifions, si celui-ci est bien à une portée suffisante pour pouvoir neutraliser son adversaire. Sinon, le système bloquera le laser, l’utilisateur devra se déplacer pour l’atteindre.

---
## Conclusion 


Pour conclure, nous avons eu une longue rèfléxion au sujet des problèmes énoncés au début du document et les solutions ont bien été représentés sur les différents diagrammes présentés dans le document, il ne nous reste plus qu’à continuer à coder le jeu à l’aide d’Eclipse. 
Le jeu sera un jeu en temps réel, en effet le « temps réel » est opposé au « tour par tour » utilisé aux échecs par exemple où, chaque joueur, l’un après l’autre,
modifie l’état du jeu à sa convenance. Les raisons pour lesquels, on a choisit de réaliser  un jeu en « temps réel », chacun est libre de réaliser
une action à n’importe quel instant. Par conséquent, le joueur doit être capable d’adapter rapidement sa stratégie à un environnement hautement dynamique. C'est un choix, où nous voulons suivre la tendance des jeux de nos jours.

Pour la suite le cours et la Java Doc et JS nous seront utile, ainsi que les tutoriels sur internet pour nous permettre de finaliser et rendre réalisable ce projet.
