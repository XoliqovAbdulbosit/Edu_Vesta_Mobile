package uz.doston.e_learn.Navigation

import SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import uz.doston.e_learn.screens.CoursesScreen
import uz.doston.e_learn.screens.LessonScreen
import uz.doston.e_learn.screens.LoginScreen
import uz.doston.e_learn.screens.OlympiadScreen
import uz.doston.e_learn.screens.OlympiadTaskScreen
import uz.doston.e_learn.screens.OlympiadsScreen
import uz.doston.e_learn.screens.ProfileScreen
import uz.doston.e_learn.screens.RegistrationScreen
import uz.doston.e_learn.screens.ReytingScreen
import uz.doston.e_learn.screens.TaskScreen
import uz.doston.e_learn.screens.TasksScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = Screens.Splash.route
    ) {
        composable(route = Screens.Splash.route) {
            SplashScreen(navController)
        }
        composable(route = Screens.Login.route) {
            LoginScreen(navController)
        }
        composable(route = Screens.Register.route) {
            RegistrationScreen(navController)
        }
        composable(route = Screens.Profile.route) {
            ProfileScreen(navController)
        }
        composable(route = Screens.Lessons.route) {
            CoursesScreen(navController)
        }
        composable(route = Screens.Rating.route) {
            ReytingScreen(navController)
        }
        composable(route = Screens.Tasks.route) {
            TasksScreen(navController)
        }
        composable(
            route = Screens.Lesson.route, arguments = listOf(navArgument("key") {
                type = NavType.StringType
            })
        ) {
            LessonScreen(navController, it.arguments?.getString("key")!!)
        }
        composable(
            route = Screens.Task.route, arguments = listOf(navArgument("key") {
                type = NavType.StringType
            })
        ) {
            TaskScreen(navController, it.arguments?.getString("key")!!)
        }
        composable(route = Screens.Olympiads.route) {
            OlympiadsScreen(navController)
        }
        composable(route = Screens.Olympiad.route, arguments = listOf(navArgument("key") {
            type = NavType.StringType
        })) {
            OlympiadScreen(name = it.arguments?.getString("key")!!, navController = navController)
        }
        composable(
            route = Screens.OlympiadTask.route, arguments = listOf(
                navArgument("key_1") { type = NavType.StringType },
                navArgument("key_2") { type = NavType.IntType },
            )
        ) {
            OlympiadTaskScreen(
                name = it.arguments?.getString("key_1")!!,
                length = it.arguments?.getInt("key_2")!!,
                navController = navController
            )
        }
    }
}