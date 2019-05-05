package bme.aut.nikoletn.advices.test

import bme.aut.nikoletn.advices.model.Advice
import bme.aut.nikoletn.advices.testInjector
import bme.aut.nikoletn.advices.ui.randomAdvices.RandomAdvicePresenter
import bme.aut.nikoletn.advices.ui.randomAdvices.RandomAdviceScreen
import bme.aut.nikoletn.advices.utils.argumentCaptor
import bme.aut.nikoletn.advices.utils.mock
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
class RandomAdviceTest {
    @Inject
    lateinit var randomAdvicePresenter: RandomAdvicePresenter
    private lateinit var randomAdviceScreen: RandomAdviceScreen

    @Throws(Exception::class)
    @Before
    fun setup() {
        testInjector.inject(this)
        randomAdviceScreen = mock()
        randomAdvicePresenter.attachScreen(randomAdviceScreen)
    }

    @Test
    fun testGetRandomAdvice() {
        randomAdvicePresenter.getRandomAdvice()
        val advice = argumentCaptor<Advice>()
        verify(randomAdviceScreen).showAdvice(advice.capture())
        val gotAdvice = advice.value
        assertNotNull(gotAdvice)
        assert(gotAdvice.id == 5)
        assert(gotAdvice.advice.equals("Ever tried. Ever failed. No matter. Try again. Fail again. Fail better."))
    }

    @After
    fun tearDown() {
        randomAdvicePresenter.detachScreen()
    }
}