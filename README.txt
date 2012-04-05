Projet TwitMiner - Yann Battista, Eric Gillet

Programme permettant l'extraction de dépendances fonctionnelles ayant une confiance supérieure à un seuil, à partir de motifs fréquents extraits par une implémentation de l'algorithme Apriori. Les données d'origine sont les Top Trends par pays récupérées avec l'API Twitter grâce à la librairie Twitter4j.
Les différentes phases du projet sont représentées par des classes, exécutées (instanciées) dans l'ordre par la classe "Maitresse" App.java.
Phase 0 : TTGetter
Phase 1 : ExtractionTT
Phase 2 : ExtractionDF
Phase 3 : Nettoyage
Phase 4 : MainPanel

La récupération des Top Trends Twitter

La classe Motif représente un motif fréquent (liste de int, selon la représentation pour l'algorithme apriori), ainsi que le support du motif. Cette classe implémente l'interface List<String>, afin de faciliter son utilisation dans les autres classes.
Une classe Serializer est présente pour faciliter la serialisation/déserialisation du dictionnaire (Mot-clé - id pour apriori) représenté par une List<String>, le rang du mot-clé étant l'id pour apriori; et la serialisation/déserialisation des dépendances fonctionnelles (représentées par une List<List<Motif>>).

La license de ce projet est disponible dans le fichier annexe LICENSE.txt
