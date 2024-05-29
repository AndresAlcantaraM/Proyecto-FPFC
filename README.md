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


## **3.4 Funcion itinerariosAire**

### Propósito:
Minimizar el tiempo que están en el aire, el cual es proporcional a la distancia entre los aeropuertos. Su consulta consiste en hallar al menos tres itinerarios para ir de cod1 a cod2 (si los hay) que minimicen el tiempo de vuelo, sin tener en cuenta el tiempo total de viaje (podría ser mejor un menor tiempo de vuelo, aunque haya mayores tiempos de espera en tierra).

### Explicación de las funciones:


#### Función Principal (itinerariosAire):

- Recibe una lista de Vuelos y una lista de Aeropuertos
- Devuelve una funcion que dado a partir de obtener los codigos de aeropuertos de destino y origen (c1 y c2); pueda devolver una lista con hasta 3 itinerarios los cuales minimizan el tiempo en el aire entre ambos aeropuertos


#### Funcion auxiliar (distancia)

Esta funcio lo que hace es calcular la distancia que existe entre C1 y C2 (aeropuerto de origen y destino), a partir de sus coordenadas 

- Recibe a1 y a2 las cuales son las coordenadad de los aeropuertos seleccionados
- Realiza el calculo restando sus distancias en X y Y 
- Finalmente devuelve el valor obtenido del calculo sqrt(dX * dX + dY * dY)


#### Funcion auxiliar (encontrarAeropuerto)

Tal y como lo dice, lo que se hace aqui es encontar los aeropuertos, donde a su vez validamos que estos existan:

- Recibe el codigo del aeropuerto a buscar (cod) de tipo string
- Luego lo que se hace es validar que el codigo del aeropuerto pasado realmente exista, si esto es asi entonces.
- La funcion retorna un contenedor (Option) en el cual estan los aeropuertos.



#### Funcion auxiliar (dfs)

- Recibe: actual (codigo del aeropuerto en el que nos encontramos), destino (codigo del aeropuerto de destino), ruta (las listas con los vuelos), distTotal (La distancia recorrida hasta el momento), visitados (los codigos de los aeropuertos qeu ya han sido visitados) y mejores (una lista donde se almacenan las mejores rutas encontradas).
- Lo que se realiza en la funcion es primero conocer donde donde estamos (esto lo hace en el primer if), apartir de esto, empezamos a comparar las rutas con los mejores casos y los almacenamos en la lista de mejores. (en el primer else) lo que se hace es una exploracion recursiva donde exploramos todos los vuelos posibles entre el aeropuerto de origen y destino (hacemos llamado a distancia para calcular la distancia); una vez obtenidos todos los vuelo hacemos un llamado recursivo a **dfs** para obtener los mejores vuelos.
- Finalmente obtenemos como resultado una lista con los mejores vuelos obtenidos de manera ordenada.


### Uso:

A partir de la utilizacion de los codigos de los aeropuetos de origen, destino y la lista de los vuelos, lo que se busca es hallar vuelos los cuales permanezcan el menor tiempo posible en el aire que vallan desde el origen hasta el destino.

### Tecnicas usadas:

- Operaciones sobre secuencias (.map / .flatMap / etc)
- Operacines sobre listas (enqueue / dequeue / etc)
- Contenedores
- Recursion (de tipo arbol, ya que primero explora todas las ramas y luego filtra los resultados)

### Version paralelizada:

la version con paralelismo en ni no cambia mucho su estructura, la unica parte donde cambia, es en la exploracion de los posibles vuelos, donde aplicamos la paralelizacion para poder explorar "multiples vuelos a la vez"

