package bme.aut.nikoletn.advices

import bme.aut.nikoletn.advices.interactor.InteractorModule
import bme.aut.nikoletn.advices.mock.MockDatabaseModule
import bme.aut.nikoletn.advices.mock.MockNetworkModule
import bme.aut.nikoletn.advices.test.MainTest
import bme.aut.nikoletn.advices.test.RandomAdviceTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MockNetworkModule::class, TestModule::class, InteractorModule::class, MockDatabaseModule::class])
interface TestComponent : AdvicesApplicationComponent {
    fun inject(mainTest: MainTest)
    fun inject(randomAdviceTest: RandomAdviceTest)
}