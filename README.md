# Rockpedia : l'API qui fait du bruit

[Swagger](/swagger-ui.html)

Voici les instructions à suivre pour être prêt à coder le plus rapidemment possible.

 		<u>1. Prérequis</u>
 		<u>2. Démarrer l'application</u>
 		<u>3. Lancer un build Jenkins</u>
 		<u>4. Analyse SonarQube</u>
 		<u>5. Surveillance avec Spring Actuator, Prometheus et Spring Boot Admin</u>


## 1. Prérequis

Vous aurez besoin des outils suivants :

- Maven (v3.6.3 ou +)
- JDK8
- Git
- un IDE (nous conseillons Intellij si possible)
- un compte Github (pour pouvoir commit)
- un compte sur la plateforme Jenkins

## 2. Démarrer

- Clonez le dépot :
> `git clone https://github.com/ALudwig57/Rockpedia`

- Build avec maven
>`mvn build`

- Executez les tests 
>`mvn test`

- Build un package
> `mvn package`

- Démarrez l'application
>`mvn spring-boot:run`

- Rendez vous à l'adresse [http://localhost:8080/](http://localhost:8080) avec un navigateur 

Vous devriez voir apparaître cette page :

![](src/main/resources/public/images/start.png)

## 3. Lancer un build Jenkins

- Rendez vous à l'adresse [https://jenkins.m2gi.win/](https://jenkins.m2gi.win/)

- Connectez vous. Si tout se passe bien, vous devriez arriver sur un écran comme celui-ci :

  ![](src/main/resources/public/images/jenkinsStart.png)

- Cliquez sur le nom du projet
- Cliquez sur lancer un build
- Vous devriez voir que quelque chose a démarré. Cliquez sur le #nombre à côté du point clignotant.
- Cliquez sur Console Output pour voir le déroulement du build
- Une fois le build terminé avec succès, passez à l'étape suivante

  ## 4. Analyse SonarQube

- Rendez vous à l'adresse [https://sonarqube.m2gi.win/dashboard?id=com.example%3Arockpedia](https://sonarqube.m2gi.win/dashboard?id=com.example%3Arockpedia)
- Vous arrivez sur le tableau de bord de l'application

![](src/main/resources/public/images/sonarqube.png)

- On peut distinguer les bugs, les failles de sécurités, la dette technique, la couverture de code...

  ## 5. Surveillance avec Spring Actuator, Prometheus et Spring Boot Admin

Spring actuator est déjà activé. Cela permet de voir pas mal d'infos relatives à l'application à l'adresse [http://localhost:8080/actuator](http://localhost:8080/actuator).

![](src/main/resources/public/images/actuator.png) 

J'ai mis en évidence les plus utiles :

- health : permet de savoir si l'application fonctionne (UP) ou pas

  ![](src/main/resources/public/images/health.png)

- info : informations générales sur l'application

  ![](src/main/resources/public/images/infos.png)

- prometheus : beaucoup d'informations pas très lisibles, surtout utilisé par des applications externes comme graphana ou Spring Boot Admin, que nous allons voir dans un instant.

![](src/main/resources/public/images/prometheus.png)

- metrics : données de surveillance, accessibles à l'adresse [http://localhost:8080/actuator/metrics/${DATA}](http://localhost:8080/actuator/metrics) (avec ${DATA} le nom de ce que vous voulez inspecter)

![](src/main/resources/public/images/metrics.png)

### Spring Boot Admin

- Clonez le dépot :
> `git clone https://github.com/SmileEdge/SpringBootAdmin.git`

- Build avec maven
>`mvn build`

- Démarrez l'application
>`mvn spring-boot:run`

- Rendez vous à l'adresse [http://localhost:8081/](http://localhost:8081) avec un navigateur, connectez-vous avec le nom d'utilisateur *admin* et le mot de passe *admin* et selectionnez l'application *spring-boot-application* en cliquant une fois dessus, puis sur le  numéro sous l'adresse [http://localhost:8080/](http://localhost:8080).

![](src/main/resources/public/images/sba.png)

Sur la page d'accueil, on peut voir les mêmes infos que par la route info de Spring Actuator, le nombre de threads de l'application, la mémoire utilisée, le nombre de passages du garbage collector...

Aperçus \> Metriques permet d'ajouter des métriques visibles sur actuator/metrics.

Aperçus \> Envirronement liste les variables d'environnement de l'application.

Aperçus \> Propriétés de configuration liste d'autres variables d'environement, plus spécifiques à Spring.

Loggers permet de définir la priorité des logs à afficher (DEBUG < FATAL).
