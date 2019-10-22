## Mercado Libre Challenge - Sistema Solar

La aplicacion esta desarrollada con spring boot, con gradle como gestor de dependencias y build.

Despues de clonear, ejecutar `./gradlew build bootRun`

Automaticamente va a bajar el gradle wrapper, va a builder y ejecutar la aplicacion.

Una vez inicializada se puede ver la documentacion de la API con swagger en:
 
`http://localhost:8080/swagger-ui.html`

Desde alli se puede invocar y testear la API `http://localhost:8080/swagger-ui.html#/Clima`

Adicional mente la aplicacion se encuentra deployada en PivotalCloudFoundry, se puede ver la documentacion y testear en :

`https://meli-challenge.cfapps.io/swagger-ui.html` ([link](https://meli-challenge.cfapps.io/swagger-ui.html))

#### Algunas anotaciones de algebra....
##### Velocidad Angular

La velocidad angular (ω) es el arco recorrido (θ), expresado en radianes por unidad de tiempo. 
Se expresa en radianes/segundos (rad/s)
ω = 2 x 3.1416 x frec

Posicion en funcion de la velocidad angular:

``p = [Xo + r cos(ωt)] X + [Yo + r sen(ωt)] Y``

Convertir grados a radianes: grados x 3.1416/180 = radianes

``1° = 0.01745rad``

La libreria Math tiene una funcion para convertir: ``public static double toRadians(double angdeg) ``
