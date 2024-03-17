package uz.doston.e_learn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import uz.doston.e_learn.Navigation.NavGraph
import uz.doston.e_learn.ui.theme.ELearnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ELearnTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
