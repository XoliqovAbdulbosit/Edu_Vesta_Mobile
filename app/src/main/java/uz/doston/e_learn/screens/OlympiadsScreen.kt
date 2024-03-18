package uz.doston.e_learn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uz.doston.e_learn.ui.theme.Background
import uz.doston.e_learn.ui.theme.Secondary
import uz.doston.e_learn.ui.theme.TextColor

@Composable
fun Olympiad(name: String, navController: NavController) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp, horizontal = 7.dp)
        .background(Secondary, RoundedCornerShape(6.dp))
        .clickable {
            navController.navigate("Olympiad/$name")
        }) {
        Text(
            color = TextColor,
            modifier = Modifier.padding(8.dp),
            text = name,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
    }
}


@Composable
fun OlympiadsScreen(navController: NavController) {
    Scaffold(bottomBar = {
        BottomNavigationComponent(navController)
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Background)
        ) {
            var olympiads by remember { mutableStateOf<List<String>>(emptyList()) }
            Manager.getOlympiadsList {
                olympiads = it
            }
            Text(
                text = "Olympiads",
                fontSize = 32.sp,
                color = TextColor,
                modifier = Modifier.padding(10.dp)
            )
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(olympiads) {
                    Olympiad(name = it, navController)
                }
            }
        }
    }
}