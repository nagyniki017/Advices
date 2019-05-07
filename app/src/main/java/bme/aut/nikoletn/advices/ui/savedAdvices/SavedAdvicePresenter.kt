package bme.aut.nikoletn.advices.ui.savedAdvices

import bme.aut.nikoletn.advices.interactor.advices.AdvicesInteractor
import bme.aut.nikoletn.advices.interactor.advices.events.AddAdviceEvent
import bme.aut.nikoletn.advices.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class SavedAdvicePresenter @Inject constructor(
    private val executor: Executor,
    private val advicesInteractor: AdvicesInteractor
) : Presenter<SavedAdviceScreen>() {
    override fun attachScreen(screen: SavedAdviceScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this) // @Subscribe is needed for it
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this) // @Subscribe method is needed for it
        super.detachScreen()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: AddAdviceEvent) {
        if (screen != null) {
            if (event.advice != null) {
                screen?.addNewAdvice(event.advice)
            }
        }
    }
}