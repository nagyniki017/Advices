package bme.aut.nikoletn.advices.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bme.aut.nikoletn.advices.model.Advice

class AdvicesViewModel: ViewModel() {
    private val randomAdviceLiveData: MutableLiveData<List<Advice>> by lazy {
        MutableLiveData<List<Advice>>()
    }

    fun getRandomAdvices(): LiveData<List<Advice>> {
        return randomAdviceLiveData
    }

    fun addUniqueRandomAdvice(advice: Advice) {
        val advices: ArrayList<Advice> = ArrayList(randomAdviceLiveData.value ?: listOf())
        val adviceIdx = advices.indexOfFirst { random -> random.id == advice.id } ?: -1
        if (adviceIdx == -1) {
            advices.add(advice)
            randomAdviceLiveData.value = advices
        }
    }
}