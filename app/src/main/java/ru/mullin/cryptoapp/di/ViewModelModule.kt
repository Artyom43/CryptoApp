package ru.mullin.cryptoapp.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import ru.mullin.cryptoapp.presentation.CoinViewModel
import kotlin.reflect.KClass

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    fun bindCoinViewModel(viewModel: CoinViewModel): ViewModel
}

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val key: KClass<out ViewModel>)