package uz.doston.e_learn.model

data class Olympiad(
    val name: String,
    val start_time: String,
    val end_time: String,
    val tasks: List<Task>,
)

data class Lesson(
    val title: String,
    val content: String,
    val grade: String,
    val subject: String,
    val link: String
)

data class Task(
    val title: String,
    val content: String,
    val answer: String,
    val wrong_answer_1: String,
    val wrong_answer_2: String,
    val wrong_answer_3: String,
    val wrong_answer_4: String,
)

data class User(
    val user__username: String,
    val cnt: Int,
)

data class SolvedInfo(
    val cnt: Int,
    val detailed: List<List<Any>>,
)

data class Profile(
    val username: String,
    val joined: String,
    val ln: Int,
    val rank: Int,
    val solved: SolvedInfo,
)