package uz.doston.e_learn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import uz.doston.e_learn.Navigation.Screens
import uz.doston.e_learn.model.Lesson
import uz.doston.e_learn.ui.theme.Background
import uz.doston.e_learn.ui.theme.TextColor


@Composable
fun LessonScreen(navController: NavController, key: String) {
    var lesson by remember { mutableStateOf(Lesson("", "", "", "", "")) }
    Manager.getLesson(key) {
        lesson = it
    }
    val cnt = LocalLifecycleOwner.current
    Scaffold(bottomBar = {
        BottomNavigationComponent(navController)
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                ExtendedFloatingActionButton(modifier = Modifier.padding(5.dp),
                    containerColor = Background,
                    text = { Text(text = "Back", color = TextColor) },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Lessons Icon",
                            tint = TextColor
                        )
                    },
                    onClick = {
                        navController.navigate(Screens.Lessons.route)
                    })
                Text(
                    color = TextColor,
                    modifier = Modifier.padding(8.dp),
                    text = lesson.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Text(
                    color = TextColor,
                    modifier = Modifier.padding(8.dp),
                    text = lesson.content,
                    fontSize = 14.sp
                )
                AndroidView(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .clip(RoundedCornerShape(16.dp)), factory = { context ->
                    YouTubePlayerView(context).apply {
                        cnt.lifecycle.addObserver(this)

                        addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                            override fun onReady(youTubePlayer: YouTubePlayer) {
                                youTubePlayer.loadVideo(lesson.link, 0f)
                            }
                        })
                    }
                })
            }
        }
    }
}