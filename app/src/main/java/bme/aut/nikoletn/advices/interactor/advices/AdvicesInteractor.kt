package bme.aut.nikoletn.advices.interactor.advices

import android.util.Log
import bme.aut.nikoletn.advices.interactor.advices.events.GetRandomAdviceEvent
import bme.aut.nikoletn.advices.network.AdvicesApi
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class AdvicesInteractor @Inject constructor(private var advicesApi: AdvicesApi) {
    fun getRandomAdvice() {
        val event = GetRandomAdviceEvent();
        try {
            val adviceQueryCall = advicesApi.getAdvice()
            val response = adviceQueryCall.execute()

            Log.d("Response", response.body().toString())
            if (response.code() != 200) {
                throw Exception("Result code is not 200")
            }

            event.code = response.code()
            event.advice = response.body()?.slip
            EventBus.getDefault().post(event)
        } catch (e: Exception) {
            event.throwable = e
            EventBus.getDefault().post(event)
        }
    }
}