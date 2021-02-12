package pe.bcp.digital.card.feature.addcard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.bcp.digital.card.data.repository.CardRepository
import pe.bcp.digital.card.util.Event
import pe.bcp.digital.card.util.Result

class AddCardViewModel(private var cardRepository: CardRepository) : ViewModel() {

    private val _navigateToHome = MutableLiveData<Event<Unit>>()
    val navigateToHome : LiveData<Event<Unit>>
        get() = _navigateToHome

    private val _progress = MutableLiveData<Event<Boolean>>()
    val progress : LiveData<Event<Boolean>>
        get() = _progress


    fun registerCard(amount: Int, expirationDate: String){
        viewModelScope.launch {
            _progress.value = Event(true)
            val result = withContext(Dispatchers.IO){
                cardRepository.addCard(amount, expirationDate)
            }
            _progress.value = Event(false)
            when(result){
                is Result.Success -> {
                    _navigateToHome.value = Event(Unit)
                }
                is Result.Error -> { }
            }

        }
    }
}