import kotlin.system.*
import kotlinx.coroutines.*

fun main() {

    println("CODIGO SINCRONO:\n")
    val time1 = measureTimeMillis {
        runBlocking {
            println("Weather forecast")
            printForecast()
            printTemperature()
        }
    }
    println("Execution time: ${time1 / 1000.0} seconds")

    println("\n---------------------------------------------------------------")
    println("CODIGO ASINCRONO - launch():\n")
    val time2 = measureTimeMillis {
        runBlocking {
            println("Weather forecast")
            launch {
                printForecast()
            }
            launch {
                printTemperature()
            }
            println("Have a good day!")
        }
    }
    println("Execution time: ${time2 / 1000.0} seconds")

    println("\n---------------------------------------------------------------")
    println("CODIGO ASINCRONO - async():\n")
    runBlocking {
        println("Weather forecast")
        val forecast: Deferred<String> = async {
            getForecast1()
        }
        val temperature: Deferred<String> = async {
            getTemperature1()
        }
        println("${forecast.await()} ${temperature.await()}")
        println("Have a good day!")
    }

    println("\n---------------------------------------------------------------")
    println("CODIGO ASINCRONO - Descomposición paralela:\n")
    runBlocking {
        println("Weather forecast")
        println(getWeatherReport1())
        println("Have a good day!")
    }
}

suspend fun printForecast() {
    delay(1000)
    println("Sunny")
}

suspend fun printTemperature() {
    delay(1000)
    println("30\u00b0C")
}

////////////////////////////////////////////

suspend fun getForecast1(): String {
    delay(1000)
    return "Sunny"
}

suspend fun getTemperature1(): String {
    delay(1000)
    return "30\u00b0C"
}

///////////////////////////////////////////

suspend fun getWeatherReport1() = coroutineScope {
    val forecast = async { getForecast1() }
    val temperature = async { getTemperature1() }
    "${forecast.await()} ${temperature.await()}"
}