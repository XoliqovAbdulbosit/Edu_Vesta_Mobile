package uz.doston.e_learn.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import uz.doston.e_learn.Navigation.Screens
import uz.doston.e_learn.R
import uz.doston.e_learn.ui.theme.*
import uz.doston.e_learn.ui.theme.Blue
import uz.doston.e_learn.ui.theme.Green


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navController: NavController) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(horizontal = 36.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "EduVesta Logo",
            modifier = Modifier.height(100.dp),
        )
        Spacer(modifier = Modifier.height(42.dp))
        TextField(
            value = username,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { username = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = RoundedCornerShape(16.dp),
            placeholder = { Text(text = "Username", fontSize = 14.sp) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Email,
                    contentDescription = "Email",
                    tint = Background,
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = TextColor,
                unfocusedTextColor = TextColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = TextColor,
                containerColor = Secondary,
            ),
            textStyle = TextStyle(fontSize = 16.sp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = password,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { password = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(16.dp),
            placeholder = { Text(text = "Password", fontSize = 14.sp) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Lock,
                    contentDescription = "Lock",
                    tint = Background,
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = TextColor,
                unfocusedTextColor = TextColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = TextColor,
                containerColor = Secondary,
            ),
            textStyle = TextStyle(fontSize = 16.sp),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        painter = painterResource(id = if (isPasswordVisible) R.drawable.hide else R.drawable.view),
                        contentDescription = null,
                        tint = Background
                    )
                }
            })
        Spacer(modifier = Modifier.height(42.dp))
        Button(
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green),
            onClick = {
                Manager.register(username, password) {
                    if (it == "Success") {
                        Manager.giveToken(context, username)
                        navController.navigate(Screens.Profile.route)
                        Toast.makeText(context, "Successfully signed up", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 62.dp)
        ) {
            Text(
                text = "Register",
                color = TextColor,
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Blue),
            onClick = {
                navController.navigate(Screens.Login.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 62.dp)
        ) {
            Text(
                text = "Log In",
                color = TextColor,
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}