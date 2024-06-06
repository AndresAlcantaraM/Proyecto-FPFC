# Proyecto-FPFC
Proyecto final de Fundamentos de Programación Funcional y Concurrente

## **3.1 Función itinerarios**
### Propósito: 
Encontrar todos los posibles itinerarios (secuencias de vuelos) entre dos aeropuertos dados.
### Explicación:
### ***Función Principal (itinerarios):***

- Recibe una lista de vuelos y una lista de aeropuertos.
- Devuelve una función que, dado un aeropuerto de origen (c1) y un aeropuerto de destino (c2), encuentra todos los itinerarios posibles entre ellos.

### ***Función Auxiliar (buscarItinerarios):***

### Parámetros:

- origen: El aeropuerto actual desde donde estamos buscando vuelos.

- destino: El aeropuerto final al que queremos llegar.

- visitados: Un conjunto de aeropuertos ya visitados para evitar ciclos.

- currentItinerary: El itinerario actual en construcción.

- Si el aeropuerto de origen es igual al destino, significa que hemos encontrado un itinerario completo y lo devolvemos.

- Filtra los vuelos que salen del aeropuerto de origen y que no han sido visitados antes.

Para cada vuelo válido:

- Añade el aeropuerto de origen al conjunto de visitados.

- Añade el vuelo actual al itinerario.

- Llama recursivamente a buscarItinerarios con el aeropuerto de destino del vuelo actual.

### Uso:

Llama a buscarItinerarios con el aeropuerto de origen y destino especificados, un conjunto vacío de visitados y un itinerario vacío.

## **3.2 Función itinerariosTiempo**
### Propósito: 
Encontrar los tres itinerarios que minimizan el tiempo de viaje entre dos aeropuertos dados.

### Explicación:
### Función Principal (itinerariosTiempo):

- Recibe una lista de vuelos y una lista de aeropuertos.
- Devuelve una función que, dado un aeropuerto de origen (c1) y un aeropuerto de destino (c2), encuentra los tres itinerarios más rápidos entre ellos.

### Preparación:

- aeropuertosMap: Crea un mapa para acceder rápidamente a los datos de los aeropuertos por su código.
- obtenerItinerarios: Usa la función itinerarios para obtener todos los itinerarios posibles entre dos aeropuertos.
  
### Calculo del Tiempo Total:

Para cada itinerario encontrado:
- Calcula el tiempo total de viaje en minutos sumando el tiempo de cada vuelo.
- El tiempo de cada vuelo se calcula considerando la diferencia de horarios GMT entre los aeropuertos de origen y destino.

### Selección de los Mejores Itinerarios:

- Ordena los itinerarios por tiempo total de viaje en minutos.
- Selecciona los tres primeros (los más rápidos).
  
### Uso:

- Llama a obtenerItinerarios con el aeropuerto de origen y destino especificados.
- Procesa los itinerarios encontrados para calcular sus tiempos totales y selecciona los tres mejores.

## **3.3 Funcion itinerariosEscalas**

### Propósito:
Hacer escalas muchas veces en un viaje es algo a lo que los viajeros aéreos le huyen. La consulta consiste en hallar al menos tres itinerarios para ir de cod1 a cod2, que hagan el menor número de escalas, sin tener en cuenta el tiempo total de viaje.

### Explicación de las funciones:


#### ***Función Principal (itinerariosEscalas):***
La función recibe dos listas como argumentos: una lista de objetos “Vuelo” y otra de objetos “Aeropuerto”. Retorna una función que toma un aeropuerto de origen y uno de destino para calcular los tres mejores itinerarios basados en el número mínimo de escalas.

#### ***Funcion auxiliar (itinerarios)***

Como ya se ha dicho, esta recibe una lista de vuelos y una lista de aeropuertos. Devuelve una función que, dado un aeropuerto de origen (c1) y un aeropuerto de destino (c2), encuentra todos los itinerarios posibles entre ellos.

#### ***Parámetros (origen: String, destino: String):***
Son los parámetros que la función principal retorna. Estos parámetros se utilizan para especificar el aeropuerto de inicio y el aeropuerto final del itinerario deseado.

#### ***Cuerpo de la función:***

sortBy: Ordena los itinerarios primero por la suma total de las escalas (_.Esc.sum) y luego por el número total de vuelos en el itinerario menos uno (ya que el primer vuelo no se considera una escala).
take(3): Selecciona los tres itinerarios superiores después de ordenarlos.Uso:

#### ***Tecnicas usadas:***

Operaciones: 
.map, (_.Esc), .sum, .length, .take(3)

### Version paralelizada:
Esta versión define dos funciones auxiliares, cuyos procesos ya fueron vistos en la versión secuencial, pero se hace para que sea más ordenado y lograr la paralelización.

#### ***Función obtenerItinerarios:*** 
Obtiene los itinerarios de manera paralela con la función ya definida itinerariosPar.
#### ***Función escalasTotales:***
Calcula el total de escalas para un itinerario dado.

Luego la paralización se logra mediante:
#### ***task:*** 
Crea una tarea paralela para cada itinerario, lo que permite que se procesen simultáneamente.
#### ***join()***: 
Espera a que la tarea paralela se complete y recupera el resultado.
#### ***sortBy y take(3)***: 
Funcionan igual que en la versión no paralela, seleccionando los tres mejores itinerarios basados en el total de escalas.
#### ***map(_._1)***: 
Extrae el itinerario del par (itinerario, total de escalas) para cada resultado.


## **3.4 Función itinerariosAire**

### Propósito:
Para muchos viajeros, el tiempo total de vuelo es un factor crucial en la planificación de sus viajes. Esta consulta se encarga de encontrar al menos tres itinerarios entre un aeropuerto de origen y un destino, priorizando aquellos con el menor tiempo total de vuelo.

### Explicación de las funciones:

#### ***Función Principal (itinerariosAire):***
La función principal `itinerariosAire` recibe dos listas: una lista de objetos `Vuelo` y otra de objetos `Aeropuerto`. Retorna una función que, al ser invocada con un aeropuerto de origen y uno de destino, calcula los tres mejores itinerarios basados en el menor tiempo total de vuelo.

#### ***Funcion auxiliar (itinerarios)***

Esta función, que recibe una lista de vuelos y una lista de aeropuertos, devuelve una función que, dado un aeropuerto de origen (c1) y un aeropuerto de destino (c2), encuentra todos los itinerarios posibles entre ellos.

#### ***Parámetros (origen: String, destino: String):***
Son los parámetros que la función principal retorna. Estos parámetros se utilizan para especificar el aeropuerto de inicio y el aeropuerto final del itinerario deseado.

#### ***Cuerpo de la función:***

**convertirAGMT:**
Convierte la hora local de salida y llegada de un vuelo a minutos en GMT (Tiempo Medio de Greenwich). Toma en cuenta las diferencias horarias entre los aeropuertos de origen y destino.

- **gmtSalida y gmtLlegada:**
  Calculan la diferencia horaria (GMT) del aeropuerto de origen y destino respectivamente, utilizando el código del aeropuerto.

- **horaSalidaGMT y horaLlegadaGMT:**
  Convierte las horas de salida y llegada del vuelo a minutos en GMT, ajustando las diferencias horarias. Si la hora de llegada es anterior a la de salida, ajusta la hora de llegada sumando 1440 minutos (24 horas) para corregir el cálculo.

**calcularTiempoVuelo:**
Calcula el tiempo total de vuelo para un itinerario dado. Suma el tiempo de todos los vuelos en el itinerario, considerando las conversiones a GMT para una comparación precisa.

- **map y sum:**
  Itera sobre cada vuelo en el itinerario, calculando el tiempo de vuelo en minutos utilizando la función `convertirAGMT`, y suma estos tiempos para obtener el tiempo total del itinerario.

**buscarMejoresItinerarios:**
Busca los mejores itinerarios entre dos aeropuertos. Si hay tres o menos itinerarios posibles, los retorna todos. Si hay más de tres, selecciona los tres con el menor tiempo total de vuelo.

- **posiblesItinerarios:**
  Obtiene todos los itinerarios posibles entre el aeropuerto de origen y el aeropuerto de destino utilizando la función auxiliar `itinerarios`.

- **sortBy y take(3):**
  Ordena los itinerarios por el tiempo total de vuelo en minutos, en orden ascendente, y selecciona los tres itinerarios con el menor tiempo total.

### ***Técnicas utilizadas:***

- **Operaciones de mapeo y reducción:**
  - `.map` se utiliza para transformar cada vuelo a su tiempo de vuelo en minutos en GMT.
  - `.sum` se utiliza para sumar estos tiempos de vuelo y obtener el tiempo total del itinerario.
  - `.sortBy` ordena los itinerarios por el tiempo total de vuelo.
  - `.take(3)` selecciona los tres mejores itinerarios basados en el menor tiempo total de vuelo.

### ***Uso:***
Para invocar la función `itinerariosAire`, se deben proporcionar dos listas: una de vuelos y otra de aeropuertos. La función resultante se llama con los códigos de los aeropuertos de origen y destino para obtener los mejores itinerarios. 

```scala
val obtenerMejoresItinerarios = itinerariosAire(listaVuelos, listaAeropuertos)
val mejoresItinerarios = obtenerMejoresItinerarios("CLO", "SVO")
```
## **3.5 Función itinerariosSalida**

### Propósito:
La función `itinerariosSalida` está diseñada para encontrar el mejor itinerario entre dos aeropuertos, que llegue a su destino antes de una hora específica. Es particularmente útil para situaciones en las que se debe llegar a un lugar a tiempo para una cita o evento.

### Explicación de las funciones:

#### ***Función Principal (itinerariosSalida):***
La función principal `itinerariosSalida` recibe dos listas: una lista de objetos `Vuelo` y otra de objetos `Aeropuerto`. Retorna una función que, al ser invocada con un aeropuerto de origen, un aeropuerto de destino, una hora y un minuto, calcula el mejor itinerario que llegue antes de la hora especificada.

#### ***Funcion auxiliar (itinerarios)***

Esta función, que recibe una lista de vuelos y una lista de aeropuertos, devuelve una función que, dado un aeropuerto de origen (c1) y un aeropuerto de destino (c2), encuentra todos los itinerarios posibles entre ellos.

#### ***Parámetros (origen: String, destino: String, h: Int, m: Int):***
Estos parámetros son usados por la función que retorna `itinerariosSalida` para especificar el aeropuerto de inicio, el aeropuerto final y la hora límite (en horas y minutos) antes de la cual se debe llegar al destino.

#### ***Cuerpo de la función:***

**aeropuertosMap:**
Convierte la lista de aeropuertos en un mapa (`Map`) para facilitar el acceso a los datos de cada aeropuerto mediante su código.

**obtenerItinerarios:**
Obtiene todos los itinerarios posibles entre los aeropuertos de origen y destino utilizando la función auxiliar `itinerarios`.

**horaCitaEnMinutos:**
Convierte la hora y los minutos de la cita a un total de minutos desde medianoche, para facilitar las comparaciones.

**itinerariosValidos:**
Filtra los itinerarios para encontrar aquellos que llegan al destino antes de la hora límite especificada. 

- **llegadaItinerarioEnMinutos:**
  Calcula la hora de llegada en minutos para cada itinerario, ajustando las diferencias horarias entre los aeropuertos de origen y destino.

**maxBy:**
Selecciona el itinerario válido que sale más tarde del aeropuerto de origen y aún llega antes de la hora límite.

**if (itinerariosValidos.isEmpty):**
Si no se encuentran itinerarios válidos, retorna una lista vacía.

### ***Técnicas utilizadas:***

- **Operaciones de mapeo y filtrado:**
  - `.map` convierte la lista de aeropuertos en un mapa para acceso rápido.
  - `.filter` selecciona los itinerarios que llegan antes de la hora límite.
  - `.maxBy` encuentra el itinerario que sale más tarde pero aún llega a tiempo.

### ***Uso:***
Para invocar la función `itinerariosSalida`, se deben proporcionar dos listas: una de vuelos y otra de aeropuertos. La función resultante se llama con los códigos de los aeropuertos de origen y destino, y la hora límite de llegada en horas y minutos.

```scala
val obtenerMejorItinerario = itinerariosSalida(listaVuelos, listaAeropuertos)
val mejorItinerario = obtenerMejorItinerario("CLO", "SVO", 10, 30)
