package bme.aut.nikoletn.advices.ui.main

import bme.aut.nikoletn.advices.model.Advice

interface MainScreen {
    fun showAdvices(advices: List<Advice>?)
}