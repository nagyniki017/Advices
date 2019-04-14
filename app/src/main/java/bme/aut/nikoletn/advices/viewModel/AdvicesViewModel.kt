package bme.aut.nikoletn.advices.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import bme.aut.nikoletn.advices.AppDatabase
import bme.aut.nikoletn.advices.model.Advice
import bme.aut.nikoletn.advices.repository.AdviceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AdvicesViewModel(application: Application): AndroidViewModel(application) {
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val randomAdviceLiveData: MutableLiveData<List<Advice>> by lazy {
        MutableLiveData<List<Advice>>()
    }
    private val savedAdviceLiveData: LiveData<List<Advice>>
    private val repository: AdviceRepository

    init {
        val adviceDao = AppDatabase.getInstance(application).adviceDao()
        repository = AdviceRepository(adviceDao)
        savedAdviceLiveData = repository.savedAdvices
    }

    fun getRandomAdvices(): LiveData<List<Advice>> {
        return randomAdviceLiveData
    }

    fun getSavedAdvices() : LiveData<List<Advice>> {
        return savedAdviceLiveData
    }

    fun addUniqueRandomAdvice(advice: Advice) {
        Log.d("ViewModel", savedAdviceLiveData.value?.toString() ?: "missing")
        val advices: ArrayList<Advice> = ArrayList(randomAdviceLiveData.value ?: listOf())
        val adviceIdx = advices.indexOfFirst { random -> random.id == advice.id } ?: -1
        if (adviceIdx == -1) {
            setStoredRating(advice)
            advices.add(advice)
            randomAdviceLiveData.value = advices
        }
    }

    fun randomAdviceRatingChanged(position: Int) {
        val changedAdvice = randomAdviceLiveData.value?.get(position)
        if (changedAdvice?.rating!! > 0) {
            insertAdvice(changedAdvice)
        } else {
            deleteAdvice(changedAdvice)
        }
    }

    fun insertAdvice(advice: Advice) = scope.launch(Dispatchers.IO) {
        repository.insert(advice)
    }

    fun deleteAdvice(advice: Advice) = scope.launch(Dispatchers.IO) {
        repository.delete(advice)
    }

    private fun setStoredRating(advice: Advice) {
        val storedMatching = savedAdviceLiveData.value?.filter { storedAdvice -> storedAdvice.id == advice.id } ?: listOf()
        if (storedMatching.isNotEmpty()) {
            advice.rating = storedMatching.get(0).rating
        }
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}