package ru.mullin.cryptoapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.mullin.cryptoapp.presentation.CoinDetailFragment
import ru.mullin.cryptoapp.presentation.CoinPriceListActivity


@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(fragment: CoinDetailFragment)

    fun inject(activity: CoinPriceListActivity)

    @Component.Factory
    interface AppComponentFactory {

        fun create(
            @BindsInstance application: Application
        ): AppComponent

    }
}