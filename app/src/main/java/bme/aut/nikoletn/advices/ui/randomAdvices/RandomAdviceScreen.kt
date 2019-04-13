package bme.aut.nikoletn.advices.ui.randomAdvices

import bme.aut.nikoletn.advices.model.Advice

interface RandomAdviceScreen {
    fun showAdvice(advices: List<Advice>?)
    fun showNetworkError(errorMsg: String)
}