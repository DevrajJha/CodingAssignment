package devraj.codingassignment.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import devraj.codingassignment.ui.MainActivity
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity
}