package pe.bcp.digital.card.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.bcp.digital.card.data.repository.UserRepository
import pe.bcp.digital.card.util.Event

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _navigateToHome = MutableLiveData<Event<String>>()
    val navigateToHome : LiveData<Event<String>>
        get() = _navigateToHome
    

    fun test(){
        _navigateToHome.value = Event("nav")
    }

}

