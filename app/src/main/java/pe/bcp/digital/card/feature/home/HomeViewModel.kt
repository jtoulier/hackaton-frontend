package pe.bcp.digital.card.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.bcp.digital.card.data.model.Summary
import pe.bcp.digital.card.data.repository.CardRepository
import pe.bcp.digital.card.util.Result

class HomeViewModel(private val cardRepository: CardRepository) : ViewModel() {

    private val _summary = MutableLiveData<Summary>()
    val summary : LiveData<Summary>
        get() = _summary

    private val _progress = MutableLiveData<Boolean>()
    val progress : LiveData<Boolean>
        get() = _progress

    init {
        getSummary()
    }

    fun getSummary() {
        viewModelScope.launch {
            _progress.value = true
            val result = withContext(Dispatchers.IO){
                cardRepository.getSummary()
            }
            _progress.value = false
            when(result){
                is Result.Success -> { _summary.value = result.data}
                is Result.Error -> { }
            }


        }
    }

}