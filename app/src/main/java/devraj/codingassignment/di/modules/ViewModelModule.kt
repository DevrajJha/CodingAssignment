package devraj.codingassignment.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import devraj.codingassignment.di.ViewModelFactory
import devraj.codingassignment.viewmodel.UserViewModel

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    protected abstract fun userViewModel(userViewModel: UserViewModel): ViewModel



}