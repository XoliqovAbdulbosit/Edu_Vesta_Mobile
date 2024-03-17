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
import androidx.compose.material3.Button
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
import uz.doston.e_learn.model.Task
import uz.doston.e_learn.ui.theme.DeepSlate
import uz.doston.e_learn.ui.theme.LightGrey
import uz.doston.e_learn.ui.theme.MidnightBlue
import uz.doston.e_learn.ui.theme.StormyGrey


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
                .background(MidnightBlue)
        ) {
            Text(
                color = LightGrey,
                modifier = Modifier.padding(8.dp),
                text = task.title,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Text(
                color = LightGrey,
                modifier = Modifier.padding(8.dp),
                text = task.content,
                fontSize = 14.sp
            )
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp, horizontal = 7.dp)
                .background(
                    if (light != 1) StormyGrey else DeepSlate, RoundedCornerShape(10.dp)
                )
                .clickable {
                    selected = task.wrong_answer_1
                    light = 1
                }) {
                Text(
                    text = task.wrong_answer_1,
                    color = LightGrey,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                )
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp, horizontal = 7.dp)
                .background(
                    if (light != 2) StormyGrey else DeepSlate, RoundedCornerShape(10.dp)
                )
                .clickable {
                    selected = task.wrong_answer_2
                    light = 2
                }) {
                Text(
                    text = task.wrong_answer_2,
                    color = LightGrey,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                )
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp, horizontal = 7.dp)
                .background(
                    if (light != 3) StormyGrey else DeepSlate, RoundedCornerShape(10.dp)
                )
                .clickable {
                    selected = task.wrong_answer_3
                    light = 3
                }) {
                Text(
                    text = task.wrong_answer_3,
                    color = LightGrey,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                )
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp, horizontal = 7.dp)
                .background(
                    if (light != 4) StormyGrey else DeepSlate, RoundedCornerShape(10.dp)
                )
                .clickable {
                    selected = task.wrong_answer_4
                    light = 4
                }) {
                Text(
                    text = task.wrong_answer_4,
                    color = LightGrey,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                )
            }
            Button(modifier = Modifier.padding(horizontal = 10.dp), onClick = {
                Manager.getTaskResponse(name, user, selected) {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }) {
                Text(text = "Submit")
            }
        }
    }
}