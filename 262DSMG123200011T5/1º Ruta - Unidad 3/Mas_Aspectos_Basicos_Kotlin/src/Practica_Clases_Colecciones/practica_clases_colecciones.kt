package Practica_Clases_Colecciones

import kotlin.compareTo

//Tarea 1
data class Event(
    val title: String,
    val description: String? = null,
    //val daypart: String
    val daypart: Daypart,
    val durationInMinutes: Int,
)

//Tarea 2
enum class Daypart {
    MORNING,
    AFTERNOON,
    EVENING,
}

//Tarea 3
val event1 = Event(title = "Wake up", description = "Time to get up", daypart = Daypart.MORNING, durationInMinutes = 0)
val event2 = Event(title = "Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15)
val event3 = Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30)
val event4 = Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60)
val event5 = Event(title = "Watch latest DevBytes video", daypart = Daypart.AFTERNOON, durationInMinutes = 10)
val event6 = Event(title = "Check out latest Android Jetpack library", daypart = Daypart.EVENING, durationInMinutes = 45)

val events = mutableListOf<Event>(event1, event2, event3, event4, event5, event6)


val Event.durationOfEvent: String
    get() = if (this.durationInMinutes < 60) {
        "short"
    } else {
        "long"
    }

fun main(){
    println("\n\nTarea 4")
    //Tarea 4
    val shortEvents = events.filter { it.durationInMinutes < 60 }
    println("You have ${shortEvents.size} short events.")

    println("\n\nTarea 5")
    //Tarea 5
    val groupedEvents = events.groupBy { it.daypart }
    groupedEvents.forEach { (daypart, events) ->
        println("$daypart: ${events.size} events")
    }

    println("\n\nTarea 6")
    //Tarea 6
    println("Last event of the day: ${events.last().title}")

    println("\n\nTarea 7")
    //Tarea 7
    println("Duration of first event of the day: ${event1.durationOfEvent}")


}