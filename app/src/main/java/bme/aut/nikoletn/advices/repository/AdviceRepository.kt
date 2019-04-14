package bme.aut.nikoletn.advices.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import bme.aut.nikoletn.advices.dao.AdviceDAO
import bme.aut.nikoletn.advices.model.Advice

class AdviceRepository(private val adviceDao: AdviceDAO) {
    val savedAdvices: LiveData<List<Advice>> = adviceDao.getAdvices()

    @WorkerThread
    suspend fun insert(advice: Advice) {
        adviceDao.insert(advice)
    }

    @WorkerThread
    suspend fun delete(advice: Advice) {
        adviceDao.delete(advice)
    }
}