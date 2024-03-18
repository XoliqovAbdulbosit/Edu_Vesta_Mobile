package uz.doston.e_learn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.navigation.NavHostController
import uz.doston.e_learn.ui.theme.Background
import uz.doston.e_learn.ui.theme.Primary
import uz.doston.e_learn.ui.theme.Secondary
import uz.doston.e_learn.ui.theme.TextColor


@Composable
fun TasksScreen(navController: NavHostController) {
    var categories by remember {
        mutableStateOf(listOf<String>())
    }
    var grades by remember {
        mutableStateOf(listOf<String>())
    }
    var tasks by remember {
        mutableStateOf(listOf<String>())
    }
    var category by remember {
        mutableStateOf("All")
    }
    var grade by remember {
        mutableStateOf("All")
    }
    Manager.getCategoriesList {
        categories = it
    }
    Manager.getGradesList {
        grades = it
    }
    Manager.getTasks {
        tasks = it
    }
    Scaffold(bottomBar = {
        BottomNavigationComponent(navController)
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(it)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(categories.size + 1) {
                    val cnt_category = if (it == 0) "All" else categories[it - 1]
                    Column(modifier = Modifier
                        .clickable {
                            category = cnt_category
                            Manager.getTasksByFilter(category, grade) {
                                tasks = it
                            }
                        }
                        .padding(horizontal = 5.dp)
                        .background(Secondary, RoundedCornerShape(8.dp))) {
                        Text(
                            modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
                            text = cnt_category,
                            color = if (category != cnt_category) TextColor else Primary,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(grades.size + 1) {
                    val cnt_grade = if (it == 0) "All" else grades[it - 1]
                    Column(modifier = Modifier
                        .clickable {
                            grade = cnt_grade
                            Manager.getTasksByFilter(category, grade) {
                                tasks = it
                            }
                        }
                        .padding(horizontal = 5.dp)
                        .background(Secondary, RoundedCornerShape(10.dp))) {
                        Text(
                            modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
                            text = "$cnt_grade grade",
                            color = if (grade != cnt_grade) TextColor else Primary,
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp)
            ) {
                items(tasks) {
                    TaskItem(task = it, navController = navController)
                }
            }
        }
    }
}


@Composable
fun TaskItem(task: String, navController: NavController) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 5.dp)
        .background(Secondary, RoundedCornerShape(6.dp))
        .clickable {
            navController.navigate("Task/${task}")
        }) {
        Text(
            color = TextColor,
            modifier = Modifier.padding(8.dp),
            text = task,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
    }
}