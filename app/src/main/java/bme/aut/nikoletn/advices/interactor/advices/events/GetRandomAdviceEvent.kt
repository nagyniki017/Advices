package bme.aut.nikoletn.advices.interactor.advices.events

import bme.aut.nikoletn.advices.model.Advice

data class GetRandomAdviceEvent(
    var code: Int = 0,
    var advice: Advice? = null,
    var throwable: Throwable? = null
)