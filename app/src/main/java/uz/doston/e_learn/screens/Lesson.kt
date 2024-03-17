package uz.doston.e_learn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import uz.doston.e_learn.model.Lesson
import uz.doston.e_learn.ui.theme.DeepSlate
import uz.doston.e_learn.ui.theme.LightGrey


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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DeepSlate)
                .padding(it)
        ) {
            Text(
                color = LightGrey,
                modifier = Modifier.padding(8.dp),
                text = lesson.title,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Text(
                color = LightGrey,
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