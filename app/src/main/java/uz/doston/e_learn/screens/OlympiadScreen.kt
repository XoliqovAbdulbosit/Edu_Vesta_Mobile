package uz.doston.e_learn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import uz.doston.e_learn.model.*
import uz.doston.e_learn.ui.theme.DeepSlate
import uz.doston.e_learn.ui.theme.LightGrey
import uz.doston.e_learn.ui.theme.VerdantGreen


@Composable
fun OlympiadScreen(name: String, navController: NavController) {
    var olympiad by remember { mutableStateOf(Olympiad("", "", "", emptyList())) }
    Manager.getOlympiad(name) {
        olympiad = it
    }
    Scaffold(bottomBar = {
        BottomNavigationComponent(navController)
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(DeepSlate)
        ) {
            Text(
                text = olympiad.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = LightGrey,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp)
            )
            Text(
                text = "Boshlanish vaqti: " + olympiad.start_time,
                fontSize = 20.sp,
                color = LightGrey,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp)
            )
            Text(
                text = "Tugash vaqti: " + olympiad.end_time,
                fontSize = 20.sp,
                color = LightGrey,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp)
            )
            Button(modifier = Modifier.padding(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = VerdantGreen),
                onClick = {
                    navController.navigate("OlympiadTask/$name/${olympiad.tasks.size}")
                }) {
                Text(
                    text = "Boshlash",
                    fontSize = 24.sp,
                    color = LightGrey,
                    modifier = Modifier.padding(horizontal = 3.dp, vertical = 3.dp)
                )
            }
        }
    }
}