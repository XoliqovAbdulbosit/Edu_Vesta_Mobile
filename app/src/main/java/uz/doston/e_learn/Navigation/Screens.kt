package uz.doston.e_learn.Navigation

sealed class Screens(val route: String) {
    object Splash : Screens("Splash")
    object Tasks : Screens("Tasks")
    object Task : Screens("Task/{key}")
    object Login : Screens("Login")
    object Register : Screens("Registration")
    object Profile : Screens("Profile")
    object Rating : Screens("Rating")
    object Lessons : Screens("Lessons")
    object Lesson : Screens("Lesson/{key}")
    object Olympiads : Screens("Olympiads")
    object Olympiad : Screens("Olympiad/{key}")
    object OlympiadTask : Screens("OlympiadTask/{key_1}/{key_2}")
}
