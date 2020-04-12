

<h1>Les étapes de la mise à jour :</h1>

<p><em>-Sélection des fonctionnalités qui vont être appliquées dans la nouvelle version:</em>
Nous allons ajoutés 3 nouvelles fonctionnalités par mise a jour.</p>

<p><em>-Calendrier pour le lancement de la mise à jour:</em>
Les mises à jour apportant de nouvelles fonctionnalités au jeu auront lieu deux fois par mois le 5 et 20 du mois afin d'avoir un jeu en perpétuelle évolution.</p>

<p><em>-déroulement de la mise à jour :</em>
La mise à jour sera disponible pour les utilisateurs a 23h59 .La mise à jour se fera automatiquement pour les utilisateurs ayant fait ce choix. Pour les autres, ils auront une notification qui prendra la forme d'une alerte au sein de l’application.</p>

<h2>Comment faire pour éviter la coupure de service ?</h2>
<p>Afin d'éviter la coupure de service pendant la mise à jour du jeu nous allons utiliser <em>l'API Monitoring</em>.A l'aide de l'API monitoring nous allons tester les fonctions de notre API.
Cela permettra de réduire considérablement les indisponibilités en détectant rapidement les défaillances. La détection précoce des problèmes d'API va empêcher les problèmes d'API d'affecter les utilisateurs de l'API.</p>

<h3>Que faire des parties en cours ?</h3>
<p>La mise à jour n'aura pas d'impact sur les parties en cours, car il ne sera pas possible de lancer une partie pendant la mise à jour.</p>

<h4>Comment changer l'API pour que les joueurs utilisant une vieille version du client web puissent toujours jouer ? (rétrocompatibilité)</h4>
<p>Afin que notre API puisse répondre a la rétrocompatibilité nous allons utiliser un en-tête de demande pour différencier les versions. Nous allons implémenter une liste de versions dans la configuration .Dans cette liste nous allons spécifier les versions retro-compatibles afin que les joueurs utilisant une version antérieur du client web puissent continuer à jouer.
Quand le client va ajouter un en-tête demande dans ses requêtes, les changements de rétrocompatibilité des versions seront appliqués.
Lorsqu'un utilisateur avec une version qui n'est pas la dernière version voudra jouer, seuls les fichiers de changement compatible à la version de l'utilisateur seront appliqués. </p>

<h5>Comment avertir les joueurs de la nouveauté une unique fois ?</h5>

<p>L'avertissement des joueurs concernant la mise à jour ce fera a l'aide d'une notification qui prendra la forme d'une alerte au sein de l’application. Cette notification sera composée de trois choix :
Installer maintenant, installer plus tard, installer la nuit (entre2h et 5h). Le message possèdera des informations sur la mise à jour du logicielle c'est-à-dire la version de la mise à jour ou encore les fonctionnalités qui sont apportés par la mise à jour.

 Cela permettra d'évitez les mails indésirables ou encore des messages pendant une session de jeu.</p>

