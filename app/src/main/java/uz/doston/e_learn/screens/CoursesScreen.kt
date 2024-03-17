package uz.doston.e_learn.screens

import Manager
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
import uz.doston.e_learn.ui.theme.AshGrey
import uz.doston.e_learn.ui.theme.Charcoal
import uz.doston.e_learn.ui.theme.LightGrey
import uz.doston.e_learn.ui.theme.MidnightBlue
import uz.doston.e_learn.ui.theme.StormyGrey


@Composable
fun CoursesScreen(navController: NavController) {
    var categories by remember {
        mutableStateOf(listOf<String>())
    }
    var grades by remember {
        mutableStateOf(listOf<String>())
    }
    var lessons by remember {
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
    Manager.getLessons {
        lessons = it
    }
    Scaffold(bottomBar = {
        BottomNavigationComponent(navController)
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MidnightBlue)
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
                            Manager.getLessonsByFilter(category, grade) {
                                lessons = it
                            }
                        }
                        .padding(horizontal = 5.dp)
                        .background(StormyGrey, RoundedCornerShape(8.dp))) {
                        Text(
                            modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
                            text = cnt_category,
                            color = if (category != cnt_category) LightGrey else AshGrey,
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
                            Manager.getLessonsByFilter(category, grade) {
                                lessons = it
                            }
                        }
                        .padding(horizontal = 5.dp)
                        .background(StormyGrey, RoundedCornerShape(10.dp))) {
                        Text(
                            modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
                            text = "$cnt_grade grade",
                            color = if (grade != cnt_grade) LightGrey else AshGrey,
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp)
            ) {
                items(lessons) {
                    PlaceItem(lesson = it, navController = navController)
                }
            }
        }
    }
}

@Composable
fun PlaceItem(lesson: String, navController: NavController) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 5.dp)
        .background(Charcoal, RoundedCornerShape(6.dp))
        .clickable {
            navController.navigate("Lesson/$lesson")
        }) {
        Text(
            color = LightGrey,
            modifier = Modifier.padding(8.dp),
            text = lesson,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
    }
}