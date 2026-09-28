import kotlinx.coroutines.*

fun main() {
    runBlocking {
        println("Weather forecast")
        println(getWeatherReport3())
        println("Have a good day!")
    }
}

suspend fun getWeatherReport3() = coroutineScope {
    val forecast = async { getForecast() }
    val temperature = async { getTemperature() }

    delay(200)
    temperature.cancel()

    "${forecast.await()}"
}

suspend fun getForecast3(): String {
    delay(1000)
    return "Sunny"
}

suspend fun getTemperature3(): String {
    delay(1000)
    return "30\u00b0C"
}