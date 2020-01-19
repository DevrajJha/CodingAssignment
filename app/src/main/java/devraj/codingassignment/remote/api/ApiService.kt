package devraj.codingassignment.remote.api


import devraj.codingassignment.model.Users
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("developers")
    fun getUserDetails(@Query("language") language:String,@Query("since") since:String):Observable<List<Users>>

}