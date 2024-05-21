package object funcionesAuxiliares {

  case class Aeropuerto(Cod: String, X: Int, Y: Int, GMT: Int)

  case class Vuelo(Aln: String, Num: Int, Org: String, HS: Int, MS: Int, Dst: String, HL: Int, ML: Int, Esc: Int)

  type Itinerario = List[Vuelo]

  //3.1
  def itinerarios(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {

    def buscarItinerarios(Org: String, Dst: String, visitados: Set[String], itinerarioActual: Itinerario): List[Itinerario] = {
      if (Org == Dst) {
        return List(itinerarioActual)
      }

      vuelos.filter(v => v.Org == Org && !visitados.contains(v.Dst)).flatMap { vuelo =>
        val newVisitados = visitados + Org
        val newItinerary = itinerarioActual :+ vuelo
        buscarItinerarios(vuelo.Dst, Dst, newVisitados, newItinerary)
      }
    }

    (c1: String, c2: String) => buscarItinerarios(c1, c2, Set(), List())
  }




  
  //////////////////////////////////////////////////////////////////////////////////
  def minutosDesdeMedianoche(hora: Int, minutos: Int): Int = hora * 60 + minutos

  def calcularTiempoTotal(itinerario: Itinerario, aeropuertos: Map[String, Aeropuerto]): Int = {
    itinerario.map { vuelo =>
      val origen = aeropuertos(vuelo.Org)
      val destino = aeropuertos(vuelo.Dst)
      val salidaMinutos = minutosDesdeMedianoche(vuelo.HS, vuelo.MS)
      val llegadaMinutos = minutosDesdeMedianoche(vuelo.HL, vuelo.ML)
      val diferenciaGMT = destino.GMT - origen.GMT
      val tiempoVuelo = (llegadaMinutos + diferenciaGMT * 60) - salidaMinutos
      if (tiempoVuelo < 0) tiempoVuelo + 24 * 60 else tiempoVuelo
    }.sum
  }

  def itinerariosTiempo(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {
    val aeropuertosMap = aeropuertos.map(a => a.Cod -> a).toMap
    val obtenerItinerarios = itinerarios(vuelos, aeropuertos)

    (c1: String, c2: String) => {
      val todosItinerarios = obtenerItinerarios(c1, c2)
      val itinerariosConTiempo = todosItinerarios.map(it => (it, calcularTiempoTotal(it, aeropuertosMap)))
      val mejoresItinerarios = itinerariosConTiempo.sortBy(_._2).take(3)
      mejoresItinerarios.map(_._1)
    }
  }
}
