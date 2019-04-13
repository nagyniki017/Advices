package bme.aut.nikoletn.advices

import bme.aut.nikoletn.advices.interactor.InteractorModule
import bme.aut.nikoletn.advices.network.NetworkModule
import bme.aut.nikoletn.advices.ui.UIModule
import bme.aut.nikoletn.advices.ui.addAdvice.AddAdviceDialogFragment
import bme.aut.nikoletn.advices.ui.main.MainActivity
import bme.aut.nikoletn.advices.ui.randomAdvices.RandomAdviceFragment
import bme.aut.nikoletn.advices.ui.savedAdvices.SavedAdviceFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(UIModule::class, NetworkModule::class, InteractorModule::class))
interface AdvicesApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(randomAdviceFragment: RandomAdviceFragment)
    fun inject(savedAdviceFragment: SavedAdviceFragment)
    fun inject(addAdviceFragment: AddAdviceDialogFragment)
}