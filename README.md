# Proyecto-FPFC
Proyecto final de Fundamentos de Programación Funcional y Concurrente

## Función itinerarios
### Propósito: 
Encontrar todos los posibles itinerarios (secuencias de vuelos) entre dos aeropuertos dados.
### Explicación:
### Función Principal (itinerarios):

- Recibe una lista de vuelos y una lista de aeropuertos.
- Devuelve una función que, dado un aeropuerto de origen (c1) y un aeropuerto de destino (c2), encuentra todos los itinerarios posibles entre ellos.

### Función Auxiliar (buscarItinerarios):

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

## Función itinerariosTiempo
### Propósito: 
Encontrar los tres itinerarios que minimizan el tiempo de viaje entre dos aeropuertos dados.

### Explicación:
### Función Principal (itinerariosTiempo):

-Recibe una lista de vuelos y una lista de aeropuertos.
-Devuelve una función que, dado un aeropuerto de origen (c1) y un aeropuerto de destino (c2), encuentra los tres itinerarios más rápidos entre ellos.

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
