package ru.mullin.cryptoapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.mullin.cryptoapp.CoinApp
import ru.mullin.cryptoapp.presentation.CoinDetailFragment
import ru.mullin.cryptoapp.presentation.CoinPriceListActivity


@AppScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(fragment: CoinDetailFragment)

    fun inject(activity: CoinPriceListActivity)

    fun inject(application: CoinApp)

    @Component.Factory
    interface AppComponentFactory {

        fun create(
            @BindsInstance application: Application
        ): AppComponent

    }
}