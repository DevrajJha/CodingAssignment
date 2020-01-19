package devraj.codingassignment.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import devraj.codingassignment.CodingApplication
import devraj.codingassignment.di.modules.ActivityModule
import devraj.codingassignment.di.modules.ApiModule
import devraj.codingassignment.di.modules.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, ViewModelModule::class, AndroidSupportInjectionModule::class, ActivityModule::class])
interface ApiComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun apiModule(apiModule: ApiModule): Builder

        fun build(): ApiComponent
    }

    abstract fun inject(appController: CodingApplication)
}