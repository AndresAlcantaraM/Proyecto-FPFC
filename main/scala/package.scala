import common._
import Datos._
package object ItinerariosPar {

  // 3.1
  def itinerariosPar(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {

    def buscarItinerarios(Org: String, Dst: String, visitados: Set[String], itinerarioActual: Itinerario): List[Itinerario] = {
      if (Org == Dst) {
        return List(itinerarioActual)
      }

      val resultadosParalelos = vuelos.filter(v => v.Org == Org && !visitados.contains(v.Dst)).map { vuelo =>
        task {
          val newVisitados = visitados + Org
          val newItinerary = itinerarioActual :+ vuelo
          buscarItinerarios(vuelo.Dst, Dst, newVisitados, newItinerary)
        }
      }

      resultadosParalelos.flatMap(_.join())
    }

    (c1: String, c2: String) => buscarItinerarios(c1, c2, Set(), List())
  }

  // Funcion auxiliar para convertir el tiempo
  def calcularTiempoTotal(itinerario: Itinerario, aeropuertos: Map[String, Aeropuerto]): Int = {
    itinerario.map { vuelo =>
      val origen = aeropuertos(vuelo.Org)
      val destino = aeropuertos(vuelo.Dst)
      val salidaMinutos = vuelo.HS * 60 + vuelo.MS
      val llegadaMinutos = vuelo.HL * 60 + vuelo.ML
      val diferenciaGMT = destino.GMT - origen.GMT
      val tiempoVuelo = (llegadaMinutos + diferenciaGMT * 60) - salidaMinutos
      if (tiempoVuelo < 0) tiempoVuelo + 24 * 60 else tiempoVuelo
    }.sum
  }

  // 3.2
  def itinerariosTiempoPar(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {
    val aeropuertosMap = aeropuertos.map(a => a.Cod -> a).toMap
    val obtenerItinerarios = itinerariosPar(vuelos, aeropuertos)

    (c1: String, c2: String) => {
      val todosItinerarios = obtenerItinerarios(c1, c2)
      val itinerariosConTiempo = todosItinerarios.map(it => task {
        (it, calcularTiempoTotal(it, aeropuertosMap))
      })

      val mejoresItinerarios = itinerariosConTiempo.map(_.join()).sortBy(_._2).take(3)
      mejoresItinerarios.map(_._1)
    }
  }
}
