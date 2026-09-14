package Colecciones_Kotlin

val rockPlanets = arrayOf<String>("Mercury", "Venus", "Earth", "Mars")

val gasPlanets = arrayOf("Jupiter", "Saturn", "Uranus", "Neptune")

val solarSystem = rockPlanets + gasPlanets

val newSolarSystem = arrayOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto")

fun main(){

    solarSystem[3] = "Little Earth"

    println(solarSystem[0])
    println(solarSystem[1])
    println(solarSystem[2])
    println(solarSystem[3])
    println(solarSystem[4])
    println(solarSystem[5])
    println(solarSystem[6])
    println(solarSystem[7])

    println("---------------------------------")
    println(newSolarSystem[8])

    println("---------------------------------")
    val solarSystem1 = listOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
    println(solarSystem1.size)
    println(solarSystem1[2])
    println(solarSystem1.get(3))
    println(solarSystem1.indexOf("Earth"))
    println(solarSystem1.indexOf("Pluto"))

    println("---------------------------------")
    for (planet in solarSystem1) {
        println(planet)
    }

    println("---------------------------------")
    val solarSystem2 = mutableListOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
    solarSystem2.add("Pluto")
    solarSystem2.add(3, "Theia")
    solarSystem2[3] = "Future Moon"
    println(solarSystem2[3])
    println(solarSystem2[9])
    println("")
    solarSystem2.removeAt(9)
    solarSystem2.remove("Future Moon")
    println(solarSystem2.contains("Pluto"))
    println("Future Moon" in solarSystem2)

    println("")
    println("")
    println("-------------JUEGOS-------------------")
    val solarSystem3 = mutableSetOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
    println(solarSystem3.size)
    solarSystem3.add("Pluto")
    println(solarSystem3.size)
    println(solarSystem3.contains("Pluto"))
    solarSystem3.add("Pluto")
    println(solarSystem3.size)
    solarSystem3.remove("Pluto")
    println(solarSystem3.size)
    println(solarSystem3.contains("Pluto"))

    println("")
    println("")
    println("-------------MAPAS-------------------")
    val solarSystem4 = mutableMapOf(
        "Mercury" to 0,
        "Venus" to 0,
        "Earth" to 1,
        "Mars" to 2,
        "Jupiter" to 79,
        "Saturn" to 82,
        "Uranus" to 27,
        "Neptune" to 14
    )
    println(solarSystem4.size)
    solarSystem4["Pluto"] = 5
    println(solarSystem4.size)
    println(solarSystem4["Pluto"])
    println(solarSystem4.get("Theia"))

    solarSystem4.remove("Pluto")
    println(solarSystem4.size)
    solarSystem4["Jupiter"] = 78
    println(solarSystem4["Jupiter"])
}