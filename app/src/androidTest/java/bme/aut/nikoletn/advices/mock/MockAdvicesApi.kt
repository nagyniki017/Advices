package bme.aut.nikoletn.advices.mock

import bme.aut.nikoletn.advices.model.Advice
import bme.aut.nikoletn.advices.model.AdviceResult
import bme.aut.nikoletn.advices.network.AdvicesApi
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MockAdvicesApi: AdvicesApi {
    override fun getAdvice(): Call<AdviceResult> {
        val adviceResult = AdviceResult()
        val advice = Advice()
        advice.rating = 2.5f
        advice.advice = "Ever tried. Ever failed. No matter. Try again. Fail again. Fail better."
        adviceResult.slip = advice

        val call = object : Call<AdviceResult> {
            @Throws(IOException::class)
            override fun execute(): Response<AdviceResult> {
                return Response.success(adviceResult)
            }

            override fun enqueue(callback: Callback<AdviceResult>) {
            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun cancel() {
            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun clone(): Call<AdviceResult> {
                return this
            }

            override fun request(): Request? {
                return null
            }
        }

        return call
    }
}