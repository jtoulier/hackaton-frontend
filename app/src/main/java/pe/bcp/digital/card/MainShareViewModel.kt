package pe.bcp.digital.card

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.bcp.digital.card.util.Event

class MainShareViewModel: ViewModel() {

    private val _notifyCard = MutableLiveData<Event<Unit>>()
    val notifyCard : LiveData<Event<Unit>>
        get() = _notifyCard

    fun notifyCard(){
        _notifyCard.value = Event(Unit)
    }
}