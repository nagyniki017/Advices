package bme.aut.nikoletn.advices.interactor

import bme.aut.nikoletn.advices.interactor.advices.AdvicesInteractor
import bme.aut.nikoletn.advices.network.AdvicesApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {
    @Provides
    @Singleton
    fun provideAdvicesInteractor(advicesApi: AdvicesApi) = AdvicesInteractor(advicesApi)
}