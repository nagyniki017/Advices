package bme.aut.nikoletn.advices.ui

import android.content.Context
import bme.aut.nikoletn.advices.interactor.advices.AdvicesInteractor
import bme.aut.nikoletn.advices.ui.main.MainPresenter
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class UIModule(private val context: Context) {

    @Provides
    fun context() = context

    @Provides
    @Singleton
    fun provideMainPresenter(executor: Executor, advicesInteractor: AdvicesInteractor) = MainPresenter(executor, advicesInteractor)

    @Provides
    @Singleton
    fun provideNetworkExecutor(): Executor = Executors.newFixedThreadPool(1)
}