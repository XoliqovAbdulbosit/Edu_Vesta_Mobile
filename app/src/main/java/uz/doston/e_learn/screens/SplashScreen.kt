import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import uz.doston.e_learn.Navigation.Screens
import uz.doston.e_learn.R
import uz.doston.e_learn.ui.theme.Background


@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current
    LaunchedEffect(true) {
        delay(1250)
        if (Manager.getToken(context) == "") navController.navigate(Screens.Login.route) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
        else {
            navController.navigate(Screens.Profile.route) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.size(300.dp),
        )
    }
}