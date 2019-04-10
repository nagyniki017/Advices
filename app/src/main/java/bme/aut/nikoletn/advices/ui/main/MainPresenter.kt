package bme.aut.nikoletn.advices.ui.main

import bme.aut.nikoletn.advices.interactor.advices.AdvicesInteractor
import bme.aut.nikoletn.advices.ui.Presenter
import java.util.concurrent.Executor
import javax.inject.Inject

class MainPresenter @Inject constructor(private val executor: Executor, private val interactor: AdvicesInteractor) :
    Presenter<MainScreen>() {
}