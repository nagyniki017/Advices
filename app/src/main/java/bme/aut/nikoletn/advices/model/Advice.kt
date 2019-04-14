package bme.aut.nikoletn.advices.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "advice")
class Advice {
    @PrimaryKey()
    @SerializedName("slip_id")
    var id: Int? = null

    @ColumnInfo(name = "advice")
    @SerializedName("advice")
    var advice: String? = null

    @ColumnInfo(name = "rating")
    var rating: Float? = null
}