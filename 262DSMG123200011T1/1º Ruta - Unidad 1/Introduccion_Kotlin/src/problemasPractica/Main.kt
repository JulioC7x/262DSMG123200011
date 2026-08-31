package problemasPractica

fun main() {
    println("EJERCICIO 1")
    ejercicio1()
    println("\n")
    println("EJERCICIO 2")
    ejercicio2()
    println("\n")
    println("EJERCICIO 3")
    ejercicio3()
    println("\n")
    println("EJERCICIO 4")
    ejercicio4()
    println("\n")
    println("EJERCICIO 5")
    ejercicio5()
    println("\n")
    println("EJERCICIO 6")
    ejercicio6()
    println("\n")
    println("EJERCICIO 7")
    val firstUserEmailId = "user_one@gmail.com"
    println(displayAlertMessage(emailId = firstUserEmailId))
    println()
    val secondUserOperatingSystem = "Windows"
    val secondUserEmailId = "user_two@gmail.com"
    println(displayAlertMessage(secondUserOperatingSystem, secondUserEmailId))
    println()
    val thirdUserOperatingSystem = "Mac OS"
    val thirdUserEmailId = "user_three@gmail.com"
    println(displayAlertMessage(thirdUserOperatingSystem, thirdUserEmailId))
    println()
    println("\n")
    println("EJERCICIO 8")
    ejercicio8()
    println("\n")
    println("EJERCICIO 9")
    ejercicio9()
    println("\n")
    println("EJERCICIO 10")
    ejercicio10()
}

//Impresión de mensajes
fun ejercicio1() {
    println("Use the val keyword when the value doesn't change.")
    println("Use the var keyword when the value can change.")
    println("When you define a function, you define the parameters that can be passed to it.")
    println("When you call a function, you pass arguments for the parameters.")
}

//Corrección de un error de compilación
fun ejercicio2() {
        println("New chat message from a friend")
}

//Plantillas de cadenas
fun ejercicio3() {
        val discountPercentage = 20
        val item = "Google Chromecast"
        val offer = "Sale  - Up to $discountPercentage% discount off $item! Hurry Up!"

        println(offer)
}

//Concatenación de cadenas
fun ejercicio4() {
        val numberOfAdults = 20
        val numberOfKids = 30
        val total = numberOfAdults + numberOfKids
        println("The total party size is: $total")
}

//Formato de mensajes
fun ejercicio5() {
        val baseSalary = 5000
        val bonusAmount = 1000
        val totalSalary = "$baseSalary + $bonusAmount"
        println("Congratulations for your bonus! You will receive a total of $totalSalary (additional bonus).")
}

//Implementación de operaciones matemáticas básicas
fun ejercicio6() {
        val firstNumber = 10
        val secondNumber = 5
        val thirdNumber = 8

        val result = add(firstNumber, secondNumber)
        val anotherResult = subtract(firstNumber, thirdNumber)

        println("$firstNumber + $secondNumber = $result")
        println("$firstNumber - $thirdNumber = $anotherResult")
    }

fun add(firstNumber: Int, secondNumber: Int): Int {
    return firstNumber + secondNumber
}

fun subtract(firstNumber: Int, secondNumber: Int): Int {
    return firstNumber - secondNumber
}

//Parámetros predeterminados
fun displayAlertMessage(operatingSystem: String = "Unknown OS",
                        emailId: String
): String {
    return "There is a new sign-in request on $operatingSystem for your Google Account $emailId."
}

//Podómetro
fun ejercicio8() {
        val steps = 4000
        val caloriesBurned = pedometerStepsToCalories(steps)
        println("Walking $steps steps burns $caloriesBurned calories")
    }

    fun pedometerStepsToCalories(numberOfSteps: Int): Double {
        val caloriesBurnedForEachStep = 0.04
        val totalCaloriesBurned = numberOfSteps * caloriesBurnedForEachStep
        return totalCaloriesBurned
}

//Comparación de dos números
fun ejercicio9() {
    println("Have I spent more time using my phone today: ${compareTime(300, 250)}")
    println("Have I spent more time using my phone today: ${compareTime(300, 300)}")
    println("Have I spent more time using my phone today: ${compareTime(200, 220)}")
}

fun compareTime(timeSpentToday: Int, timeSpentYesterday: Int): Boolean {
    return timeSpentToday > timeSpentYesterday
}

//Movimiento del código duplicado a una función
fun ejercicio10() {
    printWeatherForCity("Ankara", 27, 31, 82)
    printWeatherForCity("Tokyo", 32, 36, 10)
    printWeatherForCity("Cape Town", 59, 64, 2)
    printWeatherForCity("Guatemala City", 50, 55, 7)
}

fun printWeatherForCity(cityName: String, lowTemp: Int, highTemp: Int, chanceOfRain: Int) {
    println("City: $cityName")
    println("Low temperature: $lowTemp, High temperature: $highTemp")
    println("Chance of rain: $chanceOfRain%")
    println()
}