package Nulabilidad_Kotlin

fun main() {
    /*Reasignaciones de variables con null*/
    var favoriteActor: String? = "Sandra Oh"
    println(favoriteActor)

    favoriteActor = null
    println(favoriteActor)

    println("\n--------------")
    /*Escribir un valor Int anulable*/
    var number: Int? = 10
    println(number)

    number = null
    println(number)

    println("\n--------------")
    println(favoriteActor?.length)

    // AserciOn no nulo de !!
    //println(favoriteActor!!.length)

    println("\n--------------")
    /*Usa los condicionales if/else*/
    if(favoriteActor != null) {
        println("The number of characters in your favorite actor's name is ${favoriteActor.length}.")
    } else {
        println("You didn't input a name.")
    }

    println("\n--------------")
    val lengthOfName = if (favoriteActor != null) {
        favoriteActor.length
    } else {
        0
    }

    // val lengthOfName = favoriteActor?.length
    println("The number of characters in your favorite actor's name is $lengthOfName.")

}