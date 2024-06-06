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

#### ***Función auxiliar (itinerarios):***
Esta función, que recibe una lista de vuelos y una lista de aeropuertos, devuelve una función que, dado un aeropuerto de origen (c1) y un aeropuerto de destino (c2), encuentra todos los itinerarios posibles entre ellos.

#### ***Parámetros (origen: String, destino: String):***
Son los parámetros que la función principal retorna. Estos parámetros se utilizan para especificar el aeropuerto de inicio y el aeropuerto final del itinerario deseado.

#### ***Cuerpo de la función:***

- **convertirAGMT:**
  Convierte la hora local de salida y llegada de un vuelo a minutos en GMT (Tiempo Medio de Greenwich). Toma en cuenta las diferencias horarias entre los aeropuertos de origen y destino.

- **calcularTiempoVuelo:**
  Calcula el tiempo total de vuelo para un itinerario dado. Suma el tiempo de todos los vuelos en el itinerario, considerando las conversiones a GMT para una comparación precisa.

- **buscarMejoresItinerarios:**
  Busca los mejores itinerarios entre dos aeropuertos. Si hay tres o menos itinerarios posibles, los retorna todos. Si hay más de tres, selecciona los tres con el menor tiempo total de vuelo.


### Version paralelizada:

la version con paralelismo en ni no cambia mucho su estructura, la unica parte donde cambia, es en la exploracion de los posibles vuelos, donde aplicamos la paralelizacion para poder explorar "multiples vuelos a la vez"

