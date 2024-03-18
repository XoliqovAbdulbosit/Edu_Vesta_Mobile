package uz.doston.e_learn.screens

import Manager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import uz.doston.e_learn.Navigation.Screens
import uz.doston.e_learn.R
import uz.doston.e_learn.model.Profile
import uz.doston.e_learn.model.SolvedInfo
import uz.doston.e_learn.ui.theme.Blue
import uz.doston.e_learn.ui.theme.Background
import uz.doston.e_learn.ui.theme.TextColor
import uz.doston.e_learn.ui.theme.Red
import uz.doston.e_learn.ui.theme.Secondary
import uz.doston.e_learn.ui.theme.Green


@Composable
fun ProfileScreen(navController: NavHostController) {
    var user by remember { mutableStateOf(Profile("", "", 0, 0, SolvedInfo(0, emptyList()))) }
    val context = LocalContext.current
    val username = Manager.getToken(context)
    if (username != "") {
        Manager.getProfileInfo(username) {
            user = it
        }
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile Icon",
                    modifier = Modifier
                        .size(75.dp)
                        .padding(10.dp),
                    tint = Secondary,
                )
                Column {
                    Text(
                        text = user.username,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextColor
                    )
                    Text(text = user.joined, fontSize = 18.sp, color = TextColor)
                }
                ExtendedFloatingActionButton(modifier = Modifier.padding(start = 20.dp),
                    containerColor = Red,
                    text = { Text(text = "Log Out", color = TextColor) },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.ExitToApp,
                            contentDescription = "Log Out Icon",
                            tint = TextColor
                        )
                    },
                    onClick = {
                        Manager.giveToken(context, "")
                        navController.navigate(Screens.Login.route)
                    })
            }
            Row(modifier = Modifier.padding(vertical = 10.dp)) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.award_solid),
                        contentDescription = "Award Icon",
                        modifier = Modifier.size(40.dp),
                        tint = TextColor,
                    )
                    Text(text = user.rank.toString(), fontSize = 32.sp, color = TextColor)
                }
                Column(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.list_check_solid),
                        contentDescription = "List Icon",
                        modifier = Modifier.size(40.dp),
                        tint = TextColor,
                    )
                    Text(
                        text = "${user.solved.cnt} / ${user.ln}",
                        fontSize = 32.sp,
                        color = TextColor
                    )
                }
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                user.solved.detailed.forEach {
                    Row(modifier = Modifier.padding(vertical = 10.dp)) {
                        Text(
                            text = it[0] as String,
                            fontSize = 20.sp,
                            color = TextColor,
                            modifier = Modifier
                                .width(120.dp)
                                .padding(horizontal = 17.dp, vertical = 10.dp)
                        )
                        Row {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .background(
                                        Green, RoundedCornerShape(20.dp)
                                    )
                                    .width(80.dp)
                                    .padding(horizontal = 10.dp, vertical = 5.dp)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.check_solid),
                                    contentDescription = "Solved Icon",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(10.dp),
                                    tint = TextColor,
                                )
                                Text(text = it[1].toString(), color = TextColor)
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .background(
                                        Blue, RoundedCornerShape(20.dp)
                                    )
                                    .width(80.dp)
                                    .padding(horizontal = 10.dp, vertical = 5.dp)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.strava),
                                    contentDescription = "Send Icon",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(10.dp),
                                    tint = TextColor,
                                )
                                Text(text = it[2].toString(), color = TextColor)
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .background(
                                        Red, RoundedCornerShape(20.dp)
                                    )
                                    .width(80.dp)
                                    .padding(horizontal = 10.dp, vertical = 5.dp)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.xmark_solid),
                                    contentDescription = "Error Icon",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(10.dp),
                                    tint = TextColor,
                                )
                                Text(text = it[3].toString(), color = TextColor)
                            }
                        }
                    }
                }
            }
        }
    }
}