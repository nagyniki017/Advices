package bme.aut.nikoletn.advices.test

import bme.aut.nikoletn.advices.model.Advice
import bme.aut.nikoletn.advices.testInjector
import bme.aut.nikoletn.advices.ui.addAdvice.AddAdvicePresenter
import bme.aut.nikoletn.advices.ui.addAdvice.AddAdviceScreen
import bme.aut.nikoletn.advices.utils.mock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
class AddAdviceTest {
    @Inject
    lateinit var addAdvicePresenter: AddAdvicePresenter
    private lateinit var addAdviceScreen: AddAdviceScreen

    @Throws(Exception::class)
    @Before
    fun setup() {
        testInjector.inject(this)
        addAdviceScreen = mock()
        addAdvicePresenter.attachScreen(addAdviceScreen)
    }

    @Test
    fun testAdviceAdded() {
        val advice = Advice()
        addAdvicePresenter.addAdvice(advice)
        verify(addAdviceScreen).closeDialog()
    }

    @After
    fun tearDown() {
        addAdvicePresenter.detachScreen()
    }
}