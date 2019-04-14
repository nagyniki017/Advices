package bme.aut.nikoletn.advices.ui.randomAdvices

import bme.aut.nikoletn.advices.interactor.advices.AdvicesInteractor
import bme.aut.nikoletn.advices.interactor.advices.events.GetRandomAdviceEvent
import bme.aut.nikoletn.advices.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class RandomAdvicePresenter @Inject constructor(private val executor: Executor, private val advicesInteractor: AdvicesInteractor) : Presenter<RandomAdviceScreen>() {

    override fun attachScreen(screen: RandomAdviceScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    fun getRandomAdvice() {
        executor.execute {
            advicesInteractor.getRandomAdvice()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: GetRandomAdviceEvent) {
        if (event.throwable != null) {
            event.throwable?.printStackTrace()
            if (screen != null) {
                screen?.showNetworkError(event.throwable?.message.orEmpty())
            }
        } else {
            if (screen != null) {
                if (event.advice != null) {
                    screen?.showAdvice(event.advice!!)
                }

            }
        }
    }
}