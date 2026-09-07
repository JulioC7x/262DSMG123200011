package Condicionales_Kotlin

fun main() {

    /*Expresiones booleanas con operadores de comparación*/
    println(1 == 1)
    println(1 < 1)

    println("\n--------------")
    /*Escribir una sentencia if simple*/
    val trafficLightColor = "Amber"

    if (trafficLightColor == "Red") {
        println("Stop")
    } else if (trafficLightColor == "Yellow") {
        println("Slow")
    } else if (trafficLightColor == "Green") {
        println("Go")
    } else {
        println("Invalid traffic-light color")
    }

    println("\n-------------")
    /*Usar una sentencia when*/
    when (trafficLightColor) {
        "Red" -> println("Stop")
        "Yellow" , "Amber" -> println("Slow")
        "Green" -> println("Go")
        else -> println("Invalid traffic-light color")
    }

    println("\n-------------")
    val x: Any = 4

    /*Usar una coma para varias condiciones*/
    when (x) {
        2, 3, 5, 7 -> println("x is a prime number between 1 and 10.")
        in 1..10 -> println("x is a number between 1 and 10, but not a prime number.")
        is Int -> println("x is an integer number, but not between 1 and 10.")
        else -> println("x isn't an integer number.")
    }

    println("\n-------------")
    /*Usar if/else y when como expresiones*/
    val message =
        if (trafficLightColor == "Red") "Stop"
        else if (trafficLightColor == "Yellow") "Slow"
        else if (trafficLightColor == "Green") "Go"
        else "Invalid traffic-light color"

    /*
    val message = when(trafficLightColor) {
        "Red" -> "Stop"
        "Yellow", "Amber" -> "Slow"
        "Green" -> "Go"
        else -> "Invalid traffic-light color"
    }  */

    println(message)
}
