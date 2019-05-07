package bme.aut.nikoletn.advices.ui.savedAdvices

import bme.aut.nikoletn.advices.model.Advice

interface SavedAdviceScreen {
    fun addNewAdvice(advice: Advice?)
}