package variables

fun main() {
    val count: Int = 10
    println("You have $count unread messages.")
    println("\n")
    val unreadCount = 5
    val readCount = 100
    println("You have ${unreadCount + readCount} total messages in your inbox.")
    println("\n")
    val numberOfPhotos = 100
    val photosDeleted = 10
    println("$numberOfPhotos photos")
    println("$photosDeleted photos deleted")
    println("${numberOfPhotos - photosDeleted} photos left")
    println("\n")
    actualizar()
    println("\n")
    tipos()
}

fun actualizar(){
    var cartTotal = 0
    println("Total: $cartTotal")
    cartTotal = 20
    println("Total: $cartTotal")
    println("\n")
    var count: Int = 10
    println("You have $count unread messages.")
    count = count + 1
    println("You have $count unread messages.")
    count++
    println("You have $count unread messages.")
    count--
    println("You have $count unread messages.")
}

fun tipos(){
    //Double
        val trip1: Double = 3.20
        val trip2: Double = 4.10
        val trip3: Double = 1.72
        val totalTripLength: Double = trip1 + trip2 + trip3
        println("$totalTripLength miles left to destination")
        println("\n")
    //String
        val nextMeeting = "Next meeting: "
        val date = "January 1"
        val reminder = nextMeeting + date + "at work"
        println(reminder)
        println("Say \"hello\"")
        println("\n")
    //Booleano
        val notificationsEnabled: Boolean = false
        println("Are notifications enabled? " + notificationsEnabled)
}