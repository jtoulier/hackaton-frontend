package pe.bcp.digital.card.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.bcp.digital.card.data.repository.CardRepository
import pe.bcp.digital.card.util.Result

class HomeViewModel(private val cardRepository: CardRepository) : ViewModel() {

    init {
        getSummary()
    }

    fun getSummary() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                cardRepository.getSummary()
            }

            when(result){
                is Result.Success -> { }
                is Result.Error -> { }
            }

        }
    }

}