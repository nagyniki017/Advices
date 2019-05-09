package bme.aut.nikoletn.advices.test

import bme.aut.nikoletn.advices.testInjector
import bme.aut.nikoletn.advices.ui.main.MainPresenter
import bme.aut.nikoletn.advices.ui.main.MainScreen
import bme.aut.nikoletn.advices.utils.mock
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject

// @RunWith(RobolectricTestRunner::class)
class MainTest {
    @Inject
    lateinit var mainPresenter: MainPresenter
    private lateinit var mainScreen: MainScreen

    @Throws(Exception::class)
    @Before
    fun setup() {
        testInjector.inject(this)
        mainScreen = mock()
        mainPresenter.attachScreen(mainScreen)
    }
}