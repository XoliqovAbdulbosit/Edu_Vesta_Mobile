package uz.doston.e_learn.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import uz.doston.e_learn.ui.theme.VerdantGreen


@SuppressLint("MutableCollectionMutableState")
@Composable
fun OlympiadTaskScreen(name: String, length: Int, navController: NavController) {
    var tasks by remember { mutableStateOf<List<Task>>(emptyList()) }
    var cnt by remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    val ans by remember { mutableStateOf(MutableList(length) { "" }) }
    var light by remember { mutableIntStateOf(0) }
    var done by remember { mutableStateOf(false) }
    Manager.getOlympiadTasks(name) {
        tasks = it
        done = true
    }
    if (done) {
        Scaffold(bottomBar = {
            BottomNavigationComponent(navController)
        }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MidnightBlue)
                    .padding(it)
            ) {
                Text(
                    color = LightGrey,
                    modifier = Modifier.padding(8.dp),
                    text = tasks[cnt].title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Text(
                    color = LightGrey,
                    modifier = Modifier.padding(8.dp),
                    text = tasks[cnt].content,
                    fontSize = 14.sp
                )
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp, horizontal = 7.dp)
                    .background(
                        if (light != 1) StormyGrey else DeepSlate, RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        ans[cnt] = tasks[cnt].wrong_answer_1
                        light = 1
                    }) {
                    Text(
                        tasks[cnt].wrong_answer_1,
                        fontSize = 24.sp,
                        color = LightGrey,
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp),
                    )
                }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp, horizontal = 7.dp)
                    .background(
                        if (light != 2) StormyGrey else DeepSlate, RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        ans[cnt] = tasks[cnt].wrong_answer_2
                        light = 2
                    }) {
                    Text(
                        tasks[cnt].wrong_answer_2,
                        fontSize = 24.sp,
                        color = LightGrey,
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp),
                    )
                }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp, horizontal = 7.dp)
                    .background(
                        if (light != 3) StormyGrey else DeepSlate, RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        ans[cnt] = tasks[cnt].wrong_answer_3
                        light = 3
                    }) {
                    Text(
                        tasks[cnt].wrong_answer_3,
                        fontSize = 24.sp,
                        color = LightGrey,
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp),
                    )
                }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp, horizontal = 7.dp)
                    .background(
                        if (light != 4) StormyGrey else DeepSlate, RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        ans[cnt] = tasks[cnt].wrong_answer_4
                        light = 4
                    }) {
                    Text(
                        tasks[cnt].wrong_answer_4,
                        fontSize = 24.sp,
                        color = LightGrey,
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp),
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    if (cnt != length - 1) {
                        Button(modifier = Modifier.padding(horizontal = 5.dp),
                            colors = ButtonDefaults.buttonColors(
                                VerdantGreen
                            ),
                            onClick = {
                                if (cnt > 0) {
                                    cnt -= 1
                                } else {
                                    Toast.makeText(
                                        context, "U yerda savollar yoq", Toast.LENGTH_SHORT
                                    ).show()
                                }
                                light = when (ans[cnt]) {
                                    tasks[cnt].wrong_answer_1 -> 1
                                    tasks[cnt].wrong_answer_2 -> 2
                                    tasks[cnt].wrong_answer_3 -> 3
                                    tasks[cnt].wrong_answer_4 -> 4
                                    else -> 0
                                }
                            }) {
                            Text(
                                text = "Orqaga",
                                fontSize = 19.sp,
                                modifier = Modifier.padding(horizontal = 5.dp)
                            )
                        }
                        Button(modifier = Modifier.padding(horizontal = 5.dp),
                            colors = ButtonDefaults.buttonColors(
                                VerdantGreen
                            ),
                            onClick = {
                                if (cnt + 1 < ans.size) {
                                    cnt += 1
                                } else {
                                    Toast.makeText(
                                        context, "U yerda savollar yoq", Toast.LENGTH_SHORT
                                    ).show()
                                }
                                light = when (ans[cnt]) {
                                    tasks[cnt].wrong_answer_1 -> 1
                                    tasks[cnt].wrong_answer_2 -> 2
                                    tasks[cnt].wrong_answer_3 -> 3
                                    tasks[cnt].wrong_answer_4 -> 4
                                    else -> 0
                                }
                            }) {
                            Text(
                                text = "Oldinga",
                                fontSize = 19.sp,
                                modifier = Modifier.padding(horizontal = 5.dp)
                            )
                        }
                    } else {
                        Button(modifier = Modifier.padding(horizontal = 5.dp),
                            colors = ButtonDefaults.buttonColors(
                                VerdantGreen
                            ),
                            onClick = {
                                if (cnt > 0) {
                                    cnt -= 1
                                } else {
                                    Toast.makeText(
                                        context, "U yerda savollar yoq", Toast.LENGTH_SHORT
                                    ).show()
                                }
                                light = when (ans[cnt]) {
                                    tasks[cnt].wrong_answer_1 -> 1
                                    tasks[cnt].wrong_answer_2 -> 2
                                    tasks[cnt].wrong_answer_3 -> 3
                                    tasks[cnt].wrong_answer_4 -> 4
                                    else -> 0
                                }
                            }) {
                            Text(
                                text = "Orqaga",
                                fontSize = 19.sp,
                                modifier = Modifier.padding(horizontal = 5.dp)
                            )
                        }
                        Button(modifier = Modifier.padding(horizontal = 5.dp),
                            colors = ButtonDefaults.buttonColors(
                                VerdantGreen
                            ),
                            onClick = {
                                Manager.getOlympiadResponse(Manager.getToken(context), name, ans) {
                                    Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }) {
                            Text(
                                text = "Tugatish",
                                fontSize = 19.sp,
                                modifier = Modifier.padding(horizontal = 5.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}