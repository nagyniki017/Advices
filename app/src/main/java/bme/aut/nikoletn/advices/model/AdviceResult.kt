package bme.aut.nikoletn.advices.model

import com.google.gson.annotations.SerializedName

class AdviceResult {
    @SerializedName("slip")
    var slip: Advice? = null
}