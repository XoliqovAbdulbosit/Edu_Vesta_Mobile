import android.content.Context
import android.content.SharedPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.doston.e_learn.model.APIClient
import uz.doston.e_learn.model.APIService
import uz.doston.e_learn.model.Lesson
import uz.doston.e_learn.model.Olympiad
import uz.doston.e_learn.model.Profile
import uz.doston.e_learn.model.Task
import uz.doston.e_learn.model.User

class Manager {
    companion object {
        fun login(username: String, password: String, callback: (String) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.login(username, password).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    callback("")
                }
            })
        }

        fun register(username: String, password: String, callback: (String) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.register(username, password).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    callback("")
                }
            })
        }

        fun getCategoriesList(callback: (List<String>) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getCategories().enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>, response: Response<List<String>>
                ) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    callback(emptyList())
                }
            })
        }

        fun getGradesList(callback: (List<String>) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getGrades().enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>, response: Response<List<String>>
                ) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    callback(emptyList())
                }
            })
        }

        fun getLessons(callback: (List<String>) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getLessons().enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>, response: Response<List<String>>
                ) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    callback(emptyList())
                }
            })
        }

        fun getLessonsByFilter(category: String, grade: String, callback: (List<String>) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getLessonsByFilter(category, grade).enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>, response: Response<List<String>>
                ) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    callback(emptyList())
                }
            })
        }

        fun getTasks(callback: (List<String>) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getTasks().enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>, response: Response<List<String>>
                ) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    callback(emptyList())
                }
            })
        }

        fun getTasksByFilter(category: String, grade: String, callback: (List<String>) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getTasksByFilter(category, grade).enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>, response: Response<List<String>>
                ) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    callback(emptyList())
                }
            })
        }

        fun giveToken(context: Context, user: String) {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("db", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("user", user)
            editor.apply()
        }

        fun getToken(context: Context): String {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("db", Context.MODE_PRIVATE)
            return sharedPreferences.getString("user", "") ?: ""
        }

        fun getTask(name: String, callback: (Task) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getTask(name).enqueue(object : Callback<Task> {
                override fun onResponse(call: Call<Task>, response: Response<Task>) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<Task>, t: Throwable) {

                }
            })
        }

        fun getLesson(name: String, callback: (Lesson) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getLesson(name).enqueue(object : Callback<Lesson> {
                override fun onResponse(call: Call<Lesson>, response: Response<Lesson>) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<Lesson>, t: Throwable) {

                }
            })
        }

        fun getTaskResponse(
            name: String, user: String, selected: String, callback: (String) -> Unit
        ) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.postTaskResponse(user, name, selected).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    callback("")
                }
            })
        }

        fun getOlympiadsList(callback: (List<String>) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getOlympiads().enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>, response: Response<List<String>>
                ) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    callback(emptyList())
                }
            })
        }

        fun getOlympiad(name: String, callback: (Olympiad) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getOlympiad(name).enqueue(object : Callback<Olympiad> {
                override fun onResponse(call: Call<Olympiad>, response: Response<Olympiad>) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<Olympiad>, t: Throwable) {

                }
            })
        }

        fun getOlympiadTasks(name: String, callback: (List<Task>) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getOlympiad(name).enqueue(object : Callback<Olympiad> {
                override fun onResponse(call: Call<Olympiad>, response: Response<Olympiad>) {
                    callback(response.body()!!.tasks)
                }

                override fun onFailure(call: Call<Olympiad>, t: Throwable) {
                    callback(emptyList())
                }
            })
        }

        fun getOlympiadResponse(
            user: String, name: String, ans: List<String>, callback: (Int) -> Unit
        ) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.postOlympiadResponse(user, name, ans).enqueue(object : Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    callback(0)
                }
            })
        }

        fun getRatingList(callback: (List<User>) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getRating().enqueue(object : Callback<List<User>> {
                override fun onResponse(
                    call: Call<List<User>>, response: Response<List<User>>
                ) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    callback(emptyList())
                }
            })
        }

        fun getProfileInfo(user: String, callback: (Profile) -> Unit) {
            val api = APIClient.getInstance().create(APIService::class.java)
            api.getProfile(user).enqueue(object : Callback<Profile> {
                override fun onResponse(
                    call: Call<Profile>, response: Response<Profile>
                ) {
                    callback(response.body()!!)
                }

                override fun onFailure(call: Call<Profile>, t: Throwable) {

                }
            })
        }
    }
}