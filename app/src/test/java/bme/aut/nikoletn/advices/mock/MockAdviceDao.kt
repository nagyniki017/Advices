package bme.aut.nikoletn.advices.mock

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import bme.aut.nikoletn.advices.dao.AdviceDAO
import bme.aut.nikoletn.advices.model.Advice

@Dao
abstract class MockAdviceDao: AdviceDAO {
    override fun getAdvices(): LiveData<List<Advice>> {
        val advice = Advice()
        advice.advice = "Ever tried. Ever failed. No matter. Try again. Fail again. Fail better."
        advice.id = 5
        advice.rating = 4.5f
        val advicesLiveData = MutableLiveData<List<Advice>>()
        advicesLiveData.value = listOf<Advice>(advice, advice)
        return advicesLiveData
    }

    override fun insert(advice: Advice) {
        Log.d("MockAdviceDao", "Advice inserted")
    }

    override fun delete(advice: Advice) {
        Log.d("MockAdviceDao", "Advice deleted")
    }
}