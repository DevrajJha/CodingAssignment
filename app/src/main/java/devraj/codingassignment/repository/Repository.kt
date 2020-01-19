package devraj.codingassignment.repository

import devraj.codingassignment.model.Users
import devraj.codingassignment.remote.api.ApiService
import io.reactivex.Observable
import javax.inject.Inject

class Repository@Inject constructor(private val apiService: ApiService) {

    fun getUserList(language:String,since:String): Observable<List<Users>> {
        var observableFromApi: Observable<List<Users>>? = null
            observableFromApi = getUserListFromApi(language,since)
        return observableFromApi
    }

    private fun getUserListFromApi(language:String,since:String): Observable<List<Users>> {
        return apiService.getUserDetails(language,since)
            .doOnNext {
            }
    }


}