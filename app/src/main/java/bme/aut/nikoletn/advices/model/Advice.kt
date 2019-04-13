package bme.aut.nikoletn.advices.model

import com.google.gson.annotations.SerializedName

class Advice {
    @SerializedName("advice")
    var advice: String? = null

    @SerializedName("slip_id")
    var id: Int? = null

    var rating: Float? = null
}