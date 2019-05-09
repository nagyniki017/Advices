package bme.aut.nikoletn.advices.test

import bme.aut.nikoletn.advices.model.Advice
import bme.aut.nikoletn.advices.interactor.advices.events.AddAdviceEvent
import bme.aut.nikoletn.advices.testInjector
import bme.aut.nikoletn.advices.ui.savedAdvices.SavedAdvicePresenter
import bme.aut.nikoletn.advices.ui.savedAdvices.SavedAdviceScreen
import bme.aut.nikoletn.advices.utils.argumentCaptor
import bme.aut.nikoletn.advices.utils.mock
import org.greenrobot.eventbus.EventBus
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
class SavedAdviceTest {

    @Inject
    lateinit var savedAdvicePresenter: SavedAdvicePresenter
    private lateinit var savedAdviceScreen: SavedAdviceScreen

    @Throws(Exception::class)
    @Before
    fun setup() {
        testInjector.inject(this)
        savedAdviceScreen = mock()
        savedAdvicePresenter.attachScreen(savedAdviceScreen)
    }

    @Test
    fun testAddAdvice() {
        val advice = Advice()
        advice.id = 11
        advice.advice = "Best advice"
        val event = AddAdviceEvent()
        event.advice = advice

        EventBus.getDefault().post(event)
        verify(savedAdviceScreen).addNewAdvice(advice)
    }

    @After
    fun tearDown() {
        savedAdvicePresenter.detachScreen()
    }
}