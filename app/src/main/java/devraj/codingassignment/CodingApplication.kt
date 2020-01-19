package devraj.codingassignment

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import devraj.codingassignment.di.component.DaggerApiComponent
import devraj.codingassignment.di.modules.ApiModule
import javax.inject.Inject

class CodingApplication: Application(), HasActivityInjector {
    @Inject
     lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }



    override fun onCreate() {
        super.onCreate()
        initDI()

    }


     private fun initDI() {
        DaggerApiComponent.builder()
            .application(this)
            .apiModule(ApiModule())

            .build()
            .inject(this)

    }
}