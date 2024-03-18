package uz.doston.e_learn.screens

import Manager
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uz.doston.e_learn.Navigation.Screens
import uz.doston.e_learn.model.Task
import uz.doston.e_learn.ui.theme.Background
import uz.doston.e_learn.ui.theme.Primary
import uz.doston.e_learn.ui.theme.Secondary
import uz.doston.e_learn.ui.theme.TextColor


@Composable
fun TaskScreen(navController: NavController, name: String) {
    val context = LocalContext.current
    val user = Manager.getToken(context)
    var light by remember { mutableIntStateOf(0) }
    var selected by remember { mutableStateOf("") }
    var task by remember { mutableStateOf(Task("", "", "", "", "", "", "")) }
    Manager.getTask(name) {
        task = it
    }
    Scaffold(bottomBar = {
        BottomNavigationComponent(navController)
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Background)
        ) {
            ExtendedFloatingActionButton(modifier = Modifier.padding(5.dp),
                containerColor = Background,
                text = { Text(text = "Back", color = TextColor) },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Tasks Icon",
                        tint = TextColor
                    )
                },
                onClick = {
                    navController.navigate(Screens.Tasks.route)
                })
            Text(
                color = TextColor,
                modifier = Modifier.padding(8.dp),
                text = task.title,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Text(
                color = TextColor,
                modifier = Modifier.padding(8.dp),
                text = task.content,
                fontSize = 14.sp
            )
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp, horizontal = 7.dp)
                .background(
                    if (light != 1) Secondary else Primary, RoundedCornerShape(10.dp)
                )
                .clickable {
                    selected = task.wrong_answer_1
                    light = 1
                }) {
                Text(
                    text = task.wrong_answer_1,
                    color = TextColor,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                )
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp, horizontal = 7.dp)
                .background(
                    if (light != 2) Secondary else Primary, RoundedCornerShape(10.dp)
                )
                .clickable {
                    selected = task.wrong_answer_2
                    light = 2
                }) {
                Text(
                    text = task.wrong_answer_2,
                    color = TextColor,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                )
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp, horizontal = 7.dp)
                .background(
                    if (light != 3) Secondary else Primary, RoundedCornerShape(10.dp)
                )
                .clickable {
                    selected = task.wrong_answer_3
                    light = 3
                }) {
                Text(
                    text = task.wrong_answer_3,
                    color = TextColor,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                )
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp, horizontal = 7.dp)
                .background(
                    if (light != 4) Secondary else Primary, RoundedCornerShape(10.dp)
                )
                .clickable {
                    selected = task.wrong_answer_4
                    light = 4
                }) {
                Text(
                    text = task.wrong_answer_4,
                    color = TextColor,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                )
            }
            Button(modifier = Modifier.padding(horizontal = 10.dp), onClick = {
                Manager.getTaskResponse(name, user, selected) {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    if (it == "To'g'ri javob") navController.navigate(Screens.Tasks.route)
                }
            }) {
                Text(text = "Submit")
            }
        }
    }
}