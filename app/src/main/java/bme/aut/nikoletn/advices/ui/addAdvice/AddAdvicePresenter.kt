package bme.aut.nikoletn.advices.ui.addAdvice

import bme.aut.nikoletn.advices.interactor.advices.AdvicesInteractor
import bme.aut.nikoletn.advices.interactor.advices.events.AddAdviceEvent
import bme.aut.nikoletn.advices.model.Advice
import bme.aut.nikoletn.advices.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class AddAdvicePresenter @Inject constructor(
    private val executor: Executor,
    private val advicesInteractor: AdvicesInteractor
) : Presenter<AddAdviceScreen>() {

    fun addAdvice(advice: Advice) {
        executor.execute {
            advicesInteractor.addAdvice(advice)
        }
    }

    override fun attachScreen(screen: AddAdviceScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: AddAdviceEvent) {
        if (screen != null) {
            if (event.advice != null) {
                screen?.closeDialog()
            }
        }
    }
}