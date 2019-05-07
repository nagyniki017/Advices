package bme.aut.nikoletn.advices

import android.content.Context
import bme.aut.nikoletn.advices.interactor.advices.AdvicesInteractor
import bme.aut.nikoletn.advices.ui.main.MainPresenter
import bme.aut.nikoletn.advices.utils.UiExecutor
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
class TestModule(private val context: Context) {
    @Provides
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideMainPresenter(executor: Executor, advicesInteractor: AdvicesInteractor) = MainPresenter(executor, advicesInteractor)

    @Provides
    @Singleton
    fun provideNetworkExecutor(): Executor = UiExecutor()
}