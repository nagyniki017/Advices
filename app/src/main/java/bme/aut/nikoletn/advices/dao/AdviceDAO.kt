package bme.aut.nikoletn.advices.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import bme.aut.nikoletn.advices.model.Advice

@Dao
interface AdviceDAO {
    @Query("""SELECT * FROM advice""")
    fun getAdvices(): LiveData<List<Advice>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(advice: Advice)

    @Delete
    fun delete(advice: Advice)
}