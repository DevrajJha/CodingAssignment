package devraj.codingassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import devraj.codingassignment.model.Users
import devraj.codingassignment.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val repository: Repository): ViewModel() {


        var userResult: MutableLiveData<List<Users>> ?= null
        var userError: MutableLiveData<String> = MutableLiveData()
        var userLoader: MutableLiveData<Boolean> = MutableLiveData()
        private lateinit var disposableObserver: DisposableObserver<List<Users>>

        fun userResult(language:String,since:String): LiveData<List<Users>> {
            if (userResult==null) {
                userResult=MutableLiveData()
                loaduser(language, since)
            }
            return userResult as MutableLiveData
        }

        fun userError(): LiveData<String> {
            return userError
        }

        fun userLoader(): LiveData<Boolean> {
            return userLoader
        }

        fun loaduser(language:String,since:String) {
            userLoader.postValue(true)
            disposableObserver = object : DisposableObserver<List<Users>>() {
                override fun onComplete() {

                }

                override fun onNext(userList: List<Users>) {
                    userResult!!.postValue(userList)
                    userLoader.postValue(false)
                }

                override fun onError(e: Throwable) {
                    userError.postValue(e.message)
                    userLoader.postValue(false)
                }
            }

            repository.getUserList(language, since)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(400, TimeUnit.MILLISECONDS)
                .subscribe(disposableObserver)
        }

        fun disposeElements() {
            if (!disposableObserver.isDisposed) disposableObserver.dispose()
        }
}