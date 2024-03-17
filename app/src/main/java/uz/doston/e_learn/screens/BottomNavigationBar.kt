package uz.doston.e_learn.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import uz.doston.e_learn.ui.theme.DeepSlate
import uz.doston.e_learn.ui.theme.LightGrey
import uz.doston.e_learn.ui.theme.SteelBlue


data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)


@Composable
fun BottomNavigationComponent(navController: NavController) {
    val items = listOf(
        BottomNavigationItem(
            title = "Lessons",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        BottomNavigationItem(
            title = "Tasks",
            selectedIcon = Icons.Filled.Email,
            unselectedIcon = Icons.Outlined.Email,
        ),
        BottomNavigationItem(
            title = "Olympiads",
            selectedIcon = Icons.Filled.Star,
            unselectedIcon = Icons.Outlined.Star,
        ),
        BottomNavigationItem(
            title = "Rating",
            selectedIcon = Icons.Filled.Menu,
            unselectedIcon = Icons.Outlined.Menu,
        ),
        BottomNavigationItem(
            title = "Profile",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
        ),
    )
    val currentRoute = navController.currentDestination?.route
    NavigationBar(containerColor = DeepSlate) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(selected = currentRoute == item.title, onClick = {
                navController.navigate(item.title)
            }, label = {
                Text(text = item.title, color = LightGrey)
            }, icon = {
                Icon(
                    imageVector = if (currentRoute == item.title) {
                        item.selectedIcon
                    } else item.unselectedIcon,
                    contentDescription = item.title,
                    tint = LightGrey,
                )
            }, colors = NavigationBarItemDefaults.colors(indicatorColor = SteelBlue))
        }
    }
}