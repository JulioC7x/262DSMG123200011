package funciones

fun main() {
    //val greeting = birthdayGreeting()
    //println(greeting)

    println(birthdayGreeting("Rover", 5))
    println(birthdayGreeting(age = 2, name = "Rex"))
}

fun birthdayGreeting(name: String = "Rover", age: Int): String {
    val nameGreeting = "Happy Birthday, $name!"
    val ageGreeting = "You are now $age years old!"
    return "$nameGreeting\n$ageGreeting"
}