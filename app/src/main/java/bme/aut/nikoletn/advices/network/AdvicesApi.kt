package bme.aut.nikoletn.advices.network

import bme.aut.nikoletn.advices.model.AdviceResult
import retrofit2.Call
import retrofit2.http.GET

interface AdvicesApi {
    @GET("advice")
    fun getAdvice(): Call<AdviceResult>
}