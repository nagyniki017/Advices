package bme.aut.nikoletn.advices

import bme.aut.nikoletn.advices.interactor.InteractorModule
import bme.aut.nikoletn.advices.network.NetworkModule
import bme.aut.nikoletn.advices.ui.UIModule
import bme.aut.nikoletn.advices.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(UIModule::class, NetworkModule::class, InteractorModule::class))
interface AdvicesApplicationComponent {
    fun inject(mainActivity: MainActivity)
}