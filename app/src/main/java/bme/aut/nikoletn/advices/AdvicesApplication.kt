package bme.aut.nikoletn.advices

import android.app.Application
import bme.aut.nikoletn.advices.ui.UIModule

class AdvicesApplication : Application() {
    lateinit var injector: AdvicesApplicationComponent;

    override fun onCreate() {
        super.onCreate()
        injector = DaggerAdvicesApplicationComponent.builder()
            .uIModule(UIModule(this))
            .build()
    }
}