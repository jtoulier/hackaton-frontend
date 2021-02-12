package pe.bcp.digital.card.feature.addcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.bcp.digital.card.data.repository.CardRepository
import pe.bcp.digital.card.util.Result

class AddCardViewModel(private var cardRepository: CardRepository) : ViewModel() {

    fun registerCard(amount: Int, expirationDate: String){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                cardRepository.addCard(amount, expirationDate)
            }
            when(result){
                is Result.Success -> { }
                is Result.Error -> { }
            }

        }
    }
}