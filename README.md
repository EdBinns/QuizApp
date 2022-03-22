# QuizApp
Reto que corresponde al mes de marzo de la serie de desafios que esta proponiendo  [Mouredev](https://github.com/mouredev) en su proyecto de retos de creación mensual de aplicaciones de la comunidad MoureDev en base a requisitos, llamado [App Monthly Challenge 2022](https://github.com/mouredev/Monthly-App-Challenge-2022).

### Requisitos para la app de marzo
**CUESTIONARIO CONTRARRELOJ:** Crea una "Quiz app" contrareloj con ranking. 

Requisitos:

* El diseño es de libre elección.
* La temática de las preguntas del cuestionario será de libre elección. Puede estar bien seguir un mismo tema (por ejemplo, preguntas sobre el universo "Harry Potter").
* En la pantalla inicial podrás comenzar a jugar o consultar el ranking de puntuaciones.
* El juego consistirá en 10 preguntas aleatorias con 3 respuestas y sólo una correcta. Recomendable crear más de 10 preguntas para que no siempre salgan las mismas. Cada vez que se responde a una pregunta, se pasará a la siguiente.
* Disponemos de 30 segundos para responder cada pregunta. El contador deberá aparecer en la pantalla, y si llega a 0 se tomará como respuesta incorrecta y se pasará a la siguiente pregunta.
* Al marcar una respuesta o finalizar el tiempo, se mostrará si se ha acertado o no la pregunta, dando feedback sobre cuál sería la respuesta correcta y navegando al cabo de un par de segundos a la siguiente pantalla.
* Sistema de puntuación:
	* Contador a 0 o respuesta incorrecta = 0 puntos.
	* Contador mayor 0 y respuesta correcta = [segundos restantes] puntos. Ej: Si quedaban 9 segundos para finalizar la cuenta atrás y se acierta la pregunta, se asignan 9 puntos.
* Una vez finalizada la pregunta número 10 se mostrará en una nueva pantalla la puntuación final y se deberá introducir un nombre para guardarla de forma persistente (aunque cerremos la app). Hecho estos se mostrará la pantalla de ranking.
* La pantalla de ranking muestra ordenados de mayor a menor los 10 mejores resultados y el nombre guardado. Desde esta pantalla siempre se podrá navegar a la pantalla inicial.


## Solución del reto
 
Main View
 -------------
 ![](https://github.com/EdBinns/QuizApp/blob/main/images%20app/photo_2022-03-21_15-24-04.jpg)
 
 
Quiz View
 -------------
  ![](https://github.com/EdBinns/QuizApp/blob/main/images%20app/photo_2022-03-21_15-24-08.jpg)
  
  
 Score View
 -------------
  ![](https://github.com/EdBinns/QuizApp/blob/main/images%20app/photo_2022-03-21_15-24-13.jpg)
  
  
  Results View
 -------------
  ![](https://github.com/EdBinns/QuizApp/blob/main/images%20app/photo_2022-03-21_15-24-10.jpg)
  

Herramientas y  tecnologías
 -------------
  - [Kotlin](https://kotlinlang.org/ "Kotlin") 
  - [Room](https://developer.android.com/jetpack/androidx/releases/room "Retrofit") 
  - [Dagger Hilt](https://dagger.dev/hilt/ "Dagger hilt")
  - [Jetpack Compose](https://developer.android.com/jetpack/compose) 


Contacto
-------------

 [![LinkedIn](https://img.shields.io/badge/LinkedIn-Eduardo_Binns-0077B5?style=for-the-badge&logo=linkedin&logoColor=white&labelColor=101010)](https://www.linkedin.com/in/eduar-binns)
