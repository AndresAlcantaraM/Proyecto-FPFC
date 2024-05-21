import Itinerarios._

// Datos de ejemplo
val aeropuertosCurso = List(
  Aeropuerto("CLO", 100, 200, -500),
  Aeropuerto("BOG", 300, 500, -500),
  Aeropuerto("MDE", 200, 600, -500),
  Aeropuerto("BAQ", 350, 850, -500),
  Aeropuerto("SMR", 400, 950, -500),
  Aeropuerto("CTG", 300, 800, -500),
  Aeropuerto("PTY", 400, 1000, -500),
  Aeropuerto("JFK", 2000, 2000, -400),
  Aeropuerto("MIA", 1000, 2000, -500),
  Aeropuerto("MEX", 1000, 1000, -600),
  Aeropuerto("MAD", 5000, 5000, 100),
  Aeropuerto("SVCS", 400, 1000, -600),
  Aeropuerto("MID", 500, 100, -600),
  Aeropuerto("AUA", 500, 2000, -400),
  Aeropuerto("IST", 9000, 9000, 300),
  Aeropuerto("HND", 10000, 12000, 900),
  Aeropuerto("DXB", 9500, 11500, 400),
  Aeropuerto("SVO", 12500, 12500, 300)
)

val vuelosCurso = List(
  Vuelo("AIRVZLA", 601, "MID", 5, 0, "SVCS", 6, 0, 0),
  Vuelo("AIRVZLA", 602, "SVCS", 6, 30, "MID", 7, 30, 0),
  Vuelo("AVA", 9432, "CLO", 7, 0, "SVO", 2, 20, 4),
  Vuelo("AVA", 9432, "CLO", 7, 0, "BOG", 8, 0, 0),
  Vuelo("IBERIA", 505, "BOG", 18, 0, "MAD", 12, 0, 0),
  Vuelo("IBERIA", 506, "MAD", 14, 0, "SVO", 23, 20, 0),
  Vuelo("IBERIA", 507, "MAD", 16, 0, "SVO", 1, 20, 0),
  Vuelo("LATAM", 787, "BOG", 17, 0, "MEX", 19, 0, 0),
  Vuelo("VIVA", 756, "BOG", 9, 0, "MDE", 10, 0, 0),
  Vuelo("VIVA", 769, "MDE", 11, 0, "BAQ", 12, 0, 0),
  Vuelo("AVA", 5643, "BAQ", 14, 0, "MEX", 16, 0, 0),
  Vuelo("COPA", 1234, "CTG", 10, 0, "PTY", 11, 30, 0),
  Vuelo("AVA", 4321, "CTG", 9, 30, "SMR", 10, 0, 0),
  Vuelo("COPA", 7631, "SMR", 10, 50, "PTY", 11, 50, 0),
  Vuelo("TURKISH", 7799, "CLO", 7, 0, "IST", 14, 0, 3),
  Vuelo("QATAR", 5566, "IST", 23, 0, "SVO", 2, 0, 0)
)

// Uso de la función 3.1
val itsCurso = itinerarios(vuelosCurso, aeropuertosCurso)
val its1 = itsCurso("MID", "SVCS")
val its2 = itsCurso("CLO", "SVCS")
val its3 = itsCurso("CLO", "SVO")
val its4 = itsCurso("CLO","MEX")
val its5 = itsCurso("CTG","PTY")

//Uso de la función 3.2
val itsTiempoCurso = itinerariosTiempo(vuelosCurso, aeropuertosCurso)
val itst1 = itsTiempoCurso("MID", "SVCS")
val itst2 = itsTiempoCurso("CLO", "SVCS")
val itst3 = itsTiempoCurso("CLO", "SVO")
val itst4 = itsTiempoCurso("CLO", "MEX")
val itst5 = itsTiempoCurso("CTG", "PTY")



