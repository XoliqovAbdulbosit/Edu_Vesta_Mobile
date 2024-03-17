package uz.doston.e_learn.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIService {
    @GET("categories")
    fun getCategories(): Call<List<String>>

    @GET("grades")
    fun getGrades(): Call<List<String>>

    @GET("login/{username}/{password}")
    fun login(@Path("username") username: String, @Path("password") password: String): Call<String>

    @GET("register/{username}/{password}")
    fun register(
        @Path("username") username: String, @Path("password") password: String
    ): Call<String>

    @GET("lessons/")
    fun getLessons(): Call<List<String>>

    @GET("lessons/{category}/{grade}")
    fun getLessonsByFilter(
        @Path("category") category: String, @Path("grade") grade: String
    ): Call<List<String>>

    @GET("lesson/{name}")
    fun getLesson(@Path("name") name: String): Call<Lesson>

    @GET("tasks")
    fun getTasks(): Call<List<String>>

    @GET("tasks/{category}/{grade}")
    fun getTasksByFilter(
        @Path("category") category: String, @Path("grade") grade: String
    ): Call<List<String>>

    @GET("task/{name}")
    fun getTask(@Path("name") name: String): Call<Task>

    @POST("task/{user}/{name}")
    fun postTaskResponse(
        @Path("user") user: String, @Path("name") name: String, @Body string: String
    ): Call<String>

    @GET("olympiads/")
    fun getOlympiads(): Call<List<String>>

    @GET("olympiad/{name}")
    fun getOlympiad(@Path("name") name: String): Call<Olympiad>

    @POST("olympiad/{user}/{name}")
    fun postOlympiadResponse(
        @Path("user") user: String, @Path("name") name: String, @Body strings: List<String>
    ): Call<Int>

    @GET("rating/")
    fun getRating(): Call<List<User>>

    @GET("profile/{user}")
    fun getProfile(@Path("user") user: String): Call<Profile>
}